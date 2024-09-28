public class Main {
    public static void main(String[] args) {
        final int NUM_PHILOSOPHERS = 25; // Total number of philosophers
        Fork[] forks = new Fork[NUM_PHILOSOPHERS]; // Create an array for forks
        TableManager tableManager = new TableManager(); // Create a table manager
        Thread[] philosopherThreads = new Thread[NUM_PHILOSOPHERS]; // Create an array for philosopher threads

        // Initialize forks
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new Fork(i);
            tableManager.addPhilosopher(i % 5, i);  // Assign philosophers to 5 tables
        }

        // Initialize philosopher threads
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            Fork leftFork = forks[i];
            Fork rightFork = forks[(i + 1) % NUM_PHILOSOPHERS]; // Ensure circular assignment
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
