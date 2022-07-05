package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Statek {

    int maxWybTox, maxCiezkie, maxSieciowe, maxWszystke, maxLadownosc, ladownosc, id, iloscmaxWybTox;
    String nazwa, portMacierzysty, LokZrodlowa, LokDocelowa;
    private static final AtomicInteger counter = new AtomicInteger();

    public Statek(int maxWybTox, int maxCiezkie, int maxSieciowe, int maxWszystke, int maxLadownosc, int ladownosc, String nazwa, String portMacierzysty, String lokZrodlowa, String lokDocelowa) {
        this.maxWybTox = maxWybTox;
        this.maxCiezkie = maxCiezkie;
        this.maxSieciowe = maxSieciowe;
        this.maxWszystke = maxWszystke;
        this.maxLadownosc = maxLadownosc;
        this.ladownosc = ladownosc;
        this.nazwa = nazwa;
        this.portMacierzysty = portMacierzysty;
        LokZrodlowa = lokZrodlowa;
        LokDocelowa = lokDocelowa;
        id = counter.incrementAndGet();
        this.id = id;

    }

    public Statek() {
    }

    ;

    public void DodajStatek() {
        try {
            Scanner scan = new Scanner(System.in);

            System.out.println("Podaj nazwe");
            nazwa = scan.next();
            this.nazwa = nazwa;

            System.out.println("Podaj port macierzysty");
            portMacierzysty = scan.next();
            this.portMacierzysty = portMacierzysty;

            System.out.println("Podaj lokalizacje zrodlowa");
            LokZrodlowa = scan.next();
            this.LokZrodlowa = LokZrodlowa;

            System.out.println("Podaj lokalizacje docelowa");
            LokDocelowa = scan.next();
            this.LokDocelowa = LokDocelowa;

            do {
                System.out.println("Podaj MAX liczbe Toksycznych kontenerow i Wybuchowych kontenerow ");
                while (!scan.hasNext()) {
                    System.out.println("Wpisz numer dodatni lub 0");
                    scan.nextInt();
                }
                maxWybTox = scan.nextInt();
                this.maxWybTox = maxWybTox;
            } while (maxWybTox < 0);


            do {
                System.out.println("Podaj MAX liczbe ciezkich kontenerow: ");
                while (!scan.hasNext()) {
                    System.out.println("Wpisz numer dodatni lub 0");
                    scan.nextInt();
                }
                maxCiezkie = scan.nextInt();
                this.maxCiezkie = maxCiezkie;
            } while (maxCiezkie < 0);

            do {
                System.out.println("Podaj MAX liczbe sieciowych kontenerow: ");
                while (!scan.hasNext()) {
                    System.out.println("Wpisz numer dodatni lub 0");
                    scan.nextInt();
                }
                maxSieciowe = scan.nextInt();
                this.maxSieciowe = maxSieciowe;
            } while (maxSieciowe < 0);


            do {
                System.out.println("Podaj MAX liczbe wszystkich kontenerow: ");
                while (!scan.hasNext()) {
                    System.out.println("Wpisz numer dodatni lub 0");
                    scan.nextInt();
                }
                maxWszystke = scan.nextInt();
                this.maxWszystke = maxWszystke;
            } while (maxWszystke < 0);


            do {
                System.out.println("Podaj MAX ladownosc statku w kg: ");
                while (!scan.hasNext()) {
                    System.out.println("Wpisz numer dodatni lub 0");
                    scan.nextInt();
                }
                maxLadownosc = scan.nextInt();
                this.maxLadownosc = maxLadownosc;
            } while (maxLadownosc < 0);

            id = counter.incrementAndGet();
        } catch (InputMismatchException e) {
            System.err.println("Wprowadz statek jeszcze raz");
            DodajStatek();
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Statek " +
                " ID: " + id + "," +
                " Ilosc Wybuchowych lub Toksycznych: " + iloscmaxWybTox + "," +
                " Max Wybuchowe lub Toksyczne: " + maxWybTox + "," +
                " Max Ciezkie: " + maxCiezkie + "," +
                " Max Sieciowe: " + maxSieciowe + "," +
                " Max Wszystkie: " + maxWszystke + "," +
                " Max Ladownosc: " + maxLadownosc + "," +
                " Aktualna Ladownosc: " + ladownosc + "," +
                " Nazwa Statku: " + nazwa + "," +
                " Port Macierzysty'" + portMacierzysty + "," +
                " Lokalizacja Zrodlowa: " + LokZrodlowa + "," +
                " Lokalizacja Docelowa: " + LokDocelowa + ",";
    }

    public String toStringToFile() {
        return "Statek " +
                id + "|" +
                iloscmaxWybTox + "|" +
                maxWybTox + "|" +
                maxCiezkie + "|" +
                maxSieciowe + "|" +
                maxWszystke + "|" +
                maxLadownosc + "|" +
                ladownosc + "|" +
                nazwa + "|" +
                portMacierzysty + "|" +
                LokZrodlowa + "|" +
                LokDocelowa + "|";
    }
}
