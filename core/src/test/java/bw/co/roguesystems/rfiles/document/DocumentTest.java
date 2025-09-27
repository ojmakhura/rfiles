package bw.co.roguesystems.rfiles.document;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import bw.co.roguesystems.rfiles.document.type.DocumentType;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {

    @Test
    void factoryCreatesDocumentWithRequiredFields() {
        LocalDateTime now = LocalDateTime.now();
        DocumentType dt = new DocumentType();
        dt.setCode("DOC");
        dt.setName("DocumentType");

        Document d = Document.Factory.newInstance(now, "creator", "TARGET", "TID", "file.txt", dt);

        assertNotNull(d);
        assertEquals("file.txt", d.getFilename());
        assertEquals("TARGET", d.getTarget());
        assertEquals("TID", d.getTargetId());
        assertEquals(dt, d.getDocumentType());
    }

    @Test
    void equalsAndHashCodeBasedOnId() {
        Document a = new Document();
        Document b = new Document();
        a.setId("1");
        b.setId("1");

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());

        b.setId("2");
        assertNotEquals(a, b);
    }

    @Test
    void compareToUsesFieldsWhenIdNull() {
        Document a = new Document();
        Document b = new Document();
        a.setUrl("a-url");
        b.setUrl("b-url");

        int cmp = a.compareTo(b);
        assertTrue(cmp < 0);
    }
}
