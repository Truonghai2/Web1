package Controll.Service.Impl;

import java.util.List;

import Controll.DTO.MovieLikedInfo;
import Controll.Dao.StatsDao;
import Controll.Dao.Impl.StatsDaoImpl;
import Controll.Service.StatsService;

public class StatsServiceImpl implements StatsService {

    private StatsDao dao;

    public StatsServiceImpl() {
        dao = new StatsDaoImpl();
    }

    @Override
    public List<MovieLikedInfo> findMovieLikedInfo() {
        return dao.findMovieLikeInfo();
    }

}
