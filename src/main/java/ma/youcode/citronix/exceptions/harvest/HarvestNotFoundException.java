package ma.youcode.citronix.exceptions.harvest;

public class HarvestNotFoundException extends RuntimeException {
    public HarvestNotFoundException(String message) {
        super(message);
    }
  public HarvestNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

}
