
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.poo.Persistence;

/**
 *
 * @author 232.975909
 */

    

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import br.com.poo.model.Voto;
import br.com.poo.model.TipoVoto;

public class BancoDeDadosMongo {

    private MongoDatabase database;

    public BancoDeDadosMongo() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("urna_eletronica");
    }

    public void salvarVoto(Voto voto) {
        MongoCollection<Document> votosCollection = database.getCollection("votos");

        Document doc = new Document("numeroDigitado", voto.getNumeroDigitado())
                .append("tipo", voto.getTipo().toString());

        votosCollection.insertOne(doc);
    }
}



