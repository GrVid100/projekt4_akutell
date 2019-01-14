package propra2.person.ServiceTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import propra2.person.Model.Projekt;
import propra2.person.Repository.ProjektRepository;
import propra2.person.Service.ProjekteService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProjekteServiceTest {

    @InjectMocks
    ProjekteService projekteService;

    @Mock
    ProjektRepository projektRepository;

    private static Long[] oneId = {5L};
    private static Long[] moreIds = {1L, 2L, 3L};

    @Test
    public void getProjekteTest1Projekt() {
        List<Projekt> actual = projekteService.getProjekte(oneId);

        List<Projekt> expected = new ArrayList<>();
        expected.add(projektRepository.findAllById(oneId[0]));
        assertEquals(actual, expected);
    }

    @Test
    public void getProjekteTest3Projekte() {
        List<Projekt> actual = projekteService.getProjekte(moreIds);

        List<Projekt> expected = new ArrayList<>();
        for (Long id : moreIds) {
            Projekt projekt = projektRepository.findAllById(id);
            expected.add(projekt);
        }
        assertEquals(actual, expected);
    }
}
