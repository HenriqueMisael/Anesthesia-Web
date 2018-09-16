package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.entity.BackgroundModel;
import br.uem.iss.anesthesia.model.entity.CityModel;
import br.uem.iss.anesthesia.model.entity.MedicineModel;
import br.uem.iss.anesthesia.model.repository.BackgroundRepository;
import br.uem.iss.anesthesia.model.repository.CityRepository;
import br.uem.iss.anesthesia.model.repository.MedicineRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ModelNoveltyCheckerTest extends UnitTest {

    @Mock
    private CityRepository cityRepository;
    @Mock
    private BackgroundRepository backgroundRepository;
    @Mock
    private MedicineRepository medicineRepository;
    @InjectMocks
    private ModelNoveltyChecker modelNoveltyChecker;
    private CityModel city;
    private BackgroundModel background;
    private MedicineModel medicine;

    @Override
    public void before() {
        super.before();
        city = new CityModel();
        city.setName("Cidade");
        city.setUf("Estado");
        background = new BackgroundModel();
        background.setName("Antecedente");
        medicine = new MedicineModel();
        medicine.setName("Medicamento");
    }

    @Test
    public void nullCity() {
        assertEquals(false, modelNoveltyChecker.check((CityModel) null));
        verify(cityRepository, times(0)).findByName(any());
    }

    @Test
    public void newCity() {
        doReturn(Optional.empty()).when(cityRepository).findByName(city.getName());
        assertEquals(true, modelNoveltyChecker.check(city));
        verify(cityRepository, times(1)).findByName(city.getName());
    }

    @Test
    public void oldCity() {
        city.setId(1L);
        when(cityRepository.findByName(city.getName())).thenReturn(Optional.of(city));
        assertEquals(false, modelNoveltyChecker.check(city));
        verify(cityRepository, times(1)).findByName(city.getName());
    }

    @Test
    public void nullBackground() {
        assertEquals(false, modelNoveltyChecker.check((BackgroundModel) null));
        verify(backgroundRepository, times(0)).findByName(any());
    }

    @Test
    public void newBackground() {
        doReturn(Optional.empty()).when(cityRepository).findByName(background.getName());
        assertEquals(true, modelNoveltyChecker.check(background));
        verify(backgroundRepository, times(1)).findByName(background.getName());
    }

    @Test
    public void oldBackground() {
        background.setId(1L);
        when(backgroundRepository.findByName(background.getName())).thenReturn(Optional.of(background));
        assertEquals(false, modelNoveltyChecker.check(background));
        verify(backgroundRepository, times(1)).findByName(background.getName());
    }

    @Test
    public void nullMedicine() {
        assertEquals(false, modelNoveltyChecker.check((MedicineModel) null));
        verify(medicineRepository, times(0)).findByName(any());
    }

    @Test
    public void newMedicine() {
        doReturn(Optional.empty()).when(medicineRepository).findByName(medicine.getName());
        assertEquals(true, modelNoveltyChecker.check(medicine));
        verify(medicineRepository, times(1)).findByName(medicine.getName());
    }

    @Test
    public void oldMedicine() {
        medicine.setId(1L);
        when(medicineRepository.findByName(medicine.getName())).thenReturn(Optional.of(medicine));
        assertEquals(false, modelNoveltyChecker.check(medicine));
        verify(medicineRepository, times(1)).findByName(medicine.getName());
    }
}