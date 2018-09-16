package br.uem.iss.anesthesia.model.business;

import org.junit.Before;

import static org.mockito.MockitoAnnotations.initMocks;

public abstract class UnitTest extends Test {

    @Before
    public void before() {
        initMocks(this);
    }
}
