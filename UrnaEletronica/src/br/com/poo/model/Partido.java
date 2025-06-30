/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.poo.model;

/**
 *
 * @author 232.976393
 */
import java.util.ArrayList;
  import java.util.List;

public class Partido {
  
    private String nome;
    private String sigla;
    private List<Candidato> candidatos;

    public Partido(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
        this.candidatos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public List<Candidato> getCandidatos() {
        return candidatos;
    }

    public void adicionarCandidato(Candidato candidato) {
        candidatos.add(candidato);
    }
}


    
    
}
