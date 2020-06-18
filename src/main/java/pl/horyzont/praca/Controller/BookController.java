package pl.horyzont.praca.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.horyzont.praca.Entity.Author;
import pl.horyzont.praca.Entity.Book;
import pl.horyzont.praca.Repository.AuthorRepo;
import pl.horyzont.praca.Repository.BookRepo;

import java.util.*;

//import static pl.horyzont.praca.Entity.Book.getKeysByValue;

@Controller
public class BookController {
    private BookRepo bookRepo;
    private AuthorRepo authorRepo;

    @Autowired
    public BookController(BookRepo bookRepo, AuthorRepo authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }


    @RequestMapping("/dodaj")
    public String dodajemyDane(
            @RequestParam("tytul") String tytul,
            @RequestParam("rokWydania") Integer rokWydania,
            @RequestParam("isbn") Long isbn,
            @RequestParam("liczbaEgzemplarzy") Integer liczbaEgzemplarzy,
            @RequestParam("cenaZaKsiazke") Integer cenaZaKsiazke,
            @RequestParam("imie") String imie,
            @RequestParam("nazwisko") String nazwisko,
            @RequestParam("liczbaPublikacji") Integer liczbaPublikacji,
            @RequestParam("telefonAutora") Integer telefonAutora,
            Model model) throws Exception {
        Book book = new Book(tytul, rokWydania, isbn, liczbaEgzemplarzy, cenaZaKsiazke);
        Author author = new Author(imie, nazwisko, liczbaPublikacji, telefonAutora);
        Author author2 = new Author("Jaś", "Wojtaszek", 22, 11);


        author.addBook(book);
        author2.addBook(book);
        authorRepo.save(author);
        authorRepo.save(author2);
        bookRepo.save(book);


        model.addAttribute("book", book);
        model.addAttribute("author", author);

        System.out.println(book);
        System.out.println(author);

        bookRepo.save(book);
        model.addAttribute("book", book);
        model.addAttribute("author", author);

        book.setBook_map(author.getId_autor(), book.getId_ksiazka());
        book.setBook_map(author2.getId_autor(), book.getId_ksiazka());
        System.out.println("To jest mapa" + book.getBook_map());
        bookRepo.save(book); ///
        model.addAttribute("book", book); ////
        System.out.println(book);


        return "Widok";
    }

    @RequestMapping("/dodaj_autor")
    public String dodajemyDane(
            @RequestParam("id_ksiazka") Integer id_ksiazka, Model model) throws Exception {
        Book book = bookRepo.getOne(id_ksiazka);
        model.addAttribute("book", book);

        System.out.println("To jest mapa" + book.getBook_map());

        System.out.println(book);

        return "form_author";
    }

    @RequestMapping("/dodaj_autora")
    public String dodajemyAutora(
            @RequestParam("id_ksiazka") Integer id_ksiazka,
            @RequestParam("imie") String imie,
            @RequestParam("nazwisko") String nazwisko,
            @RequestParam("liczbaPublikacji") Integer liczbaPublikacji,
            @RequestParam("telefonAutora") Integer telefonAutora,
            Model model) throws Exception {

        Book book = bookRepo.getOne(id_ksiazka);
        System.out.println("Takie mamy mapa: " + book.getBook_map());
        Author author = new Author(imie, nazwisko, liczbaPublikacji, telefonAutora);

        author.addBook(book);
        authorRepo.save(author);
        bookRepo.save(book);

        model.addAttribute("book", book);
        model.addAttribute("author", author);
        System.out.println(book);
        System.out.println(author);

        bookRepo.save(book);

        book.setBook_map(author.getId_autor(), book.getId_ksiazka());
        System.out.println("To jest mapa" + book.getBook_map());
        bookRepo.save(book);   /////
        model.addAttribute("book", book);  /////

        return "Widok";
    }

    @RequestMapping("/autor")
    public String dodajemydane_2(@RequestParam("id_ksiazka") Integer id_ksiazka, Model model) throws Exception {
        System.out.println(bookRepo.findById(id_ksiazka));
        Book book = bookRepo.getOne(id_ksiazka);

        Set<Integer> authors_id = book.getKeysByValue(id_ksiazka);
        System.out.println("Takie id maja autorzy" + authors_id);

        System.out.println("Jakich autorow mamy" + authorRepo.findAllById(authors_id));
        model.addAttribute("author", authorRepo.findAllById(authors_id));

        return "Pokaz2_1";
    }


