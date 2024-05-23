package Controll.Dao.Impl;

import java.util.List;

import Controll.Dao.AbstactDao;
import Controll.Dao.CommentDao;
import Controll.Entity.Comment;

public class CommentDaoImpl extends AbstactDao<Comment> implements CommentDao {

	@Override
	public List<Comment> findByUser(String email) {
		String sql = "SELECT o FROM Comment o WHERE o.user.email = ?0 AND o.video.isActive = 1 ORDER BY o.commentDate DESC";
		return super.findMany(Comment.class, sql, email);
	}

	@Override
	public List<Comment> findByMovieId(Integer movie) {
		String sql = "SELECT o FROM Comment o WHERE o.video.id= ?0 AND o.video.isActive = 1 ORDER BY o.commentDate DESC";
		return super.findMany(Comment.class, sql, movie);
	}

	@Override
	public Comment findByMovieIdGetUser(Integer movieId) {
		String sql = "SELECT o FROM Comment o WHERE o.video.id = ?0 AND o.video.isActive = 1";
		return super.findOne(Comment.class, sql, movieId);
	}

	@Override
	public Comment findByUserIdAndMovieId(Integer userId, Integer movieId) {
		String sql = "SELECT o FROM Comment o WHERE o.user.id = ?0 AND o.video.id = ?1 AND o.video.isActive = 1";
		return super.findOne(Comment.class, sql, userId, movieId);
	}

}
