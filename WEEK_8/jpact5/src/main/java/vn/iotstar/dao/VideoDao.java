package vn.iotstar.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAconfig;
import vn.iotstar.dao.impl.IVideoDao;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Video;

public class VideoDao implements IVideoDao {

	@Override
	public int count() {
		// TODO Auto-generated method stub
		EntityManager enma = JPAconfig.getEntityManager();

		String jpql = "SELECT count(v) FROM Video v";

		Query query = enma.createQuery(jpql);

		return ((Long) query.getSingleResult()).intValue();
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		// TODO Auto-generated method stub
		EntityManager enma = JPAconfig.getEntityManager();

		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);

		query.setFirstResult(page * pagesize);

		query.setMaxResults(pagesize);

		return query.getResultList();
	}

	@Override
	public List<Video> searchByTitleAndCateId(String title, int cateId) {
	    EntityManager enma = JPAconfig.getEntityManager();

	    // Tìm kiếm theo title và categoryId
	    String jpql = "SELECT v FROM Video v WHERE v.title LIKE :title AND v.category.id = :cateId";

	    TypedQuery<Video> query = enma.createQuery(jpql, Video.class);

	    // Thiết lập giá trị cho tham số title và cateId
	    query.setParameter("title", "%" + title + "%");
	    query.setParameter("cateId", cateId);

	    return query.getResultList();
	}

	@Override
	public Video findByTitle(String name) throws Exception {
		// TODO Auto-generated method stub
		EntityManager enma = JPAconfig.getEntityManager();

		String jpql = "SELECT v FROM Video v WHERE v.title =:title";

		try {

			TypedQuery<Video> query = enma.createQuery(jpql, Video.class);

			query.setParameter("title", name);

			Video video = query.getSingleResult();

			if (video == null) {

				throw new Exception("Video đã tồn tại");

			}

			return video;

		} finally {

			enma.close();

		}
	}

	@Override
	public List<Video> findAll() {
		// TODO Auto-generated method stub
		EntityManager enma = JPAconfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
		return query.getResultList();
	}

	@Override
	public Video findById(int videoid) {
		// TODO Auto-generated method stub
		EntityManager enma = JPAconfig.getEntityManager();
		Video video = enma.find(Video.class, videoid);
		return video;
	}

	@Override
	public void delete(int videoid) throws Exception {
		// TODO Auto-generated method stub
		EntityManager enma = JPAconfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			// Gọi phương thức để insert, update, delete
			Video video = enma.find(Video.class, videoid);
			if (video != null) {
				enma.remove(video);
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
	public void update(Video video) {
		// TODO Auto-generated method stub
		EntityManager enma = JPAconfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			enma.merge(video);
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
	public void insert(Video video) {
		// TODO Auto-generated method stub
		EntityManager enma = JPAconfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			enma.persist(video);
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
	public List<Video> findByCateId(int cateid) {
	    EntityManager enma = JPAconfig.getEntityManager();

	    // Tìm kiếm theo Category ID
	    String jpql = "SELECT v FROM Video v WHERE v.category.id = :cateid";

	    TypedQuery<Video> query = enma.createQuery(jpql, Video.class);

	    // Thiết lập giá trị cho tham số cateid
	    query.setParameter("cateid", cateid);

	    return query.getResultList();
	}
}
