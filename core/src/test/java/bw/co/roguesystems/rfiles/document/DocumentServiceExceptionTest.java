package bw.co.roguesystems.rfiles.document;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentServiceExceptionTest {

    @Test
    void constructorsAndMessageArguments() {
        DocumentServiceException ex1 = new DocumentServiceException();
        assertNotNull(ex1);

        DocumentServiceException ex2 = new DocumentServiceException("msg");
        assertEquals("msg", ex2.getMessage());

        Exception cause = new RuntimeException("root");
        DocumentServiceException ex3 = new DocumentServiceException("m", cause);
        assertEquals("m", ex3.getMessage());

        ex3.setMessageArguments(new Object[]{"a","b"});
        assertArrayEquals(new Object[]{"a","b"}, ex3.getMessageArguments());
    }
}
