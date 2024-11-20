package ma.youcode.citronix.exceptions.farm;

public class FarmNotFoundException extends RuntimeException {
    public FarmNotFoundException(String message) {
        super(message);
    }
    public FarmNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
