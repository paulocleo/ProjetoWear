package com.example.pc.soccerwear;

/**
 * Created by PC on 23/10/2017.
 */

public class Time {

    private int id;
    private String nome;
    private String abreviacao;
    private String caminhoEscudo;

    public Time(int id, String nome, String abreviacao, String caminhoEscudo) {
        this.id = id;
        this.nome = nome;
        this.abreviacao = abreviacao;
        this.caminhoEscudo = caminhoEscudo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public String getCaminhoEscudo() {
        return caminhoEscudo;
    }

    public void setCaminhoEscudo(String caminhoEscudo) {
        this.caminhoEscudo = caminhoEscudo;
    }
}
