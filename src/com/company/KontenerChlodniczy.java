package com.company;

import Interfejsy.Elektrycznosc;

import java.util.Scanner;

public class KontenerChlodniczy extends KontenerCiezki implements Elektrycznosc {
    int temperatura;

    public KontenerChlodniczy(int idNadawcy, String zabezpieczenia, String certyfikaty, int tara, int wagaNetto, int idStatku, int wagaBrutto, String kolor, String znak, int temperatura) {
        super(idNadawcy, zabezpieczenia, certyfikaty, tara, wagaNetto, idStatku, wagaBrutto, kolor, znak);
        this.temperatura = temperatura;
    }

    public KontenerChlodniczy(int idNadawcy, String zabezpieczenia, String certyfikaty, int tara, int wagaNetto, int wagaBrutto, String kolor, int temperatura) {
        super(idNadawcy, zabezpieczenia, certyfikaty, tara, wagaNetto, wagaBrutto, kolor);
        this.temperatura = temperatura;
    }

    public KontenerChlodniczy() {
    }

    ;

    @Override
    public void dodajKontener() {
        super.dodajKontener();
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj Temperature");
        temperatura = scan.nextInt();
        this.temperatura = temperatura;
    }

    @Override
    public String toString() {
        return "Kontener Chlodniczy :" +
                "Id: " + id + "|" +
                "Id Nadawcy: " + idNadawcy + "|" +
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
                "Status:  " + status +
                "Temperatura :" + temperatura;
    }


    public String toStringToFile() {
        return "Kontener Chlodniczy |" +
                id + "|" +
                idNadawcy + "|" +
                idStatku + "|" +
                magazyn + "|" +
                torKolejowy + "|" +
                zabezpieczenia + "|" +
                certyfikaty + "|" +
                tara + "|" +
                wagaNetto + "|" +
                wagaBrutto + "|" +
                dataNaMagazyn + "|" +
                dataUtylizacji + "|" +
                kolor + "|" +
                temperatura + "|" +
                znak + "|" +
                status;

    }

    @Override
    public void GetElektrycznosc() {
        boolean pradStatek = true;
    }
}
