package ma.youcode.citronix.dto.request.treeHarvest;

import jakarta.validation.constraints.NotNull;

public record TreeHarvestUpdateDTO(
        @NotNull Double quantity,
        @NotNull Long harvestId,
        @NotNull Long treeId

) {
}
