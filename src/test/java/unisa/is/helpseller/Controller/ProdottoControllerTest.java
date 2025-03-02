/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisa.is.helpseller.Controller;

/**
 *
 * @author UTENTE
 */
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import unisa.is.helpseller.Model.AziendaModel;
import unisa.is.helpseller.Model.ProdottoModel;


@SpringBootTest
public class ProdottoControllerTest {

    @Autowired
    private ProdottoController controller;

    @Test
    public void findAllStatus() throws Exception {
        ResponseEntity<List<ProdottoModel>> response = controller.findAll();
        List<ProdottoModel> allProdotti = response.getBody();
        assertThat(response.getStatusCode().compareTo(HttpStatus.OK));
        assertThat(allProdotti).asList();
    }

    @Test
    public void findIncorrectId() throws Exception {
        ResponseEntity<ProdottoModel> response = controller.findId(-1);
        ProdottoModel prodotto = response.getBody();
        assertThat(response.getStatusCode().compareTo(HttpStatus.NOT_FOUND));
        assertThat(prodotto).isNull();
    }

    @Test
    public void findCorrectId() throws Exception {
        ResponseEntity<ProdottoModel> response = controller.findId(1);
        ProdottoModel prodotto = response.getBody();
        assertThat(response.getStatusCode().compareTo(HttpStatus.OK));
        assertThat(prodotto).isNotNull();
    }
    //int id, String nomeProdotto, double prezzo, String descrizione, int quantita, String immagine, int quantitaMinima, 
    //int peso, int volume, int idAzienda, List<RecensioneModel> recensioni, List<ScontoModel> sconti
    @Test
    public void CreateDestroy() throws Exception {
        ProdottoModel prodotto = new ProdottoModel("wafer croccanti", 2, "wafer al cioccolato",
                150, "", 1, 1, 1, 3, null, null);
        ResponseEntity<Integer> response = controller.insert(prodotto);
        assertThat(response.getStatusCode().compareTo(HttpStatus.OK));
        assertThat(response.getBody() > 0);

        ResponseEntity<Integer> response2 = controller.deleteId(response.getBody());
        assertThat(response2.getStatusCode().compareTo(HttpStatus.OK));
        assertThat(response2.getBody() > 0);
    }

    @Test
    public void invalidInsert(){
        ProdottoModel prodotto = new ProdottoModel("wafer croccanti", 2, "wafer al cioccolato",
                150, "", 1, 1, 1, 300, null, null);
        ResponseEntity<Integer> response = controller.insert(prodotto);
        assertThat(response.getStatusCode().compareTo(HttpStatus.OK));
        assertThat(response.getBody() == null);
    }

    @Test
    public void invalidDelete(){
        ResponseEntity<Integer> response = controller.deleteId(150);
        assertThat(response.getStatusCode().compareTo(HttpStatus.OK));
        assertThat(response.getBody() == null);
    }

    @Test
    public void update(){
        ProdottoModel p = controller.findId(1).getBody();
        p.setPrezzo(4);
        ResponseEntity<Integer> response = controller.update(p);
        assertThat(response.getStatusCode().compareTo(HttpStatus.OK));
        assertThat(response.getBody() > 0);
    }

    @Test
    public void invalidUpdate(){
        ProdottoModel p = controller.findId(1).getBody();
        p.setIdAzienda(1505);
        ResponseEntity<Integer> response = controller.update(p);
        assertThat(response.getStatusCode().compareTo(HttpStatus.OK));
        assertThat(response.getBody() == null);
    }
    
    @Test
    public void ricercaByAzienda() throws Exception {
        ResponseEntity<List<ProdottoModel>> response = controller.findProdottiByAzienda(1);
        List<ProdottoModel> allProdotti = response.getBody();
        assertThat(response.getStatusCode().compareTo(HttpStatus.OK));
        assertThat(allProdotti).asList();
    }
    
    @Test
    public void invalidRicercaByAzienda() throws Exception {
        ResponseEntity<List<ProdottoModel>> response = controller.findProdottiByAzienda(100);
        List<ProdottoModel> allProdotti = response.getBody();
        assertThat(response.getStatusCode().compareTo(HttpStatus.NOT_FOUND));
        assertThat(allProdotti).asList();
        assertThat(allProdotti).asList().isEmpty();
    }
    
    @Test
    public void ricercaByNome() throws Exception {
        ResponseEntity<List<ProdottoModel>> response = controller.findProdottiByNome("kebab");
        List<ProdottoModel> allProdotti = response.getBody();
        assertThat(response.getStatusCode().compareTo(HttpStatus.OK));
        assertThat(allProdotti).asList();
        assertThat(allProdotti.get(0)).isNotNull();
    }
    
