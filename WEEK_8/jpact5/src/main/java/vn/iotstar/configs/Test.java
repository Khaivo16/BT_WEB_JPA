package vn.iotstar.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.iotstar.entity.Category;

public class Test {
	public static void main(String[] args) {
		EntityManager enma = JPAconfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		Category cate = new Category();
		cate.setCategoryname("Iphone");
		cate.setImages("abc.jpg");
		cate.setStatus(1);
		try {
			trans.begin();
			enma.persist(cate);
			trans.commit();
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			trans.rollback();
			throw e;
		}
		finally {
			enma.close();
		}
	}
}
