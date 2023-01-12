package com.company;

import java.util.Scanner;

public class ToxLiqCont extends LiquidCont {
    int viscosity;

    public ToxLiqCont(int senderId, String security, String certificates, int tara, int nettoWeight, int shipId, int bruttoWeight, String color, int liquidity, int viscosity) {
        super(senderId, security, certificates, tara, nettoWeight, shipId, bruttoWeight, color, liquidity);
        this.viscosity = viscosity;
    }

    @Override
    public String toString() {
        return "Toxic Liquid Contaiener" + ": " +
                "ID: " + id + "|" +
                "SenderID: " + senderId + "|" +
                "ShipID: " + shipId + "|" +
                "Storage: " + storage + "|" +
                "Railway Track: " + railTrack + "|" +
                "Liquidity: " + fluidity + "|" +
                "Security: " + secure + "|" +
                "Certificates: " + certificates + "|" +
                "Color: " + color + "|" +
                "Tara: " + tara + "|" +
                "Netto weight: " + nettoWeight + "|" +
                "Brutto weight: " + bruttoWeight + "|" +
                "On storage date: " + storageDate + "|" +
                "viscosity: " + viscosity + "|" +
                "Status: " + status;
    }

    public String toStringToFile() {
        return "Toxic Liquid Contaiener" + ": " +
                id + "|" +
                senderId + "|" +
                shipId + "|" +
                storage + "|" +
                railTrack + "|" +
                fluidity + "|" +
                secure + "|" +
                certificates + "|" +
                color + "|" +
                tara + "|" +
                nettoWeight + "|" +
                bruttoWeight + "|" +
                storageDate + "|" +
                viscosity + "|" +
                status;
    }

    public ToxLiqCont(int senderId, String security, String certificates, int tara, int nettoWeight, int bruttoWeight, String color, int liquidity, int viscosity) {
        super(senderId, security, certificates, tara, nettoWeight, bruttoWeight, color, liquidity);
        this.viscosity = viscosity;
    }

    public ToxLiqCont() {
    }

    @Override
    public void addCont() {
        super.addCont();
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("type viscosity(1-10)");
            while (!scan.hasNext()) {
                System.out.println("Type number between 1 and 10");
                scan.nextInt();
            }
            viscosity = scan.nextInt();
            this.viscosity = viscosity;
        } while (viscosity < 0 || viscosity > 10);

    }

}
