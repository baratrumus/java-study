package ru.job4j.KofeMachine;

import org.junit.Test;
import ru.job4j.condition.Point;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class KofeMachineTest {
    @Test
    public void when50and33TheRestIs10_5_2() {
        KofeMachine km = new KofeMachine(50, 33);
        assertThat(km.changes(), is(new int[]{10, 5, 2}));
    }



    @Test
    public void when50and22() {
        KofeMachineNoList km = new KofeMachineNoList(50, 22);
        assertThat(km.changes1(), is(new int[]{10, 10, 5, 2, 1}));
    }
}


