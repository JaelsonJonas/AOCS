package br.com.fiap.aocs.models;


public class Tarefa {

    private String nome;
    private String descricao;
    private String duracao;

      
    public Tarefa(String nome, String descricao, String duracao){
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
    }


    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }


    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public String getDuracao(){
        return duracao;
    }

    public void setDuracao(String duracao){
        this.duracao = duracao;
    }
}