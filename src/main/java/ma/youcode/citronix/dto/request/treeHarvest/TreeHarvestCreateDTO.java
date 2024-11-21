package ma.youcode.citronix.dto.request.treeHarvest;

import jakarta.validation.constraints.NotNull;

public record TreeHarvestCreateDTO(

        @NotNull Long harvestId,
        @NotNull Long treeId

) {}
