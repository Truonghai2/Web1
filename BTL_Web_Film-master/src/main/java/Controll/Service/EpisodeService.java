package Controll.Service;

import java.util.List;

import Controll.Entity.Episode;

public interface EpisodeService {

	Episode findById(Integer id);
	
	List<Episode> findByMovieId(Integer id);

	Episode findByHref(String href);

	List<Episode> findAll();

	List<Episode> findAllMovieDelete();

	List<Episode> findByName(String name);

	List<Episode> findMovieTrending();

	List<Episode> findAll(int pageNumber, int pageSize);

	List<Episode> findAllMovieDelete(int pageNumber, int pageSize);

//	Episode create(String title, String href, String poster, String daodien, String dienvien, String theloai, String mota,
//			String price, String description);
//
//	Episode update(String title, String href, String daodien, String dienvien, String theloai, String mota, String price,
//			String description);
//
//	Episode updateDisabled(String title, String href, String daodien, String dienvien, String theloai, String mota,
//			String price, String description);
//
//	Episode RestoreVideo(String href);
//
//	Episode delete(String href);
//
//	Episode DeleteVideoRestore(String href);

}
