
package unisa.is.helpseller.Service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unisa.is.helpseller.Entity.Recensione;
import unisa.is.helpseller.Repo.RecensioneRepo;

@Service
@Transactional
public class RecensioneService {
	@Autowired
    private final RecensioneRepo recensioneRepo;
    
    @Autowired
    public RecensioneService(RecensioneRepo recensioneRepo) {this.recensioneRepo = recensioneRepo;}
    
    public List<Recensione> findAll() {
        return recensioneRepo.findAll();
    }

    public Recensione findId(int id) {
        return recensioneRepo.findId(id);
    }
    
    public void deleteId(int id) {
        recensioneRepo.deleteId(id);
    }
    
    public void insert(Recensione rec) {
        recensioneRepo.insert(rec.getTesto(), rec.getVoto(), rec.getData(), rec.getIdProdotto(), rec.getIdDistributore());
    }
    
    public void udpate(Recensione rec) {
        recensioneRepo.update(rec.getTesto(), rec.getVoto(), rec.getData(), rec.getIdProdotto(), rec.getIdDistributore(), rec.getId());
    }
    
}
