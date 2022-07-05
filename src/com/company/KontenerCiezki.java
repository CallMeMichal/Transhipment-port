package com.company;

import java.util.Scanner;

public class KontenerCiezki extends KontenerPodstawowy {
    public String znak;

    public KontenerCiezki(int idNadawcy, String zabezpieczenia, String certyfikaty, int tara, int wagaNetto, int idStatku, int wagaBrutto, String kolor, String znak) {
        super(idNadawcy, zabezpieczenia, certyfikaty, tara, wagaNetto, idStatku, wagaBrutto, kolor);
        this.znak = znak;
    }

    public KontenerCiezki(int idNadawcy, String zabezpieczenia, String certyfikaty, int tara, int wagaNetto, int wagaBrutto, String kolor) {
        super(idNadawcy, zabezpieczenia, certyfikaty, tara, wagaNetto, wagaBrutto, kolor);
        this.znak = znak;
    }

    public KontenerCiezki() {
    }

    ;

    @Override
    public void dodajKontener() {
        super.dodajKontener();
        System.out.println("podaj znak");
        Scanner scan = new Scanner(System.in);
        znak = scan.next();
        this.znak = znak;
    }


    @Override
    public String toString() {
        return "Kontner Ciezki:" + "" +
                "Id: " + id + "|" +
                "Id Nadawcy: " + idNadawcy + "|" +
                "Id Statku: " + idStatku + "|" +
                "Magazyn: " + magazyn + "|" +
                "Tor Kolejowy: " + torKolejowy + "|" +
                "Zabezpieczenia: " + zabezpieczenia + "|" +
                "Certyfikaty: " + certyfikaty + "|" +
                "Kolor: " + kolor + "|" +
                "Tara: " + tara + "|" +
                "Data na magazynie:" + dataNaMagazyn + "|" +
                "Data utylizacji:" + dataUtylizacji + "|" +
                "Waga Netto: " + wagaNetto + "|" +
                "Waga Brutto: " + wagaBrutto + "|" +
                "Znak: " + znak + "|" +
                "Status: " + status;
    }

    public String toStringToFile() {
        return "Kontner Ciezki:" + "" +
                id + "|" +
                idNadawcy + "|" +
                idStatku + "|" +
                magazyn + "|" +
                torKolejowy + "|" +
                zabezpieczenia + "|" +
                certyfikaty + "|" +
                kolor + "|" +
                tara + "|" +
                dataNaMagazyn + "|" +
                dataUtylizacji + "|" +
                wagaNetto + "|" +
                wagaBrutto + "|" +
                znak + "|" +
                status;
    }
}
