import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TableManager {
    private final List<Integer>[] tables = new ArrayList[6];  // 5 normal tables + 6th table
    private final Random random = new Random();

    public TableManager() {
        for (int i = 0; i < 6; i++) {
            tables[i] = new ArrayList<>();
        }
    }

    // Check if a table is deadlocked
    public boolean isDeadlocked(int philosopherId) {
        // Simulate deadlock detection logic (for simplicity, return true randomly)
        return random.nextBoolean();  // Randomly trigger deadlock for now
    }

    // Move philosopher to the 6th table if deadlocked
    public synchronized void moveToSixthTable(int philosopherId) {
        for (int i = 0; i < 6; i++) {
            if (tables[i].contains(philosopherId)) {
                tables[i].remove(Integer.valueOf(philosopherId));
                tables[5].add(philosopherId);  // Move to the 6th table
                System.out.println("Philosopher " + philosopherId + " moved to sixth table.");
                break;
            }
        }
    }


}
