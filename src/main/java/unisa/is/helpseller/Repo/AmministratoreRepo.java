
package unisa.is.helpseller.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import unisa.is.helpseller.Entity.Amministratore;

public interface AmministratoreRepo extends JpaRepository<Amministratore, Integer> {
   
    //JPQL
    @Query("SELECT a FROM Amministratore a WHERE a.id = ?1")
   Amministratore findId(int id);
   
   //JPQL
   @Modifying
   @Query("DELETE FROM Amministratore a WHERE a.id = ?1")
   void deleteId(int id);
   
   //SQL
   @Modifying
   @Query(
   value = "INSERT INTO amministratore (username, email, password) VALUES (:username, :email, :password)",
           nativeQuery = true)
   void insert(@Param("username") String username, @Param("email") String email, @Param("password") String password);
   
   //JPQL
   @Modifying
   @Query("UPDATE Amministratore a SET password = :value WHERE a.id = :id")
   void updatePassword (@Param("value") String value, @Param("id") int id);
}
