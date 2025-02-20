import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class HippodromeTest {

    void testConstructor_IllegalArgumentException() {
        IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
        assertEquals("Horses cannot be null.", e1.getMessage());

        IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(new ArrayList<>());
        });
        assertEquals("Horses cannot be empty.", e2.getMessage());
    }

    @Test
    void testGetHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + (i + 1), 1.0, 1));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
        for (int i = 0; i < 30; i++) {
            assertEquals("Horse" + (i + 1), hippodrome.getHorses().get(i).getName());
        }
    }

    @Test
    void testMove() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse mockHorse = mock(Horse.class);
            horses.add(mockHorse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            verify(horse, times(1)).move();
        }
    }

    @Test
    void testGetWinner() {
        List<Horse> horses = new ArrayList<>();
        Horse mockHorse1 = mock(Horse.class);
        Horse mockHorse2 = mock(Horse.class);
        Horse mockHorse3 = mock(Horse.class);

        when(mockHorse1.getDistance()).thenReturn(10.0);
        when(mockHorse2.getDistance()).thenReturn(20.0);
        when(mockHorse3.getDistance()).thenReturn(15.0);

        horses.add(mockHorse1);
        horses.add(mockHorse2);
        horses.add(mockHorse3);

        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(mockHorse2, hippodrome.getWinner());
    }
}