
package com.rea.test;

import com.rea.test.command.BaseNoParameterCommandHandler;
import com.rea.test.command.BaseParameterCommandHandler;
import com.rea.test.models.Command;
import com.rea.test.models.Robot;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static com.rea.test.models.Command.PLACE;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public class FileReader {

    private Map<Command, BaseParameterCommandHandler> commandHandlersWithParameters;
    private Map<Command, BaseNoParameterCommandHandler> commandHandlersWithoutParameters;

    public FileReader(Map<Command, BaseParameterCommandHandler> commandHandlersWithParameters,
                      Map<Command, BaseNoParameterCommandHandler> commandHandlersWithoutParameters) {
        this.commandHandlersWithParameters = commandHandlersWithParameters;
        this.commandHandlersWithoutParameters = commandHandlersWithoutParameters;
    }

    public boolean readFile(Robot robot, String filePath) throws FileNotFoundException {

        File inputFile = new File(filePath);
        if (inputFile.exists()) {
            try {
                LineIterator iterator = FileUtils.lineIterator(inputFile, "UTF-8");

                while (iterator.hasNext()) {
                    String command = iterator.nextLine();

                    if (StringUtils.isBlank(command)) {
                        continue;
                    }
                    executeCommand(robot, command);
                }
            } catch (IOException e) {
                System.out.println("Error reading file, error: [" + e.getMessage() + "]");
            }
        } else {
            throw new FileNotFoundException(String.format("Source file does not exist at: [%s]", filePath));
        }

        return false;
    }

    private void executeCommand(Robot robot, String commandStr) {
        String[] commandArray = commandStr.split(" ");
        Optional<Command> command = getCommand(commandArray[0]);
        if (!command.isPresent()) {
            return;
        }
        switch (command.get()) {
            case PLACE:
                if (commandArray.length >= 2) {
                    commandHandlersWithParameters.get(PLACE).execute(robot, commandArray[1].split(","));
                } else {
                    System.out.println("Ignoring command PLACE, Command requires position as parameters");
                }
                break;
            case MOVE:
            case LEFT:
            case RIGHT:
            case REPORT:
                commandHandlersWithoutParameters.get(command.get()).execute(robot);
                break;
            default:
                System.out.println("Command " + command.get() + " not yet implemented");
        }
    }

    private Optional<Command> getCommand(String name) {
        try {
            return of(Command.valueOf(name));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return empty();
        }
    }

}
