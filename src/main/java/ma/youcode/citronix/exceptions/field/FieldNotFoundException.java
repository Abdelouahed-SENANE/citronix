package ma.youcode.citronix.exceptions.field;

public class FieldNotFoundException extends RuntimeException {
    public FieldNotFoundException(String message) {
        super(message);
    }
  public FieldNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
