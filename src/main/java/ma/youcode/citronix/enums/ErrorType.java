package ma.youcode.citronix.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorType {

    FIELD_MISMATCH("Tree and harvest are not in the same field."),
    DUPLICATE_HARVEST_TREE("Tree already harvested during the %s season on %s."),
    DUPLICATE_HARVEST_FIELD("Field already harvested during the %s season on %s."),
    NON_PRODUCTIVE_TREE("Tree is not productive for harvesting."),
    NOT_FOUND("%s not found."),
    FIELD_SIZE_LIMIT_EXCEEDED("Field surface cannot be greater than  %s m²"),
    MAX_FIELD("Farm cannot have more than 10 fields"),
    NOT_ON_SAME_FIELD("This tree cannot be harvested because it is not in the same field."),
    TREE_NOT_HARVESTED("This tree cannot be harvested .");

    private final String message;

    public String getMessage(Object... arg) {
        return String.format(message, arg);
    }
}
