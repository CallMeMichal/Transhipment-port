package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Ship {

    int maxExpTox, maxHeavy, MaxNetw, maxAll, maxCapacity, capacity, id, numberMaxExt;
    String name, homePort, sourceLocation, targetLocation;
    private static final AtomicInteger counter = new AtomicInteger();

    public Ship(int maxExpTox, int maxHeavy, int MaxNetw, int maxAll, int maxCapacity, int capacity, String name, String homePort, String sourceLocation, String targetLocation) {
        this.maxExpTox = maxExpTox;
        this.maxHeavy = maxHeavy;
        this.MaxNetw = MaxNetw;
        this.maxAll = maxAll;
        this.maxCapacity = maxCapacity;
        this.capacity = capacity;
        this.name = name;
        this.homePort = homePort;
        this.sourceLocation = sourceLocation;
        this.targetLocation = targetLocation;
        id = counter.incrementAndGet();
        this.id = id;

    }

    public Ship() {
    }

    ;

    public void addShip() {
        try {
            Scanner scan = new Scanner(System.in);

            System.out.println("Enter name");
            name = scan.next();
            this.name = name;

            System.out.println("Enter home port");
            homePort = scan.next();
            this.homePort = homePort;

            System.out.println("Enter source location");
            sourceLocation = scan.next();
            this.sourceLocation = sourceLocation;

            System.out.println("Enter destination location");
            targetLocation = scan.next();
            this.targetLocation = targetLocation;

            do {
                System.out.println("Give MAX number of Toxic containers and Explosive containers ");
                while (!scan.hasNext()) {
                    System.out.println("Enter positive number or 0");
                    scan.nextInt();
                }
                maxExpTox = scan.nextInt();
                this.maxExpTox = maxExpTox;
            } while (maxExpTox < 0);


            do {
                System.out.println("Specify the MAX number of heavy containers: ");
                while (!scan.hasNext()) {
                    System.out.println("Enter positive number or 0");
                    scan.nextInt();
                }
                maxHeavy = scan.nextInt();
                this.maxHeavy = maxHeavy;
            } while (maxHeavy < 0);

            do {
                System.out.println("Specify the MAX number of network containers: ");
                while (!scan.hasNext()) {
                    System.out.println("Enter positive number or 0");
                    scan.nextInt();
                }
                MaxNetw = scan.nextInt();
                this.MaxNetw = MaxNetw;
            } while (MaxNetw < 0);


            do {
                System.out.println("Enter the MAX number of all containers: ");
                while (!scan.hasNext()) {
                    System.out.println("Enter positive number or 0");
                    scan.nextInt();
                }
                maxAll = scan.nextInt();
                this.maxAll = maxAll;
            } while (maxAll < 0);


            do {
                    System.out.println("Specify the MAX capacity of the ship in kg: ");
                while (!scan.hasNext()) {
                    System.out.println("Enter positive number or 0");
                    scan.nextInt();
                }
                maxCapacity = scan.nextInt();
                this.maxCapacity = maxCapacity;
            } while (maxCapacity < 0);

            id = counter.incrementAndGet();
        } catch (InputMismatchException e) {
            System.err.println("Type again");
            addShip();
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Shipk " +
                " ID: " + id + "," +
                " Quantity of Explosive or Toxic: " + numberMaxExt + "," +
                " Max Explosive or Toxic: " + maxExpTox + "," +
                " Max Heavy: " + maxHeavy + "," +
                " Max Networking: " + MaxNetw + "," +
                " Max All: " + maxAll + "," +
                " Max Capacity: " + maxCapacity + "," +
                " Current Load Capacity: " + capacity + "," +
                " Name of Ship: " + name + "," +
                " Home Port'" + homePort + "," +
                " Zrodlowa Location: " + sourceLocation + "," +
                " Target Location: " + targetLocation + ",";
    }

    public String toStringToFile() {
        return "Ship " +
                id + "|" +
                numberMaxExt + "|" +
                maxExpTox + "|" +
                maxHeavy + "|" +
                MaxNetw + "|" +
                maxAll + "|" +
                maxCapacity + "|" +
                capacity + "|" +
                name + "|" +
                homePort + "|" +
                sourceLocation + "|" +
                targetLocation + "|";
    }
}
