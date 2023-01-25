package med.voll.api.domain.paciente;

import lombok.*;
import med.voll.api.dto.AtualizacaoPacienteDTO;
import med.voll.api.domain.endereco.Endereco;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Paciente")
@Table(name = "pacientes")
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    @Embedded
    private Endereco endereco;
    private boolean ativo;

    public void atualizarInformacoes(AtualizacaoPacienteDTO dados) {

        if (dados.getNome() != null) {
            this.nome = dados.getNome();
        }
        if (dados.getTelefone() != null) {
            this.telefone = dados.getTelefone();
        }
        if (dados.getEndereco() != null) {
            this.endereco.atualizarInformacoes(dados.getEndereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
