package Controll.Service;

import java.util.List;
import java.util.Set;

import Controll.DTO.MovieDTO;
import Controll.Entity.Category;
import Controll.Entity.Movie;

public interface MovieService {

	Movie findById(Integer id);

	Movie findByHref(String href);

	List<MovieDTO> findAllMovies();

	List<Movie> findAllMovieDelete();

	Movie findBySingleName(String name);

	List<Movie> findMovieTrending();

	List<MovieDTO> findAllMovies(int pageNumber, int pageSize);

	List<MovieDTO> findAllMovieDelete(int pageNumber, int pageSize);

	Movie create(String title, String href1, String href2, String href3, String poster, String daodien, String dienvien, String[] categoryIds,
            String mota, String rawPrice, String description);

	Movie update(Integer id, String title, String href1, String href2, String href3, String poster, String daodien, String dienvien, String[] categoryIds, String mota, String rawPrice,
			String description);

	Movie updateDisabled(String title, String href, String daodien, String dienvien, String theloai, String mota,
			String price, String description);

	Movie RestoreMovie(String href);

	Movie delete(Integer Id);

	Movie DeleteMovieRestore(String href);

}
