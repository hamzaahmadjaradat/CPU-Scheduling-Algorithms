package com.example.operating_system;

import java.util.*;

public class schedulingAlgorithms {
    public static final int Quantam = 20;

    public int[] FCFS(List<Process> processList) {
        Collections.sort(processList, Comparator.comparingInt(Process -> Process.ArrivalTime)); //sort the list to start with the first arrival process
        int currentTimeInterval = 0;
        int totalTurnaround = 0;
        int totalWaitingTime = 0;
        for (Process process : processList) {
            // calculate turnaround time for each process
            int turnaroundTime = currentTimeInterval + process.cpuBurstTime - process.ArrivalTime;
            // calculate waiting time for each process
            int waitingTime = turnaroundTime - process.cpuBurstTime;
            //assign the turnaround time in the current process(update in the source process)
            process.turnedAroundTime = turnaroundTime;
            //assign the waiting time in the current process(update in the source process)
            process.waitingTime = waitingTime;
            // update current time by adding the process's CPU burst time to currentTimeInterval
            currentTimeInterval += process.cpuBurstTime;
        }
        //make sure that the first process has no waiting time because it is the first one
        if ((processList.get(0).waitingTime + processList.get(0).ArrivalTime) == 0) {
            processList.get(0).waitingTime = 0;
        }
        //calculate the average of ATT and AWT from all processes
        double TATAverage = 0;
        double WTAverage = 0;

        for (Process process : processList) {
            TATAverage += process.turnedAroundTime;
            WTAverage += process.waitingTime;
        }
        int result[] = {(int) TATAverage / 8, (int) WTAverage / 8};
        return result;
    }

    public int[] SJF(List<Process> processList) {
        int currentTimeInterval = 0, processCounter = 0;
        Process currentProcess = new Process();
        //traverse until all processes get executed
        while (processCounter < processList.size()) {
            for (int i = 0; i < processList.size(); i++) {
                if (processList.get(i).remainingTime > 0) { //add the uncompleted execution process to work with
                    currentProcess = processList.get(i);
                    break;
                }
            }
            //find the process with the min remaining time at every single time
            for (int i = 0; i < processList.size(); i++) {
                if (processList.get(i).ArrivalTime > currentTimeInterval || processList.get(i).remainingTime == 0) {
                    //this means if the arrival time of a process is greater than the current time
                    //then don't work with this process because it hasn't arrived yet
                    //and also if the remaining time of this process is 0 that means it has completed the execution
                    // so there is no need to work with it again
                    continue;

                }
                if (processList.get(i).remainingTime < currentProcess.remainingTime) {
                    //if the remaining time of the current process is greater than
                    //the process (processList.get(i)) that means we need to switch to this process(processList.get(i))
                    //as this is the idea of the shortest job first

                    currentProcess = processList.get(i);
                }
            }
            //decrement the time by 1
            currentProcess.remainingTime -= 1;
            if (currentProcess.remainingTime == 0) {
                //this means that the process has finished execution because evey time we decrement the remaining
                // if the process is completed we need to increment the prcessCounter
                processCounter++;
                currentProcess.turnedAroundTime = currentTimeInterval + 1;
            }
            //increment the intervalTime
            currentTimeInterval++;
        }
        //assign the result which are the ATT,AWT
        for (int i = 0; i < processList.size(); i++) {
            int TAT = processList.get(i).turnedAroundTime;
            int At = processList.get(i).ArrivalTime;
            int CBT = processList.get(i).cpuBurstTime;
            //I initialized the variables for to get the final result
            processList.get(i).waitingTime = TAT - At - CBT;
            processList.get(i).turnedAroundTime = processList.get(i).waitingTime + CBT;

        }

        double TATAverage = 0;
        double WTAverage = 0;
        // after finishing the algorithm we get the Turned Around Time
        // and the waiting time with the loop all over the processes list
        for (Process process : processList) {
            TATAverage += process.turnedAroundTime;
            WTAverage += process.waitingTime;
        }
        int result[] = {(int) TATAverage / 8, (int) WTAverage / 8};
        return result;
        // send the result
    }

