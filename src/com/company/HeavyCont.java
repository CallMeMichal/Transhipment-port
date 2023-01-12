package com.company;

import java.util.Scanner;

public class HeavyCont extends BasicCont {
    public String sign;

    public HeavyCont(int senderId, String security, String certificates, int tara, int nettoWeight, int shipId, int bruttoWeight, String color, String sign) {
        super(senderId, security, certificates, tara, nettoWeight, shipId, bruttoWeight, color);
        this.sign = sign;
    }

    public HeavyCont(int senderId, String security, String certificates, int tara, int nettoWeight, int bruttoWeight, String color) {
        super(senderId, security, certificates, tara, nettoWeight, bruttoWeight, color);
        this.sign = sign;
    }

    public HeavyCont() {
    }

    ;

    @Override
    public void addCont() {
        super.addCont();
        System.out.println("type sign");
        Scanner scan = new Scanner(System.in);
        sign = scan.next();
        this.sign = sign;
    }


    @Override
    public String toString() {
        return "Heavy Container:" + "" +
                "ID: " + id + "|" +
                "Sender ID: " + senderId + "|" +
                "Ship ID: " + shipId + "|" +
                "Storage: " + storage + "|" +
                "Railway track: " + railTrack + "|" +
                "Security: " + secure + "|" +
                "Certificates: " + certificates + "|" +
                "Color: " + color + "|" +
                "Tara: " + tara + "|" +
                "On storage date:" + storageDate + "|" +
                "Delete date:" + deleteDate + "|" +
                "Netto weight: " + nettoWeight + "|" +
                "Brutto weight: " + bruttoWeight + "|" +
                "Sign: " + sign + "|" +
                "Status: " + status;
    }

    public String toStringToFile() {
        return "Heavy Container:" + "" +
                id + "|" +
                senderId + "|" +
                shipId + "|" +
                storage + "|" +
                railTrack + "|" +
                secure + "|" +
                certificates + "|" +
                color + "|" +
                tara + "|" +
                storageDate + "|" +
                deleteDate + "|" +
                nettoWeight + "|" +
                bruttoWeight + "|" +
                sign + "|" +
                status;
    }
}
