package Controll.Dao;

import java.util.List;

import Controll.DTO.MovieLikedInfo;

public interface StatsDao {

	List<MovieLikedInfo> findMovieLikeInfo();
}
