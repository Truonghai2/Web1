package Controll.Service;

import java.util.List;

import Controll.DTO.MovieLikedInfo;

public interface StatsService {
	List<MovieLikedInfo> findMovieLikedInfo();
}
