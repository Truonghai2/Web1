package Controll.Controller.Admin;

import java.io.IOException;
import java.util.List;

import Controll.DTO.MovieLikedInfo;
import Controll.Entity.Hoadon;
import Controll.Entity.User;
import Controll.Service.HoaDonService;
import Controll.Service.StatsService;
import Controll.Service.UserService;
import Controll.Service.Impl.HoaDonServiceImpl;
import Controll.Service.Impl.StatsServiceImpl;
import Controll.Service.Impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({ "/dashboarh", "/likeonemovie", "/userlike", "/usershare", "/doanhthu","/notifications"  })
public class HomeControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StatsService statsService = new StatsServiceImpl();
	private UserService userService = new UserServiceImpl();
	private HoaDonService hoadonService = new HoaDonServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();

		switch (path) {
		case "/dashboarh":
			doGetDashBoarh(request, response);
			break;
		case "/likeonemovie":
			doGetListMovieLike(request, response);
			break;
		case "/userlike":
			doGetUserLike(request, response);
			break;
		case "/usershare":
			doGetUserShare(request, response);
			break;
		case "/doanhthu":
			doGetDoanhThu(request, response);
			break;
		case "/notifications":
			doGetNotifacations(request, response);
			break;
		}
	}
	
	protected  void doGetNotifacations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		List<Notifications> notifications = notificationService.getAllNotifications();
		List<User> user = userService.findAll();
		

//		request.setAttribute("notifications", notifications);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/views/admin/notifications.jsp").forward(request, response);
	}

	protected void doGetDashBoarh(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Hoadon> hoadon = hoadonService.findAll();
		List<Hoadon> hoadonSuccess = hoadonService.findHoadonSuccess();

		int totalPrice = 0;
		for (Hoadon hd : hoadonSuccess) {
			String vnp_Aumout = hd.getAmount();
			int price = Integer.parseInt(vnp_Aumout);

			totalPrice += price;
		}

		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("hoadon", hoadon);
		request.getRequestDispatcher("/views/admin/dashboarh.jsp").forward(request, response);
	}

	protected void doGetListMovieLike(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<MovieLikedInfo> likeInfo = statsService.findMovieLikedInfo();
		request.setAttribute("countlike", likeInfo);
		request.getRequestDispatcher("/views/admin/list-likeMovie.jsp").forward(request, response);
	}

	protected void doGetUserLike(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer movieId = Integer.parseInt(request.getParameter("href"));

		List<MovieLikedInfo> movie = statsService.findMovieLikedInfo();

		if (movieId == null) {
			request.setAttribute("movie", movie);
		} else {
			List<User> user = userService.findUserLikedMovieByMovieId(movieId);
			request.setAttribute("user", user);
		}

		request.setAttribute("movieId", movieId);
		request.setAttribute("movie", movie);
		request.getRequestDispatcher("/views/admin/list-userLike.jsp").forward(request, response);
	}

	protected void doGetUserShare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer movieId = Integer.parseInt(request.getParameter("href"));

		List<MovieLikedInfo> movie = statsService.findMovieLikedInfo();

		if (movieId == null) {
			request.setAttribute("movie", movie);
		} else {
			List<User> user = userService.UserShareMovieById(movieId);
			request.setAttribute("user", user);
		}

		request.setAttribute("movieHref", movieId);
		request.setAttribute("movie", movie);
		request.getRequestDispatcher("/views/admin/list-userShare.jsp").forward(request, response);
	}

	protected void doGetDoanhThu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer movieId = Integer.parseInt(request.getParameter("href"));

		List<MovieLikedInfo> movie = statsService.findMovieLikedInfo();

		if (movieId == null) {
			request.setAttribute("movie", movie);
		} else {
			List<User> paymentVnpay = userService.UserPaymentVnpayById(movieId);
			request.setAttribute("paymentVnpay", paymentVnpay);
		}

		request.setAttribute("movieHref", movieId);
		request.setAttribute("movie", movie);
		request.getRequestDispatcher("/views/admin/DoanhThu.jsp").forward(request, response);
	}

}
