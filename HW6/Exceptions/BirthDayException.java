package HW6.Exceptions;

public class BirthDayException extends Exception {
    public BirthDayException (String message){
        super("Неверный формат даты рождения (" + message + ")");
    }
}
