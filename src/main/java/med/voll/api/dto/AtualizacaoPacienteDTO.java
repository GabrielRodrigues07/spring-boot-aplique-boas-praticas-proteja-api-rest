package med.voll.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AtualizacaoPacienteDTO {

    @NotNull
    private Long id;
    private String nome;
    private String telefone;
    private EnderecoDTO endereco;
}
