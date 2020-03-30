package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.OfferDto;
import softuni.exam.models.dtos.OffersRootDto;
import softuni.exam.models.entites.Offer;
import softuni.exam.parsers.XmlParser;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidationUtil;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class OfferServiceImpl implements OfferService {

    private final static String PATH = "C:\\Users\\Vasil\\Desktop\\Real Deal_Skeleton\\RealDeal_Skeleton\\src\\main\\resources\\files\\xml\\offers.xml";

    private final OfferRepository offerRepository;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final CarRepository carRepository;
    private final SellerRepository sellerRepository;
    private final ValidationUtil validationUtil;


    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, FileUtil fileUtil, XmlParser xmlParser, ModelMapper modelMapper, CarRepository carRepository, SellerRepository sellerRepository, ValidationUtil validationUtil) {
        this.offerRepository = offerRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.carRepository = carRepository;
        this.sellerRepository = sellerRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return this.fileUtil.readFile(PATH);
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        OffersRootDto offersRootDto = this.xmlParser.read(OffersRootDto.class, PATH);

        for (OfferDto offerDto : offersRootDto.getOffers()) {

            Offer offer = this.modelMapper.map(offerDto, Offer.class);

            if (!this.validationUtil.isValid(offer)) {
                stringBuilder.append("Invalid offer").append(System.lineSeparator());
                continue;
            }

            offer.setCar(this.carRepository.findById(offerDto.getCarDto().getId()).orElse(null));
            offer.setSeller(this.sellerRepository.findById(offerDto.getSellerXmlDto().getId()).orElse(null));

            this.offerRepository.saveAndFlush(offer);

            stringBuilder.append("Successfully import offer ")
                    .append(offer.getAddedOn())
                    .append(" - ")
                    .append(offer.hasGoldStatus())
                    .append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
