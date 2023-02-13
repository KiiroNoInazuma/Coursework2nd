package exceptions;

import java.io.IOException;

public class IncorrectArgumentException extends IOException {
    String argument;
    IncorrectArgumentException(String argument){
        super(argument);
        this.argument = argument;
    }
}
