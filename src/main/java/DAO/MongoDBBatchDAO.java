package DAO;

import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class MongoDBBatchDAO {

    private static MongoClient mongoClient = null;
    private static MongoDatabase mongoDB =null;
    private static final String url = "mongodb+srv://admin:Admin123!@nikesmongocluster.8zv5pfi.mongodb.net/?retryWrites=true&w=majority";
    private static String dbName = "";
    private static String tableName ="";
    public MongoDBBatchDAO(String dbName, String tableName) {
        this.dbName = dbName;
        this.tableName = tableName;
    }

    private MongoDatabase getDatabase(){
        if(mongoClient == null) {
            try {
                mongoClient = MongoClients.create(url);
            } catch (MongoException mE) {
                System.out.print("Exception: " + mE);
            }
        }
        if(mongoDB == null) {
            try {
                mongoDB = mongoClient.getDatabase(dbName);

            } catch (MongoException mdbE) {
                System.out.print("Exception: " + mdbE);
            }
        }
        return mongoDB;
    }

    public List<Document> getAllBatches(){
        MongoDatabase mongoDB = getDatabase();
        MongoCollection collecton = null;
        List<Document> listOfBatches = new ArrayList<>();
        FindIterable<Document> response = mongoDB.getCollection(tableName).find();

        for(Document doc: response){
            listOfBatches.add(doc);
        }
        return listOfBatches;
    }

    public Document getBatchByName(String name){
        MongoDatabase monogDB = getDatabase();
        Document batch = null;
        try {
            batch = getDatabase().getCollection(tableName).find(Filters.eq("name",name)).first();

        }catch (MongoException mE){
            System.out.print("Exception: " + mE);
            batch.append("Exception",mE);
        }
        return batch;
    }

    public Document addBatch(Document batch){
        return new Document("_id", new ObjectId()).append("ResultObject", getDatabase().getCollection(tableName).insertOne(batch));
    }
}
