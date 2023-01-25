package med.voll.api.domain.medico;

import lombok.*;
import med.voll.api.dto.AtualizacaoMedicoDTO;
import med.voll.api.domain.endereco.Endereco;

import javax.persistence.*;

@Table(name = "medicos")
@Entity(name = "Medico")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private boolean ativo;


    public void atualizarInformacoes(AtualizacaoMedicoDTO dados) {
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
