package br.com.pocdatamongodb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CharacterRootDTO {

    @NotNull(message = "Name obrigatorio")
    private String name;

    @NotNull(message = "Points obrigatorio")
    private Long points;
}
