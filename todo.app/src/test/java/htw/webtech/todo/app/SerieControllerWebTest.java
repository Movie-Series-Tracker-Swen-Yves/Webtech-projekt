package htw.webtech.todo.app;

import htw.webtech.todo.app.controller.SerieController;
import htw.webtech.todo.app.service.SerieService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SerieController.class)
class SerieControllerWebTest {

    @Autowired MockMvc mvc;
    @MockBean SerieService serieService;

    @Test
    void postSerie_formUrlEncoded_redirectsToHome() throws Exception {
        mvc.perform(post("/serie")
                        .param("title", "Ahsoka")
                        .param("season", "1")
                        .param("episode", "3")
                        .param("minutes", "45")
                        .param("notes", "Ganz okay"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}
