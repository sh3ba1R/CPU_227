import java.util.*;

public class RoundRobin {
    int quantum;

    public RoundRobin(int quantum) {
        this.quantum = quantum;
    }

    public void schedule(List<PCB> originalList) {
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        System.out.println("\n--- Round Robin Scheduling (Quantum = " + quantum + "ms) ---");

        Queue<PCB> queue = new LinkedList<>();
        Map<Integer, Integer> remainingTime = new HashMap<>();
        Map<Integer, Integer> firstStart = new HashMap<>();

        for (PCB job : originalList) {
            queue.add(job);
            remainingTime.put(job.id, job.burstTime);
        }

        while (!queue.isEmpty()) {
            PCB job = queue.poll();
            int remaining = remainingTime.get(job.id);
            int runTime = Math.min(remaining, quantum);

            if (!firstStart.containsKey(job.id)) {
                job.startTime = currentTime;
                firstStart.put(job.id, currentTime);
            }

            System.out.println("Job " + job.id + " | Start: " + currentTime + "ms | End: " + (currentTime + runTime) + "ms");

            currentTime += runTime;
            remaining -= runTime;

            if (remaining > 0) {
                remainingTime.put(job.id, remaining);
                queue.add(job);
            } else {
                job.finishTime = currentTime;
                job.burstTime = remainingTime.get(job.id); // for final stat
                job.calculateTimes();
                totalWaitingTime += job.waitingTime;
                totalTurnaroundTime += job.turnaroundTime;
            }
        }

        System.out.println("Average Waiting Time: " + (totalWaitingTime / originalList.size()) + "ms");
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / originalList.size()) + "ms");
    }
}
