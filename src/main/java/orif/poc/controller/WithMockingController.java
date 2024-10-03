package orif.poc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/with")
public class WithMockingController {

  @GetMapping("/mocking")
  // This is an exemple function to showcase the unit testing without mocking
  public int withMocking() {
    return 1;
  }
}
