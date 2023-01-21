package pl.pjatk.zsb.controller;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.zsb.domain.*;
import pl.pjatk.zsb.repository.RentRepository;
import pl.pjatk.zsb.repository.ZSBRepository;
import pl.pjatk.zsb.service.ZSBService;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/db_library")
@Api("/api/tasks")
public class ZSBController {
    private final ZSBService ZSBService;
    private final ZSBRepository zsbRepository;

    private final RentRepository rentRepository;

    public ZSBController(ZSBService ZSBService,
                         ZSBRepository zsbRepository, RentRepository rentRepository) {
        this.ZSBService = ZSBService;
        this.zsbRepository = zsbRepository;
        this .rentRepository = rentRepository;
    }

    @GetMapping("/getFavourites")
    public ResponseEntity<List<Favourite>> getFavourites(@RequestParam String mail_user) {
        return ResponseEntity.ok(ZSBService.getFavourites(mail_user));
    }

    @GetMapping("/example")
    @ApiOperation(value = "Example endpoint", notes = "This is example endpoint")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = String.class),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<String> example() {
        return ResponseEntity.ok("Example");
    }


    @GetMapping("/books/example")
    @ApiOperation(value = "Example book endpoint", notes = "This is example endpoint returning example book")
    public ResponseEntity<Book> getSampleBook() {
        Book sampleBook = ZSBService.getExampleBook();
        if (sampleBook == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(sampleBook);
    }

    @PostMapping("/newFavourite")
    public ResponseEntity<Favourite> newFavourite(@RequestParam Integer id_book, @RequestParam String mail_user) {
        return ResponseEntity.ok(ZSBService.addFavourite(id_book, mail_user));
    }

    @DeleteMapping("/deleteFavourite")
    public ResponseEntity<Void> deleteFavourite(@RequestParam Integer id_book, @RequestParam String mail_user) {
        ZSBService.removeFavourite(id_book, mail_user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(@RequestParam String mail) {
        return ResponseEntity.ok(ZSBService.getUser(mail));
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(ZSBService.getAllUsers());
    }

    @DeleteMapping("/removeUser")
    public ResponseEntity<Void> removeUser(@RequestParam String mail) {
        ZSBService.removeUser(mail);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/checkPassword")
    public ResponseEntity<User> returnIfGoodPassword(@RequestParam String mail, @RequestParam String password) {
        return ResponseEntity.ok(ZSBService.returnIfGoodPassword(mail, password));
    }

    @PostMapping("/newUser")
    public ResponseEntity<User> newUser(@RequestParam String fname, @RequestParam String sname, @RequestParam String mail,
                                        @RequestParam Date birthdate, @RequestParam String city, @RequestParam Type type,
                                        @RequestParam String password) {
        return ResponseEntity.ok(ZSBService.newUser(fname, sname, mail, birthdate, city, type, password));
    }

    @GetMapping("/getBookByMail")
    public ResponseEntity<List<Book>> getBookByMail(@RequestParam String mail) {
        return ResponseEntity.ok(ZSBService.getAllBooksByMail(mail));
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Book[].class),
            @ApiResponse(code = 400, message = "Bad request", response = Book.class)
    })
    @ApiOperation(value = "Return all zsb", notes = "info about all zsb")
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(ZSBService.getAllBooks());
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Book[].class),
            @ApiResponse(code = 400, message = "Bad request", response = Book.class)
    })

    @ApiOperation(value = "Get book by id", notes = "get info about book by id")
    @GetMapping("/book")
    public ResponseEntity<Book> getBookById(@RequestParam Integer id) {
        Book book = ZSBService.getBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(book);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Book[].class),
            @ApiResponse(code = 400, message = "Bad request", response = Book.class)
    })
    @ApiOperation(value = "Add book", notes = "add book info")
    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestParam Integer id, @RequestParam String title, @RequestParam String author, @RequestParam Genres genre,
                                         @RequestParam String language, @RequestParam Integer pubyear, @RequestParam String publisher,
                                         @RequestParam String owner_ID, @RequestParam Date beginning, @RequestParam Date end) {
        Date help1=beginning;
        Date help2=end;
        if(beginning.compareTo(end)==0){
            help1=null;
            help2=null;
        }
        Book book = new Book(id, title, author, genre, language, pubyear, publisher, owner_ID, help1, help2);
        return ResponseEntity.ok(ZSBService.addBook(book));
    }

    @ApiOperation(value = "Delete movie by id", notes = "delete book info by id")
    @DeleteMapping("/removeBook")
    public ResponseEntity<Void> deleteBook(@RequestParam Integer id) {
        ZSBService.deleteBook(id);
        return ResponseEntity.ok().build();
    }


    @ApiOperation(value = "Get book by title", notes = "get info about book by title")
    @GetMapping("/books/title/{title}")
    public ResponseEntity<Book> getBookByTitle(@PathVariable(value = "title") String title) {
        Book book = ZSBService.getBookByTitle(title);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(book);
    }

    @ApiOperation(value = "Get book by genre", notes = "get book by genre")
    @GetMapping("/books/genre/{genre}")
    public ResponseEntity<List<Book>> getBookByGenre(@PathVariable(value = "genre") Genres genre) {
        List<Book> book = ZSBService.getBookByGenre(genre);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(book);
    }
    @ApiOperation(value = "Book a book", notes = "Book a book from the library")
    @PostMapping("/books/{id}/rent")
    public ResponseEntity<Book> rentBook(@PathVariable(value = "id") Integer id, @RequestBody RentDTO rentDTO) {
        Book book = zsbRepository.findById(id).orElse(null);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        if (!book.isAvailable()) {
            return ResponseEntity.badRequest().body(book);
        }
        Date beginning = rentDTO.getBeginning();
        int days = rentDTO.getDays();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginning);
        calendar.add(Calendar.DATE, days);
        Date end = calendar.getTime();
        book.setBeginning(beginning);
        book.setEnd(end);
        book.setAvailable(false);
        zsbRepository.save(book);
        Rent rent = new Rent(rentDTO.getMail(), book);
        rentRepository.save(rent);
        return ResponseEntity.ok().body(book);
    }
    @ApiOperation(value = "Admin set book available", notes = "Allows admin to set a book available")
    @PostMapping("/books/{id}/return")
    public ResponseEntity<Book> setBookAvailable(@PathVariable(value = "id") Integer id) {
        Book book = zsbRepository.findById(id).orElse(null);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        book.setAvailable(true);
        zsbRepository.save(book);
        return ResponseEntity.ok().body(book);
    }
    @ApiOperation(value = "Get all rents", notes = "Get all rents")
    @GetMapping("/rents")
    public ResponseEntity<List<Rent>> getAllRents() {
        return ResponseEntity.ok(rentRepository.findAll());
    }

    @ApiOperation(value = "Get all rents by mail", notes = "Get all rents by mail")
    @GetMapping("/rents/{mail}")
    public ResponseEntity<List<Rent>> getAllRentsByMail(@PathVariable(value = "mail") String mail) {
        return ResponseEntity.ok(Collections.singletonList(rentRepository.findAllByMail(mail)));
    }

//    @ApiOperation(value = "Get all rents by book", notes = "Get all rents by book")
//    @GetMapping("/rents/{book}")
//    public ResponseEntity<List<Rent>> getAllRentsByBook(@PathVariable(value = "book") Book book) {
//        return ResponseEntity.ok(Collections.singletonList(rentRepository.findAllByBook(book)));
//    }


}

