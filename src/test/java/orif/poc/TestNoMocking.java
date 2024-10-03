package orif.poc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.context.annotation.Import;
import org.springframework.boot.test.context.SpringBootTest;

import orif.poc.controller.NoMockingController;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
public class TestNoMocking {
  NoMockingController noMocking = new NoMockingController();

  @BeforeEach
  public void before() {
    System.out.println("Before");
  }

  @AfterEach
  public void after() {
    System.out.println("After");
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
    assertEquals(1, noMocking.noMocking());
  }

  @Test
  public void test_return_value_is_not_1() {
    System.out.println("SHOULD BE FALSE");
    assertEquals(1, noMocking.noMocking());
  }
}
