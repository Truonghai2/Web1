package Controll.Service.Impl;

import java.sql.Timestamp;
import java.util.List;

import Controll.Dao.RatingDao;
import Controll.Dao.Impl.RatingDaoImpl;
import Controll.Entity.Movie;
import Controll.Entity.Rating;
import Controll.Entity.User;
import Controll.Service.RatingService;

public class RatingServiceImpl implements RatingService {

	private RatingDao dao;

	public RatingServiceImpl() {
		dao = new RatingDaoImpl();
	}

	@Override
	public List<Rating> findByUser(String email) {
		return dao.findByEmail(email);
	}

	@Override
	public Rating findByUserIdAndMovieId(Integer userId, Integer movieId) {
		return dao.findByUserIdAndMovieId(userId, movieId);
	}

	@Override
	public Rating CreateAndUpdate(User user, Movie movie, int rating) {
		Rating existRating = findByUserIdAndMovieId(user.getId(), movie.getId());
		if (existRating != null) {
			existRating.setRating(rating);
			existRating.setRatingDate(new Timestamp(System.currentTimeMillis()));
			dao.update(existRating);
		} else {
			existRating = new Rating();
			existRating.setUser(user);
			existRating.setMovie(movie);
			existRating.setRating(rating);
			existRating.setRatingDate(new Timestamp(System.currentTimeMillis()));
			dao.create(existRating);
		}
		return existRating;
	}

}
