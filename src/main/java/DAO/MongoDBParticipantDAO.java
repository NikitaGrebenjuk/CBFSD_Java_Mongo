package DAO;

import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class MongoDBParticipantDAO {

    private static MongoClient mongoClient = null;
    private static MongoDatabase mongoDB =null;
    private static final String url = "mongodb+srv://admin:Admin123!@nikesmongocluster.8zv5pfi.mongodb.net/?retryWrites=true&w=majority";
    private static String dbName = "";
    private static String tableName ="";
    public MongoDBParticipantDAO(String dbName, String tableName) {
        this.dbName = dbName;
        this.tableName = tableName;
    }

    private MongoDatabase getDatabase(){
        if(mongoClient == null) {
            try {
                mongoClient = MongoClients.create(url);
                System.out.println("DONE: mongoClient");

            } catch (MongoException mE) {
                System.out.print("Exception: " + mE);
            }
        }
        if(mongoDB == null) {
            try {
                mongoDB = mongoClient.getDatabase(dbName);
                System.out.println("DONE: mongoDatabase");

            } catch (MongoException mdbE) {
                System.out.print("Exception: " + mdbE);
            }
        }
        return mongoDB;
    }

    public List<Document> getAllParticipants(){
        MongoDatabase mongoDB = getDatabase();
        MongoCollection collecton = null;
        List<Document> listOfParticipants = new ArrayList<>();
        FindIterable<Document> response = mongoDB.getCollection(tableName).find();
        System.out.println("DONE: get all querry");

        for(Document doc: response){
            listOfParticipants.add(doc);
        }
        return listOfParticipants;
    }

    public Document getParticipantByName(String name){
        MongoDatabase monogDB = getDatabase();
        Document participant = null;
        try {
            participant = getDatabase().getCollection(tableName).find(Filters.eq("name",name)).first();
            System.out.println("DONE: get participant by ID");

        }catch (MongoException mE){
            System.out.print("Exception: " + mE);
            participant.append("Exception",mE);
        }
        return participant;
    }

    public Document addParticipant(Document participant){
        System.out.println("entered: add participant");
        return new Document("_id", new ObjectId()).append("ResultObject", getDatabase().getCollection(tableName).insertOne(participant));
    }
}
