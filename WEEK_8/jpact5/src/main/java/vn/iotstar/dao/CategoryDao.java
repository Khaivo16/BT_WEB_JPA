package vn.iotstar.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAconfig;
import vn.iotstar.dao.impl.ICategoryDao;
import vn.iotstar.entity.Category;

public class CategoryDao implements ICategoryDao {
	@Override
	public void insert(Category category) {
		EntityManager enma = JPAconfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			enma.persist(category);
			trans.commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void update(Category category) {
		EntityManager enma = JPAconfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			enma.merge(category);
			trans.commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void delete(int cateid) throws Exception {
		EntityManager enma = JPAconfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			// Gọi phương thức để insert, update, delete
			Category category = enma.find(Category.class, cateid);
			if (category != null) {
				enma.remove(category);
			} else {
				throw new Exception("Không tìm thấy");
			}

			trans.commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public Category findById(int cateid) {
		EntityManager enma = JPAconfig.getEntityManager();
		Category category = enma.find(Category.class, cateid);
		return category;
	}

	@Override
	public List<Category> findAll() {
		EntityManager enma = JPAconfig.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
		return query.getResultList();
	}

	@Override
	public Category findByCategoryname(String name) throws Exception {

		EntityManager enma = JPAconfig.getEntityManager();

		String jpql = "SELECT c FROM Category c WHERE c.categoryname =:catename";

		try {

			TypedQuery<Category> query = enma.createQuery(jpql, Category.class);

			query.setParameter("catename", name);

			Category category = query.getSingleResult();

			if (category == null) {

				throw new Exception("Category Name đã tồn tại");

			}

			return category;

		} finally {

			enma.close();

		}

	}

	@Override
	public List<Category> searchByName(String catname) {

		EntityManager enma = JPAconfig.getEntityManager();

		String jpql = "SELECT c FROM Category c WHERE c.categoryname like :catname";

		TypedQuery<Category> query = enma.createQuery(jpql, Category.class);

		query.setParameter("catname", "%" + catname + "%");

		return query.getResultList();

	}


	@Override
	public List<Category> findAll(int page, int pagesize) {

		EntityManager enma = JPAconfig.getEntityManager();

		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);

		query.setFirstResult(page * pagesize);

		query.setMaxResults(pagesize);

		return query.getResultList();

	}


	@Override
	public int count() {

		EntityManager enma = JPAconfig.getEntityManager();

		String jpql = "SELECT count(c) FROM Category c";

		Query query = enma.createQuery(jpql);

		return ((Long) query.getSingleResult()).intValue();

	}
}
