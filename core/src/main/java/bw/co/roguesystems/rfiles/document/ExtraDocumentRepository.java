package bw.co.roguesystems.rfiles.document;

import java.util.Collection;

public interface ExtraDocumentRepository  extends DocumentRepository {

    /**
     * Finds documents by their document type ID.
     *
     * @param documentTypeId the ID of the document type
     * @return a collection of Documents associated with the specified document type ID
     */
    Collection<Document> findByDocumentTypeId(String documentTypeId);

    /**
     * Finds documents by their target and target ID.
     *
     * @param target the entity type of the target
     * @param targetId the ID of the target
     * @return a collection of Documents associated with the specified target and target ID
     */
    Collection<Document> findByTargetAndTargetId(String target, String targetId);
    
}
