package pl.pjatk.zsb.controller;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.zsb.domain.*;
import pl.pjatk.zsb.service.ZSBService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/db_library")
@Api("/api/tasks")
public class ZSBController {
    private final ZSBService ZSBService;

    public ZSBController(ZSBService ZSBService) {
        this.ZSBService = ZSBService;
    }


    @GetMapping("/getFavourites")
    public ResponseEntity<List<Favourite>> getFavourites(@RequestParam String mail_user) {
        return ResponseEntity.ok(ZSBService.getFavourites(mail_user));
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
    public ResponseEntity<List<Book>> getAllMovies() {
        return ResponseEntity.ok(ZSBService.getAllBooks());
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Book[].class),
            @ApiResponse(code = 400, message = "Bad request", response = Book.class)
    })

    @ApiOperation(value = "Get movie by id", notes = "get info about movie by id")
    @GetMapping("/book")
    public ResponseEntity<Book> getMovieById(@ApiParam(value = "unique id of movie", example = "123") @RequestParam Integer id) {
        return ResponseEntity.ok(ZSBService.getBookById(id));
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Book[].class),
            @ApiResponse(code = 400, message = "Bad request", response = Book.class)
    })
    @ApiOperation(value = "Add movie", notes = "add movie info")
    @PostMapping("/addBook")
    public ResponseEntity<Book> addMovie(@RequestParam Integer id, @RequestParam String title, @RequestParam String author, @RequestParam Genres genre,
                                         @RequestParam String language, @RequestParam Integer pubyear, @RequestParam String publisher,
                                         @RequestParam String owner_ID, @RequestParam Date beginning, @RequestParam Date end) {
        Book book = new Book(id, title, author, genre, language, pubyear, publisher, owner_ID, beginning, end);
        return ResponseEntity.ok(ZSBService.addBook(book));
    }

    @ApiOperation(value = "Delete movie by id", notes = "delete movie info by id")
    @DeleteMapping("/removeBook")
    public ResponseEntity<Void> deleteBook(@RequestParam Integer id) {
        ZSBService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

}

