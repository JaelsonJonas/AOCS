package br.com.fiap.aocs.models;

import java.util.List;

import br.com.fiap.aocs.DTO.ValidaUsuarioDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_AOCS_USUARIO")
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    @Column(name = "ID_USUARIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Login ou senha invalidos")
    @Size(max = 50)
    @Email
    @Column(name = "DS_LOGIN", nullable = false, length = 50)
    private String login;

    @NotBlank(message = "Login ou senha invalidos")
    @Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres")
    @Column(name = "DS_SENHA", nullable = false, length = 100)
    private String senha;

    @OneToMany
    private List<Tarefa> tarefas;

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario(String login) {
        this.login = login;
    }

    public Usuario(@Valid ValidaUsuarioDTO validaUsuarioDTO) {
        if (validaUsuarioDTO.login() != null && !validaUsuarioDTO.login().isEmpty()){
            this.login = validaUsuarioDTO.login().toLowerCase();
        }
        if (validaUsuarioDTO.senha() != null && !validaUsuarioDTO.senha().isEmpty()){
            this.senha = validaUsuarioDTO.senha();
        }
    }


}
