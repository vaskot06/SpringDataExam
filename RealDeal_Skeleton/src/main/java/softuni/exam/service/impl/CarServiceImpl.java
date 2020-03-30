package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.CarJsonDto;
import softuni.exam.models.entites.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class CarServiceImpl implements CarService {

    public static final String CAR_PATH = "C:\\Users\\Vasil\\Desktop\\Real Deal_Skeleton\\RealDeal_Skeleton\\src\\main\\resources\\files\\json\\cars.json";

    private final FileUtil fileUtil;
    private final CarRepository carRepository;
    private Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public CarServiceImpl(FileUtil fileUtil, CarRepository carRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.fileUtil = fileUtil;
        this.carRepository = carRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return this.fileUtil.readFile(CAR_PATH);
    }

    @Override
    public String importCars() throws IOException {


        StringBuilder stringBuilder = new StringBuilder();

        CarJsonDto[] carJsonDto = this.gson.fromJson(readCarsFileContent(), CarJsonDto[].class);

        for (CarJsonDto jsonDto : carJsonDto) {
            Car car = this.modelMapper.map(jsonDto, Car.class);

            if (!this.validationUtil.isValid(car)) {
                stringBuilder.append("Invalid car").append(System.lineSeparator());
                continue;
            }
            this.carRepository.saveAndFlush(car);
            stringBuilder.append("Successfully imported car - ").append(car.getMake())
                    .append(" - ").append(car.getModel()).append(System.lineSeparator());

        }

        return stringBuilder.toString().trim();
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        return null;
    }
}
