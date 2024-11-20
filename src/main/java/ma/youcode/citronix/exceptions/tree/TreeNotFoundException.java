package ma.youcode.citronix.exceptions.tree;

public class TreeNotFoundException extends RuntimeException {
    public TreeNotFoundException(String message) {
        super(message);
    }
  public TreeNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
