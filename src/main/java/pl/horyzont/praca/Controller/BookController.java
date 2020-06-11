package pl.horyzont.praca.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
            Model model)throws Exception{
        Book book = new Book (tytul,rokWydania, isbn, liczbaEgzemplarzy, cenaZaKsiazke);
        Author author= new Author(imie, "Kowalski", 3, 51232323);
        System.out.println(book);
        System.out.println(author);
        author.addBook(book);
        authorRepo.save(author);
        bookRepo.save(book);

        model.addAttribute("book", book);
        model.addAttribute("author",author);
        System.out.println(book);
        System.out.println(author);
        return "Widok";
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
        return "Pokaz2";
    }


    @RequestMapping (value="/kasuj")
    public String kasuj (@RequestParam ("id_ksiazka") Integer id_ksiazka, Model model){
        bookRepo.deleteById(id_ksiazka);

        model.addAttribute("book", bookRepo.findAll());
        return "Pokaz2";
    }

    @RequestMapping("/przekieruj")
    public String przekieruj(
            @RequestParam("id_ksiazka") Integer id_ksiazka, Model model
    )
            throws Exception {
        System.out.println(bookRepo.findById(id_ksiazka));
        model.addAttribute("book", bookRepo.findById(id_ksiazka));
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
            Model model)throws Exception{
        Book book = new Book (id_ksiazka, tytul,rokWydania, isbn, liczbaEgzemplarzy, cenaZaKsiazke);
        System.out.println(book);
        bookRepo.save(book);
        model.addAttribute("book", book);
        return "Widok";
    }

    @RequestMapping ("/wyszukaj" )
    public String wyszukaj(@RequestParam ("kryterium" ) Integer kryterium ,
                           Model model){
        model.addAttribute( "book",
                bookRepo.findAllByrokWydania(kryterium)) ;
        return "pokaz2";
    }
}
