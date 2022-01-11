
package unisa.is.helpseller.Service;

import java.sql.Date;
import java.util.List;
import javax.transaction.Transactional;
import static org.apache.tomcat.jni.User.username;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unisa.is.helpseller.Entity.Documento;
import unisa.is.helpseller.Repo.DocumentoRepo;

@Service
@Transactional
public class DocumentoService {
    private final DocumentoRepo documentoRepo;
    
    @Autowired
    public DocumentoService(DocumentoRepo documentoRepo) {this.documentoRepo = documentoRepo;}
    
    public List<Documento> findAll() {
        return documentoRepo.findAll();
    }
    
    public Documento findId(int id) {
        return documentoRepo.findId(id);
    }
    
    public void deleteId(int id) {
        documentoRepo.deleteId(id);
    }
    
    public void insert(Documento doc) {
        documentoRepo.insert(doc.getTitolo(), doc.getAutore(), doc.getIdOrdine(), doc.getData());
    }
    
    public void udpate(Documento doc) {
        documentoRepo.update(doc.getTitolo(), doc.getAutore(), doc.getIdOrdine(), doc.getData(), doc.getId());
    }
}
