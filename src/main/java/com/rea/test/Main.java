
package com.rea.test;

import com.rea.test.command.*;
import com.rea.test.models.Command;
import com.rea.test.models.Robot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static com.rea.test.models.Command.*;
import static com.rea.test.models.Command.REPORT;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class Main {
    public static void main(String[] args) {
        System.out.println("Application started ......\n\n\n");

        Main main = new Main();
        if (main.isValidFilePath(args)) {
            main.process(args[0]);
        } else {
            System.out.println("Please provide valid filePath as commandLine argument");
        }
        System.out.println("\n\n\nApplication finished");
    }

    private boolean isValidFilePath(String[] args) {
        if (args != null && args.length >= 1 && !isBlank(args[0])) {
            File file = new File(args[0]);
            return file.exists();
        }
        return false;
    }

    private void process(String filePath) {
        try {
            FileReader reader = new FileReader(getCommandHandlersWithParameters(), getCommandHandlersWithoutParameters());
            reader.readFile(new Robot(), filePath);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public Map<Command,BaseParameterCommandHandler> getCommandHandlersWithParameters() {
        Map<Command, BaseParameterCommandHandler> commandHandlersWithParameters = new HashMap<>();
        commandHandlersWithParameters.put(PLACE, new PlaceCommandHandler());
        return commandHandlersWithParameters;
    }

    public Map<Command,BaseNoParameterCommandHandler> getCommandHandlersWithoutParameters() {
        Map<Command, BaseNoParameterCommandHandler> commandHandlersWithoutParameters = new HashMap<>();
        commandHandlersWithoutParameters.put(MOVE, new MoveCommandHandler());
        commandHandlersWithoutParameters.put(LEFT, new LeftCommandHandler());
        commandHandlersWithoutParameters.put(RIGHT, new RightCommandHandler());
        commandHandlersWithoutParameters.put(REPORT, new ReportCommandHandler());
        return commandHandlersWithoutParameters;
    }
}
