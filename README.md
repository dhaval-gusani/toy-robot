For this application to work, we would require JAVA 1.8 or above
To Build we would require to have Maven 3.2.x installed and an internet connection


In order to build please execute
'mvn clean install'

In order to run the application after a successful build, execute following command from target directory
'java -jar toy-robot-1.0-SNAPSHOT-jar-with-dependencies.jar <path to source file>'


Code Structure
User is expected to provide file containing commands as commandline argument
All commands are case sensitive and accepted only in UPPER CASE
All exceptions are handled and the error message is printed to console
Code is divided into 3 main modules

 - models: contains all the models
 - command: contains all the CommandHandlers (handlers to execute each commands)
    Each command handler can handle its own command, all validations regarding that command,
    all exception handling and error/result printing for that command
 - main: contains configuration constants, file reader & Main class
    Contains a constant file that contains constants like max board size
    Contains a file reader that would read the file and invoke command handler according to the commands in file
    Contains a Main file, which is the executable file for this project