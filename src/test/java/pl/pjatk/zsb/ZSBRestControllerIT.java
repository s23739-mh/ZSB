package pl.pjatk.zsb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.pjatk.zsb.domain.RentDTO;

import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@SpringBootTest
@AutoConfigureMockMvc
public class ZSBRestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnSuccess_exampleBook() throws Exception {
        mockMvc.perform(get("/db_library/books/example"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"title\":\"Harry Potter\",\"author\":\"J.K. Rowling\",\"genre\":" +
                        "\"HORROR\",\"language\":\"English\",\"pubyear\":2000,\"publisher\":\"Good\",\"owner_mail\":" +
                        "\"John Doe\",\"beginning\":null,\"end\":null}"));
    }

    @Test
    void shouldReturnSuccess_BookByTitle() throws Exception{
        mockMvc.perform(get("/db_library/books/title/test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":8,\"title\":\"test\",\"author\":\"test\",\"genre\":" +
                        "\"TEST\",\"language\":\"test\",\"pubyear\":1111,\"publisher\":\"test\",\"owner_mail\":" +
                        "\"test\",\"beginning\":null,\"end\":null}"));
    }

    @Test
    void shouldReturnSuccess_BooksByGenre() throws Exception{
        mockMvc.perform(get("/db_library/books/genre/HORROR"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(7)));
    }

    @Test
    void ShouldReturnSuccess_RentABook() throws Exception{
        RentDTO rentDTO = new RentDTO("test@example.com", new Date(), 7);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(rentDTO);
        mockMvc.perform(post("/db_library/books/8/rent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(8))
                .andExpect(jsonPath("$.title").value("test"))
                .andExpect(jsonPath("$.available").value(false));
    }

    @Test
    void ShouldReturnSuccess_ReturnABook() throws Exception{
        mockMvc.perform(post("/db_library/books/8/return"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":8,\"title\":\"test\",\"author\":\"test\",\"genre\":" +
                        "\"TEST\",\"language\":\"test\",\"pubyear\":1111,\"publisher\":\"test\",\"owner_mail\":" +
                        "null,\"beginning\":null,\"end\":null,\"available\":true}"));
    }
}
