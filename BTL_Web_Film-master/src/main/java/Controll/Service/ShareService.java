package Controll.Service;

import java.util.List;

import Controll.Entity.Movie;
import Controll.Entity.Share;
import Controll.Entity.User;

public interface ShareService {

	List<Share> findByUser(String username);

	Share findByUserIdAndMovieId(Integer userId, Integer movieId);

	Share create(User user, Movie movie, String email);

}
