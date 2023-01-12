package com.company;

import Interfejsy.Elektrycznosc;

import java.util.Scanner;

public class ColdCont extends HeavyCont implements Elektrycznosc {
    int celsjus;

    public ColdCont(int senderId, String security, String certyfikaty, int tara, int nettoWeight, int shipId, int bruttoWeight, String color, String sign, int celsjus) {
        super(senderId, security, certyfikaty, tara, nettoWeight, shipId, bruttoWeight, color, sign);
        this.celsjus = celsjus;
    }

    public ColdCont(int senderId, String security, String certificates, int tara, int nettoWeight, int bruttoWeight, String color, int celsjus) {
        super(senderId, security, certificates, tara, nettoWeight, bruttoWeight, color);
        this.celsjus = celsjus;
    }

    public ColdCont() {
    }

    ;

    @Override
    public void addCont() {
        super.addCont();
        Scanner scan = new Scanner(System.in);
        System.out.println("Type temperature:");
        celsjus = scan.nextInt();
        this.celsjus = celsjus;
    }

    @Override
    public String toString() {
        return "Cooling Container :" +
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
                "Status:  " + status +
                "Temperature :" + celsjus;
    }


    public String toStringToFile() {
        return "Cooling Container |" +
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
                celsjus + "|" +
                sign + "|" +
                status;

    }

    @Override
    public void GetElektricity() {
        boolean ShipElec = true;
    }
}
