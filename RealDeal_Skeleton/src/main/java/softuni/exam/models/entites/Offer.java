package softuni.exam.models.entites;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {

    @Column
    @Min(1)
    private double price;
    @Column
    @Size(min = 5)
    private String description;
    @Column(name = "has_gold_status")
    private Boolean hasGoldStatus;
    @Column(name = "added_on")
    private String addedOn;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @ManyToMany
    @JoinTable(
            name = "offers_pictures",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id"))
    private List<Picture> pictures;


    public Offer() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean hasGoldStatus() {
        return hasGoldStatus;
    }

    public void setHasGoldStatus(Boolean hasGoldStatus) {
        this.hasGoldStatus = hasGoldStatus;
    }

    public String  getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String  addedOn) {
        this.addedOn = addedOn;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
}
