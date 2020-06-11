package pl.horyzont.praca.Entity;

import javax.persistence.*;
import java.util.*;

@Entity
public class Author {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_autor;
    private String imie;
    private String nazwisko;
    private Integer liczbaPublikacji;
    private Integer telefonAutora;

    @ManyToMany (cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable (
            name = "authors_books",
            joinColumns = @JoinColumn (name = "author_id"),
            inverseJoinColumns = @JoinColumn (name = "book_id")
    )
    private Set<Book> books = new HashSet<>();


    public void addBook (Book book){
        this.books.add(book);
        book.getAuthors().add(this);
    }

    public void removeBook (Book book){
        this.books.remove(book);
        book.getAuthors().remove(this);
    }

    public Author() {
    }

    public Author(String imie, String nazwisko, Integer liczbaPublikacji, Integer telefonAutora) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.liczbaPublikacji = liczbaPublikacji;
        this.telefonAutora = telefonAutora;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Integer getLiczbaPublikacji() {
        return liczbaPublikacji;
    }

    public void setLiczbaPublikacji(Integer liczbaPublikacji) {
        this.liczbaPublikacji = liczbaPublikacji;
    }

    public Integer getTelefonAutora() {
        return telefonAutora;
    }

    public void setTelefonAutora(Integer telefonAutora) {
        this.telefonAutora = telefonAutora;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id_autor=" + id_autor +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", liczbaPublikacji=" + liczbaPublikacji +
                ", telefonAutora=" + telefonAutora +
                '}';
    }
}

