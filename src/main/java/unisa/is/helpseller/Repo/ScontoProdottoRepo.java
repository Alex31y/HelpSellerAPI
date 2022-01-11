
package unisa.is.helpseller.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import unisa.is.helpseller.Entity.ScontoProdotto;

public interface ScontoProdottoRepo extends JpaRepository<ScontoProdotto, Integer>{
   @Query("SELECT sp FROM ScontoProdotto sp WHERE sp.id = ?1")
   ScontoProdotto findId(int id);
   
   @Modifying
   @Query("DELETE FROM ScontoProdotto sp WHERE sp.id = ?1")
   void deleteId(int id);
   
   //SQL
   @Modifying
   @Query(
   value = "INSERT INTO sconto_prodotto (id_sconto, id_prodotto) VALUES (:id_sconto, :id_prodotto)",
           nativeQuery = true)
   void insert(@Param("id_sconto") int id_sconto, @Param("id_prodotto") int id_prodotto);
   
   //JPQL
   @Modifying
   @Query("UPDATE ScontoProdotto s SET id_sconto = :id_sconto, id_prodotto = :id_prodotto WHERE s.id_sconto = :id_sconto_old AND s.id_prodotto = :id_prodotto_old")
   void update(@Param("id_sconto") int id_sconto, @Param("id_prodotto") int id_prodotto, @Param("id_sconto_old") int id_sconto_old, @Param("id_prodotto_old") int id_prodotto_old);
}
