package med.voll.api.dto;

import lombok.Getter;
import lombok.Setter;
import med.voll.api.medico.Especialidade;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MedicoDTO {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String telefone;

    @NotBlank
    @Size(min = 4, max = 6)
    private String crm;

    @NotNull
    private Especialidade especialidade;

    @NotNull
    @Valid
    private EnderecoDTO endereco;

    private boolean ativo;
}
