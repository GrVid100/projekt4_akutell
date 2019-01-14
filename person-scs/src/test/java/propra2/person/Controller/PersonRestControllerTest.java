package propra2.person.Controller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import propra2.person.Model.Person;
import propra2.person.Repository.EventRepository;
import propra2.person.Repository.PersonRepository;

import java.nio.charset.Charset;
import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestContext.class, WebApplicationContext.class})
@WebAppConfiguration

public class PersonRestControllerTest {
    private MockMvc mockMvc;
    @Mock
    PersonRepository personRepository;
    @Mock
    EventRepository eventRepository;
    private Person person= new Person();
    @Before
    public void setup() {
        // wichtig für get url
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        person.setId(1L);
        person.setVorname("Tom");
        person.setNachname("Stark");
        person.setJahreslohn("10000");
        person.setKontakt("tung@gmail.com");
        person.setProjekteId(new Long[]{3L,2L});
        when(personRepository.findById(any())).thenReturn(Optional.ofNullable(person));
    }
    @Test
    public void getEvents() {
        verify(eventRepository,times(1)).findAll();
        verify(eventRepository,times(1)).deleteAll();

    }
//    @Test
//    public void getById() throws Exception {
//        when(personRepository.findById(1L)).thenReturn(Optional.ofNullable(person));
//
//        mockMvc.perform(get("/{id}", 1L))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) content().contentType(TestUtil.APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect((ResultMatcher) jsonPath("$[0].id", is(1L)))
//                .andExpect((ResultMatcher) jsonPath("$[0].vorname", is("Tom")))
//                .andExpect((ResultMatcher) jsonPath("$[0].nachname", is("Stark")))
//              ;
//
//        verify(personRepository,times(1)).findById(1L);
//
//    }

}
class TestUtil {

    static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );
}
