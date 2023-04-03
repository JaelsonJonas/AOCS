package br.com.fiap.aocs.models;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "T_AOCS_TAREFA")
public class Tarefa {

    @Id
    @Column(name = "ID_TAREFA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome da tarefa deve possui entre 5 à 50 caracteres")
    @Size(max = 50)
    @Column(name = "NM_TAREFA", nullable = false, length = 50)
    private String titulo;

    @Size(max = 300)
    @Column(name = "DS_TAREFA", length = 300)
    private String descricao;

    @NotNull
    @FutureOrPresent(message = "Data deve ser futura")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DT_TAREFA", nullable = false)
    private LocalDate data;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm:ss")
    @Column(name = "HR_TAREFA", nullable = false)
    private LocalTime duracao;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    public Tarefa() {

    }

    public Tarefa(String descricao) {
        this.descricao = descricao;
    }

    public Tarefa(Long id, String titulo, String descricao, LocalDate data, LocalTime duracao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.duracao = duracao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getDuracao() {
        return duracao;
    }

    public void setDuracao(LocalTime duracao) {
        this.duracao = duracao;
    }

}