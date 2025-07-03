package br.com.poo.view;

import br.com.poo.model.*;
import br.com.poo.Persistence.BancoDeDadosMongo;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TelaRelatorio extends JFrame {

    private BancoDeDadosMongo banco;

    private JPanel painelPrincipal;
    private JLabel tituloLabel;
    private JTextArea relatorioArea;
    private JScrollPane scrollPane;

    public TelaRelatorio(BancoDeDadosMongo banco) {
        this.banco = banco;
        initComponents();
        mostrarRelatorioFinal();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatório de Votos");

        painelPrincipal = new JPanel();
        painelPrincipal.setBackground(new Color(51, 51, 51));
        painelPrincipal.setLayout(new BorderLayout(10,10));

        tituloLabel = new JLabel("RELATÓRIO", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(Color.WHITE);

        relatorioArea = new JTextArea();
        relatorioArea.setEditable(false);
        relatorioArea.setBackground(new Color(51, 51, 51));
        relatorioArea.setForeground(Color.WHITE);
        relatorioArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        relatorioArea.setLineWrap(true);
        relatorioArea.setWrapStyleWord(true);
        relatorioArea.setBorder(null);

        scrollPane = new JScrollPane(relatorioArea);
        scrollPane.setBorder(null);
        scrollPane.setBackground(new Color(51, 51, 51));

        painelPrincipal.add(tituloLabel, BorderLayout.NORTH);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(painelPrincipal);

        setSize(520, 400);
        setLocationRelativeTo(null);
    }

    public void mostrarRelatorioFinal() {
        List<Candidato> candidatos = banco.getTodosCandidatos();
        List<Voto> votos = banco.getTodosVotos();

        long totalVotos = votos.size();
        long brancos = votos.stream().filter(v -> v.getTipo() == TipoVoto.BRANCO).count();
        long nulos = votos.stream().filter(v -> v.getTipo() == TipoVoto.NULO).count();
        long validos = votos.stream().filter(v -> v.getTipo() == TipoVoto.VALIDO).count();

        Map<String, Long> votosPorNumero = votos.stream()
                .filter(v -> v.getTipo() == TipoVoto.VALIDO)
                .collect(Collectors.groupingBy(Voto::getNumeroDigitado, Collectors.counting()));

        StringBuilder sb = new StringBuilder();

        for (Candidato c : candidatos) {
            long totalCandidato = votosPorNumero.getOrDefault(String.valueOf(c.getNumero()), 0L);
            double percentual = validos == 0 ? 0 : (100.0 * totalCandidato / validos);
            sb.append(String.format("%s (%d): %.0f%% (%d votos)%n",
                    c.getNome(), c.getNumero(), percentual, totalCandidato));
        }

        double percBrancos = totalVotos == 0 ? 0 : (100.0 * brancos / totalVotos);
        double percNulos = totalVotos == 0 ? 0 : (100.0 * nulos / totalVotos);

        sb.append(String.format("%nVotos Brancos: %.0f%% (%d votos)%n", percBrancos, brancos));
        sb.append(String.format("Votos Nulos: %.0f%% (%d votos)%n", percNulos, nulos));

        relatorioArea.setText(sb.toString());
    }

}
