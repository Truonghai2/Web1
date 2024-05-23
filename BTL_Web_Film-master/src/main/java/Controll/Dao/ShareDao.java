package Controll.Dao;

import java.util.List;

import Controll.Entity.Share;

public interface ShareDao {

	List<Share> findByUser(String username);
	
	Share findByUserIdAndMovieId(Integer userId, Integer movieId);

	Share create(Share entity);

}
