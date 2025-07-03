package br.com.poo.Controller;

import br.com.poo.model.*;
import br.com.poo.Persistence.BancoDeDadosMongo;
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

    public String confirmarVoto() {
        System.out.println("Número digitado: " + numeroDigitado);
        if (numeroDigitado.equals("99999")) {
            return "FINALIZAR";
        }

        Candidato candidato = bancoDeDados.buscarCandidatoPorNumero(numeroDigitado);
        if (candidato != null) {
            bancoDeDados.registrarVoto(new Voto(numeroDigitado, TipoVoto.VALIDO));
        } else {
            bancoDeDados.registrarVoto(new Voto(numeroDigitado, TipoVoto.NULO));
        }

        limparNumero();
        return "OK";
    }

    public BancoDeDadosMongo getBanco() {
        return bancoDeDados;
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
