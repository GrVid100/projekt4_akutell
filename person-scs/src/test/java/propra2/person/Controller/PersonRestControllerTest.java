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
    @Before
    public void setup() {
        Optional<Person> person;
        person.get().setVorname("Tom");
        person.get().setNachname("Stark");
        person.get().setJahreslohn("10000");
    }
    @Test
    public void getById() throws Exception {
        verify(personRepository,times(1)).findById(1L);
    }

    @Test
    public void getEvents() {
        verify(eventRepository,times(1)).findAll();
        verify(eventRepository,times(1)).deleteAll();

    }
    @Test
    public void getById() throws Exception {

        when(personRepository.findById(1L)).thenReturn();

        mockMvc.perform(get("/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect((ResultMatcher) jsonPath("$[0].id", is(1)))
                .andExpect((ResultMatcher) jsonPath("$[0].description", is("Lorem ipsum")))
                .andExpect((ResultMatcher) jsonPath("$[0].title", is("Foo")))
                .andExpect((ResultMatcher) jsonPath("$[1].id", is(2)))
                .andExpect((ResultMatcher) jsonPath("$[1].description", is("Lorem ipsum")))
                .andExpect((ResultMatcher) jsonPath("$[1].title", is("Bar")));

        verify(todoServiceMock, times(1)).findAll();
    }

}
public class TestUtil {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );
}
