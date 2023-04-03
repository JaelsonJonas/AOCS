package br.com.fiap.aocs.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "T_AOCS_USUARIO")
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

    public Usuario() {

    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario(Long id, String login, String senha, List<Tarefa> tarefas) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.tarefas = tarefas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;

    }

}
