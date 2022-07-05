package com.company;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class KontenerPodstawowy {

    String nadawca, zabezpieczenia, certyfikaty;
    public String kolor;
    int tara, wagaNetto, wagaBrutto, idStatku;
    private static final AtomicInteger counter = new AtomicInteger(); //
    public int id = 1;
    int magazyn = 0;
    int torKolejowy = 0;
    int idNadawcy = 0;
    int status;
    Date dataNaMagazyn;
    Date dataUtylizacji;

    public int getMagazyn() {
        return magazyn;
    }

    public String getNadawca() {
        return nadawca;
    }

    public int getId() {
        return id;
    }

    public int getIdStatku() {
        return idStatku;
    }

    public void setIdStatku(int idStatku) {
        this.idStatku = idStatku;
    }

    public int getWagaBrutto() {
        return wagaBrutto;
    }

    public void setDataNaMagazyn() {
        this.dataNaMagazyn = new Date();
    }

    // jak dodajemy w locie to musimy miec przypisanie kontenera do okreslnego statku;
    public KontenerPodstawowy(int idNadawcy, String zabezpieczenia, String certyfikaty, int tara, int wagaNetto, int idStatku, int wagaBrutto, String kolor) {
        this.idNadawcy = idNadawcy;
        this.zabezpieczenia = zabezpieczenia;
        this.certyfikaty = certyfikaty;
        this.tara = tara;
        this.wagaNetto = wagaNetto;
        this.wagaBrutto = wagaBrutto;
        this.idStatku = idStatku;
        this.kolor = kolor;
        id = counter.incrementAndGet();
        status = 1;
    }

    public KontenerPodstawowy(int idNadawcy, String zabezpieczenia, String certyfikaty, int tara, int wagaNetto, int wagaBrutto, String kolor) {
        this.idNadawcy = idNadawcy;
        this.zabezpieczenia = zabezpieczenia;
        this.certyfikaty = certyfikaty;
        this.tara = tara;
        this.wagaNetto = wagaNetto;
        this.wagaBrutto = wagaBrutto;
        this.kolor = kolor;
        id = counter.incrementAndGet();
        status = 1;

    }

    public KontenerPodstawowy() {
    }

    ;

    public void dodajKontener() {
        try {
            Scanner scan = new Scanner(System.in);

            do {
                System.out.println("Podaj tare: ");
                while (!scan.hasNext()) {
                    System.out.println("Wpisz numer dodatni lub 0");
                    scan.nextInt();
                }
                tara = scan.nextInt();
                this.tara = tara;
            } while (tara < 0);

            System.out.println("Podaj zabezpieczenia: ");
            zabezpieczenia = scan.next();
            this.zabezpieczenia = zabezpieczenia;


            do {
                System.out.println("Podaj wage netto: ");
                while (!scan.hasNext()) {
                    System.out.println("Wpisz numer dodatni lub 0");
                    scan.nextInt();
                }
                wagaNetto = scan.nextInt();
                this.wagaNetto = wagaNetto;
            } while (wagaNetto < 0);

            this.wagaBrutto = wagaNetto + tara;

            System.out.println("Podaj certyfikaty: ");
            certyfikaty = scan.next();
            this.certyfikaty = certyfikaty;

            System.out.println("Podaj kolor");
            kolor = scan.next();
            this.kolor = kolor;
            id = counter.incrementAndGet();
            status = 1;
        } catch (InputMismatchException e) {
            System.err.println("Wprowadz dane ponownie");
            dodajKontener();
        }
    }

    public Date getDataNaMagazyn() {
        return dataNaMagazyn;
    }


    @Override
    public String toString() {
        return "Kontener Podstawowy :" +
                "Id:" + id + "|" +
                "IdNadawcy: " + idNadawcy + "|" +
                "ID Statku" + idStatku + "|" +
                "Magazyn: " + magazyn + "|" +
                "Tor kolejowy:" + torKolejowy + "|" +
                "Zabezpieczenia: " + zabezpieczenia + "|" +
                "Certyfikaty: " + certyfikaty + "|" +
                "Waga Netto:" + wagaNetto + "|" +
                "Waga Brutto:" + wagaBrutto + "|" +
                "Data Utylizacji: " + dataUtylizacji + "|" +
                "Kolor: " + kolor + "|" +
                "Tara: " + tara + "|" +
                "Status:" + status;

    }

    public String toStringToFile() {
        return "Kontener Podstawowy |" +
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
                status;

    }


}