    @Test
    public void invalidRicercaByNome() throws Exception {
        ResponseEntity<List<ProdottoModel>> response = controller.findProdottiByNome("caratteri non presenti nel db");
        List<ProdottoModel> allProdotti = response.getBody();
        assertThat(response.getStatusCode().compareTo(HttpStatus.NOT_FOUND));
        assertThat(allProdotti).asList();
        assertThat(allProdotti).asList().isEmpty();
    }
//il nome è del prodotto, l'id è dell'azienda
    @Test
    public void ricercaByNomeInAzienda() throws Exception {
        ResponseEntity<List<ProdottoModel>> response = controller.findProdottiByNomeInAzienda("cola", 1);
        List<ProdottoModel> allProdotti = response.getBody();
        assertThat(response.getStatusCode().compareTo(HttpStatus.OK));
        assertThat(allProdotti).asList();
        assertThat(allProdotti.get(0)).isNotNull();
    }
    
    @Test
    public void invalidRicercaByNomeInAzienda() throws Exception {
        ResponseEntity<List<ProdottoModel>> response = controller.findProdottiByNomeInAzienda("cola", 4);
        List<ProdottoModel> allProdotti = response.getBody();
        assertThat(response.getStatusCode().compareTo(HttpStatus.NOT_FOUND));
        assertThat(allProdotti).asList();
        assertThat(allProdotti).asList().isEmpty();
    }
//ricerca partendo dall'id dell'ordine
    @Test
    public void ricerdaProdottiInOrdine() throws Exception {
        ResponseEntity<List<ProdottoModel>> response = controller.findProdottiInOrdine(1);
        List<ProdottoModel> allProdotti = response.getBody();
        assertThat(response.getStatusCode().compareTo(HttpStatus.OK));
        assertThat(allProdotti).asList();
        //assertThat(allProdotti.get(0)).isNotNull(); da aggiungere nel DB!
    }

    @Test
    public void ricerdaProdottiInOrdineFail() throws Exception {
        ResponseEntity<List<ProdottoModel>> response = controller.findProdottiInOrdine(999);
        List<ProdottoModel> allProdotti = response.getBody();
        assertThat(response.getStatusCode().compareTo(HttpStatus.NOT_FOUND));
        assertThat(allProdotti).asList();
        //assertThat(allProdotti.get(0)).isNotNull(); da aggiungere nel DB!
    }
    
    @Test
    public void invalidRicerdaProdottiInOrdine() throws Exception {
        ResponseEntity<List<ProdottoModel>> response = controller.findProdottiInOrdine(15);
        List<ProdottoModel> allProdotti = response.getBody();
        assertThat(response.getStatusCode().compareTo(HttpStatus.NOT_FOUND));
        assertThat(allProdotti).asList();
        assertThat(allProdotti).asList().isEmpty();
    }
    
    @Test
    public void ricerdaProdottiInSconto() throws Exception {
        ResponseEntity<List<ProdottoModel>> response = controller.findProdottiInSconto(4);
        List<ProdottoModel> allProdotti = response.getBody();
        assertThat(response.getStatusCode().compareTo(HttpStatus.OK));
        assertThat(allProdotti).asList();
        assertThat(allProdotti.isEmpty());
    }
    
    @Test
    public void invalidRicerdaProdottiInSconto() throws Exception {
        ResponseEntity<List<ProdottoModel>> response = controller.findProdottiInSconto(120);
        List<ProdottoModel> allProdotti = response.getBody();
        assertThat(response.getStatusCode().compareTo(HttpStatus.NOT_FOUND));
        assertThat(allProdotti).asList();
        assertThat(allProdotti).asList().isEmpty();
    }

    @Test
    public void ricerdaProdottiByIdInAzienda() throws Exception {
        ResponseEntity<List<ProdottoModel>> response = controller.findProdottiByIdInAzienda(1, 1);
        List<ProdottoModel> allProdotti = response.getBody();
        assertThat(response.getStatusCode().compareTo(HttpStatus.OK));
        assertThat(allProdotti).asList();
        assertThat(allProdotti).asList().isNotEmpty();
    }

    @Test
    public void invalidRicerdaProdottiByIdInAzienda() throws Exception {
        ResponseEntity<List<ProdottoModel>> response = controller.findProdottiByIdInAzienda(1, 150);
        List<ProdottoModel> allProdotti = response.getBody();
        assertThat(response.getStatusCode().compareTo(HttpStatus.OK));
        assertThat(allProdotti).asList();
        assertThat(allProdotti).asList().isEmpty();
    }
}