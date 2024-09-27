import java.util.Random;

public class Philosopher implements Runnable {
    private final int id;
    private final Fork leftFork;
    private final Fork rightFork;
    private final Random random = new Random();
    private final TableManager tableManager;  // Manages tables and handles deadlock

    public Philosopher(int id, Fork leftFork, Fork rightFork, TableManager tableManager) {
        this.id = id;  // Philosopher ID (A, B, C, etc.)
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.tableManager = tableManager;
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                if (tableManager.isDeadlocked(id)) {  // Check if deadlock has occurred
                    moveToSixthTable();
                    return;
                }
                pickUpForks();
                eat();
                putDownForks();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void think() throws InterruptedException {
        int thinkTime = random.nextInt(10) * 1000;  // Think for 0-10 seconds
        System.out.println("Philosopher " + id + " is thinking for " + thinkTime / 1000 + " seconds.");
        Thread.sleep(thinkTime);
    }

    private void pickUpForks() throws InterruptedException {
        synchronized (leftFork) {
            System.out.println("Philosopher " + id + " picked up left fork " + leftFork.getId());
            Thread.sleep(4000);  // Wait 4 seconds to pick up the right fork
            synchronized (rightFork) {
                System.out.println("Philosopher " + id + " picked up right fork " + rightFork.getId());
            }
        }
    }

    private void eat() throws InterruptedException {
        int eatTime = random.nextInt(5) * 1000;  // Eat for 0-5 seconds
        System.out.println("Philosopher " + id + " is eating for " + eatTime / 1000 + " seconds.");
        Thread.sleep(eatTime);
    }

    private void putDownForks() {
        System.out.println("Philosopher " + id + " put down both forks.");
    }

    private void moveToSixthTable() {
        System.out.println("Philosopher " + id + " is moving to the sixth table.");
        tableManager.moveToSixthTable(id);
    }
}
