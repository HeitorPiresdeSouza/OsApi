package br.eti.hpds.OSApiApplication.domain.dto;

import br.eti.hpds.OSApiApplication.domain.model.StatusOrdemServico;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record AtualizaStatusDTO(

        @Schema(name = "Status", example = "ABERTA", required = false)
        @NotNull (message = "Status é obrigatório")
        StatusOrdemServico status
) {}
