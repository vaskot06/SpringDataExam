package softuni.exam.config;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.exam.util.FileUtilImpl;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.ValidationUtilImpl;

import javax.validation.Validation;
import javax.validation.Validator;


@Configuration
public class ApplicationBeanConfiguration {


    @Bean
    public FileUtilImpl fileUtil() {
        return new FileUtilImpl();
    }

    @Bean
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }


    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl(validator());
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
