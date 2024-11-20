package ma.tr.citronix.exception;

public class NotCompleteProcess extends RuntimeException {
    public NotCompleteProcess(String message) {
        super(message);
    }
}
