package Controll.Controller.Admin;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import Controll.DTO.MovieDTO;
import Controll.Entity.Category;
import Controll.Entity.Movie;
import Controll.Service.CategoryService;
import Controll.Service.MovieService;
import Controll.Service.Impl.CategoryServiceImpl;
import Controll.Service.Impl.MovieServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet({"/movieviews", "/movieadd", "/movieedit", "/moviedelete", "/moviedisabled", "/restoremovie", "/editmoviedisabled"})
public class MovieControllerAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final int VIDEO_MAX_PAGE_SIZE = 10;
    private MovieService movieService = new MovieServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();

        switch (path) {
            case "/movieviews":
                doGetViewsMovie(request, response);
                break;
            case "/moviedisabled":
                doGetMovieDisabled(request, response);
                break;
            case "/movieadd":
                doGetNewFilm(request, response);
                break;
            case "/movieedit":
                doGetEditFilm(request, response);
                break;
            case "/editmoviedisabled":
                doGetEditFilmDisable(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();

        switch (path) {
            case "/movieadd":
                doPostNewFilm(request, response);
                break;
            case "/movieedit":
                doPostEditFilm(request, response);
                break;
            case "/editmoviedisabled":
                doPostEditFilmDisable(request, response);
                break;
            case "/moviedelete":
                doPostDeleteFilm(request, response);
                break;
            case "/restoremovie":
                doPostRestoreMovie(request, response);
                break;
        }
    }

    //	danh sách movie
    protected void doGetViewsMovie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        
        System.out.println(movies);

        request.setAttribute("movies", movies);
        request.getRequestDispatcher("/views/admin/add-movie.jsp").forward(request, response);
    }

    //	danh sách ngưng công chiếu
    protected void doGetMovieDisabled(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Movie> countMovie = movieService.findAllMovieDelete();
        int maxPage = (int) Math.ceil(countMovie.size() / (double) VIDEO_MAX_PAGE_SIZE);
        request.setAttribute("maxPage", maxPage);

        String pageNumber = request.getParameter("page");

        List<MovieDTO> movies;
        if (pageNumber == null || Integer.valueOf(pageNumber) > maxPage) {
            movies = movieService.findAllMovieDelete(1, VIDEO_MAX_PAGE_SIZE);
            request.setAttribute("currenPage", 1);
        } else {
            movies = movieService.findAllMovieDelete(Integer.valueOf(pageNumber), VIDEO_MAX_PAGE_SIZE);
            request.setAttribute("currenPage", Integer.valueOf(pageNumber));
        }
        System.out.println(movies);

        request.setAttribute("movies", movies);
        request.getRequestDispatcher("/views/admin/DataMovieDisabled.jsp").forward(request, response);
    }

    //	thêm phim mới
    protected void doGetNewFilm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<Category> categories;
        categories = categoryService.findAll();
        System.out.print(categories);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/views/admin/newFilm.jsp").forward(request, response);
    }
            
    protected void doPostNewFilm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();

        String title = request.getParameter("title");
        String href1 = request.getParameter("href1");
        String href2 = request.getParameter("href2");
        String href3 = request.getParameter("href3");
        String poster = request.getParameter("poster");
        String daodien = request.getParameter("daodien");
        String dienvien = request.getParameter("dienvien");
        String[] categoryIds = request.getParameterValues("categoryIds");
        String mota = request.getParameter("mota");
        String price = request.getParameter("price");
        String noted = request.getParameter("noted");

        List<Category> categories = categoryService.findAll();
        request.setAttribute("categories", categories);

        if (isValid(title, href1, href2, href3, daodien, dienvien, mota, noted)) {
            try {
                Movie movieCreate = movieService.create(title, href1, href2, href3, poster, daodien, dienvien, categoryIds, mota, price, noted);
                if (movieCreate != null) {
                    session.setAttribute("addMovieSuccess", true);
                    response.sendRedirect("movieviews");
                    return;
                } else {
                    session.setAttribute("addMovieSuccess", false);
                    response.sendRedirect("movieviews");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace(); // Log the error for debugging
                request.setAttribute("error", "An error occurred while creating the movie.");
            }
        } else {
            request.setAttribute("error", "All fields are required.");
        }

        request.getRequestDispatcher("/views/admin/newFilm.jsp").forward(request, response);
    }

    private boolean isValid(String title, String href1, String href2, String href3, String daodien, String dienvien, String mota, String noted) {
        return title != null && !title.isEmpty() &&
               href1 != null && !href1.isEmpty() &&
               href2 != null && !href2.isEmpty() &&
               href3 != null && !href3.isEmpty() &&
               daodien != null && !daodien.isEmpty() &&
               dienvien != null && !dienvien.isEmpty() &&
               mota != null && !mota.isEmpty() &&
               noted != null && !noted.isEmpty();
    }


    
    //	chỉnh sửa phim
    protected void doGetEditFilm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer id = Integer.parseInt(request.getParameter("Id"));
        Movie movie = movieService.findById(id);
        List<Category> categories;
        categories = categoryService.findAll();
        System.out.print(categories);
        request.setAttribute("categories", categories);
        // format giá tiền
        int pice = movie.getPrice();
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedPrice = decimalFormat.format(pice);

        request.setAttribute("formattedPrice", formattedPrice);
        request.setAttribute("movie", movie);
        request.getRequestDispatcher("/views/admin/EditFilm.jsp").forward(request, response);
    }

    protected void doPostEditFilm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	Integer id = Integer.parseInt(request.getParameter("Id"));
    	String title = request.getParameter("title");
        String href1 = request.getParameter("href1");
        String href2 = request.getParameter("href2");
        String href3 = request.getParameter("href3");
        String poster = request.getParameter("poster");
        String daodien = request.getParameter("daodien");
        String dienvien = request.getParameter("dienvien");
        String[] categoryIds = request.getParameterValues("categoryIds");
        String mota = request.getParameter("mota");
        String price = request.getParameter("price");
        String noted = request.getParameter("noted");

        System.out.println("Received parameters: " + title + ", " + href1 + ", " + href2 + ", " + href3 + ", " + poster + ", " + daodien + ", " + dienvien + ", " + Arrays.toString(categoryIds) + ", " + mota + ", " + price + ", " + noted);

        
        if (isValid(title, href1, href2, href3, daodien, dienvien, mota, noted)) {
        	System.out.println(1);
            try {
            	System.out.println(2);
                Movie movieUpdate = movieService.update(id, title, href1, href2, href3, poster, daodien, dienvien, categoryIds, mota, price, noted);
                System.out.println(movieUpdate);
                System.out.println(3);
                if (movieUpdate != null) {
                    session.setAttribute("editMovieSuccess", true);
                    response.sendRedirect("movieviews");
                    return;
                } else {
                    session.setAttribute("editMovieSuccess", false);
                    response.sendRedirect("movieviews");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace(); // Log the error for debugging
                request.setAttribute("error", "An error occurred while creating the movie.");
            }
        } else {
            request.setAttribute("error", "All fields are required.");
        }

        request.getRequestDispatcher("/views/admin/EditFilm.jsp").forward(request, response);
    }

    //	chỉnh sửa phim ngưng hoạt động
    protected void doGetEditFilmDisable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String href = request.getParameter("href");
        Movie movie = movieService.findByHref(href);

        // format giá tiền
        int pice = movie.getPrice();
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedPrice = decimalFormat.format(pice);

        request.setAttribute("formattedPrice", formattedPrice);
        request.setAttribute("movie", movie);
        request.getRequestDispatcher("/views/admin/EditFilmDisabled.jsp").forward(request, response);
    }

    protected void doPostEditFilmDisable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String href = request.getParameter("href");
        String daodien = request.getParameter("daodien");
        String dienvien = request.getParameter("dienvien");
        String theloai = request.getParameter("category");
        String mota = request.getParameter("mota");
        String price = request.getParameter("price");
        String noted = request.getParameter("noted");

        if (title != null && href != null && daodien != null && dienvien != null && theloai != null && mota != null && noted != null) {

            Movie moviesUpdate = movieService.updateDisabled(title, href, daodien, dienvien, theloai, mota, price, noted);
            if (moviesUpdate != null) {
                response.sendRedirect("moviedisabled");
            }
        }
    }

    //	khôi phục movie đã xoá
    protected void doPostRestoreMovie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String href = request.getParameter("href");
        Movie movie = movieService.RestoreMovie(href);

        if (movie != null) {
            response.sendRedirect("moviedisabled");
        }
    }

    //	xoá phim
    protected void doPostDeleteFilm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer id = Integer.parseInt(request.getParameter("Id"));
        Movie movies = movieService.delete(id);

        if (movies != null) {
            response.sendRedirect("movieviews");
        }
    }

}
