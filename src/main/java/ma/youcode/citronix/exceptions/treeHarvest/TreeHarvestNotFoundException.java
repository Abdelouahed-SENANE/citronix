package ma.youcode.citronix.exceptions.treeHarvest;

public class TreeHarvestNotFoundException extends RuntimeException {
    public TreeHarvestNotFoundException(String message) {
        super(message);
    }
  public TreeHarvestNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
