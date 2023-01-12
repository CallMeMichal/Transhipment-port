package com.company;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class BasicCont {

    String sender, secure, certificates;
    public String color;
    int tara, nettoWeight, bruttoWeight, shipId;
    private static final AtomicInteger counter = new AtomicInteger(); //
    public int id = 1;
    int storage = 0;
    int railTrack = 0;
    int senderId = 0;
    int status;
    Date storageDate;
    Date deleteDate;

    public int getStorage() {
        return storage;
    }

    public String getSender() {
        return sender;
    }

    public int getId() {
        return id;
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public int getBruttoWeight() {
        return bruttoWeight;
    }

    public void setDataNaMagazyn() {
        this.storageDate = new Date();
    }

    public BasicCont(int senderId, String secure, String certificates, int tara, int nettoWeight, int shipId, int bruttoWeight, String color) {
        this.senderId = senderId;
        this.secure = secure;
        this.certificates = certificates;
        this.tara = tara;
        this.nettoWeight = nettoWeight;
        this.bruttoWeight = bruttoWeight;
        this.shipId = shipId;
        this.color = color;
        id = counter.incrementAndGet();
        status = 1;
    }

    public BasicCont(int senderId, String secure, String certificates, int tara, int nettoWeight, int bruttoWeight, String color) {
        this.senderId = senderId;
        this.secure = secure;
        this.certificates = certificates;
        this.tara = tara;
        this.nettoWeight = nettoWeight;
        this.bruttoWeight = bruttoWeight;
        this.color = color;
        id = counter.incrementAndGet();
        status = 1;

    }

    public BasicCont() {
    }

    ;

    public void addCont() {
        try {
            Scanner scan = new Scanner(System.in);

            do {
                System.out.println("Type tare ");
                while (!scan.hasNext()) {
                    System.out.println("Enter positive number or zero");
                    scan.nextInt();
                }
                tara = scan.nextInt();
                this.tara = tara;
            } while (tara < 0);

            System.out.println("Enter security: ");
            secure = scan.next();
            this.secure = secure;


            do {
                System.out.println("Enter netto weight: ");
                while (!scan.hasNext()) {
                    System.out.println("Enter positive number or zero");
                    scan.nextInt();
                }
                nettoWeight = scan.nextInt();
                this.nettoWeight = nettoWeight;
            } while (nettoWeight < 0);

            this.bruttoWeight = nettoWeight + tara;

            System.out.println("Enter certificates: ");
            certificates = scan.next();
            this.certificates = certificates;

            System.out.println("Enter color");
            color = scan.next();
            this.color = color;
            id = counter.incrementAndGet();
            status = 1;
        } catch (InputMismatchException e) {
            System.err.println("Type again");
            addCont();
        }
    }

    public Date getStorageDate() {
        return storageDate;
    }


    @Override
    public String toString() {
        return "Basic Container :" +
                "ID:" + id + "|" +
                "Sender ID: " + senderId + "|" +
                "Ship ID" + shipId + "|" +
                "Storage: " + storage + "|" +
                "Railway track:" + railTrack + "|" +
                "Security: " + secure + "|" +
                "Certificates: " + certificates + "|" +
                "Netto weight:" + nettoWeight + "|" +
                "Brutto weight:" + bruttoWeight + "|" +
                "Delete time: " + deleteDate + "|" +
                "Color: " + color + "|" +
                "Tare: " + tara + "|" +
                "Status:" + status;

    }

    public String toStringToFile() {
        return "Basic Container |" +
                id + "|" +
                senderId + "|" +
                shipId + "|" +
                storage + "|" +
                railTrack + "|" +
                secure + "|" +
                certificates + "|" +
                tara + "|" +
                nettoWeight + "|" +
                bruttoWeight + "|" +
                storageDate + "|" +
                deleteDate + "|" +
                color + "|" +
                status;

    }


}
