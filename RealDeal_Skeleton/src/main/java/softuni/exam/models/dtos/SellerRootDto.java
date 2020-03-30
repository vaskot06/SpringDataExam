package softuni.exam.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sellers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerRootDto {

    @XmlElement(name = "seller")
    private List<SellerDto> sellers;

    public SellerRootDto() {
    }

    public List<SellerDto> getSellers() {
        return sellers;
    }

    public void setSellers(List<SellerDto> sellers) {
        this.sellers = sellers;
    }
}
