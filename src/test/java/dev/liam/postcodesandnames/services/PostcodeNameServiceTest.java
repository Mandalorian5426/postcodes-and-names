package dev.liam.postcodesandnames.services;

import dev.liam.postcodesandnames.models.PostcodeName;
import dev.liam.postcodesandnames.repositories.PostcodeNameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class PostcodeNameServiceTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public PostcodeNameService postcodeNameService() {
            return new PostcodeNameService();
        }
    }

    @Autowired
    private PostcodeNameService postcodeNameService;

    @MockBean
    private PostcodeNameRepository postcodeNameRepository;

    @Before
    public void setUp() {
        List<PostcodeName> postcodeNames = new ArrayList<>();
        postcodeNames.add(new PostcodeName(1,1000,"Ben"));
        postcodeNames.add(new PostcodeName(2,2000,"Luke"));
        postcodeNames.add(new PostcodeName(3,3000,"Han"));

        Mockito.when(postcodeNameRepository.findAll())
                .thenReturn(postcodeNames);
    }

    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        Stream<String> namesStream = postcodeNameService.getAllNamesWithinPostcodeRange(1500, 2500).get();
        String[] namesArr = namesStream.toArray(String[]::new);

        assertThat(namesArr.length).isEqualTo(1);
        assertThat(namesArr[0]).isEqualTo("Luke");
    }
}
