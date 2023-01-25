package med.voll.api.domain.medico;

import lombok.Getter;
import lombok.Setter;
import med.voll.api.domain.endereco.Endereco;

@Getter
@Setter
public class DadosDetalhamentoMedico {

    private Long id;
    private String nome;
    private String email;
    private String crm;
    private Especialidade especialidade;
    private Endereco endereco;

    public DadosDetalhamentoMedico(Medico medico) {
        this.id = medico.getId();
        this.nome = medico.getNome();
        this.email = medico.getEmail();
        this.crm = medico.getCrm();
        this.especialidade = medico.getEspecialidade();
        this.endereco = medico.getEndereco();
    }
}
