import java.util.*;

public class PriorityScheduling {
    public void schedule(List<PCB> readyQueue) {
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        System.out.println("\n--- Priority Scheduling ---");

        readyQueue.sort((a, b) -> b.priority - a.priority); // 8 highest

        for (PCB job : readyQueue) {
            job.startTime = currentTime;
            job.finishTime = currentTime + job.burstTime;
            currentTime = job.finishTime;

            job.calculateTimes();
            totalWaitingTime += job.waitingTime;
            totalTurnaroundTime += job.turnaroundTime;

            System.out.println("Job " + job.id + " | Priority: " + job.priority + " | Start: " + job.startTime + "ms | End: " + job.finishTime + "ms");

            if (job.waitingTime > job.priority * 10) {
                System.out.println("-> Job " + job.id + " suffered from STARVATION.");
            }
        }

        System.out.println("Average Waiting Time: " + (totalWaitingTime / readyQueue.size()) + "ms");
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / readyQueue.size()) + "ms");
    }
}
