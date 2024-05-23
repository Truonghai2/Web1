package Controll.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Controll.Contanst.SessionAtrr;
import Controll.DTO.MovieDTO;
import Controll.Dao.UserDao;
import Controll.Dao.Impl.UserDaoImpl;
import Controll.Entity.History;
import Controll.Entity.Hoadon;
import Controll.Entity.Movie;
import Controll.Entity.User;
import Controll.Service.HistoryService;
import Controll.Service.HoaDonService;
import Controll.Service.MovieService;
import Controll.Service.UserService;
import Controll.Service.Impl.HistoryServiceImpl;
import Controll.Service.Impl.HoaDonServiceImpl;
import Controll.Service.Impl.MovieServiceImpl;
import Controll.Service.Impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet({ "/index", "/introduce", "/categories", "/favorites", "/history", "/search", "/verifyemail",
		"/transaction", "/errorpage" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final int VIDEO_MAX_PAGE_SIZE = 12;
	private UserDao dao = new UserDaoImpl();
	private HistoryService historyService = new HistoryServiceImpl();
	private UserService userService = new UserServiceImpl();
	private MovieService movieService = new MovieServiceImpl();
	private HoaDonService hoadonService = new HoaDonServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String path = request.getServletPath();

		switch (path) {
		case "/index":
			doGetIndex(request, response);
			break;
		case "/introduce":
			doGetInfo(request, response);
			break;
		case "/categories":
			doGetCategories(request, response);
			break;
		case "/favorites":
			doGetFavorites(session, request, response);
			break;
		case "/history":
			doGetHistory(session, request, response);
			break;
		case "/search":
			doGetSearch(request, response);
			break;
		case "/verifyemail":
			doGetVerifySuccess(request, response);
			break;
		case "/transaction":
			doGetTransactionHistory(session, request, response);
			break;
		case "/errorpage":
			doGetErrorPage(request, response);
			break;
		}
	}

	protected void doGetIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Movie> movieTrend = movieService.findMovieTrending();

		List<MovieDTO> countMovie = movieService.findAllMovies();
		System.out.println("movies" + countMovie);
		int maxPage = (int) Math.ceil(countMovie.size() / (double) VIDEO_MAX_PAGE_SIZE);
		request.setAttribute("maxPage", maxPage);

		String pageNumber = request.getParameter("page");

		List<MovieDTO> movies;
		if (pageNumber == null || Integer.valueOf(pageNumber) > maxPage) {
			movies = movieService.findAllMovies(1, VIDEO_MAX_PAGE_SIZE);
			request.setAttribute("currenPage", 1);
		} else {
			movies = movieService.findAllMovies(Integer.valueOf(pageNumber), VIDEO_MAX_PAGE_SIZE);
			request.setAttribute("currenPage", Integer.valueOf(pageNumber));
		}
		System.out.println("movies" + movies);

		request.setAttribute("movieTrend", movieTrend);
		request.setAttribute("movies", movies);
		request.getRequestDispatcher("/views/user/index.jsp").forward(request, response);
	}

	protected void doGetInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/user/introduce.jsp").forward(request, response);
	}

	protected void doGetCategories(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<MovieDTO> countMovie = movieService.findAllMovies();
		int maxPage = (int) Math.ceil(countMovie.size() / (double) VIDEO_MAX_PAGE_SIZE);
		request.setAttribute("maxPage", maxPage);

		String pageNumber = request.getParameter("page");

		List<MovieDTO> movies;
		if (pageNumber == null || Integer.valueOf(pageNumber) > maxPage) {
			movies = movieService.findAllMovies(1, VIDEO_MAX_PAGE_SIZE);
			request.setAttribute("currenPage", 1);
		} else {
			movies = movieService.findAllMovies(Integer.valueOf(pageNumber), VIDEO_MAX_PAGE_SIZE);
			request.setAttribute("currenPage", Integer.valueOf(pageNumber));
		}

		request.setAttribute("movies", movies);
		request.getRequestDispatcher("/views/user/categories.jsp").forward(request, response);

	}

	protected void doGetFavorites(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) session.getAttribute(SessionAtrr.CURRENT_USER);
		List<History> histories = historyService.findByUserAndIsLiked(user.getEmail());
		List<Movie> movies = new ArrayList<>();

		for (int i = 0; i < histories.size(); i++) {
			movies.add(histories.get(i).getMovie());
		}

		request.setAttribute("movies", movies);
		request.getRequestDispatcher("/views/user/favorites.jsp").forward(request, response);

	}

	protected void doGetHistory(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) session.getAttribute(SessionAtrr.CURRENT_USER);
		List<History> histories = historyService.findByUser(user.getEmail());
		List<Movie> movies = new ArrayList<>();

		int limit = 12;

		for (int i = 0; i < histories.size(); i++) {
			movies.add(histories.get(i).getMovie());

			if (movies.size() >= limit) {
				break;
			}
		}

		request.setAttribute("movies", movies);
		request.getRequestDispatcher("/views/user/history.jsp").forward(request, response);

	}

	protected void doGetSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nameMovie = request.getParameter("search");

		List<Movie> movieTrend = movieService.findMovieTrending();
		List<Movie> movies = movieService.findByName(nameMovie);

		request.setAttribute("movieTrend", movieTrend);
		request.setAttribute("movies", movies);
		request.getRequestDispatcher("/views/user/SearchMovie.jsp").forward(request, response);
	}

	protected void doGetVerifySuccess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String token = request.getParameter("token");
		User user = userService.findByToken(token);
		if (user != null) {
			String email = user.getEmail();
			request.setAttribute("email", email);
			user.setIsActive(Boolean.TRUE);
			dao.update(user);
		}
		request.getRequestDispatcher("/views/user/VerifySuccess.jsp").forward(request, response);
	}

	protected void doGetTransactionHistory(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) session.getAttribute(SessionAtrr.CURRENT_USER);
		if (user != null) {
			List<Hoadon> hoadon = hoadonService.findByEmail(user.getEmail());
			request.setAttribute("hoadon", hoadon);
			request.getRequestDispatcher("/views/user/Transactionhistory.jsp").forward(request, response);
		}
	}

	private void doGetErrorPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/404Page/404page.jsp").forward(request, response);
	}

}
