package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Sender {
    String name, adres, surname;
    String pesel;
    int senderId;
    int warnings;
    LocalDate peselDate;
    private static final AtomicInteger counter = new AtomicInteger();

    public Sender(String name, String surname, String adres, String pesel) {
        this.name = name;
        this.surname = surname;
        this.adres = adres;
        this.pesel = pesel;
        senderId = counter.incrementAndGet();
        this.senderId = senderId;
        dateCalcul();
    }

    public Sender() {
    }

    public void addSender() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter name:");
        name = scan.next();
        this.name = name;

        System.out.println("Enter surname:");
        surname = scan.next();
        this.surname = surname;

        System.out.println("Enter adress:");
        adres = scan.next();
        this.adres = adres;

        System.out.println("Enter pesel number:");
        pesel = scan.next();
        this.pesel = pesel;
        dateCalcul();
        senderId = counter.incrementAndGet();
    }

    public void dateCalcul() {
        var rokString = pesel.substring(0, 2);
        String year;
        String month;
        String day;
        if (Integer.parseInt(rokString) > 22) {
            year = 19 + rokString;
            month = pesel.substring(2, 4);
            day = pesel.substring(4, 6);
            String pelnaData = day + "/" + month + "/" + year;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(pelnaData, formatter);
            peselDate = localDate;
        } else if (Integer.parseInt(rokString) < 22) {
            year = 20 + rokString;
            month = pesel.substring(2, 4);
            day = pesel.substring(4, 6);
            String pelnaData = day + "/" + month + "/" + year;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(pelnaData, formatter);
            peselDate = localDate;
        }
    }


    @Override
    public String toString() {
        return "Sender: " +
                "name='" + name + "|" +
                "adres='" + adres + "|" +
                "surname='" + surname + "|" +
                "pesel=" + pesel + "|" +
                "BirthDate=" + peselDate + "|" +
                "senderId=" + senderId;
    }

    public String toStringToFile() {
        return "Sender: " +
                name + "|" +
                adres + "|" +
                surname + "|" +
                pesel + "|" +
                peselDate + "|" +
                senderId;
    }
}
