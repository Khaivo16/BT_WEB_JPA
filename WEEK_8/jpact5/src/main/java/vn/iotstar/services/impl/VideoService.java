package vn.iotstar.services.impl;
import java.util.List;
import vn.iotstar.dao.VideoDao;
import vn.iotstar.dao.impl.IVideoDao;
import vn.iotstar.entity.Video;
import vn.iotstar.services.IVideoService;

public class VideoService implements IVideoService {
	IVideoDao videoDao = new VideoDao();
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return videoDao.count();
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		// TODO Auto-generated method stub
		return videoDao.findAll(page, pagesize);
	}

	

	@Override
	public Video findByTitle(String name) throws Exception {
		// TODO Auto-generated method stub
		return videoDao.findByTitle(name);
	}

	@Override
	public List<Video> findAll() {
		// TODO Auto-generated method stub
		return videoDao.findAll();
	}

	@Override
	public Video findById(int videoid) {
		// TODO Auto-generated method stub
		return videoDao.findById(videoid);
	}

	@Override
	public void delete(int videoid) throws Exception {
		// TODO Auto-generated method stub
		videoDao.delete(videoid);
	}

	@Override
	public void update(Video video) {
		// TODO Auto-generated method stub
		videoDao.update(video);
	}

	@Override
	public void insert(Video video) {
		// TODO Auto-generated method stub
		videoDao.insert(video);
	}

	@Override
	public List<Video> findByCateId(int cateid) {
		// TODO Auto-generated method stub
		return videoDao.findByCateId(cateid);
	}

	@Override
	public List<Video> searchByTitleAndCateId(String title, int cateId) {
		// TODO Auto-generated method stub
		return videoDao.searchByTitleAndCateId(title, cateId);
	}

}
