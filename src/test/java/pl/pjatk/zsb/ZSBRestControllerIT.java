package pl.pjatk.zsb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@SpringBootTest
@AutoConfigureMockMvc
public class ZSBRestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnSuccess_exampleBook() throws Exception {
        mockMvc.perform(get("/db_library/getexample"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":10,\"title\":\"noname\",\"author\":\"idk\",\"genre\":\"HORROR\",\"language\":\"polish\",\"pubyear\":2010,\"publisher\":\"pjatk\",\"available\":true}"));
    }
}
