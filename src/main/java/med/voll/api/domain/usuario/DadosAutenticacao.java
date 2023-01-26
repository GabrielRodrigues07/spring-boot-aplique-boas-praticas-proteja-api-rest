package med.voll.api.domain.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class DadosAutenticacao {

    private String login;
    private String senha;
}
