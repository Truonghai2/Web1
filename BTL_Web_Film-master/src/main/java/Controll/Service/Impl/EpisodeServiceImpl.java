package Controll.Service.Impl;

import java.sql.Timestamp;
import java.util.List;

import Controll.Dao.EpisodeDao;
import Controll.Dao.Impl.EpisodeDaoImpl;
import Controll.Entity.Episode;
import Controll.Service.EpisodeService;

public class EpisodeServiceImpl implements EpisodeService {

	private EpisodeDao dao;

	public EpisodeServiceImpl() {
		dao = new EpisodeDaoImpl();
	}

	@Override
	public Episode findById(Integer id) {
		return dao.findById(id);
	}
	
	@Override
	public List<Episode> findByMovieId(Integer id) {
		return dao.findByMovieId(id);
	}

	@Override
	public Episode findByHref(String href) {
		return dao.findByHref(href);
	}

	@Override
	public List<Episode> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Episode> findAllMovieDelete() {
		return dao.findAllMovieDelete();
	}

	@Override
	public List<Episode> findByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public List<Episode> findAll(int pageNumber, int pageSize) {
		return dao.findAll(pageNumber, pageSize);
	}

	@Override
	public List<Episode> findAllMovieDelete(int pageNumber, int pageSize) {
		return dao.findAllMovieDelete(pageNumber, pageSize);
	}

	@Override
	public List<Episode> findMovieTrending() {
		return dao.findMovieTrending();
	}


//	@Override
//	public Episode create(String title, String href, String poster, String daodien, String dienvien, String theloai,
//			String mota, String rawPrice, String description) {
//		Episode videosHref = findByHref(href);
//		if (videosHref == null) {
//			videosHref = new Episode();
//			String cleanPrice = rawPrice.replace(".", "");
//			int price = Integer.parseInt(cleanPrice);
//
//			videosHref.setTitle(title);
//			videosHref.setHref(href);
//			videosHref.setPoster(poster);
//			videosHref.setDaodien(daodien);
//			videosHref.setDienvien(dienvien);
//			videosHref.setTheloai(theloai);
//			videosHref.setMota(mota);
//			videosHref.setPrice(price);
//			videosHref.setDescription(description);
//			videosHref.setIsActive(Boolean.TRUE);
//			videosHref.setViews(0);
//			videosHref.setShares(0);
//			videosHref.setAddDate(new Timestamp(System.currentTimeMillis()));
//			return dao.create(videosHref);
//		}
//		return videosHref;
//	}
//
//	@Override
//	public Video update(String title, String href, String daodien, String dienvien, String theloai, String mota,
//			String rawPrice, String description) {
//		Video videosHref = findByHref(href);
//
//		int price = 0;
//		if (rawPrice.contains(",")) {
//			String cleanPrice = rawPrice.replace(",", "");
//			price = Integer.parseInt(cleanPrice);
//		} else {
//			String cleanPrice = rawPrice.replace(".", "");
//			price = Integer.parseInt(cleanPrice);
//		}
//
//		videosHref.setTitle(title);
//		videosHref.setDaodien(daodien);
//		videosHref.setDienvien(dienvien);
//		videosHref.setTheloai(theloai);
//		videosHref.setMota(mota);
//		videosHref.setPrice(price);
//		videosHref.setDescription(description);
//		videosHref.setIsActive(Boolean.TRUE);
//		return dao.update(videosHref);
//	}
//
//	@Override
//	public Video updateDisabled(String title, String href, String daodien, String dienvien, String theloai, String mota,
//			String rawPrice, String description) {
//		Video videosHref = findByHref(href);
//
//		int price = 0;
//		if (rawPrice.contains(",")) {
//			String cleanPrice = rawPrice.replace(",", "");
//			price = Integer.parseInt(cleanPrice);
//		} else {
//			String cleanPrice = rawPrice.replace(".", "");
//			price = Integer.parseInt(cleanPrice);
//		}
//
//		videosHref.setTitle(title);
//		videosHref.setDaodien(daodien);
//		videosHref.setDienvien(dienvien);
//		videosHref.setTheloai(theloai);
//		videosHref.setMota(mota);
//		videosHref.setPrice(price);
//		videosHref.setDescription(description);
//		videosHref.setIsActive(Boolean.FALSE);
//		return dao.update(videosHref);
//	}
//
//	@Override
//	public Video delete(String href) {
//		Video entity = findByHref(href);
//		entity.setIsActive(Boolean.FALSE);
//		return dao.update(entity);
//	}
//
//	@Override
//	public Video RestoreVideo(String href) {
//		Video video = findByHref(href);
//		video.setIsActive(Boolean.TRUE);
//		return dao.update(video);
//	}
//
//	@Override
//	public Video DeleteVideoRestore(String href) {
//		Video video = findByHref(href);
//		return dao.delete(video);
//	}

}
