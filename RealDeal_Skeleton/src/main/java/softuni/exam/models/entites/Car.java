package softuni.exam.models.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    @Column
    @Size(min = 2, max = 19)
    private String make;
    @Column
    @Size(min = 2, max = 19)
    private String model;
    @Column
    @Min(1)
    private Integer kilometers;
    @Column(name = "registered_on")
    private String registeredOn;
    @OneToMany(mappedBy = "car")
    private List<Picture> pictures;
    @OneToMany(mappedBy = "car")
    private List<Offer> offers;

    public Car(){

    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getKilometers() {
        return kilometers;
    }

    public void setKilometers(Integer kilometers) {
        this.kilometers = kilometers;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(String registeredOn) {
        this.registeredOn = registeredOn;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
