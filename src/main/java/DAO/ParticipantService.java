package DAO;

import org.bson.Document;

import java.util.List;

public class ParticipantService {

    private static final String dbName = "devDB";
    private static final String tableName = "participants";
    private final MongoDBParticipantDAO partDAO;
    public ParticipantService(){
        super();
        this.partDAO = new MongoDBParticipantDAO(dbName,tableName);
    }

    public List<Document> getAllParticipants(){
        return this.partDAO.getAllParticipants();
    }

    public Document addParticipant(Document participant){
        return this.partDAO.addParticipant(participant);
    }
}
