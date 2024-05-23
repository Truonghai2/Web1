package Controll.Dao;

import java.util.List;

import Controll.Entity.Episode;

public interface EpisodeDao {

	Episode findById(Integer id);
	
	List<Episode> findByMovieId(Integer id);

	Episode findByHref(String href);

	List<Episode> findAll();

	List<Episode> findAllMovieDelete();

	List<Episode> findByName(String name);

	List<Episode> findAll(int pageNumber, int pageSize);

	List<Episode> findAllMovieDelete(int pageNumber, int pageSize);

	List<Episode> findMovieTrending();

	Episode create(Episode entity);

	Episode update(Episode entity);

	Episode delete(Episode entity);

}
