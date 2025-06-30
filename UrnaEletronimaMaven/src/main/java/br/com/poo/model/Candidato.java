/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.poo.model;

/**
 *
 * @author 232.975909
 */
public class Candidato {
    private int numero;
    private String nome;
    private String partido;
    private String caminhoImagem;

    public Candidato(int numero, String nome, String partido, String caminhoImagem) {
        this.numero = numero;
        this.nome = nome;
        this.partido = partido;
        this.caminhoImagem = caminhoImagem;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public String getPartido() {
        return partido;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }
}

    

