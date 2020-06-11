package pl.horyzont.praca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.horyzont.praca.Entity.Book;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository <Book, Integer> {
    List<Book> findAllByrokWydania(Integer wyszukaj);
}
