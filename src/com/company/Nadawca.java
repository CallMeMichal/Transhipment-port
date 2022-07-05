package com.company;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Nadawca {
    String imie, adres, nazwisko;
    String pesel;
    int dataUrodzenia;
    int idNadawca;
    int Ostrzerzenie;
    LocalDate dataUrodzeniaZPsel;
    private static final AtomicInteger counter = new AtomicInteger();

    public Nadawca(String imie, String nazwisko, String adres, String pesel) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.adres = adres;
        this.pesel = pesel;
        idNadawca = counter.incrementAndGet();
        this.idNadawca = idNadawca;
        ObliczanieDaty();
    }

    public Nadawca() {
    }

    public void DodajNadawce() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Podaj Imie Nadawcy:");
        imie = scan.next();
        this.imie = imie;

        System.out.println("Podaj Nazwisko Nadawcy:");
        nazwisko = scan.next();
        this.nazwisko = nazwisko;

        System.out.println("Podaj Adres Nadawcy:");
        adres = scan.next();
        this.adres = adres;

        System.out.println("Podaj Pesel Nadawcy:");
        pesel = scan.next();
        this.pesel = pesel;
        ObliczanieDaty();
        idNadawca = counter.incrementAndGet();
    }

    public void ObliczanieDaty() {
        var rokString = pesel.substring(0, 2);
        String rok;
        String miesiac;
        String dzien;
        if (Integer.parseInt(rokString) > 22) {
            rok = 19 + rokString;
            miesiac = pesel.substring(2, 4);
            dzien = pesel.substring(4, 6);
            String pelnaData = dzien + "/" + miesiac + "/" + rok;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(pelnaData, formatter);
            dataUrodzeniaZPsel = localDate;
        } else if (Integer.parseInt(rokString) < 22) {
            rok = 20 + rokString;
            miesiac = pesel.substring(2, 4);
            dzien = pesel.substring(4, 6);
            String pelnaData = dzien + "/" + miesiac + "/" + rok;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(pelnaData, formatter);
            dataUrodzeniaZPsel = localDate;
        }
    }


    @Override
    public String toString() {
        return "Nadawca: " +
                "imie='" + imie + "|" +
                "adres='" + adres + "|" +
                "nazwisko='" + nazwisko + "|" +
                "pesel=" + pesel + "|" +
                "dataUrodzenia=" + dataUrodzeniaZPsel + "|" +
                "idNadawca=" + idNadawca;
    }

    public String toStringToFile() {
        return "Nadawca: " +
                imie + "|" +
                adres + "|" +
                nazwisko + "|" +
                pesel + "|" +
                dataUrodzeniaZPsel + "|" +
                idNadawca;
    }
}
