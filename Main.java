import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        final int MAX_MEMORY = 2048; // Set the memory limit to 2048MB
        int usedMemory = 0; // Track the total memory used

        List<PCB> jobs = new ArrayList<>();

        // Read from job.txt
        BufferedReader br = new BufferedReader(new FileReader("job.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) continue;
            String[] parts = line.split("[:;]");
            int id = Integer.parseInt(parts[0]);
            int burst = Integer.parseInt(parts[1]);
            int priority = Integer.parseInt(parts[2]);
            int mem = Integer.parseInt(parts[3]);

            // Check if adding this job exceeds the memory limit
            if (usedMemory + mem > MAX_MEMORY) {
                System.out.println("Job " + id + " exceeds memory limit and will be skipped.");
                continue; // Skip this job
            }

            // Add the job and update used memory
            jobs.add(new PCB(id, burst, mem, priority));
            usedMemory += mem;
        }
        br.close();

        System.out.println("Total memory used: " + usedMemory + " MB");
        System.out.println("Select Algorithm: 1) FCFS  2) Round Robin  3) Priority");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        // Pass the original jobs list directly to the scheduling algorithms
        switch (choice) {
            case 1:
                new FCFS().schedule(jobs);
                break;
            case 2:
                new RoundRobin(7).schedule(jobs);
                break;
            case 3:
                new PriorityScheduling().schedule(jobs);
                break;
            default:
                System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}