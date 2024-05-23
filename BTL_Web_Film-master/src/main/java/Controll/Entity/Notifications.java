package Controll.Entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notifications")
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = true)
    @JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
    private User user;

    @Column(name = "type")
    private Integer type;

    @Column(name = "content")
    private String content;

    @Column(name = "type_create")
    private Integer typeCreate = 1;

    @Column(name = "seen")
    private Integer seen;

    @Column(name="href", nullable = true)
    private String href;
    
    @Column(name = "isActive", nullable = true)
    private Integer isActive;

    @Column(name = "addDate")
    @CreationTimestamp
    private Timestamp addDate;

    // Getters and setters
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTypeCreate() {
        return typeCreate;
    }

    public void setTypeCreate(Integer typeCreate) {
        this.typeCreate = typeCreate;
    }

    public Integer getSeen() {
        return seen;
    }

    public void setSeen(Integer seen) {
        this.seen = seen;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setHref(String href) {
    	this.href = href;
    }
    public String getHref() {
    	return href;
    }
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Timestamp getAddDate() {
        return addDate;
    }

    public void setAddDate(Timestamp addDate) {
        this.addDate = addDate;
    }
    
   
}
