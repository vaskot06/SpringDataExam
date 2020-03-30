package softuni.exam.models.dtos;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

public class CarJsonDto {

    @Expose
    @Size(min = 2, max = 19)
    private String make;
    @Expose
    @Size(min = 2, max = 19)
    private String model;
    @Expose
    @Min(1)
    private Integer kilometers;
    @Expose
    private String registeredOn;

    public CarJsonDto() {
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
}
