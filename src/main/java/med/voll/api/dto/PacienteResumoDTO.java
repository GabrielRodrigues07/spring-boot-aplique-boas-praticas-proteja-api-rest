package med.voll.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PacienteResumoDTO {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
}