    public int[] MLFQ(List<Process> processList) {

        //the first queue depends on RR with Q=10
        //the second queue depends on RR with Q=50
        //the third queue depends on FCFS
        LinkedList<Process> RRQueueWith10Q = new LinkedList<>();
        LinkedList<Process> RRQueueWith50Q = new LinkedList<>();
        LinkedList<Process> FCFSQueue = new LinkedList<>();
        for (Process process : processList)
            RRQueueWith10Q.add(process);

        Process process;
        //begin time is the time of the first process arrived
        int currentTimeInterval = RRQueueWith10Q.peek().ArrivalTime;
        while (!(RRQueueWith10Q.isEmpty() && RRQueueWith50Q.isEmpty() && FCFSQueue.isEmpty())) {
            // Check if the queue with time quantum 10 is not empty
            if (!RRQueueWith10Q.isEmpty()) {
                process = RRQueueWith10Q.peek();
                // Check if the current time interval is before the arrival time of the process
                // and the queue with time quantum 50 is not empty
                if (currentTimeInterval < process.ArrivalTime && !RRQueueWith50Q.isEmpty()) {
                    process = RRQueueWith50Q.poll();
                }
                // Check if the current time interval is before the arrival of the process,
                // the queue with time quantum 50 is empty, and the FCFS queue is not empty
                else if (currentTimeInterval < process.ArrivalTime && RRQueueWith50Q.isEmpty() && !FCFSQueue.isEmpty()) {
                    process = FCFSQueue.poll();
                }
                // If none of the above conditions are met, dequeue from the queue with time quantum 10
                else if (!RRQueueWith10Q.isEmpty()) {
                    process = RRQueueWith10Q.poll();
                }
            }
            // If the queue with time quantum 10 is empty, check the queue with time quantum 50
            else if (!RRQueueWith50Q.isEmpty()) {
                process = RRQueueWith50Q.peek();
                // Check if the current time interval is before the arrival of the process
                // and the FCFS queue is not empty
                if (currentTimeInterval < process.ArrivalTime && !FCFSQueue.isEmpty()) {
                    process = FCFSQueue.poll();
                }
                // If the above condition is not met, dequeue from the queue with time quantum 50
                else if (!RRQueueWith50Q.isEmpty()) {
                    process = RRQueueWith50Q.poll();
                }
            }
            // If both queues with time quantum 10 and 50 are empty, dequeue from the FCFS queue
            else {
                process = FCFSQueue.poll();
            }
            // Check if the process has not started yet
            if (process.startTime == -1) {
                process.startTime = Math.max(process.ArrivalTime, currentTimeInterval);
            } else {
                process.startTime = process.startTime;
            }
            // calculate the time quantum based on the process stage
            int timeQuantum;
            if (process.queueStage == 1) {
                timeQuantum = 10;
            } else if (process.queueStage == 2) {
                timeQuantum = 50;
            } else {
                timeQuantum = process.remainingTime;
            }
            // Update the current time interval based on the minimum of remaining time and time quantum

            currentTimeInterval += Math.min(process.remainingTime, timeQuantum);
            // Update the remaining time of the process
            int timeRemaining = Math.min(process.remainingTime, timeQuantum);
            process.remainingTime -= timeRemaining;
            // Check if the process has completed its execution
            if (process.remainingTime == 0) {
                // Update process completion information
                process.endTime = currentTimeInterval;
                process.turnedAroundTime = (process.endTime - process.ArrivalTime);
                process.waitingTime = (process.turnedAroundTime - process.cpuBurstTime);
                processList.set(process.processID, process);
            } else {
                // If the process has not completed, update its stage and enqueue it accordingly
                if (process.queueStage == 1) {
                    process.queueStage = 2;
                    RRQueueWith50Q.add(process);
                } else if (process.queueStage == 2) {
                    process.queueStage = 3;
                    FCFSQueue.add(process);
                }
            }
        }
        double TATAverage = 0;
        double WTAverage = 0;
        for (Process process1 : processList) {
            TATAverage += process1.turnedAroundTime;
            WTAverage += process1.waitingTime;
        }
        int result[] = {(int) TATAverage / 8, (int) WTAverage / 8};
        return result;
    }

    public int[] RR(List<Process> processList) {
        // Create a copy from the original processList
        List<Process> list = new ArrayList<>(processList);
        int currentTimeInterval = 0;
        Queue<Process> queue = new LinkedList<>(processList);
        // we put the process in the queue until they are all completed execution
        while (!queue.isEmpty()) {
            // Get the next process from the queue
            Process process = queue.poll();
            // Check if the remaining time of the process is less than or equal to the quantum=20

            if (process.remainingTime <= Quantam) {
                // Update current Time Interval and process attributes (remainingTime,turnedAroundTime,waitingTime)
                // for processes with remaining time less than or equal to the quantum = 20
                currentTimeInterval += process.remainingTime;
                list.get(process.processID).remainingTime = 0;
                list.get(process.processID).turnedAroundTime = currentTimeInterval - process.ArrivalTime;
                list.get(process.processID).waitingTime = list.get(process.processID).turnedAroundTime - process.cpuBurstTime;
            } else {
                // Update current time and process attributes for processes with remaining time greater than the  quantum=20
                currentTimeInterval += Quantam;
                list.get(process.processID).waitingTime += currentTimeInterval - process.ArrivalTime;
                list.get(process.processID).remainingTime -= Quantam;
                // Add the process back to the queue for another processing
                queue.offer(process);
            }
        }
        // Initialize variables to accumulate turned around time and waiting time
        double TATAverage = 0;
        double WTAverage = 0;

        // Iterate through each process in the processList
        for (Process process : processList) {
            // Accumulate turned around time and waiting time
            TATAverage += process.turnedAroundTime;
            WTAverage += process.waitingTime;
        }
        // Calculate the integer representations of Average Turn Around Time and Average Waiting Time
        int result[] = {(int) TATAverage / processList.size(), (int) WTAverage / processList.size()};

        // Return the result array
        return result;
    }

    /*
     * Display process information including process ID, Arrival Time, CPU Burst Time,
     * Waiting Time, and Turned Around Time for each process in the given list.
     * Additionally, calculate and display the Average Turn Around Time and Average Waiting Time.
     *
     */
    public void displayProcesses(List<Process> list) {
        // Initialize variables to calculate average turn around time and average waiting time
        int TATAverage = 0;
        int WTAverage = 0;
        System.out.println("The Last List Data In The Operation ");
        // Display  headers
        System.out.println("processID\tArrival Time\tCBU Burst Time\tWaiting time\tTurned Around Time");

        // Iterate through each process in the list
        for (Process process : list) {
            // Display process information
            System.out.println("\t" + process.processID + "\t\t\t    " + process.ArrivalTime +
                    "\t\t\t\t" + process.cpuBurstTime + "\t\t\t\t" + process.waitingTime +
                    "\t\t\t\t\t" + process.turnedAroundTime);

            // Update total turned around time and total waiting time for calculating averages
            TATAverage += process.turnedAroundTime;
            WTAverage += process.waitingTime;
        }
        System.out.println("");
        // Calculate and display the Average Turn Around Time
        System.out.println("The Average Turn Around Time = " + (float) TATAverage / list.size());

        // Calculate and display the Average Waiting Time
        System.out.println("The Average Waiting Time = " + (float) WTAverage / list.size());
        System.out.println("\n\n");
    }
}