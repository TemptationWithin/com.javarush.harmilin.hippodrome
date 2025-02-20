import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

class HorseTest {

    @Test
    void testConstructor_IllegalArgumentException() {
        IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 1.0, 1);
        });
        assertEquals("Name cannot be null.", e1.getMessage());

        IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("ExampleName", -1.0, 1);
        });
        assertEquals("Speed cannot be negative.", e2.getMessage());

        IllegalArgumentException e3 = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("ExampleName", 1.0, -1);
        });
        assertEquals("Distance cannot be negative.", e3.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t", "\n", "\r", "\t   ", " \t "})
    void testConstructor_ThrowsIllegalArgumentExceptionForInvalidStrings(String input) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(input, 1.0, 1);
        });
        assertEquals("Name cannot be blank.", e.getMessage());
    }


    @Test
    void testGetName() {
        Horse horse = new Horse("ExampleName", 1.0, 1);
        assertEquals("ExampleName", horse.getName());
    }

    @Test
    void testGetSpeed() {
        Horse horse = new Horse("ExampleName", 1.0, 1);
        assertEquals(1.0, horse.getSpeed());
    }

    @Test
    void testGetDistance() {
        Horse horse1 = new Horse("ExampleName", 1.0, 1);
        Horse horseWithTwoArguments = new Horse("ExampleName", 1.0);

        assertEquals(1.0, horse1.getDistance());
        assertEquals(0, horseWithTwoArguments.getDistance());
    }

    @Test
    void testMove() {
        try {
            MockedStatic<Horse> mockedStatic = mockStatic(Horse.class);
            double mockValue = 0.5;
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(mockValue);
            Horse horse = new Horse("ExampleName", 10, 1);
            double distance = horse.getDistance();
            horse.move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9), times(1));
            double expectedDistance = distance + horse.getSpeed() * Horse.getRandomDouble(0.2, 0.9);
            assertEquals(expectedDistance, horse.getDistance(), 0.001);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}