package br.com.fiap.aocs.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Tarefa {

    private String titulo;
    private String descricao;
    private LocalDate data;
    private LocalTime duracao;

    public Tarefa(){

    }

    public Tarefa(String titulo, String descricao, LocalDate data, LocalTime duracao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.duracao = duracao;
    }

    public String getNome() {
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