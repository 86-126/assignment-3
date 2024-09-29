import java.util.ArrayList;
import java.util.List;

public class TableManager {
    private final List<List<Integer>> tables;
    private final List<Integer> sixthTable;
    private Integer lastMovedPhilosopher = null;

    public TableManager() {
        tables = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tables.add(new ArrayList<>());  // Create 5 tables
        }
        sixthTable = new ArrayList<>();  // Sixth table
    }

    public synchronized void addPhilosopher(int tableNumber, int philosopherId) {
        if (tableNumber >= 0 && tableNumber < 5) {
            tables.get(tableNumber).add(philosopherId);
        }
    }

    public synchronized boolean moveToSixthTable(int philosopherId) {
        // Move philosopher to the sixth table if there's room
        if (sixthTable.size() < 5) {
            System.out.println("Philosopher " + getPhilosopherLabel(philosopherId) + " is moving to the sixth table.");
            sixthTable.add(philosopherId);
            System.out.println("Philosopher " + getPhilosopherLabel(philosopherId) + " moved to sixth table.");
            lastMovedPhilosopher = philosopherId;
            return false;
        } else {
            System.out.println("Sixth table is now full, potential deadlock.");
            return true;  // Simulate deadlock once the sixth table is full
        }
    }

    public synchronized void printLastMover() {
        if (lastMovedPhilosopher != null) {
            System.out.println("The last philosopher to move to the sixth table before deadlock: " + getPhilosopherLabel(lastMovedPhilosopher));
        } else {
            System.out.println("No philosopher moved to the sixth table.");
        }
    }

    private String getPhilosopherLabel(int id) {
        return String.valueOf((char) ('A' + id));
    }
}
