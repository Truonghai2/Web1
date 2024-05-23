package Controll.Dao;

import java.util.List;

import Controll.Entity.Comment;

public interface CommentDao {

	List<Comment> findByUser(String email);

	List<Comment> findByMovieId(Integer movieId);

	Comment findByUserIdAndMovieId(Integer userId, Integer movieId);

	Comment findByMovieIdGetUser(Integer movieId);

	Comment create(Comment entity);

}
