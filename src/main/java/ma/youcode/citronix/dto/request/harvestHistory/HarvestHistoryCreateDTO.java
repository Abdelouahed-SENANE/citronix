package ma.youcode.citronix.dto.request.harvestHistory;

import jakarta.validation.constraints.NotNull;

public record HarvestHistoryCreateDTO(
        @NotNull double quantity,
        @NotNull Long harvestId,
        @NotNull Long treeId

) {}
