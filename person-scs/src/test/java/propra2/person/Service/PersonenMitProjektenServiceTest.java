package propra2.person.Service;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import propra2.person.Model.Person;
import propra2.person.Model.PersonMitProjekten;
import propra2.person.Model.Projekt;
import propra2.person.Repository.PersonRepository;
import propra2.person.Repository.ProjektRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestContext.class, WebApplicationContext.class})
@WebAppConfiguration
public class PersonenMitProjektenServiceTest {
    @Mock
    PersonRepository personRepository;
    @Mock
    ProjektRepository projektRepository;


    private List<Person> persons=new ArrayList<>();
    private Person person = new Person();
    private Projekt projekt3L = new Projekt();
    private PersonMitProjekten personMitProjekten=new PersonMitProjekten();
    private List<Projekt> projektList=new ArrayList<>();
    private List<PersonMitProjekten> personMitProjektenList =new ArrayList<>();
    @Before
    public void setup(){
        // person
        person.setId(1L);
        person.setVorname("Tom");
        person.setNachname("Stark");
        person.setJahreslohn("10000");
        person.setKontakt("tung@gmail.com");
        person.setProjekteId(new Long[]{3L,2L});

        persons.add(person);
        // projekt
        projekt3L.setId(3L);
        //
        projektList.add(projekt3L);
        //
        personMitProjekten.setPerson(person);
        personMitProjekten.setProjekte(projektList);

       // when(personRepository.findAll()).thenReturn(persons);

//        when(projektRepository.findAllById(anyLong())).thenReturn(projekt3L);
    }
    @Test
    public void returnPersonenMitProjekten() {
        PersonenMitProjektenService mock =mock(PersonenMitProjektenService.class);
        Assert.assertEquals(mock.returnPersonenMitProjekten(),personMitProjektenList);

    }
}