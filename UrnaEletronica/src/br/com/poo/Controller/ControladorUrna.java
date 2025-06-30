import java.util.List;
import model.BancoDeDadosFake;
import model.Candidato;
import model.TipoVoto;
import model.Voto;

public class ControladorUrna {

    private String numeroDigitado = "";
    private BancoDeDadosFake banco;

    public ControladorUrna(BancoDeDadosFake banco) {
        this.banco = banco;
    }

    // Chamada quando o usuário pressiona um número
    public void digitarNumero(String numero) {
        if (numeroDigitado.length() < 5) {
            numeroDigitado += numero;
        }
    }

    // Corrige a digitação
    public void corrigir() {
        numeroDigitado = "";
    }

    // Retorna o número atual digitado (para exibir na tela)
    public String getNumeroDigitado() {
        return numeroDigitado;
    }

    // Verifica se o número digitado é de um candidato conhecido
    public Candidato buscarCandidato() {
        try {
            int numero = Integer.parseInt(numeroDigitado);
            for (Candidato c : banco.getCandidatos()) {
                if (c.getNumero() == numero) {
                    return c;
                }
            }
        } catch (NumberFormatException e) {
            // nada
        }
        return null;
    }

    // Chamada quando o botão CONFIRMA é pressionado
    public String confirmar() {
        if (numeroDigitado.equals("")) {
            // Nenhum número digitado
            return "Nada digitado.";
        }

        if (numeroDigitado.equals("99999")) {
            // Comando especial para encerrar votação
            return "ENCERRAR";
        }

        Candidato candidato = buscarCandidato();

        if (candidato != null) {
            banco.registrarVoto(new Voto(candidato.getNumero(), TipoVoto.VALIDO));
            numeroDigitado = "";
            return "VOTO_VALIDO";
        } else {
            banco.registrarVoto(new Voto(-1, TipoVoto.NULO));
            numeroDigitado = "";
            return "VOTO_NULO";
        }
    }

    // Chamada quando o botão BRANCO é pressionado
    public String votarBranco() {
        banco.registrarVoto(new Voto(-1, TipoVoto.BRANCO));
        numeroDigitado = "";
        return "VOTO_BRANCO";
    }
}


} 
