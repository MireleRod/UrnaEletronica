/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.poo.model;

/**
 *
 * @author 232.984669
 */
public class Candidato {
    
    private int numero;
    private String nome;
    private String partido;
    private String foto;
    private int votos;
    
    
    public Candidato(int numero, String nome, String partido, String foto, int votos){
        this.numero=numero;
        this.nome=nome;
        this.partido=partido;
        this.foto=foto;
        this.votos=votos;
    }
    
    public int getNumero(){
        return numero;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getPartido(){
        return partido;
    }
    
    public String getFoto(){
        return foto;
    }
    
    public int getVotos(){
        return votos;
    }
    
     public void votos(){
        votos++;
    } 
     
}