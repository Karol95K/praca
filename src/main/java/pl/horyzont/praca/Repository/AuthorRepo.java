package pl.horyzont.praca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.horyzont.praca.Entity.Author;

public interface AuthorRepo extends JpaRepository <Author,Integer> {
}
