package com.company;

import java.util.Scanner;

public class KontenerWybuchowe extends KontenerCiezki {
    int latwopalnosc;

    public KontenerWybuchowe(int idNadawcy, String zabezpieczenia, String certyfikaty, int tara, int wagaNetto, int idStatku, int wagaBrutto, String kolor, String znak, int latwopalnosc) {
        super(idNadawcy, zabezpieczenia, certyfikaty, tara, wagaNetto, idStatku, wagaBrutto, kolor, znak);
        this.latwopalnosc = latwopalnosc;
    }

    public KontenerWybuchowe(int idNadawcy, String zabezpieczenia, String certyfikaty, int tara, int wagaNetto, int wagaBrutto, String kolor, int latwopalnosc) {
        super(idNadawcy, zabezpieczenia, certyfikaty, tara, wagaNetto, wagaBrutto, kolor);
        this.latwopalnosc = latwopalnosc;
    }


    public KontenerWybuchowe() {
    }

    @Override
    public void dodajKontener() {
        super.dodajKontener();
        System.out.println("podaj latwopalnosc(1-10)");
        Scanner scan = new Scanner(System.in);
        latwopalnosc = scan.nextInt();
        this.latwopalnosc = latwopalnosc;
    }

    @Override
    public String toString() {
        return "Kontener Wybuchowe" + ": " +
                "Id: " + id + "|" +
                "Id nadawcy: " + idNadawcy + "|" +
                "Id Statku: " + idStatku + "|" +
                "Magazyn: " + magazyn + "|" +
                "Tor Kolejowy: " + torKolejowy + "|" +
                "Znak: " + znak + "|" +
                "Zabezpieczenia: " + zabezpieczenia + "|" +
                "Certyfikaty: " + certyfikaty + "|" +
                "Kolor: " + kolor + "|" +
                "Tara: " + tara + "|" +
                "Waga Netto: " + wagaNetto + "|" +
                "Waga Brutto: " + wagaBrutto + "|" +
                "Data na magazynie:" + dataNaMagazyn + "|" +
                "Data utylizacji:" + dataUtylizacji + "|" +
                "Latwopalnosc: " + latwopalnosc + "|" +
                "Status: " + status;
    }

    public String toStringToFile() {
        return "Kontener Wybuchowe" + ": " +
                id + "|" +
                idNadawcy + "|" +
                idStatku + "|" +
                magazyn + "|" +
                torKolejowy + "|" +
                znak + "|" +
                zabezpieczenia + "|" +
                certyfikaty + "|" +
                kolor + "|" +
                tara + "|" +
                wagaNetto + "|" +
                wagaBrutto + "|" +
                dataNaMagazyn + "|" +
                dataUtylizacji + "|" +
                latwopalnosc + "|" +
                status;
    }
}
