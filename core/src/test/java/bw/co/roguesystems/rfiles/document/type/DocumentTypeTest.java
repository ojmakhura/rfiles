package bw.co.roguesystems.rfiles.document.type;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTypeTest {

    @Test
    void addAndRemoveDocumentsChangesCollection() {
        DocumentType dt = new DocumentType();
        bw.co.roguesystems.rfiles.document.Document doc = new bw.co.roguesystems.rfiles.document.Document();
        assertTrue(dt.addDocuments(doc));
        assertFalse(dt.addDocuments(doc)); // adding same element returns false
        assertTrue(dt.removeDocuments(doc));
    }
}
