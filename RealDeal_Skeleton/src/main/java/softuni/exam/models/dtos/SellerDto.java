package softuni.exam.models.dtos;

import softuni.exam.models.entites.Rating;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "seller")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerDto {

    @XmlElement(name = "first-name")
    @Size(min = 2, max = 19)
    private String firstName;
    @XmlElement(name = "last-name")
    @Size(min = 2, max = 19)
    private String lastName;
    @XmlElement
    @Pattern(regexp = "[\\w]+@[\\w]+\\..*")
    private String email;
    @XmlElement
    @Enumerated(value = EnumType.STRING)
    private Rating rating;
    @Column(nullable = false)
    private String town;

    public SellerDto() {
    }

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
}
