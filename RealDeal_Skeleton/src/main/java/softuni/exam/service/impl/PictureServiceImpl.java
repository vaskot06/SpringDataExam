package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.PictureJsonDto;
import softuni.exam.models.entites.Car;
import softuni.exam.models.entites.Picture;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;

@Service
public class PictureServiceImpl implements PictureService {

    public final static String PICTURE_PATH = "C:\\Users\\Vasil\\Desktop\\Real Deal_Skeleton\\RealDeal_Skeleton\\src\\main\\resources\\files\\json\\pictures.json";

    private final PictureRepository pictureRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final CarRepository carRepository;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, CarRepository carRepository) {
        this.pictureRepository = pictureRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.carRepository = carRepository;
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return this.fileUtil.readFile(PICTURE_PATH);
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        PictureJsonDto[] pictureJsonDtos = this.gson.fromJson(readPicturesFromFile(), PictureJsonDto[].class);


        for (PictureJsonDto pictureJsonDto : pictureJsonDtos) {

            Picture picture = this.modelMapper.map(pictureJsonDto, Picture.class);
            Car car = this.carRepository.findById(pictureJsonDto.getCar()).get();
            picture.setCar(car);
            if (!this.validationUtil.isValid(picture)) {
                stringBuilder.append("Invalid picture").append(System.lineSeparator());
                continue;
            }
            this.pictureRepository.saveAndFlush(picture);
            stringBuilder.append("Successfully imported picture - ").append(picture.getName())
                    .append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
