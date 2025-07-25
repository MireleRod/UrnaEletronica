/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author 232.975909
 */

package br.com.poo.Persistence;

import br.com.poo.model.*;
import com.mongodb.client.*;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class BancoDeDadosMongo {

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> votosCollection;
    private final MongoCollection<Document> candidatosCollection;
    private final MongoCollection<Document> partidosCollection;

    public BancoDeDadosMongo() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("urna");
        votosCollection = database.getCollection("votos");
        candidatosCollection = database.getCollection("candidatos");
        partidosCollection = database.getCollection("partidos");
    }

    public void registrarVoto(Voto voto) {
            Document doc = new Document("numeroDigitado",
                    voto.getNumeroDigitado() == null ? null : voto.getNumeroDigitado().toString())
                    .append("tipo", voto.getTipo().toString());
            votosCollection.insertOne(doc);

    }

    public Candidato buscarCandidatoPorNumero(String numero) {
        Document filtro = new Document("numero", Integer.parseInt(numero));
        Document doc = candidatosCollection.find(filtro).first();

        if (doc != null) {
            String nome = doc.getString("nome");
            String siglaPartido = doc.getString("siglaPartido");
            String caminhoImagem = doc.getString("caminhoImagem");

            Partido partido = buscarPartidoPorSigla(siglaPartido);
            int numeroInt = Integer.parseInt(numero);
            return new Candidato(numeroInt, nome, partido, caminhoImagem);
        }
        return null;
    }

    public Partido buscarPartidoPorSigla(String sigla) {
        Document doc = partidosCollection.find(new Document("sigla", sigla)).first();
        if (doc != null) {
            String nome = doc.getString("nome");
            String siglaPartido = doc.getString("sigla");
            return new Partido(nome, siglaPartido);
        }
        return null;
    }

    public List<Candidato> getTodosCandidatos() {
        List<Candidato> candidatos = new ArrayList<>();
        for (Document doc : candidatosCollection.find()) {
            int numero = doc.getInteger("numero");
            String nome = doc.getString("nome");
            String siglaPartido = doc.getString("siglaPartido");
            String caminhoImagem = doc.getString("caminhoImagem");

            Partido partido = buscarPartidoPorSigla(siglaPartido);
            if (partido != null) {
                candidatos.add(new Candidato(numero, nome, partido, caminhoImagem));
            }
        }
        return candidatos;
    }

    public List<Voto> getTodosVotos() {
        List<Voto> votos = new ArrayList<>();
        for (Document doc : votosCollection.find()) {
            Object numeroObj = doc.get("numeroDigitado");  // pega como Object
            String numeroDigitado = null;
            if (numeroObj != null) {
                if (numeroObj instanceof Integer) {
                    numeroDigitado = Integer.toString((Integer) numeroObj);
                } else if (numeroObj instanceof String) {
                    numeroDigitado = (String) numeroObj;
                }
            }
            TipoVoto tipo = TipoVoto.valueOf(doc.getString("tipo"));
            votos.add(new Voto(numeroDigitado, tipo));
        }
        return votos;
    }


    public List<Partido> getTodosPartidos() {
        List<Partido> partidos = new ArrayList<>();
        for (Document doc : partidosCollection.find()) {
            String nome = doc.getString("nome");
            String sigla = doc.getString("sigla");
            partidos.add(new Partido(nome, sigla));
        }
        return partidos;
    }

    public void limparDados() {
        votosCollection.deleteMany(new Document());
        candidatosCollection.deleteMany(new Document());
        partidosCollection.deleteMany(new Document());
    }
}
