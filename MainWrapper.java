import java.io.*;
import java.util.*;

public class MainWrapper {
    public static void main(String[] args) throws Exception {
        final int MAX_MEMORY = 2048; // Set the memory limit to 2048MB
        int usedMemory = 0; // Track the total memory used
        List<String> validJobs = new ArrayList<>();

        // Read from job.txt
        BufferedReader br = new BufferedReader(new FileReader("job.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) continue;
            String[] parts = line.split("[:;]");
            int mem = Integer.parseInt(parts[3]);

            // Check if adding this job exceeds the memory limit
            if (usedMemory + mem > MAX_MEMORY) {
                System.out.println("Job " + parts[0] + " exceeds memory limit and will be skipped.");
                continue; // Skip this job
            }

            validJobs.add(line);
            usedMemory += mem;
        }
        br.close();

        // Write valid jobs back to job.txt
        BufferedWriter bw = new BufferedWriter(new FileWriter("job.txt"));
        for (String job : validJobs) {
            bw.write(job);
            bw.newLine();
        }
        bw.close();

        System.out.println("Total memory used: " + usedMemory + " MB");

        // Call the original Main class
        Main.main(args);
    }
}
