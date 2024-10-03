package orif.poc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import orif.poc.controller.MainController;
import orif.poc.service.ArticleService;

@AutoConfigureMockMvc(addFilters = false) // Disable security filters like CSRF
@ExtendWith(MockitoExtension.class)
@WebMvcTest(MainController.class)
public class TestAPIWithMocking {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleService articleService;

    @InjectMocks
    private MainController mainController;

    @Test
    public void testAddNewArticle() throws Exception {
        String title = "Test Title";
        String body = "Test Body";

        // Mock the service to do nothing when addNewArticle is called
        doNothing().when(articleService).addNewArticle(title, body);

        // Perform a POST request to /article/add
        mockMvc.perform(post("/article/add")
                .param("title", title)
                .param("body", body))
                .andExpect(status().isOk()) // Expect HTTP 200 OK
                .andExpect(content().string("Article added")); // Expect the response body to contain "Article added"

        // Verify that the service's addNewArticle method was called with the correct
        // parameters
        verify(articleService).addNewArticle(title, body);
    }
}
