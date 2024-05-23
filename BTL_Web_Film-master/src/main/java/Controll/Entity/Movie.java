package Controll.Entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Movie")
public class Movie {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "href1")
    private String href1;

    @Column(name = "href2")
    private String href2;

    @Column(name = "href3")
    private String href3;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
        name = "movie_categories",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();

    @Column(name = "poster")
    private String poster;

    @Column(name = "`views`")
    private Integer views;

    @Column(name = "shares")
    private Integer shares;

    @Column(name = "description")
    private String description;

    @Column(name = "daodien")
    private String daodien;

    @Column(name = "dienvien")
    private String dienvien;

    @Column(name = "mota")
    private String mota;

    @Column(name = "price")
    private int price;

    @Column(name = "isActive")
    private Boolean isActive;

    @Column(name = "addDate")
    private Timestamp addDate;

    @OneToMany(mappedBy = "movie")
    private List<Hoadon> hoadon;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Episode> episodes;

    // Constructors
    public Movie() {}

    public Movie(String title, String href1, String href2, String href3, String poster, String daodien, String dienvien, String mota, int price, String description) {
        this.title = title;
        this.href1 = href1;
        this.href2 = href2;
        this.href3 = href3;
        this.poster = poster;
        this.daodien = daodien;
        this.dienvien = dienvien;
        this.mota = mota;
        this.price = price;
        this.description = description;
        this.isActive = true;
        this.views = 0;
        this.shares = 0;
        this.addDate = new Timestamp(System.currentTimeMillis());
    }
   
    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    
    

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDaodien() {
        return daodien;
    }

    public void setDaodien(String daodien) {
        this.daodien = daodien;
    }

    public String getDienvien() {
        return dienvien;
    }

    public void setDienvien(String dienvien) {
        this.dienvien = dienvien;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Timestamp getAddDate() {
        return addDate;
    }

    public void setAddDate(Timestamp addDate) {
        this.addDate = addDate;
    }

    public List<Hoadon> getHoadon() {
        return hoadon;
    }

    public void setHoadon(List<Hoadon> hoadon) {
        this.hoadon = hoadon;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public void addCategory(Category category) {
        categories.add(category);
        category.getMovies().add(this);
    }
    
    public List<String> getCategoryNames() {
        return categories.stream().map(Category::getName).collect(Collectors.toList());
    }

    public void removeCategory(Category category) {
        categories.remove(category);
        category.getMovies().remove(this);
    }
}
