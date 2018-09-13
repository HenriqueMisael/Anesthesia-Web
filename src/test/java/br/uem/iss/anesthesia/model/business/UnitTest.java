package br.uem.iss.anesthesia.model.business;

import org.junit.Before;

import static org.mockito.MockitoAnnotations.initMocks;

public abstract class UnitTest {

    @Before
    public void before() {
        initMocks(this);
    }
}
