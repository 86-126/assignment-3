public class Main {
    public static void main(String[] args) {
        final int NUM_PHILOSOPHERS = 25;
        final int NUM_FORKS = 30;

        Fork[] forks = new Fork[NUM_FORKS]; // Create an array for 30 forks
        TableManager tableManager = new TableManager(); // Create a table manager
        Thread[] philosopherThreads = new Thread[NUM_PHILOSOPHERS]; // Create an array for philosopher threads

        // Initialize forks
        for (int i = 0; i < NUM_FORKS; i++) {
            forks[i] = new Fork(i);
        }

        // Assign philosophers to tables and initialize philosopher threads
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            Fork leftFork = forks[i % 5 + (i / 5) * 5];
            Fork rightFork = forks[(i + 1) % 5 + (i / 5) * 5];
            tableManager.addPhilosopher(i / 5, i);
            philosopherThreads[i] = new Thread(new Philosopher(i, leftFork, rightFork, tableManager));
        }

        // Start all philosopher threads
        for (Thread philosopherThread : philosopherThreads) {
            philosopherThread.start();
        }

        // Optional: Wait for threads to finish (in this case, they won't)
        for (Thread philosopherThread : philosopherThreads) {
            try {
                philosopherThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Print the last philosopher who moved to the sixth table
        tableManager.printLastMover();
    }
}
