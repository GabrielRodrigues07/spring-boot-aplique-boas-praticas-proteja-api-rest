package med.voll.api.domain.usuario;


import lombok.*;

import javax.persistence.*;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;
}
