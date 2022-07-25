package courseproject.instashop.models;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private long id;

    private String title, anons, full_text, color;
    private double oldprice, newprice;
    private int size;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnons() {
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public String getFulltext() {
        return full_text;
    }

    public void setFulltext(String full_text) {
        this.full_text = full_text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getOldprice() {
        return oldprice;
    }

    public void setOldprice(double oldprice) {
        this.oldprice = oldprice;
    }

    public double getNewprice() {
        return newprice;
    }

    public void setNewprice(double newprice) {
        this.newprice = newprice;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
