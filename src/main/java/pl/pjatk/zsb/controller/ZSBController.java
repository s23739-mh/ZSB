package pl.pjatk.zsb.controller;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.zsb.domain.Book;
import pl.pjatk.zsb.service.ZSBService;

import java.util.List;

@RestController
@RequestMapping("/db_library")
@Api("/api/tasks")
public class ZSBController {
    private final ZSBService ZSBService;

    public ZSBController(ZSBService ZSBService) {
        this.ZSBService = ZSBService;
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Book[].class),
            @ApiResponse(code = 400, message = "Bad request", response = Book.class)
    })
    @ApiOperation(value = "Return all zsb", notes = "info about all zsb")
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllMovies() {
        return ResponseEntity.ok(ZSBService.getAll());
    }
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Book[].class),
            @ApiResponse(code = 400, message = "Bad request", response = Book.class)
    })
    @ApiOperation(value = "Get example movie", notes = "get info about example movie")
    @GetMapping("/getexample")
    public ResponseEntity<Book> getExampleMovie() {
        return ResponseEntity.ok(ZSBService.getExampleBook());
    }
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Book[].class),
            @ApiResponse(code = 400, message = "Bad request", response = Book.class)
    })
    @ApiOperation(value = "Get movie by id",notes = "get info about movie by id")
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getMovieById(@ApiParam(value = "unique id of movie", example = "123") @PathVariable Integer id) {
        return ResponseEntity.ok(ZSBService.getBookById(id));
    }
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Book[].class),
            @ApiResponse(code = 400, message = "Bad request", response = Book.class)
    })
    @ApiOperation(value = "Add movie",notes = "add movie info")
    @PostMapping("/addbooks")
    public ResponseEntity<Book> addMovie(@RequestBody Book book) {
        return ResponseEntity.ok(ZSBService.addBook(book));
    }
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Book[].class),
            @ApiResponse(code = 400, message = "Bad request", response = Book.class)
    })
    @ApiOperation(value = "Update movie", notes = "update movie info")
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateMovie(@ApiParam(value = "unique id of movie", example = "123")@PathVariable Integer id, @RequestBody Book book) {
        return ResponseEntity.ok(ZSBService.updateBook(id, book));
    }
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Book[].class),
            @ApiResponse(code = 400, message = "Bad request", response = Book.class)
    })
    @ApiOperation(value = "Delete movie by id",notes = "delete movie info by id")
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteMovie(@ApiParam(value = "unique id of movie", example = "123")@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Book[].class),
            @ApiResponse(code = 400, message = "Bad request", response = Book.class)
    })
    @ApiOperation(value = "Return movie", notes = "Make movie available after rental")
    @PutMapping("/makeAvailable/{id}")
    public ResponseEntity<Book> makeAvailable(@ApiParam(value = "unique id of movie", example = "123")@PathVariable Integer id) {
        return ResponseEntity.ok(ZSBService.makeAvailable(id));
    }
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Book[].class),
            @ApiResponse(code = 400, message = "Bad request", response = Book.class)
    })
    @ApiOperation(value = "Rent movie", notes = "Rent movie")
    @PutMapping("/makeNotAvailable/{id}")
    public ResponseEntity<Book> makeNotAvailable(@ApiParam(value = "unique id of movie", example = "123")@PathVariable Integer id) {
        return ResponseEntity.ok(ZSBService.makeNotAvailable(id));
    }
}

