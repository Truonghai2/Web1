package Controll.Service;

import java.util.List;

import Controll.Entity.Movie;
import Controll.Entity.Rating;
import Controll.Entity.User;

public interface RatingService {

	List<Rating> findByUser(String username);

	Rating findByUserIdAndMovieId(Integer userId, Integer movieId);

	Rating CreateAndUpdate(User user, Movie movie, int rating);
	
}
