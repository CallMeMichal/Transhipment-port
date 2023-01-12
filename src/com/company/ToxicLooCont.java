package com.company;

import java.util.Scanner;

public class ToxicLooCont extends HeavyCont {
    int loose;

    public ToxicLooCont(int senderId, String security, String certificates, int tara, int nettoWeight, int shipId, int bruttoWeight, String color, String sign, int loose) {
        super(senderId, security, certificates, tara, nettoWeight, shipId, bruttoWeight, color, sign);
        this.loose = loose;
    }

    @Override
    public String toString() {
        return "Container Toxic Loose" + ": " +
                "Id: " + id + "|" +
                "SenderID: " + senderId + "|" +
                "ShipID: " + shipId + "|" +
                "Storage: " + storage + "|" +
                "Railway Track: " + railTrack + "|" +
                "Sign: " + sign + "|" +
                "Security: " + secure + "|" +
                "Certificates: " + certificates + "|" +
                "Color: " + color + "|" +
                "Tara: " + tara + "|" +
                "Netto Weight: " + nettoWeight + "|" +
                "Brutto Weight: " + bruttoWeight + "|" +
                "On storage date:" + storageDate + "|" +
                "Date of disposal:" + deleteDate + "|" +
                "Loose: " + loose + "|" +
                "Status:  " + status;
    }

    public String toStringToFile() {
        return "Container Toxic Loose" + ": " +
                id + "|" +
                senderId + "|" +
                shipId + "|" +
                storage + "|" +
                railTrack + "|" +
                sign + "|" +
                secure + "|" +
                certificates + "|" +
                color + "|" +
                tara + "|" +
                nettoWeight + "|" +
                bruttoWeight + "|" +
                storageDate + "|" +
                deleteDate + "|" +
                loose + "|" +
                status;
    }


    public ToxicLooCont(int senderId, String security, String certificates, int tara, int nettoWeight, int bruttoWeight, String color, int loose) {
        super(senderId, security, certificates, tara, nettoWeight, bruttoWeight, color);
        this.loose = loose;
    }

    public ToxicLooCont() {
    }

    @Override
    public void addCont() {
        super.addCont();

        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Type loose(1-10): ");
            while (!scan.hasNext()) {
                System.out.println("Type number from 1 to 10");
                scan.nextInt();
            }
            loose = scan.nextInt();
            this.loose = loose;
        } while (loose < 0 || loose > 10);

    }

}
