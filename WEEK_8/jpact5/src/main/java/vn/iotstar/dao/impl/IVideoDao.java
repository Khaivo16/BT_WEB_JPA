package vn.iotstar.dao.impl;

import java.util.List;

import vn.iotstar.entity.Video;

public interface IVideoDao {
	int count();

	List<Video> findAll(int page, int pagesize);

	List<Video> searchByTitleAndCateId(String title, int cateId);

	Video findByTitle(String name) throws Exception;

	List<Video> findAll();

	Video findById(int videoid);
	
	List<Video> findByCateId(int cateid);

	void delete(int videoid) throws Exception;

	void update(Video video);

	void insert(Video video);
}
