
package unisa.is.helpseller.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import unisa.is.helpseller.Service.ScontoProdottoService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import unisa.is.helpseller.Entity.ScontoProdotto;

@RestController
@RequestMapping("/scontoprodotto")
@CrossOrigin("http://localhost:4200")
public class ScontoProdottoController {
    private final ScontoProdottoService scontoProdottoService;
    
    @Autowired
    public ScontoProdottoController(ScontoProdottoService scontoProdottoService) {this.scontoProdottoService = scontoProdottoService;}
    
      @GetMapping("/findAll")
    public ResponseEntity<List<ScontoProdotto>> findAll() {
        List<ScontoProdotto> scontoProdotti = scontoProdottoService.findAll();
        return new ResponseEntity<>(scontoProdotti, HttpStatus.OK);
    }
    
    @GetMapping("/findId/{id}")
    public ResponseEntity<ScontoProdotto> findId(@PathVariable("id") int id) {
        ScontoProdotto scontoProdotto = scontoProdottoService.findId(id);
        return new ResponseEntity<>(scontoProdotto, HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteId/{id}")
    public ResponseEntity<ScontoProdotto> deleteId(@PathVariable("id") int id) {
        scontoProdottoService.deleteId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
