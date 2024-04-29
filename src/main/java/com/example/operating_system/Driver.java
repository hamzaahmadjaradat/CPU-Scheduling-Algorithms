package com.example.operating_system;

import java.util.*;

public class Driver {

    public static final int numOfProcess = 8; //total number of processes in the program
    static List<Process> processList = new ArrayList<>();
    // the main list that holds the processes, and we always keep reset it by the generateProcesses(); method

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice = -1;
        while (true) {
            System.out.println("enter the scheduling Algorithm number  you like to start: \n 1: First Come First Serve \n" +
                    " 2: Shortest Jop First \n 3: ROUND ROBIN \n 4: MultiLevel FeedBack Queue\n 5: leave the program\n"); //asking the user to enter the scheduling Algorithm
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    generateFCFS();     // First Come First Serve
                    break;
                case 2:
                    generateSJF();      // Shortest Jop First
                    break;
                case 3:
                    generateRR();       // ROUND ROBIN
                    break;
                case 4:
                    generateMLFQ();     // MultiLevel FeedBack Queue
                    break;
                case 5:
                    System.out.println("Thanks For Trying The Program");//leave the program
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void generateSJF() {
        schedulingAlgorithms scheduler = new schedulingAlgorithms();    //assign an object from the scheduling Algorithms class
        int counter = 0;        //the counter that will start from 0 till 100000
        //the variables that in the end we will display the result (Average Turned Around Time) and (Average waiting time)
        double ATT = 0;
        double AWT = 0;
        while (counter < 100) {             // the first one is the 100 iterations
            generateProcesses();            //generate 8 processes every iteration
            int data[] = scheduler.SJF(processList);                  //send the list to the scheduling Algorithm to get
            ATT += data[0];           //get the ATT                  // the ATT and AWT for a single iteration in the form of array with two indexes
            AWT += data[1];         //get the AWT
            counter++;
        }
        double ATT_100 = ATT / 100;  //make the average of ATT from  0 to 100 iterations
        double AWT_100 = AWT / 100; //make the average of AWT from  0 to 100 iterations

        counter = 0;                   //repeat the same thing but now from 0 to 1000
        ATT = 0;
        AWT = 0;
        while (counter < 1000) {
            generateProcesses();
            int data[] = scheduler.SJF(processList);
            ATT += data[0];
            AWT += data[1];
            counter++;
        }
        double ATT_1000 = ATT / 1000;
        double AWT_1000 = AWT / 1000;

        counter = 0;            //repeat the same thing but now from 0 to 10000
        ATT = 0;
        AWT = 0;
        while (counter < 10000) {
            generateProcesses();
            int data[] = scheduler.SJF(processList);
            ATT += data[0];
            AWT += data[1];
            counter++;
        }
        double ATT_10000 = ATT / 10000;
        double AWT_10000 = AWT / 10000;

        counter = 0;            //repeat the same thing but now from 0 to 100000
        ATT = 0;
        AWT = 0;
        while (counter < 100000) {
            generateProcesses();
            int data[] = scheduler.SJF(processList);
            ATT += data[0];
            AWT += data[1];
            counter++;
        }
        double ATT_100000 = ATT / 100000;
        double AWT_100000 = AWT / 100000;
        System.out.println("\t\t100\t\t\t1000\t\t10000\t\t100000");
        System.out.printf("ATT :\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f%n", ATT_100, ATT_1000, ATT_10000, ATT_100000);   //display the averages of ATT for 100 and 1000 and 10000 and 100000 iterations
        System.out.printf("AWT :\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f%n\n\n", AWT_100, AWT_1000, AWT_10000, AWT_100000);   //display the averages of AWT for 100 and 1000 and 10000 and 100000 iterations
        scheduler.displayProcesses(processList);

    }

    private static void generateRR() {
        schedulingAlgorithms scheduler = new schedulingAlgorithms();        //assign an object from the scheduling Algorithms class
        int counter = 0; //the counter that will start from 0 till 100000
        //the variables that in the end we will display the result (Average Turned Around Time) and (Average waiting time)
        double ATT = 0;
        double AWT = 0;
        while (counter < 100) {       // the first one is the 100 iterations
            generateProcesses();     //generate 8 processes every iteration
            int data[] = scheduler.RR(processList);           //send the list to the scheduling Algorithm to get
            // get the ATT and AWT for a single iteration in the form of array with two indexes
            ATT += data[0];  //get the ATT
            AWT += data[1]; //get the AWT
            counter++;
        }
        double ATT_100 = ATT / 100;   //make the average of ATT from  0 to 100 iterations
        double AWT_100 = AWT / 100;  //make the average of AWT from  0 to 100 iterations


        counter = 0;                //repeat the same thing but now from 0 to 1000
        ATT = 0;
        AWT = 0;
        while (counter < 1000) {
            generateProcesses();
            int data[] = scheduler.RR(processList);
            ATT += data[0];
            AWT += data[1];
            counter++;
        }
        double ATT_1000 = ATT / 1000;
        double AWT_1000 = AWT / 1000;
        counter = 0;
        ATT = 0;
        AWT = 0;
        while (counter < 10000) {//repeat the same thing but now from 0 to 10000
            generateProcesses();
            int data[] = scheduler.RR(processList);
            ATT += data[0];
            AWT += data[1];
            counter++;
        }
        double ATT_10000 = ATT / 10000;
        double AWT_10000 = AWT / 10000;
        counter = 0;
        ATT = 0;
        AWT = 0;
        while (counter < 100000) {  //repeat the same thing but now from 0 to 100000
            generateProcesses();
            int data[] = scheduler.RR(processList);
            ATT += data[0];
            AWT += data[1];
            counter++;
        }
        double ATT_100000 = ATT / 100000;
        double AWT_100000 = AWT / 100000;
        System.out.println("\t\t100\t\t\t1000\t\t10000\t\t100000");
        System.out.printf("ATT :\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f%n", ATT_100, ATT_1000, ATT_10000, ATT_100000);  //display the averages of ATT for 100 and 1000 and 10000 and 100000 iterations
        System.out.printf("AWT :\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f%n\n\n", AWT_100, AWT_1000, AWT_10000, AWT_100000); //display the averages of AWT for 100 and 1000 and 10000 and 100000 iterations
        scheduler.displayProcesses(processList);

    }

    private static void generateMLFQ() {
        schedulingAlgorithms scheduler = new schedulingAlgorithms();        //assign an object from the scheduling Algorithms class
        int counter = 0; //the counter that will start from 0 till 100000
        //the variables that in the end we will display the result (Average Turned Around Time) and (Average waiting time)
        double ATT = 0;
        double AWT = 0;
        while (counter < 100) {       // the first one is the 100 iterations
            generateProcessesMLFQ();     //generate 8 processes every iteration
            int data[] = scheduler.MLFQ(processList);           //send the list to the scheduling Algorithm to get
            // get the ATT and AWT for a single iteration in the form of array with two indexes
            ATT += data[0];  //get the ATT
            AWT += data[1]; //get the AWT
            counter++;
        }
        double ATT_100 = ATT / 100;   //make the average of ATT from  0 to 100 iterations
        double AWT_100 = AWT / 100;  //make the average of AWT from  0 to 100 iterations


        counter = 0;                //repeat the same thing but now from 0 to 1000
        ATT = 0;
        AWT = 0;
        while (counter < 1000) {
            generateProcessesMLFQ();
            int data[] = scheduler.MLFQ(processList);
            ATT += data[0];
            AWT += data[1];
            counter++;
        }
        double ATT_1000 = ATT / 1000;
        double AWT_1000 = AWT / 1000;
        counter = 0;
        ATT = 0;
        AWT = 0;
        while (counter < 10000) {//repeat the same thing but now from 0 to 10000
            generateProcessesMLFQ();
            int data[] = scheduler.MLFQ(processList);
            ATT += data[0];
            AWT += data[1];
            counter++;
        }
        double ATT_10000 = ATT / 10000;
        double AWT_10000 = AWT / 10000;
        counter = 0;
        ATT = 0;
        AWT = 0;
        while (counter < 100000) {  //repeat the same thing but now from 0 to 100000
            generateProcessesMLFQ();
            int data[] = scheduler.MLFQ(processList);
            ATT += data[0];
            AWT += data[1];
            counter++;
        }
        double ATT_100000 = ATT / 100000;
        double AWT_100000 = AWT / 100000;
        System.out.println("\t\t100\t\t\t1000\t\t10000\t\t100000");
        System.out.printf("ATT :\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f%n", ATT_100, ATT_1000, ATT_10000, ATT_100000);  //display the averages of ATT for 100 and 1000 and 10000 and 100000 iterations
        System.out.printf("AWT :\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f%n\n\n", AWT_100, AWT_1000, AWT_10000, AWT_100000); //display the averages of AWT for 100 and 1000 and 10000 and 100000 iterations
        scheduler.displayProcesses(processList);

    }

    private static void generateProcessesMLFQ() {
        processList.clear(); //make sure the list is empty and cleared so this will give various processes
        List<Process> list = new ArrayList<>();// empty list to set the processes in the main list
        // the random starts sequential till the last process in terms of arrival time
        int min = 0;
        int max = 5;
        for (int i = 0; i < 8; i++) {
            int arrivalTime = (int) (Math.random() * (max - min + 1)) + min;
            min = arrivalTime + 1;
            max = min + 5;
            //the min and max are incrementing with every process generates
            int burstTime = (int) (Math.random() * 95) + 5; // burst time is random from 5 to 100
            list.add(new Process(i, arrivalTime, burstTime, 1));
        }
        processList = list; // add the list to the main list that we will work with
    }

    private static void generateFCFS() {
        schedulingAlgorithms scheduler = new schedulingAlgorithms();    //assign an object from the scheduling Algorithms class (FIRST COME FIRST SERVE)
        int counter = 0;        //the counter that will start from 0 till 100000
        //the variables that in the end we will display the result (Average Turned Around Time) and (Average waiting time)
        double ATT = 0;
        double AWT = 0;
        while (counter < 100) {             // the first one is the 100 iterations
            generateProcesses();            //generate 8 processes every iteration
            int data[] = scheduler.FCFS(processList);                  //send the list to the scheduling Algorithm to get
            ATT += data[0];           //get the ATT                  // the ATT and AWT for a single iteration in the form of array with two indexes
            AWT += data[1];         //get the AWT
            counter++;
        }
        double ATT_100 = ATT / 100;  //make the average of ATT from  0 to 100 iterations
        double AWT_100 = AWT / 100; //make the average of AWT from  0 to 100 iterations

        counter = 0;                   //repeat the same thing but now from 0 to 1000
        ATT = 0;
        AWT = 0;
        while (counter < 1000) {
            generateProcesses();
            int data[] = scheduler.FCFS(processList);
            ATT += data[0];
            AWT += data[1];
            counter++;
        }
        double ATT_1000 = ATT / 1000;
        double AWT_1000 = AWT / 1000;

        counter = 0;            //repeat the same thing but now from 0 to 10000
        ATT = 0;
        AWT = 0;
        while (counter < 10000) {
            generateProcesses();
            int data[] = scheduler.FCFS(processList);
            ATT += data[0];
            AWT += data[1];
            counter++;
        }
        double ATT_10000 = ATT / 10000;
        double AWT_10000 = AWT / 10000;

        counter = 0;            //repeat the same thing but now from 0 to 100000
        ATT = 0;
        AWT = 0;
        while (counter < 100000) {
            generateProcesses();
            int data[] = scheduler.FCFS(processList);
            ATT += data[0];
            AWT += data[1];
            counter++;
        }
        double ATT_100000 = ATT / 100000;
        double AWT_100000 = AWT / 100000;
        System.out.println("\t\t100\t\t\t1000\t\t10000\t\t100000");
        System.out.printf("ATT :\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f%n", ATT_100, ATT_1000, ATT_10000, ATT_100000);   //display the averages of ATT for 100 and 1000 and 10000 and 100000 iterations
        System.out.printf("AWT :\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f%n\n\n", AWT_100, AWT_1000, AWT_10000, AWT_100000);   //display the averages of AWT for 100 and 1000 and 10000 and 100000 iterations
        scheduler.displayProcesses(processList);
    }

    public static void generateProcesses() {
        processList.clear(); //make sure the list is empty and cleared so this will give various processes
        List<Process> list = new ArrayList<>();// empty list to set the processes in the main list
        // the random starts sequential till the last process in terms of arrival time
        int min = 0;
        int max = 5;
        for (int i = 0; i < 8; i++) {
            int arrivalTime = (int) (Math.random() * (max - min + 1)) + min;
            min = arrivalTime + 1;
            max = min + 5;
            //the min and max are incrementing with every process generates
            int burstTime = (int) (Math.random() * 95) + 5; // burst time is random from 5 to 100
            list.add(new Process(i, arrivalTime, burstTime)); //add to the list the process
        }
        processList = list; // add the list to the main list that we will work with
    }
}
