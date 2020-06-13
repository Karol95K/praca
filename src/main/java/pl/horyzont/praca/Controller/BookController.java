package pl.horyzont.praca.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.horyzont.praca.Entity.Author;
import pl.horyzont.praca.Entity.Book;
import pl.horyzont.praca.Repository.AuthorRepo;
import pl.horyzont.praca.Repository.BookRepo;

import java.util.Optional;

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
    public String dodajemyDane (
            @RequestParam("tytul") String tytul,
            @RequestParam("rokWydania") Integer rokWydania,
            @RequestParam("isbn") Long isbn,
            @RequestParam("liczbaEgzemplarzy") Integer liczbaEgzemplarzy,
            @RequestParam("cenaZaKsiazke") Integer cenaZaKsiazke,
            @RequestParam("imie") String imie,
            @RequestParam ("nazwisko") String nazwisko,
            @RequestParam ("liczbaPublikacji") Integer liczbaPublikacji,
            @RequestParam ("telefonAutora") Integer telefonAutora,
            Model model)throws Exception{
        Book book = new Book (tytul,rokWydania, isbn, liczbaEgzemplarzy, cenaZaKsiazke);
        Author author= new Author(imie, nazwisko, liczbaPublikacji,telefonAutora);

        System.out.println(book);
        System.out.println(author);
        author.addBook(book);
        authorRepo.save(author);
        bookRepo.save(book);

        model.addAttribute("book", book);
        model.addAttribute("author",author);
       // dodajemydane_2(author, model);
        System.out.println(book);
        System.out.println(author);

        return "Widok";
    }

    @RequestMapping ("/autor")
    public String dodajemydane_2( @RequestParam("id_autor") Integer id_autor, Model model)throws Exception{
        System.out.println(authorRepo.findById(id_autor));
        //Author author = new Author("Wiechu","szczesny",32,2124123);
        model.addAttribute("author",authorRepo.getOne(id_autor));//authorRepo.findById(id_autor)
        //authorRepo.getOne(id_autor);


        return "Pokaz2_1";
    }

    @RequestMapping("/form")
    String formularz (){
        return "Formularz";
    }

    @RequestMapping("/home")
    public String home (Model model) throws Exception{
        int i = 0;
        for (Book book : bookRepo.findAll()) {
            System.out.println(book);
        }
        model.addAttribute("book", bookRepo.findAll());
        return "home";
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

    @RequestMapping("/pokaz2")
    public String pokaz2(Model model) throws Exception {
        int i = 0;
        for (Book book : bookRepo.findAll()) {
            System.out.println(book);
        }

        model.addAttribute("book", bookRepo.findAll());
        model.addAttribute("author",authorRepo.findAll());
        return "Pokaz2";
    }


    @RequestMapping (value="/kasuj")
    public String kasuj (@RequestParam ("id_autor") Integer id_autor, Model model){
        Author author=authorRepo.getOne(id_autor);
        Book book=bookRepo.getOne(id_autor);
        author.removeBook(book);
        bookRepo.deleteById(id_autor);

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
    public String update (
            @RequestParam("id_ksiazka") Integer id_ksiazka,
            @RequestParam("tytul") String tytul,
            @RequestParam("rokWydania") Integer rokWydania,
            @RequestParam("isbn") Long isbn,
            @RequestParam("liczbaEgzemplarzy") Integer liczbaEgzemplarzy,
            @RequestParam("cenaZaKsiazke") Integer cenaZaKsiazke,
            @RequestParam("imie") String imie,
            @RequestParam ("nazwisko") String nazwisko,
            @RequestParam ("liczbaPublikacji") Integer liczbaPublikacji,
            @RequestParam ("telefonAutora") Integer telefonAutora,
            Model model)throws Exception{
        Book book = new Book (id_ksiazka, tytul,rokWydania, isbn, liczbaEgzemplarzy, cenaZaKsiazke);
        Author author= new Author(id_ksiazka,imie, nazwisko, liczbaPublikacji,telefonAutora);

        author.addBook(book);
        bookRepo.save(book);
        authorRepo.save(author);

        model.addAttribute("book", book);
        model.addAttribute("author",author);
        // dodajemydane_2(author, model);
        System.out.println(author);
        System.out.println(book);

        return "Widok";
    }

    @RequestMapping ("/wyszukaj" )
    public String wyszukaj(@RequestParam ("kryterium" ) Integer kryterium , Model model){
        model.addAttribute( "book", bookRepo.findAllByrokWydania(kryterium)) ;
        return "pokaz2";
    }
}
