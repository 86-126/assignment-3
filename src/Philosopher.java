import java.util.Random;

public class Philosopher implements Runnable {
    private int id;
    private Fork leftFork;
    private Fork rightFork;
    private TableManager tableManager;
    private Random random = new Random();

    public Philosopher(int id, Fork leftFork, Fork rightFork, TableManager tableManager) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.tableManager = tableManager;
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                if (tableManager.moveToSixthTable(id)) {
                    return;
                }
                eat();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void think() throws InterruptedException {
        int thinkingTime = random.nextInt(10);
        System.out.println("Philosopher " + getPhilosopherLabel(id) + " is thinking for " + thinkingTime + " seconds.");
        Thread.sleep(thinkingTime * 1000);
    }

    private void eat() throws InterruptedException {
        synchronized (leftFork) {
            System.out.println("Philosopher " + getPhilosopherLabel(id) + " picked up left fork " + leftFork.getId());
            Thread.sleep(4000);
            synchronized (rightFork) {
                System.out.println("Philosopher " + getPhilosopherLabel(id) + " picked up right fork " + rightFork.getId());
                int eatingTime = random.nextInt(5) + 1;
                System.out.println("Philosopher " + getPhilosopherLabel(id) + " is eating for " + eatingTime + " seconds.");
                Thread.sleep(eatingTime * 1000);
                System.out.println("Philosopher " + getPhilosopherLabel(id) + " put down both forks.");
            }
        }
    }

    private String getPhilosopherLabel(int id) {
        return String.valueOf((char) ('A' + id));
    }
}
