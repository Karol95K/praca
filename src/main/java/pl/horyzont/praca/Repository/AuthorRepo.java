package pl.horyzont.praca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.horyzont.praca.Entity.Author;

public interface AuthorRepo extends JpaRepository <Author,Integer> {
  //  @Query(value = "SELECT * FROM AUTHOR WHERE BOOK_ID = %?1")
  //  Author findById(Integer ksiazka_id);
}
