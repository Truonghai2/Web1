package Controll.Filter;

import java.io.IOException;

import Controll.Contanst.SessionAtrr;
import Controll.Entity.Hoadon;
import Controll.Entity.User;
import Controll.Entity.Movie;
import Controll.Service.HoaDonService;
import Controll.Service.MovieService;
import Controll.Service.Impl.HoaDonServiceImpl;
import Controll.Service.Impl.MovieServiceImpl;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter({"/movie"})
public class MovieFilter implements Filter {

    HoaDonService hoadonService = new HoaDonServiceImpl();
    MovieService movieService = new MovieServiceImpl();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String actionParam = req.getParameter("action");

        if (actionParam == null) {
            resp.sendRedirect("errorpage");
        } else {
            switch (actionParam) {
                case "details":
                    chain.doFilter(req, resp);
                    break;
                case "like":
                    proFile(req, resp, chain);
                    break;
                case "watch":
                    checkOutNotPayment(req, resp, chain);
                    break;
            }
        }
    }

    private void proFile(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(SessionAtrr.CURRENT_USER);
        if (user == null) {
            resp.sendRedirect("errorpage");
        } else {
            chain.doFilter(req, resp);
        }
    }

    private void checkOutNotPayment(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        String href = req.getParameter("id");

        User user = (User) session.getAttribute(SessionAtrr.CURRENT_USER);
        Movie movie = movieService.findByHref(href);

        if (movie.getPrice() != 0 && user == null) {
            resp.sendRedirect("movie?action=details&id=" + href);
        } else if (movie.getPrice() != 0 && user != null && movie != null) {
            System.out.println("user id " + user.getId() + " movie id " + movie.getId());
            Hoadon hd = hoadonService.findHoaDonByUserIdAndMovieId(user.getId(), movie.getId());
            System.out.println(hd);
            if (hd == null) {
                resp.sendRedirect("movie?action=details&id=" + href);
            } else {
                if (hd.getResponseCode().equals("00")) {
                    chain.doFilter(req, resp);
                } else {
                    resp.sendRedirect("movie?action=details&id=" + href);
                }
            }
        } else {
            chain.doFilter(req, resp);
        }
    }

}
