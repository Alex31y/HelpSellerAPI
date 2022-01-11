package unisa.is.helpseller.Repo;

import java.sql.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import unisa.is.helpseller.Entity.Sconto;

public interface ScontoRepo extends JpaRepository<Sconto, Integer> {
   @Query("SELECT s FROM Sconto s WHERE s.id = ?1")
   Sconto findId(int id);
   
   @Modifying
   @Query("DELETE FROM Sconto s WHERE s.id = ?1")
   void deleteId(int id);
   
   //SQL
   @Modifying
   @Query(
   value = "INSERT INTO sconto (percentuale, tipo, quantita, id_azienda, data_fine, data_inizio) VALUES (:percentuale, :tipo, :quantita, :id_azienda, :data_fine, :data_inizio)",
           nativeQuery = true)
   void insert(@Param("percentuale") int percentuale, @Param("tipo") String tipo, @Param("quantita") int quantita, 
           @Param("id_azienda") int id_azienda, @Param("data_fine") Date data_fine, @Param("data_inizio") Date data_inizio);
   
   //JPQL
   @Modifying
   @Query("UPDATE Sconto s SET percentuale = :percentuale, tipo = :tipo, quantita = :quantita, "
           + "id_azienda = :id_azienda, data_fine = :data_fine, data_inizio = :data_inizio WHERE s.id = :id")
   void update(@Param("percentuale") int percentuale, @Param("tipo") String tipo, @Param("quantita") int quantita, 
           @Param("id_azienda") int id_azienda, @Param("data_fine") Date data_fine, @Param("data_inizio") Date data_inizio, @Param("id") int id);
}
