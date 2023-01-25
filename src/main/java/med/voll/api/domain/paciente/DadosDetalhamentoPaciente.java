package med.voll.api.domain.paciente;

import lombok.Getter;
import lombok.Setter;
import med.voll.api.domain.endereco.Endereco;

@Getter @Setter
public class DadosDetalhamentoPaciente {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private Endereco endereco;

    public DadosDetalhamentoPaciente(Paciente paciente) {

        this.id = paciente.getId();
        this.nome = paciente.getNome();
        this.email = paciente.getEmail();
        this.cpf = paciente.getCpf();
        this.telefone = paciente.getTelefone();
        this.endereco = paciente.getEndereco();
    }
}
