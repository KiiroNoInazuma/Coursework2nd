package exceptions;

public class TaskNotFoundException extends IllegalArgumentException {
    TaskNotFoundException(String text){
        super(text);
    }

}
