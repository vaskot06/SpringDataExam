package softuni.exam.models.entites;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {


    @Column(unique = true)
    @Size(min = 2, max = 19)
    private String name;
    @Column(name = "date_and_time")
    private String dateAndTime;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToMany(mappedBy = "pictures")
    private List<Offer> offers;

    public Picture() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
