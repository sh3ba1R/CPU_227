public class PCB {
    int id;
    int burstTime;
    int memoryRequired;
    int priority;
    String state;
    int startTime;
    int finishTime;
    int arrivalTime = 0;
    int waitingTime;
    int turnaroundTime;

    public PCB(int id, int burstTime, int memoryRequired, int priority) {
        this.id = id;
        this.burstTime = burstTime;
        this.memoryRequired = memoryRequired;
        this.priority = priority;
        this.state = "New";
    }

    public void calculateTimes() {
        this.turnaroundTime = this.finishTime - this.arrivalTime;
        this.waitingTime = this.turnaroundTime - this.burstTime;
    }
}
