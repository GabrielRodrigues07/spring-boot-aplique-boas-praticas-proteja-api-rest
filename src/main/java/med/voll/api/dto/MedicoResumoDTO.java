package med.voll.api.dto;

import lombok.Getter;
import lombok.Setter;
import med.voll.api.medico.Especialidade;

@Getter
@Setter
public class MedicoResumoDTO {

    private Long id;
    private String nome;
    private String email;
    private String crm;
    private Especialidade especialidade;
}
