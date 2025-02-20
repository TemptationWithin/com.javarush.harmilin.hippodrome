import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MainTest {

    @Test
    @Disabled
    @Timeout(22000)
    void testMain() {
        Horse mockHorse1 = mock(Horse.class);
        Horse mockHorse2 = mock(Horse.class);
        Horse mockHorse3 = mock(Horse.class);

        when(mockHorse1.getDistance()).thenReturn(10.0);
        when(mockHorse2.getDistance()).thenReturn(20.0);
        when(mockHorse3.getDistance()).thenReturn(30.0);

        List<Horse> mockHorses = List.of(mockHorse1, mockHorse2, mockHorse3);
        Hippodrome mockHippodrome = new Hippodrome(mockHorses);

        for (int i = 0; i < 100; i++) {
            mockHippodrome.move();
        }

        Horse winner = mockHippodrome.getWinner();
        assertEquals(mockHorse3, winner);

        verify(mockHorse1, times(100)).move();
        verify(mockHorse2, times(100)).move();
        verify(mockHorse3, times(100)).move();
    }


}