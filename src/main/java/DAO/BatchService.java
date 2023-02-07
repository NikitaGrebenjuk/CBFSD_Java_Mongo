package DAO;

import org.bson.Document;

import java.util.List;

public class BatchService {

    private static final String dbName = "devDB";
    private static final String tableName = "batches";
    private final MongoDBBatchDAO batchDAO;
    public BatchService(){
        super();
        this.batchDAO = new MongoDBBatchDAO(dbName,tableName);
    }

    public List<Document> getAllBatches(){
        return this.batchDAO.getAllBatches();
    }

    public Document addBatch(Document batch){
        return this.batchDAO.addBatch(batch);
    }
}
