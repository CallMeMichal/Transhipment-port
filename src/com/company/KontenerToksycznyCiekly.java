package com.company;

import java.util.Scanner;

public class KontenerToksycznyCiekly extends KontenerCiekle {
    int lepkosc;

    public KontenerToksycznyCiekly(int idNadawcy, String zabezpieczenia, String certyfikaty, int tara, int wagaNetto, int idStatku, int wagaBrutto, String kolor, int cieklosc, int lepkosc) {
        super(idNadawcy, zabezpieczenia, certyfikaty, tara, wagaNetto, idStatku, wagaBrutto, kolor, cieklosc);
        this.lepkosc = lepkosc;
    }

    @Override
    public String toString() {
        return "Kontener Toksyczny Ciekly" + ": " +
                "Id: " + id + "|" +
                "Id Nadawcy: " + idNadawcy + "|" +
                "Id statku: " + idStatku + "|" +
                "Magazyn: " + magazyn + "|" +
                "Tor Kolejowy: " + torKolejowy + "|" +
                "Cieklosc: " + cieklosc + "|" +
                "Zabezpieczenia: " + zabezpieczenia + "|" +
                "Certyfikaty: " + certyfikaty + "|" +
                "Kolor: " + kolor + "|" +
                "Tara: " + tara + "|" +
                "Waga Netto: " + wagaNetto + "|" +
                "Waga Brutto: " + wagaBrutto + "|" +
                "Data na Magazynie: " + dataNaMagazyn + "|" +
                "Lepkosc: " + lepkosc + "|" +
                "Status: " + status;
    }

    public String toStringToFile() {
        return "Kontener Toksyczny Ciekly" + ": " +
                id + "|" +
                idNadawcy + "|" +
                idStatku + "|" +
                magazyn + "|" +
                torKolejowy + "|" +
                cieklosc + "|" +
                zabezpieczenia + "|" +
                certyfikaty + "|" +
                kolor + "|" +
                tara + "|" +
                wagaNetto + "|" +
                wagaBrutto + "|" +
                dataNaMagazyn + "|" +
                lepkosc + "|" +
                status;
    }

    public KontenerToksycznyCiekly(int idNadawcy, String zabezpieczenia, String certyfikaty, int tara, int wagaNetto, int wagaBrutto, String kolor, int cieklosc, int lepkosc) {
        super(idNadawcy, zabezpieczenia, certyfikaty, tara, wagaNetto, wagaBrutto, kolor, cieklosc);
        this.lepkosc = lepkosc;
    }

    public KontenerToksycznyCiekly() {
    }

    @Override
    public void dodajKontener() {
        super.dodajKontener();
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("podaj lepkosc(1-10)");
            while (!scan.hasNext()) {
                System.out.println("Wpisz numer dodatni od 1 do 10");
                scan.nextInt();
            }
            lepkosc = scan.nextInt();
            this.lepkosc = lepkosc;
        } while (lepkosc < 0 || lepkosc > 10);

    }

}
