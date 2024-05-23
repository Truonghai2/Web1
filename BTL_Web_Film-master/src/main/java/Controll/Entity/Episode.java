package Controll.Entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Episode")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "episodeNumber", nullable = false)
    private int episodeNumber;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "href1", nullable = false)
    private String href1;
    
    @Column(name = "href2", nullable = false)
    private String href2;
    
    @Column(name = "href3", nullable = false)
    private String href3;

    @ManyToOne
    @JoinColumn(name = "movieId", nullable = false)
    private Movie movie;

    @Column(name = "release_date", nullable = false)
    private Timestamp releaseDate;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHref1() {
		return href1;
	}

	public void setHref1(String href1) {
		this.href1 = href1;
	}
	
	public String getHref2() {
		return href2;
	}

	public void setHref2(String href2) {
		this.href2 = href2;
	}
	
	public String getHref3() {
		return href3;
	}

	public void setHref3(String href3) {
		this.href3 = href3;
	}

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }
}
