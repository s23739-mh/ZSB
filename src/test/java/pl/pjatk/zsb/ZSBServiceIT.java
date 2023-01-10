package pl.pjatk.zsb;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.pjatk.zsb.domain.Book;
import pl.pjatk.zsb.domain.Genres;
import pl.pjatk.zsb.repository.ZSBRepository;
import pl.pjatk.zsb.service.ZSBService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ZSBServiceIT {

    @MockBean
    private ZSBRepository zsbRepository;

    @Autowired
    private ZSBService zsbService;

    @Test
    void shouldFindById() {
        Mockito.when(zsbRepository.findById(any()))
                .thenReturn(Optional.of(new Book(10, "noname", "idk", Genres.HORROR, "polish", 2010, "pjatk", null, null, null)));

        Book byId = zsbService.findById(1);

        assertThat(byId).isNotNull();
    }

    @Test
    void shouldFindAll() {
        List<Book> books = new ArrayList<>();
        Book book = new Book(10, "noname", "idk", Genres.HORROR,"polish",2010,"pjatk",null,null,null);
        books.add(1, book);
        Mockito.when(zsbRepository.findAll()).thenReturn(books);

        List<Book> all = zsbService.getAll();

        assertThat(all).hasSize(books.size());

    }

    @Test
    void shouldGetEmptyZooList() {
        List<Book> zoos = List.of();
        Mockito.when(zsbRepository.findAll()).thenReturn(zoos);

        List<Book> all = zsbService.getAll();

        assertThat(all).isEmpty();

    }


}
