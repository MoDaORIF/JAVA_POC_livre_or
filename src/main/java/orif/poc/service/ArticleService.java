package orif.poc.service;

import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import orif.poc.model.Article;
import orif.poc.repository.ArticleRepository;

@Service
public class ArticleService {

  @Autowired
  ArticleRepository articleRepository;

  // ╭─────────────────────────────────────────────────────────╮
  // │ CREATE │
  // ╰─────────────────────────────────────────────────────────╯

  public void addNewArticle(String title, String body) {

    Article n = new Article();
    n.setTitle(title);
    n.setBody(body);
    articleRepository.save(n);
  }

  // ╭─────────────────────────────────────────────────────────╮
  // │ READ │
  // ╰─────────────────────────────────────────────────────────╯

  /**
   * Get all article in the form of a json list
   * 
   *
   * @return Iterable<Article>: List of article in the form of a JSON
   */
  public Iterable<Article> getAllArticle() {
    return articleRepository.findAll();
  }

  /**
   * Find an article by its ID
   * 
   *
   * @param id
   * @return Optional<Article>: The article in a JSON format
   */
  public Optional<Article> getById(Integer id) {
    return articleRepository.findById(id);
  }

  // ╭─────────────────────────────────────────────────────────╮
  // │ Update │
  // ╰─────────────────────────────────────────────────────────╯

  public Integer updateArticle(Integer id, String title, String body) {

    Optional<Article> optionalArticle = articleRepository.findById(id);

    if (!optionalArticle.isPresent()) {
      // failure: no article found with this ID
      return 1;
    }

    Article article = optionalArticle.get();

    if (title != null && !title.isEmpty()) {
      article.setTitle(title);
    }
    if (body != null && !body.isEmpty()) {
      article.setBody(body);
    }

    articleRepository.save(article);
    // success
    return 0;
  }

  // ╭─────────────────────────────────────────────────────────╮
  // │ Delete │
  // ╰─────────────────────────────────────────────────────────╯

  /**
   * Deletes an article with the given ID
   *
   * @param id
   */
  public void deleteArticle(Integer id) {
    articleRepository.deleteById(id);
  }
}
