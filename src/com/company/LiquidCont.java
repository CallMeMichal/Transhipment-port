package com.company;

import java.util.Scanner;

public class LiquidCont extends BasicCont {
    int fluidity;

    public LiquidCont(int senderId, String security, String certificates, int tara, int nettoWeight, int shipId, int bruttoWeight, String color, int fluidity) {
        super(senderId, security, certificates, tara, nettoWeight, shipId, bruttoWeight, color);
        this.fluidity = fluidity;
    }

    public LiquidCont(int senderId, String security, String certificates, int tara, int nettoWeight, int bruttoWeight, String color, int fluidity) {
        super(senderId, security, certificates, tara, nettoWeight, bruttoWeight, color);
        this.fluidity = fluidity;
    }

    public LiquidCont() {
    }

    @Override
    public void addCont() {
        super.addCont();
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("enter fluidity (1-10)");
            while (!scan.hasNext()) {
                System.out.println("Enter number between 1 and 10");
                scan.nextInt();
            }
            fluidity = scan.nextInt();
            this.fluidity = fluidity;
        } while (fluidity < 0 || fluidity > 10);

    }

    @Override
    public String toString() {
        return "Liquid Container:" + ": " +
                "Id:" + id + "|" +
                "Ship ID:" + shipId + "|" +
                "Storage:" + storage + "|" +
                "Railway track:" + railTrack + "|" +
                "Sender ID:" + senderId + "|" +
                "Security:" + secure + "|" +
                "Certificates:" + certificates + "|" +
                "Color:" + color + "|" +
                "Tara:" + tara + "|" +
                "Netto Weight:" + nettoWeight + "|" +
                "Brutto Weight:" + bruttoWeight + "|" +
                "On storage date:" + storageDate + "|" +
                "Delete Time:" + deleteDate + "|" +
                "Status:" + status + "|" +
                "Fluidity(1-10): " + fluidity;
    }

    public String toStringToFile() {
        return "Liquid Container:" + ": " +
                id + "|" +
                shipId + "|" +
                storage + "|" +
                railTrack + "|" +
                senderId + "|" +
                secure + "|" +
                certificates + "|" +
                color + "|" +
                tara + "|" +
                nettoWeight + "|" +
                bruttoWeight + "|" +
                storageDate + "|" +
                deleteDate + "|" +
                status + "|" +
                fluidity;
    }
}
