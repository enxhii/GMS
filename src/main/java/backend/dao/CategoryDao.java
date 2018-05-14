package backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import backend.model.Category;

@Repository
public class CategoryDao {
	final static Logger logger = LogManager.getLogger(CategoryDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	public List<Category> getCategory() {
		try {
			String sql = "select c from Category c";
			List<Category> lista = entityManager.createQuery(sql, Category.class).getResultList();
			return lista;

		} catch (Exception e) {
			logger.debug(e);
		}
		return null;
	}

	public void addCategory(Category category) {
		entityManager.persist(category);
	}
}
