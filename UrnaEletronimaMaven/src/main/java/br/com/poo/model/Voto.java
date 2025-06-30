/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.poo.model;

/**
 *
 * @author 232.975909
 */
public class Voto {
    private int numeroDigitado;
    private TipoVoto tipo;

    public Voto(String numeroDigitado, TipoVoto tipo) {
        this.numeroDigitado = numeroDigitado;
        this.tipo = tipo;
    }

    public int getNumeroDigitado() {
        return numeroDigitado;
    }

    public TipoVoto getTipo() {
        return tipo;
    }
}
