package orif.poc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import orif.poc.model.Article;
import orif.poc.service.ArticleService;

@Controller
@RequestMapping(path = "/article")
public class MainController {
    @Autowired
    public ArticleService articleService;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewArticle(@RequestParam String title, @RequestParam String body) {
        articleService.addNewArticle(title, body);
        return "Article added";
    }

    @GetMapping(path = "/get-all")
    public @ResponseBody Iterable<Article> getAllArticle() {
        return articleService.getAllArticle();
    }

    @PostMapping(path = "/get-by-id")
    public @ResponseBody Optional<Article> getById(@RequestParam Integer id) {
        return articleService.getById(id);
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteById(@RequestParam Integer id) {
        articleService.deleteArticle(id);
        return "Article deleted";
    }

    @PostMapping(path = "/update")
    public @ResponseBody String updateArticle(@RequestParam Integer id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String body) {

        // Ensure at least one of title or body is provided
        if ((title == null || title.isEmpty()) && (body == null || body.isEmpty())) {
            return "Error: Either title or body must be provided!";
        }

        // Update the article and check the result
        if (articleService.updateArticle(id, title, body) == 0) {
            return "Article updated successfully";
        } else {
            return "An error occurred!";
        }
    }
}
