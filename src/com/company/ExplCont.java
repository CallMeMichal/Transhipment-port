package com.company;

import java.util.Scanner;

public class ExplCont extends HeavyCont {
    int inflammability;

    public ExplCont(int senderId, String security, String certificates, int tara, int nettoWeight, int shipId, int bruttoWeight, String color, String sign, int inflammability) {
        super(senderId, security, certificates, tara, nettoWeight, shipId, bruttoWeight, color, sign);
        this.inflammability = inflammability;
    }

    public ExplCont(int senderId, String security, String certificates, int tara, int nettoWeight, int bruttoWeight, String color, int inflammability) {
        super(senderId, security, certificates, tara, nettoWeight, bruttoWeight, color);
        this.inflammability = inflammability;
    }


    public ExplCont() {
    }

    @Override
    public void addCont() {
        super.addCont();
        System.out.println("enter inflammability(1-10)");
        Scanner scan = new Scanner(System.in);
        inflammability = scan.nextInt();
        this.inflammability = inflammability;
    }

    @Override
    public String toString() {
        return "Explosive Container" + ": " +
                "ID: " + id + "|" +
                "Sender ID: " + senderId + "|" +
                "Ship ID: " + shipId + "|" +
                "Storage: " + storage + "|" +
                "Railway track: " + railTrack + "|" +
                "Sign: " + sign + "|" +
                "Security: " + secure + "|" +
                "Certificates: " + certificates + "|" +
                "Color: " + color + "|" +
                "Tara: " + tara + "|" +
                "Netto weight: " + nettoWeight + "|" +
                "Brutto weight: " + bruttoWeight + "|" +
                "On storage date:" + storageDate + "|" +
                "Delete date:" + deleteDate + "|" +
                "Inflammability: " + inflammability + "|" +
                "Status: " + status;
    }

    public String toStringToFile() {
        return "Explosive Container" + ": " +
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
                inflammability + "|" +
                status;
    }
}
