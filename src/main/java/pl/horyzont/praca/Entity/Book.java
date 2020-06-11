package pl.horyzont.praca.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_ksiazka;
    private String tytul;
    private Integer rokWydania;
    private Long isbn;
    private Integer liczbaEgzemplarzy;
    private Integer cenaZaKsiazke;

    @ManyToMany (mappedBy = "books")
    private Set<Author> authors = new HashSet<>();


    public Book() {
    }

    public Book(String tytul, Integer rokWydania, Long isbn, Integer liczbaEgzemplarzy, Integer cenaZaKsiazke, Set<Author> authors) {
        this.tytul = tytul;
        this.rokWydania = rokWydania;
        this.isbn = isbn;
        this.liczbaEgzemplarzy = liczbaEgzemplarzy;
        this.cenaZaKsiazke = cenaZaKsiazke;
        this.authors = authors;
    }

    public Book(String tytul, Integer rokWydania, Long isbn, Integer liczbaEgzemplarzy, Integer cenaZaKsiazke) {
        this.tytul = tytul;
        this.rokWydania = rokWydania;
        this.isbn = isbn;
        this.liczbaEgzemplarzy = liczbaEgzemplarzy;
        this.cenaZaKsiazke = cenaZaKsiazke;
    }

    public Book(Integer id_ksiazka, String tytul, Integer rokWydania, Long isbn, Integer liczbaEgzemplarzy, Integer cenaZaKsiazke) {
        this.id_ksiazka = id_ksiazka;
        this.tytul = tytul;
        this.rokWydania = rokWydania;
        this.isbn = isbn;
        this.liczbaEgzemplarzy = liczbaEgzemplarzy;
        this.cenaZaKsiazke = cenaZaKsiazke;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Integer getId_ksiazka() {
        return id_ksiazka;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public Integer getRokWydania() {
        return rokWydania;
    }

    public void setRokWydania(Integer rokWydania) {
        this.rokWydania = rokWydania;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public Integer getLiczbaEgzemplarzy() {
        return liczbaEgzemplarzy;
    }

    public void setLiczbaEgzemplarzy(Integer liczbaEgzemplarzy) {
        this.liczbaEgzemplarzy = liczbaEgzemplarzy;
    }

    public Integer getCenaZaKsiazke() {
        return cenaZaKsiazke;
    }

    public void setCenaZaKsiazke(Integer cenaZaKsiazke) {
        this.cenaZaKsiazke = cenaZaKsiazke;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id_ksiazka=" + id_ksiazka +
                ", tytul='" + tytul + '\'' +
                ", rokWydania=" + rokWydania +
                ", isbn=" + isbn +
                ", liczbaEgzemplarzy=" + liczbaEgzemplarzy +
                ", cenaZaKsiazke=" + cenaZaKsiazke +
                ", authors=" + authors +
                '}';
    }
}
