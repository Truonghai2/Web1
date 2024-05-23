package Controll.Service;

import java.util.List;

import Controll.Entity.Comment;
import Controll.Entity.Movie;
import Controll.Entity.User;

public interface CommentService {

	List<Comment> findByUser(String username);

	List<Comment> findByMovieId(Integer movieId);

	Comment findByUserIdAndMovieId(Integer userId, Integer movieId);

	Comment findByMovieIdGetUser(Integer movieId);

	Comment create(User user, Movie movie, String comment);

}
