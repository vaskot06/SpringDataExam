package softuni.exam.models.dtos;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferDto {

    @XmlElement
    @Size(min = 5)
    private String description;
    @XmlElement
    @Min(1)
    private double price;
    @XmlElement(name = "added-on")
    private String  addedOn;
    @XmlElement(name = "has-gold-status")
    private Boolean hasGoldStatus;
    @XmlElement(name = "car")
    private CarDto carDto;
    @XmlElement(name = "seller")
    private SellerXmlDto sellerXmlDto;

    public OfferDto() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String  getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String  addedOn) {
        this.addedOn = addedOn;
    }

    public Boolean hasGoldStatus() {
        return hasGoldStatus;
    }

    public void setHasGoldStatus(Boolean hasGoldStatus) {
        this.hasGoldStatus = hasGoldStatus;
    }

    public CarDto getCarDto() {
        return carDto;
    }

    public void setCarDto(CarDto carDto) {
        this.carDto = carDto;
    }

    public SellerXmlDto getSellerXmlDto() {
        return sellerXmlDto;
    }

    public void setSellerXmlDto(SellerXmlDto sellerXmlDto) {
        this.sellerXmlDto = sellerXmlDto;
    }
}
