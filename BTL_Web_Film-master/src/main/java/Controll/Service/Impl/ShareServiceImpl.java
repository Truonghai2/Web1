package Controll.Service.Impl;

import java.sql.Timestamp;
import java.util.List;

import Controll.Dao.ShareDao;
import Controll.Dao.Impl.ShareDaoImpl;
import Controll.Entity.Movie;
import Controll.Entity.Share;
import Controll.Entity.User;
import Controll.Service.ShareService;

public class ShareServiceImpl implements ShareService {

	private ShareDao dao;

	public ShareServiceImpl() {
		dao = new ShareDaoImpl();
	}

	@Override
	public List<Share> findByUser(String email) {
		return dao.findByUser(email);
	}

	@Override
	public Share findByUserIdAndMovieId(Integer userId, Integer movieId) {
		return dao.findByUserIdAndMovieId(userId, movieId);
	}

	@Override
	public Share create(User user, Movie movie, String email) {
		Share existShare = findByUserIdAndMovieId(user.getId(), movie.getId());
		if (existShare == null) {
			existShare = new Share();
			existShare.setUser(user);
			existShare.setMovie(movie);
			existShare.setEmail(email);
			existShare.setShareDate(new Timestamp(System.currentTimeMillis()));
			dao.create(existShare);
		}
		return existShare;
	}

}
