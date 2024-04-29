package com.example.operating_system;

public class Process {

    // Attributes of a single process
    int processID;          //  id of the process
    int ArrivalTime;        // Time at which the process arrives to the ready queue
    int cpuBurstTime;       // Time required by the process for the CPU execution
    int remainingTime;      // the Remaining time for the process to complete its execution in the cpu
    int waitingTime;        // Time the process has spent waiting in the ready queue till it ends execution
    int turnedAroundTime;   // Total time taken by the process to complete its execution
    //(from the time it arrives the ready queue till it finishes execution)
    int startTime; // Initialize start time(the time that the process starts execution)
    int endTime; // Initialize start time(the time that the process ends execution)
    int queueStage;  // Set the priority stage of the process(10,50,FCFS)


    // the constructor to initialize the process attributes
    public Process(int processID, int arrivalTime, int cpuBurstTime) {
        this.processID = processID;  // id of the process
        this.ArrivalTime = arrivalTime; // Time at which the process arrives to the ready queue
        this.cpuBurstTime = cpuBurstTime; // Time required by the process for the CPU execution
        this.remainingTime = cpuBurstTime; // the Remaining time for the process to complete its execution in the cpu

    }
    //another constructor used for the MultiLevel FeedBack Queue algorithm
    public Process(int processID, int arrivalTime, int cpuBurstTime,int stage) {
        this.processID = processID; //the number of the process
        this.ArrivalTime = arrivalTime; //The time at which the process arrives in the system.
        this.cpuBurstTime = cpuBurstTime;   //The time required by the process to execute on the CPU
        this.remainingTime = cpuBurstTime; // Initially set remaining time to CPU burst time
        this.startTime=0; // Initialize start time(the time that the process starts execution)
        this.endTime=0; // Initialize start time(the time that the process ends execution)
        this.queueStage =stage;   // Set the priority stage of the process(10,50,FCFS)

    }

    // Default constructor
    public Process() {
    }
     //toString method to provide a string representation of the process
    @Override
    public String toString() {
        return "Process{" +
                "processID=" + processID +
                ", ArrivalTime=" + ArrivalTime +
                ", cpuBurstTime=" + cpuBurstTime +
                '}';
    }
}
