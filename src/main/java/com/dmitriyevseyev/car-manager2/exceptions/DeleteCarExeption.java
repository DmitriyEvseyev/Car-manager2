package exceptions;

public class DeleteCarExeption extends Exception{

    public DeleteCarExeption() {
        super();
    }

    public DeleteCarExeption(String message) {
        super(message);
    }
}