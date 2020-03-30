package softuni.exam.models.entites;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "sellers")
public class Seller extends BaseEntity {

    @Column(name = "first_name")
    @Size(min = 2, max = 19)
    private String firstName;
    @Column(name = "last_name")
    @Size(min = 2, max = 19)
    private String lastName;
    @Column
    @Pattern(regexp = "[\\w]+@[\\w]+\\..*")
    private String email;
    @Column(name = "rating", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Rating rating;
    @Column(nullable = false)
    private String town;
    @OneToMany(mappedBy = "seller")
    private List<Offer> offers;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
