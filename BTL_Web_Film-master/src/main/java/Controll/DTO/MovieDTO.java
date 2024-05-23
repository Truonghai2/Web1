package Controll.DTO;

import java.sql.Timestamp;
import java.util.List;


import Controll.Entity.Category;
import Controll.Entity.Movie;

public class MovieDTO {
    private int id;
    private String title;
    private String href1;
    private String href2;
    private String href3;
    private String poster;
    private int views;
    private int shares;
    private String description;
    private String daodien;
    private String dienvien;
    private String mota;
    private int price;
    private Boolean isActive;
    private Timestamp addDate;
    private Object hoadon; // Replace Object with the actual type
    private Object episodes; // Replace Object with the actual type
    private List<String> categoryNames;
    

    // Getters và Setters
    
    public MovieDTO() {};
    
    
    
    
    public MovieDTO(int id, String title, String href1, String href2, String href3, String poster, int views,
            int shares, String description, String daodien, String dienvien, String mota, int price,
            Boolean isActive, Timestamp addDate, Object hoadon, Object episodes, List<String> categoryNames) {
				this.id = id;
				this.title = title;
				this.href1 = href1;
				this.href2 = href2;
				this.href3 = href3;
				this.poster = poster;
				this.views = views;
				this.shares = shares;
				this.description = description;
				this.daodien = daodien;
				this.dienvien = dienvien;
				this.mota = mota;
				this.price = price;
				this.isActive = isActive;
				this.addDate = addDate;
				this.hoadon = hoadon;
				this.episodes = episodes;
				this.categoryNames = categoryNames;
			}

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
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

    public Object getHoadon() {
        return hoadon;
    }

    public void setHoadon(Object hoadon) {
        this.hoadon = hoadon;
    }

    public Object getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Object episodes) {
        this.episodes = episodes;
    }

    public List<String> getCategoryNames() {
        return categoryNames;
    }

    
    
    public void setCategoryNames(List<String> categoryNames) {
        this.categoryNames = categoryNames;
    }
    
    
    
    
    
    
}
