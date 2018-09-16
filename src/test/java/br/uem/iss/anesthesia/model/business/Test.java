package br.uem.iss.anesthesia.model.business;

import org.assertj.core.util.Sets;

import java.util.Set;

import static java.util.Arrays.asList;

public class Test {

    protected <T> Set<T> asSet(T... objects) {
        return Sets.newHashSet(asList(objects));
    }
}
