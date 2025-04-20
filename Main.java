import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
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
            jobs.add(new PCB(id, burst, mem, priority));
        }
        br.close();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Algorithm: 1) FCFS  2) Round Robin  3) Priority");
        int choice = scanner.nextInt();

        // Deep copy of jobs to avoid modifying original
        List<PCB> cloned = new ArrayList<>();
        for (PCB j : jobs)
            cloned.add(new PCB(j.id, j.burstTime, j.memoryRequired, j.priority));

        switch (choice) {
            case 1:
                new FCFS().schedule(cloned);
                break;
            case 2:
                new RoundRobin(7).schedule(cloned);
                break;
            case 3:
                new PriorityScheduling().schedule(cloned);
                break;
            default:
                System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}