    @RequestMapping("/form")
    String formularz() {
        return "Formularz";
    }

    @RequestMapping("/home")
    public String home(Model model) throws Exception {
        int i = 0;
        for (Book book : bookRepo.findAll()) {
            System.out.println(book);
        }
        model.addAttribute("book", bookRepo.findAll());
        return "home";
    }


    @RequestMapping("/pokaz2")
    public String pokaz2(Model model) throws Exception {
        int i = 0;
        for (Book book : bookRepo.findAll()) {
            System.out.println(book);
        }

        model.addAttribute("book", bookRepo.findAll());
        model.addAttribute("author", authorRepo.findAll());

        return "Pokaz2";
    }


    @RequestMapping(value = "/kasuj")
    public String kasuj(@RequestParam("id_ksiazka") Integer id_ksiazka, Model model) {
        //Author author=authorRepo.getOne(id_autor);
        Book book = bookRepo.getOne(id_ksiazka);

        Set<Integer> authors_id = book.getKeysByValue(id_ksiazka);
        System.out.println("Takie id maja autorzy" + authors_id);

        int set_size = authors_id.size();
        int[] tab = new int[set_size];
        System.out.println("Rozmiar tablicy: " + set_size);
        int index = 0;
        for (Integer i : authors_id) {
            tab[index++] = i;
        }

        int a = 0;
        for (Integer j : tab) {
            Author author = authorRepo.getOne(tab[a]);
            System.out.println("Usuwamy autora o id " + tab[a++]);
            author.removeBook(book);
        }


        //author.removeBook(book);
        bookRepo.deleteById(id_ksiazka);

        model.addAttribute("book", bookRepo.findAll());
        return "Pokaz2";
    }

    @RequestMapping("/przekieruj")
    public String przekieruj(
            @RequestParam("id_ksiazka") Integer id_ksiazka, Model model
    )
            throws Exception {
        //System.out.println(bookRepo.findById(id_ksiazka));
        model.addAttribute("book", bookRepo.findById(id_ksiazka));
        model.addAttribute("author", authorRepo.getOne(id_ksiazka));
        //System.out.println(authorRepo);

        return "Aktualizuj";
    }

    @RequestMapping("/aktualizuj")
    public String update(
            @RequestParam("id_ksiazka") Integer id_ksiazka,
            @RequestParam("tytul") String tytul,
            @RequestParam("rokWydania") Integer rokWydania,
            @RequestParam("isbn") Long isbn,
            @RequestParam("liczbaEgzemplarzy") Integer liczbaEgzemplarzy,
            @RequestParam("cenaZaKsiazke") Integer cenaZaKsiazke,
            @RequestParam("imie") String imie,
            @RequestParam("nazwisko") String nazwisko,
            @RequestParam("liczbaPublikacji") Integer liczbaPublikacji,
            @RequestParam("telefonAutora") Integer telefonAutora,
            Model model) throws Exception {
        Book book = new Book(id_ksiazka, tytul, rokWydania, isbn, liczbaEgzemplarzy, cenaZaKsiazke);
        Author author = new Author(id_ksiazka, imie, nazwisko, liczbaPublikacji, telefonAutora);

        author.addBook(book);
        bookRepo.save(book);
        authorRepo.save(author);

        model.addAttribute("book", book);
        model.addAttribute("author", author);
        // dodajemydane_2(author, model);
        System.out.println(author);
        System.out.println(book);

        return "Widok";
    }

    @RequestMapping("/wyszukaj")
    public String wyszukaj(@RequestParam("kryterium") Integer kryterium, Model model) {
        model.addAttribute("book", bookRepo.findAllByrokWydania(kryterium));
        return "pokaz2";
    }


    @RequestMapping("/pokaz")
    public String pokaz(Model model) throws Exception {
        int i = 0;
        for (Book book : bookRepo.findAll()) {
            System.out.println(book);
        }
        model.addAttribute("book", bookRepo.findAll());
        return "Pokaz";
    }
}
