import java.util.*;

public class FCFS {
    public void schedule(List<PCB> readyQueue) {
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        System.out.println("\n--- FCFS Scheduling ---");
        for (PCB job : readyQueue) {
            job.state = "Running";
            job.startTime = currentTime;
            job.finishTime = currentTime + job.burstTime;
            currentTime = job.finishTime;

            job.calculateTimes();
            totalWaitingTime += job.waitingTime;
            totalTurnaroundTime += job.turnaroundTime;

            System.out.println("Job " + job.id + " | Start: " + job.startTime + "ms | End: " + job.finishTime + "ms");
        }

        System.out.println("Average Waiting Time: " + (totalWaitingTime / readyQueue.size()) + "ms");
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / readyQueue.size()) + "ms");
    }
}
