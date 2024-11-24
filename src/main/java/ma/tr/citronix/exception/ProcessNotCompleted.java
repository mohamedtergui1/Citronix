package ma.tr.citronix.exception;

public class ProcessNotCompleted extends RuntimeException {
    public ProcessNotCompleted(String message) {
        super(message);
    }
}
