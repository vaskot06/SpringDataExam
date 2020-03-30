package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.SellerDto;
import softuni.exam.models.dtos.SellerRootDto;
import softuni.exam.models.entites.Seller;
import softuni.exam.parsers.XmlParser;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidationUtil;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class SellerServiceImpl implements SellerService {

    private static final String PATH = "C:\\Users\\Vasil\\Desktop\\Real Deal_Skeleton\\RealDeal_Skeleton\\src\\main\\resources\\files\\xml\\sellers.xml";

    private final SellerRepository sellerRepository;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository, FileUtil fileUtil, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.sellerRepository = sellerRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return this.fileUtil.readFile(PATH);
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        SellerRootDto sellerRootDto = this.xmlParser.read(SellerRootDto.class, PATH);

        for (SellerDto sellerDto : sellerRootDto.getSellers()) {

            Seller seller = this.modelMapper.map(sellerDto, Seller.class);

            if (!this.validationUtil.isValid(seller)) {
                stringBuilder.append("Invalid seller").append(System.lineSeparator());
                continue;
            }

            if (seller.getRating() == null) {
                stringBuilder.append("Invalid seller").append(System.lineSeparator());
                continue;
            }

            this.sellerRepository.saveAndFlush(seller);

            stringBuilder.append("Successfully imported car - ").append(seller.getLastName())
                    .append(" - ").append(seller.getEmail()).append(System.lineSeparator());

        }



        return stringBuilder.toString().trim();

    }
}
