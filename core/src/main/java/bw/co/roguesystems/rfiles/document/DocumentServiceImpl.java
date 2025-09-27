// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: AndroMDAModel::backend::bw.co.roguesystems.rfiles::document::DocumentService
 * STEREOTYPE:  Service
 */
package bw.co.roguesystems.rfiles.document;

import bw.co.roguesystems.rfiles.document.type.DocumentTypeRepository;
import bw.co.roguesystems.rfiles.minio.MinioService;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.co.roguesystems.rfiles.document.DocumentService
 */
@Service("documentService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class DocumentServiceImpl
        extends DocumentServiceBase {

    private final ExtraDocumentRepository extraDocumentRepository;
    private final MinioService minioService;
    private final DocumentTypeRepository documentTypeRepository;

    public DocumentServiceImpl(
            DocumentDao documentDao,
            DocumentRepository documentRepository,
            MinioService minioService,
            MessageSource messageSource,
            ExtraDocumentRepository extraDocumentRepository, DocumentTypeRepository documentTypeRepository) {

        super(
                documentDao,
                documentRepository,
                messageSource);
        this.extraDocumentRepository = extraDocumentRepository;
        this.minioService = minioService;
        this.documentTypeRepository = documentTypeRepository;
    }

    /**
     * @see bw.co.roguesystems.rfiles.document.DocumentService#findById(String)
     */
    @Override
    protected DocumentDTO handleFindById(String id)
            throws Exception {

        Document doc = documentRepository.findById(id).orElse(null);
        if (doc == null) {
            return null;
        }

        return documentDao.toDocumentDTO(doc);
    }

    /**
     * @see bw.co.roguesystems.rfiles.document.DocumentService#save(DocumentDTO)
     */
    @Override
    protected DocumentDTO handleSave(DocumentDTO document)
            throws Exception {

        Document doc = documentDao.documentDTOToEntity(document);
        doc = documentRepository.save(doc);

        return documentDao.toDocumentDTO(doc);

    }

    /**
     * @see bw.co.roguesystems.rfiles.document.DocumentService#remove(String)
     */
    @Override
    protected boolean handleRemove(String id)
            throws Exception {

        documentRepository.deleteById(id);
        return true;
    }

    /**
     * @see bw.co.roguesystems.rfiles.document.DocumentService#getAll()
     */
    @Override
    protected Collection<DocumentDTO> handleGetAll()
            throws Exception {
        Collection<Document> docs = documentRepository.findAll();
        return documentDao.toDocumentDTOCollection(docs);
    }

    /**
     * @see bw.co.roguesystems.rfiles.document.DocumentService#search(String)
     */
    @Override
    protected Collection<DocumentDTO> handleSearch(String criteria)
            throws Exception {

        // TODO implement protected Collection<DocumentDTO> handleSearch(String
        // criteria)
        throw new UnsupportedOperationException(
                "bw.co.roguesystems.rfiles.document.DocumentService.handleSearch(String criteria) Not implemented!");
    }

    /**
     * @see bw.co.roguesystems.rfiles.document.DocumentService#getAll(Integer,
     *      Integer)
     */
    @Override
    protected Page<DocumentDTO> handleGetAll(Integer pageNumber, Integer pageSize)
            throws Exception {
        PageRequest request = PageRequest.of(pageNumber, pageSize);
        Page<Document> docs = documentRepository.findAll(request);

        return docs.map(documentDao::toDocumentDTO);
    }

    /**
     * @see bw.co.roguesystems.rfiles.document.DocumentService#findByDocumentType(String)
     */
    @Override
    protected Collection<DocumentDTO> handleFindByDocumentType(String documentTypeId)
            throws Exception {
        Collection<Document> docs = extraDocumentRepository.findByDocumentTypeId(documentTypeId);
        if (docs == null || docs.isEmpty()) {
            return null;
        }

        return documentDao.toDocumentDTOCollection(docs);
    }

    /**
     * @see bw.co.roguesystems.rfiles.document.DocumentService#upload(String,
     *      String, String, File)
     */
    @Override
    protected DocumentDTO handleUpload(String target, String targetId, String documentTypeId, File file, String user)
            throws Exception {

        String id = null;
        try { //(InputStream inputStream = file.getInputStream()) {

//             id = minioService.uploadFile(file.getName(), new FileInputStream(file), file.length(), file.c);
            System.out.println("File uploaded to MinIO: " + id);

            Document doc = Document.Factory.newInstance();
            doc.setDocumentType(documentTypeRepository.getReferenceById(documentTypeId));
//            doc.set
            // doc.setFilePath(fileName);
            // doc.setCreatedBy(user);
            // doc.setCreatedAt(LocalDateTime.now());
            // doc.setDocumentName(file.getOriginalFilename());
            // doc.setUrl(id);
            // doc.setVersion("0.0.1");
            // doc.setDocumentId(UUID.randomUUID().toString());
            // doc.setParent(parent);
            // doc.setDir(false);

            return documentDao.toDocumentDTO(documentRepository.save(doc));

        } catch (Exception e) {
            e.printStackTrace();
//            throw new DocumentServiceException("Error uploading file: " + file.getOriginalFilename());
            throw new DocumentServiceException("Error uploading file: ");
        }
        
    }

    /**
     * @see bw.co.roguesystems.rfiles.document.DocumentService#findByTarget(String,
     *      String)
     */
    @Override
    protected Collection<DocumentDTO> handleFindByTarget(String target, String targetId)
            throws Exception {
        Collection<Document> docs = extraDocumentRepository.findByTargetAndTargetId(target, targetId);
        if (docs == null || docs.isEmpty()) {
            return null;
        }

        return documentDao.toDocumentDTOCollection(docs);
    }

    /**
     * @see bw.co.roguesystems.rfiles.document.DocumentService#updateFile(String,
     *      String)
     */
    @Override
    protected DocumentDTO handleUpdateFile(String id, String url, String user)
            throws Exception {
        // TODO implement protected DocumentDTO handleUpdateFile(String id, String url)
        throw new UnsupportedOperationException(
                "bw.co.roguesystems.rfiles.document.DocumentService.handleUpdateFile(String id, String url) Not implemented!");
    }

}