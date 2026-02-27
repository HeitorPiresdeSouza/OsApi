package br.eti.hpds.OSApiApplication.domain.dto;

import br.eti.hpds.OSApiApplication.domain.model.StatusOrdemServico;
import jakarta.validation.constraints.NotNull;

public record AtualizaStatusDTO(

        @NotNull (message = "Status é obrigatório")
        StatusOrdemServico status
) {}
