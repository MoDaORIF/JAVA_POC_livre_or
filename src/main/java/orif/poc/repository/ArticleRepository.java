package orif.poc.repository;

import org.springframework.data.repository.CrudRepository;

import orif.poc.model.Article;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ArticleRepository extends CrudRepository<Article, Integer> {

}
