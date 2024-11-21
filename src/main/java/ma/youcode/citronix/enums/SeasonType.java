package ma.youcode.citronix.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SeasonType {
    AUTUMN("Autumn"),
    SPRING("Spring"),
    SUMMER("Summer"),
    WINTER("Winter");

    private final String displayName;
}
