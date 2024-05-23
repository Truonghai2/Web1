package Controll.Service;

import java.util.List;

import Controll.Entity.History;
import Controll.Entity.Movie;
import Controll.Entity.User;


public interface HistoryService {

	List<History> findByUser(String username);

	List<History> findByUserAndIsLiked(String username);

	History findByUserIdAndMovieId(Integer userId, Integer movieId);

	History create(User user, Movie movie);

	Boolean updateLikeOrUnLike(User userId, Integer movieId);

}
