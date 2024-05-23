package Controll.Service.Impl;

import java.sql.Timestamp;
import java.util.List;

import Controll.Dao.HistoryDao;
import Controll.Dao.Impl.HistoryDaoImpl;
import Controll.Entity.History;
import Controll.Entity.Movie;
import Controll.Entity.User;
import Controll.Service.HistoryService;
import Controll.Service.MovieService;

public class HistoryServiceImpl implements HistoryService {

	private HistoryDao dao;
	private MovieService movieService = new MovieServiceImpl();

	public HistoryServiceImpl() {
		dao = new HistoryDaoImpl();
	}

	@Override
	public List<History> findByUser(String email) {
		return dao.findByUser(email);
	}

	@Override
	public List<History> findByUserAndIsLiked(String email) {
		return dao.findByUserAndIsLiked(email);
	}

	@Override
	public History findByUserIdAndMovieId(Integer userId, Integer movieId) {
		return dao.findByUserIdAndMovieId(userId, movieId);
	}

	@Override
	public History create(User user, Movie movie) {
		History existHistory = findByUserIdAndMovieId(user.getId(), movie.getId());
		if (existHistory == null) {
			existHistory = new History();
			existHistory.setUser(user);
			existHistory.setMovie(movie);
			existHistory.setViewedDate(new Timestamp(System.currentTimeMillis()));
			existHistory.setIsLiked(Boolean.FALSE);
			dao.create(existHistory);
		}
		return existHistory;
	}

	@Override
	public Boolean updateLikeOrUnLike(User user, Integer movieId) {
		Movie movie = movieService.findById(movieId);
		History existHistory = findByUserIdAndMovieId(user.getId(), movie.getId());

		if (existHistory.getIsLiked() == Boolean.FALSE) {
			existHistory.setIsLiked(Boolean.TRUE);
			existHistory.setLikedDate(new Timestamp(System.currentTimeMillis()));
		} else {
			existHistory.setIsLiked(Boolean.FALSE);
			existHistory.setLikedDate(null);
		}
		History updateHistory = dao.update(existHistory);
		return updateHistory != null ? true : false;
	}

}
