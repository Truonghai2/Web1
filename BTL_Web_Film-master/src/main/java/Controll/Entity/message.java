package Controll.Entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="message")
public class message {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "userId", referencedColumnName = "id")
    @JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
    private User user;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "roomId", referencedColumnName = "id")
    @JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
	private room room;
	
	
	private String body;
	
	@Column(name="link", nullable = true)
	private String link;
	
	@Column(name="created_at")
	@CreationTimestamp
	private Timestamp created_at;
	
	
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public room getRoom() {
    	return room;
    }
    public void setRoom(room room) {
    	this.room = room;
    }
    
    public String getBody() {
    	return body;
    }
    
    public void setBody(String body) {
    	this.body = body;
    }
    
    
    public String getLink() {
    	return link;
    }
    
    public void setLink(String link) {
    	this.link = link;
    }
    public Timestamp getAddDate() {
        return created_at;
    }

    public void setAddDate(Timestamp addDate) {
        this.created_at = addDate;
    }
    
    @Override
    public String toString() {
    	return "id" + id + '/' 
    			+ "user" + user + " / " + "room" + room + " / " + "body" + body + " / " + "link" + link;
    }
}
