package courseproject.instashop.models;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private long id;

    private String region, city, street, status;
    private int streetnum, officenum;

    public double getSummary_price() {
        return summary_price;
    }

    public void setSummary_price(double summary_price) {
        this.summary_price = summary_price;
    }

    private double summary_price;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private long user_id, cart_id;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getCart_id() {
        return cart_id;
    }

    public void setCart_id(long cart_id) {
        this.cart_id = cart_id;
    }


    public Order(String region, String city, String street, int streetnum, int officenum) {
        this.region = region;
        this.city = city;
        this.street = street;
        this.streetnum = streetnum;
        this.officenum = officenum;
    }

    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetnum() {
        return streetnum;
    }

    public void setStreetnum(int streetnum) {
        this.streetnum = streetnum;
    }

    public int getOfficenum() {
        return officenum;
    }

    public void setOfficenum(int officenum) {
        this.officenum = officenum;
    }




}
