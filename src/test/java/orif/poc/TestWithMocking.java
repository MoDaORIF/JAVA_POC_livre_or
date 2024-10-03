package orif.poc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import orif.poc.controller.MainController;
import orif.poc.controller.WithMockingController;
import orif.poc.service.ArticleService;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
public class TestWithMocking {
    WithMockingController withMocking = new WithMockingController();
    MainController mainController = new MainController();
    ArticleService articleService = new ArticleService();

    private AutoCloseable closeable;

    @BeforeEach
    void setup() {
        System.out.println("Before");
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        System.out.println("After");
        closeable.close();
    }

    @BeforeAll
    public static void beforeClass() {
        System.out.println("Before Class");
    }

    @AfterAll
    public static void afterClass() {
        System.out.println("After Class");
    }

    @Test
    public void test_return_value_is_1() {
        System.out.println("SHOULD BE TRUE");
        assertEquals(1, withMocking.withMocking());
    }

    @Test
    public void test_add_new_user() {
        // Mock the ArticleService
        ArticleService articleServiceMock = mock(ArticleService.class);

        // Inject the mocked service into the controller
        MainController mainController = new MainController();
        mainController.articleService = articleServiceMock;

        // Define input parameters
        String title = "New Article Title";
        String body = "New Article Body";

        // Stub the void method to do nothing
        doNothing().when(articleServiceMock).addNewArticle(title, body);

        // Call the method we want to test
        String response = mainController.addNewArticle(title, body);

        // Verify that the ArticleService's addNewArticle method was called with correct
        // parameters
        verify(articleServiceMock).addNewArticle(title, body);

        // Assert the expected result from the controller
        assertEquals("Article added", response);
    }
}
