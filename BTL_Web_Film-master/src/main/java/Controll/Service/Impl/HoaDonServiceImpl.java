package Controll.Service.Impl;

import java.sql.Timestamp;
import java.util.List;

import Controll.Dao.HoaDonDao;
import Controll.Dao.Impl.HoaDonImpl;
import Controll.Entity.Hoadon;
import Controll.Entity.Movie;
import Controll.Entity.User;
import Controll.Service.HoaDonService;

public class HoaDonServiceImpl implements HoaDonService {

	HoaDonDao dao;

	public HoaDonServiceImpl() {
		dao = new HoaDonImpl();
	}

	@Override
	public List<Hoadon> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Hoadon> findHoadonSuccess() {
		return dao.findHoadonSuccess();
	}

	@Override
	public List<Hoadon> findByEmail(String email) {
		return dao.findByEmail(email);
	}

	@Override
	public Hoadon findHoaDonByUserIdAndMovieId(int userId, int movieId) {
		return dao.findHoaDonByUserIdAndMovieId(userId, movieId);
	}

	@Override
	public Hoadon create(String TxnRef, Movie movie, User user, String OrderInfo, String ResponseCode,
			String TransactionNo, String BankCode, String Amount) {
		Hoadon hoadon = new Hoadon();
		hoadon.setTxnRef(TxnRef);
		hoadon.setMovie(movie);
		hoadon.setUser(user);
		hoadon.setOrderInfo(OrderInfo);
		hoadon.setResponseCode(ResponseCode);
		hoadon.setTransactionNo(TransactionNo);
		hoadon.setBankCode(BankCode);
		hoadon.setAmount(Amount);
		hoadon.setPayDate(new Timestamp(System.currentTimeMillis()));
		return dao.create(hoadon);
	}

	@Override
	public Hoadon remove(int userId, int videoId) {
		Hoadon hoadon = findHoaDonByUserIdAndMovieId(userId, videoId);
		return dao.remove(hoadon);
	}

}
