package med.voll.api.domain.endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.dto.EnderecoDTO;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public void atualizarInformacoes(EnderecoDTO dados) {
        if (dados.getLogradouro() != null) {
            this.logradouro = dados.getLogradouro();
        }
        if (dados.getBairro() != null) {
            this.bairro = dados.getBairro();
        }
        if (dados.getCep() != null) {
            this.cep = getCep();
        }
        if (dados.getUf() != null) {
            this.uf = getUf();
        }
        if (dados.getCidade() != null) {
            this.cidade = dados.getCidade();
        }
        if (dados.getNumero() != null) {
            this.numero = dados.getNumero();
        }
        if (dados.getComplemento() != null) {
            this.complemento = dados.getComplemento();
        }
    }
}
