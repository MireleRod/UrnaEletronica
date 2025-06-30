package br.com.poo.controller;

import br.com.poo.model.*;
import br.com.poo.persistence.BancoDeDadosMongo;
import java.util.List;

public class ControladorUrna {

    private String numeroDigitado = "";
    private BancoDeDadosMongo bancoDeDados;

    public ControladorUrna() {
        bancoDeDados = new BancoDeDadosMongo();
    }

    public void adicionarDigito(String digito) {
        if (numeroDigitado.length() < 5) {
            numeroDigitado += digito;
        }
    }

    public void corrigir() {
        numeroDigitado = "";
    }

    public void votarBranco() {
        bancoDeDados.registrarVoto(new Voto("", TipoVoto.BRANCO));
        limparNumero(); // Limpa após votar em branco
    }

    public String confirmar() {
    if (numeroDigitado.equals("99999")) {
        return "FINALIZAR";
    }
    confirmarVoto(); // chama o método real que grava o voto
    return "OK";
}

    public void confirmarVoto() {
        if (numeroDigitado.equals("99999")) {
            // sinaliza que é para abrir o relatório, o tratamento ocorre na tela
            return;
        }

        Candidato candidato = bancoDeDados.buscarCandidatoPorNumero(numeroDigitado);
        if (candidato != null) {
            bancoDeDados.registrarVoto(new Voto(numeroDigitado, TipoVoto.VALIDO));
        } else {
            bancoDeDados.registrarVoto(new Voto(numeroDigitado, TipoVoto.NULO));
        }
        limparNumero(); // Limpa após confirmar o voto
    }

    public String getNumeroDigitado() {
        return numeroDigitado;
    }

    public Candidato buscarCandidato() {
        return bancoDeDados.buscarCandidatoPorNumero(numeroDigitado);
    }

    public List<Voto> getVotos() {
        return bancoDeDados.getTodosVotos();
    }

    public List<Candidato> getCandidatos() {
        return bancoDeDados.getTodosCandidatos();
    }

    public List<Partido> getPartidos() {
        return bancoDeDados.getTodosPartidos();
    }

    public void limparNumero() {
        this.numeroDigitado = "";
    }
}




