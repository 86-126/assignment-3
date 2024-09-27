import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TableManager {
    private final List<Integer>[] tables = new ArrayList[6];
    private final Random random = new Random();
    private char lastMover = ' ';

    public TableManager() {
        for (int i = 0; i < 6; i++) {
            tables[i] = new ArrayList<>();
        }
    }

    // Check if a table is deadlocked
    public boolean isDeadlocked(int philosopherId) {
        return random.nextBoolean();
    }

    // Move philosopher to the 6th table if deadlocked
    public synchronized void moveToSixthTable(int philosopherId) {
        for (int i = 0; i < 6; i++) {
            if (tables[i].contains(philosopherId)) {
                tables[i].remove(Integer.valueOf(philosopherId));
                tables[5].add(philosopherId);  // Move to the 6th table
                lastMover = (char) ('A' + philosopherId);
                System.out.println("Philosopher " + lastMover + " moved to sixth table.");
                break;
            }
        }
    }

    public void printLastMover() {
        System.out.println("The last philosopher to move to the sixth table before deadlock: " + lastMover);
    }

    // Add philosopher to a table
    public synchronized void addPhilosopher(int tableId, int philosopherId) {
        tables[tableId].add(philosopherId);
    }
}
