package com.company;

import java.util.Scanner;

public class KontenerToksycznySypki extends KontenerCiezki {
    int sypkosc;

    public KontenerToksycznySypki(int idNadawcy, String zabezpieczenia, String certyfikaty, int tara, int wagaNetto, int idStatku, int wagaBrutto, String kolor, String znak, int sypkosc) {
        super(idNadawcy, zabezpieczenia, certyfikaty, tara, wagaNetto, idStatku, wagaBrutto, kolor, znak);
        this.sypkosc = sypkosc;
    }

    @Override
    public String toString() {
        return "Kontener Toksyczny Sypki" + ": " +
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
                "Sypkosc: " + sypkosc + "|" +
                "Status:  " + status;
    }

    public String toStringToFile() {
        return "Kontener Toksyczny Sypki" + ": " +
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
                sypkosc + "|" +
                status;
    }


    public KontenerToksycznySypki(int idNadawcy, String zabezpieczenia, String certyfikaty, int tara, int wagaNetto, int wagaBrutto, String kolor, int sypkosc) {
        super(idNadawcy, zabezpieczenia, certyfikaty, tara, wagaNetto, wagaBrutto, kolor);
        this.sypkosc = sypkosc;
    }

    public KontenerToksycznySypki() {
    }

    @Override
    public void dodajKontener() {
        super.dodajKontener();

        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Podaj sypkosc(1-10): ");
            while (!scan.hasNext()) {
                System.out.println("Wpisz numer dodatni od 1 do 10");
                scan.nextInt();
            }
            sypkosc = scan.nextInt();
            this.sypkosc = sypkosc;
        } while (sypkosc < 0 || sypkosc > 10);

    }

}
