package Controll.Service.Impl;

import java.sql.Timestamp;
import java.util.List;

import Controll.Dao.CommentDao;
import Controll.Dao.Impl.CommentDaoImpl;
import Controll.Entity.Comment;
import Controll.Entity.Movie;
import Controll.Entity.User;
import Controll.Service.CommentService;

public class CommentServiceImpl implements CommentService {

	private CommentDao dao;

	public CommentServiceImpl() {
		dao = new CommentDaoImpl();
	}

	@Override
	public List<Comment> findByUser(String email) {
		return dao.findByUser(email);
	}

	@Override
	public List<Comment> findByMovieId(Integer movieId) {
		return dao.findByMovieId(movieId);
	}

	@Override
	public Comment findByMovieIdGetUser(Integer movieId) {
		return dao.findByMovieIdGetUser(movieId);
	}

	@Override
	public Comment findByUserIdAndMovieId(Integer userId, Integer movieId) {
		return dao.findByUserIdAndMovieId(userId, movieId);
	}

	@Override
	public Comment create(User user, Movie movie, String comment) {
		Comment cmt = findByUserIdAndMovieId(user.getId(), movie.getId());
		if (cmt == null) {
			cmt = new Comment();
			cmt.setUser(user);
			cmt.setMovie(movie);
			cmt.setComment(comment);
			cmt.setCommentDate(new Timestamp(System.currentTimeMillis()));
			return dao.create(cmt);

		}
		cmt = new Comment();
		cmt.setUser(user);
		cmt.setMovie(movie);
		cmt.setComment(comment);
		cmt.setCommentDate(new Timestamp(System.currentTimeMillis()));
		return dao.create(cmt);
	}


}
