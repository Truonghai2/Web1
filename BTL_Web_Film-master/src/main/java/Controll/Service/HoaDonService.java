package Controll.Service;

import java.util.List;

import Controll.Entity.Hoadon;
import Controll.Entity.Movie;
import Controll.Entity.User;

public interface HoaDonService {

	List<Hoadon> findAll();

	List<Hoadon> findHoadonSuccess();

	List<Hoadon> findByEmail(String email);

	Hoadon findHoaDonByUserIdAndMovieId(int userId, int movieId);

	Hoadon create(String TxnRef, Movie movie, User user, String OrderInfo, String ResponseCode,
			String TransactionNo, String BankCode, String Amount);

	Hoadon remove(int userId, int MovieId);
}
