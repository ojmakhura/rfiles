// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: AndroMDAModel::backend::bw.co.roguesystems.rfiles::document::type::DocumentTypeService
 * STEREOTYPE:  Service
 */
package bw.co.roguesystems.rfiles.document.type;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.co.roguesystems.rfiles.document.type.DocumentTypeService
 */
@Service("documentTypeService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class DocumentTypeServiceImpl
    extends DocumentTypeServiceBase
{
    public DocumentTypeServiceImpl(
        DocumentTypeDao documentTypeDao,
        DocumentTypeRepository documentTypeRepository,
        MessageSource messageSource
    ) {
        
        super(
            documentTypeDao,
            documentTypeRepository,
            messageSource
        );
    }

    /**
     * @see bw.co.roguesystems.rfiles.document.type.DocumentTypeService#findById(String)
     */
    @Override
    protected DocumentTypeDTO handleFindById(String id)
        throws Exception
    {
        DocumentType documentType = this.getDocumentTypeRepository().getReferenceById(id);
        DocumentTypeDTO documentTypeDTO = this.getDocumentTypeDao().toDocumentTypeDTO(documentType);
        return documentTypeDTO;
    }

    /**
     * @see bw.co.roguesystems.rfiles.document.type.DocumentTypeService#save(DocumentTypeDTO)
     */
    @Override
    protected DocumentTypeDTO handleSave(DocumentTypeDTO documentType)
        throws Exception
    {
        DocumentType documentTypeEntity = this.getDocumentTypeDao().documentTypeDTOToEntity(documentType);
        documentTypeEntity = documentTypeRepository.save(documentTypeEntity);

        return this.getDocumentTypeDao().toDocumentTypeDTO(documentTypeEntity);
    }

    /**
     * @see bw.co.roguesystems.rfiles.document.type.DocumentTypeService#remove(String)
     */
    @Override
    protected boolean handleRemove(String id)
        throws Exception
    {
        documentTypeRepository.deleteById(id);
        return true;
    }

    /**
     * @see bw.co.roguesystems.rfiles.document.type.DocumentTypeService#getAll()
     */
    @Override
    protected Collection<DocumentTypeDTO> handleGetAll()
        throws Exception
    {
        Collection<DocumentType> all = documentTypeRepository.findAll();
        return this.getDocumentTypeDao().toDocumentTypeDTOCollection(all);
    }

    private Specification<DocumentType> getSpecification(String criteria) {

        if (StringUtils.isBlank(criteria)) {
            return null;
        }

        return (root, query, cb) -> cb.or(
                cb.like(cb.lower(root.get("name")), "%" + criteria.toLowerCase() + "%"),
                cb.like(cb.lower(root.get("code")), "%" + criteria.toLowerCase() + "%"));
    }

    /**
     * @see bw.co.roguesystems.rfiles.document.type.DocumentTypeService#search(String)
     */
    @Override
    protected Collection<DocumentTypeDTO> handleSearch(String criteria)
        throws Exception
    {
        Specification<DocumentType> specification = getSpecification(criteria);

        Collection<DocumentType> all = documentTypeRepository.findAll(specification);
        return this.getDocumentTypeDao().toDocumentTypeDTOCollection(all);
    }

    /**
     * @see bw.co.roguesystems.rfiles.document.type.DocumentTypeService#getAll(Integer, Integer)
     */
    @Override
    protected Page<DocumentTypeDTO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {
        // TODO implement protected  Page<DocumentTypeDTO> handleGetAll(Integer pageNumber, Integer pageSize)
        throw new UnsupportedOperationException("bw.co.roguesystems.rfiles.document.type.DocumentTypeService.handleGetAll(Integer pageNumber, Integer pageSize) Not implemented!");
    }

    /**
     * @see bw.co.roguesystems.rfiles.document.type.DocumentTypeService#search(String, Integer, Integer)
     */
    @Override
    protected Page<DocumentTypeDTO> handleSearch(String criteria, Integer pageNumber, Integer pageSize)
        throws Exception
    {
        Specification<DocumentType> specification = getSpecification(criteria);

        Page<DocumentType> all = specification == null
                ? documentTypeRepository.findAll(PageRequest.of(pageNumber, pageSize))
                : documentTypeRepository.findAll(specification, PageRequest.of(pageNumber, pageSize));

        return all.map(this.getDocumentTypeDao()::toDocumentTypeDTO);
    }

}