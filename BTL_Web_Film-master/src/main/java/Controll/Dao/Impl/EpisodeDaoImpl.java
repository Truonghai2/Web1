package Controll.Dao.Impl;

import java.util.List;

import Controll.Dao.AbstactDao;
import Controll.Dao.EpisodeDao;
import Controll.Entity.Episode;

public class EpisodeDaoImpl extends AbstactDao<Episode> implements EpisodeDao {

	@Override
	public Episode findById(Integer id) {
		return super.findById(Episode.class, id);
	}
	
	@Override
	public List<Episode> findByMovieId(Integer id) {
		String sql = "SELECT o FROM Episode o Where o.video.id = ?0";
		return super.findMany(Episode.class, sql, id);
	}

	@Override
	public Episode findByHref(String href) {
		String sql = "SELECT o FROM Episode o WHERE o.videoUrl = ?0";
		return super.findOne(Episode.class, sql, href);
	}

	@Override
	public List<Episode> findAll() {
		return super.findAll(Episode.class, true);
	}

	@Override
	public List<Episode> findAllMovieDelete() {
		return super.findAllMovieDelete(Episode.class, true);
	}

	@Override
	public List<Episode> findByName(String name) {
		String sql = "SELECT o FROM Episode o WHERE o.title LIKE ?0";
		return super.findMany(Episode.class, sql, "%" + name + "%");
	}

	@Override
	public List<Episode> findAll(int pageNumber, int pageSize) {
		return super.findAll(Episode.class, true, pageNumber, pageSize);
	}

	@Override
	public List<Episode> findAllMovieDelete(int pageNumber, int pageSize) {
		return super.findAllMovieDelete(Episode.class, true, pageNumber, pageSize);
	}

	@Override
	public List<Episode> findMovieTrending() {
		String jpql = "SELECT o FROM Episode o WHERE o.isActive = 1 ORDER BY o.addDate DESC";
		return super.findManyMaxResult(Episode.class, jpql, 4);
	}

}
