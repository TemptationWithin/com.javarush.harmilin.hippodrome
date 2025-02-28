import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.isNull;

public class Hippodrome {

    private final List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        if (isNull(horses)) {
            Logger.getRootLogger().info("Horses list is null");
            throw new IllegalArgumentException("Horses cannot be null.");
        } else if (horses.isEmpty()) {
            Logger.getRootLogger().info("Horses list is empty");
            throw new IllegalArgumentException("Horses cannot be empty.");
        }

        this.horses = horses;
        Logger.getRootLogger().info("Создание Hippodrome, лошадей [" + this.horses.size() +"]");
    }

    public List<Horse> getHorses() {
        return Collections.unmodifiableList(horses);
    }

    public void move() {
        horses.forEach(Horse::move);
    }

    public Horse getWinner() {
        return horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();
    }
}
