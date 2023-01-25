package med.voll.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class EnderecoDTO {

    @NotBlank
    private String logradouro;

    @NotBlank
    private String bairro;

    @NotBlank
    @Pattern(regexp = "\\d{8}")
    private String cep;

    private String numero;
    private String complemento;

    @NotBlank
    private String cidade;

    @NotBlank
    private String uf;
}
