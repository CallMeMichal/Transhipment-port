package com.company;

import java.util.Scanner;

public class KontenerCiekle extends KontenerPodstawowy {
    int cieklosc;

    public KontenerCiekle(int idNadawcy, String zabezpieczenia, String certyfikaty, int tara, int wagaNetto, int idStatku, int wagaBrutto, String kolor, int cieklosc) {
        super(idNadawcy, zabezpieczenia, certyfikaty, tara, wagaNetto, idStatku, wagaBrutto, kolor);
        this.cieklosc = cieklosc;
    }

    public KontenerCiekle(int idNadawcy, String zabezpieczenia, String certyfikaty, int tara, int wagaNetto, int wagaBrutto, String kolor, int cieklosc) {
        super(idNadawcy, zabezpieczenia, certyfikaty, tara, wagaNetto, wagaBrutto, kolor);
        this.cieklosc = cieklosc;
    }

    public KontenerCiekle() {
    }

    @Override
    public void dodajKontener() {
        super.dodajKontener();
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("podaj cieklosc (1-10)");
            while (!scan.hasNext()) {
                System.out.println("Wpisz numer dodatni od 1 do 10");
                scan.nextInt();
            }
            cieklosc = scan.nextInt();
            this.cieklosc = cieklosc;
        } while (cieklosc < 0 || cieklosc > 10);

    }

    @Override
    public String toString() {
        return "Kontener Ciekly:" + ": " +
                "Id:" + id + "|" +
                "Id Statku:" + idStatku + "|" +
                "Magazyn:" + magazyn + "|" +
                "Tor kolejowy:" + torKolejowy + "|" +
                "Id Nadawcy:" + idNadawcy + "|" +
                "Zabezpieczenia:" + zabezpieczenia + "|" +
                "Certyfikaty:" + certyfikaty + "|" +
                "Kolor:" + kolor + "|" +
                "Tara:" + tara + "|" +
                "Waga Netto:" + wagaNetto + "|" +
                "Waga Brutto:" + wagaBrutto + "|" +
                "Data na magazynie:" + dataNaMagazyn + "|" +
                "Data utylizacji:" + dataUtylizacji + "|" +
                "Status:" + status + "|" +
                "Cieklosc(1-10): " + cieklosc;
    }

    public String toStringToFile() {
        return "Kontener Ciekly:" + ": " +
                id + "|" +
                idStatku + "|" +
                magazyn + "|" +
                torKolejowy + "|" +
                idNadawcy + "|" +
                zabezpieczenia + "|" +
                certyfikaty + "|" +
                kolor + "|" +
                tara + "|" +
                wagaNetto + "|" +
                wagaBrutto + "|" +
                dataNaMagazyn + "|" +
                dataUtylizacji + "|" +
                status + "|" +
                cieklosc;
    }
}
