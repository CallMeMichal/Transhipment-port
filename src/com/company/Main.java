package com.company;

import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Statek> listaStatkow = new ArrayList<Statek>();
    static ArrayList<KontenerPodstawowy> listaKontenerow = new ArrayList<KontenerPodstawowy>();
    static ArrayList<Nadawca> listaNadawcow = new ArrayList<Nadawca>();


    static Date czas = new Date();

    static Magazyn magazyn = new Magazyn(100);
    static TorKolejowy tor = new TorKolejowy();


    public static void main(String[] args) throws InterruptedException, IOException {
        KontneryIStatki();


        new Thread(new Runnable() {
            public void run() {
                int dzien = 1;
                while (true) {

                    try {
                        System.out.println("dzień " + dzien + " " + czas);
                        Thread.sleep(5000);
                        for (KontenerPodstawowy kontener : listaKontenerow.stream().filter(x -> x.magazyn == 1 && x.status == 1).toList()) {
                            if (kontener instanceof KontenerWybuchowe) {
                                Calendar c = Calendar.getInstance();
                                c.setTime(kontener.dataNaMagazyn);
                                c.add(Calendar.DATE, 5);
                                var limitCzasowy = c.getTime();
                                if (limitCzasowy.before(czas)) {
                                    var nadawcaa = listaNadawcow.stream().filter(x -> x.idNadawca == kontener.idNadawcy).findFirst().get();

                                    nadawcaa.Ostrzerzenie += 1;
                                    System.out.println("Kontener wybuchowy jest magazynowany dłużej niż 5 dni, nastepuje utylizacja, nadawca " + nadawcaa.imie + " " + nadawcaa.nazwisko + " dostaje  " + nadawcaa.Ostrzerzenie + " upomnienie");
                                    kontener.status = -1;
                                    kontener.dataUtylizacji = czas;
                                    throw new IrresponsibleSenderWithDangerousGoods(kontener);
                                }

                            } else if (kontener instanceof KontenerToksycznyCiekly) {

                                Calendar c = Calendar.getInstance();
                                c.setTime(kontener.dataNaMagazyn);
                                c.add(Calendar.DATE, 10);
                                var limitCzasowy = c.getTime();
                                if (limitCzasowy.before(czas)) {
                                    var nadawcaa = listaNadawcow.stream().filter(x -> x.idNadawca == kontener.idNadawcy).findFirst().get();
                                    nadawcaa.Ostrzerzenie += 1;
                                    kontener.status = -1;
                                    kontener.dataUtylizacji = czas;
                                    System.out.println("Kontener Toksyczny Ciekly jest magazynowany dłużej niż 10 dni, nastepuje utylizacja, nadawca " + nadawcaa.imie + " " + nadawcaa.nazwisko + " dostaje pierwsze upomnienie");
                                    throw new IrresponsibleSenderWithDangerousGoods(kontener);

                                }

                            } else if (kontener instanceof KontenerToksycznySypki) {
                                Calendar c = Calendar.getInstance();
                                c.setTime(kontener.dataNaMagazyn);
                                c.add(Calendar.DATE, 14);
                                var limitCzasowy = c.getTime();
                                if (limitCzasowy.before(czas)) {
                                    var nadawcaa = listaNadawcow.stream().filter(x -> x.idNadawca == kontener.idNadawcy).findFirst().get();
                                    nadawcaa.Ostrzerzenie += 1;
                                    System.out.println("Kontener Toksyczny Ciekly jest magazynowany dłużej niż 14 dni, nastepuje utylizacja, nadawca " + nadawcaa.imie + " " + nadawcaa.nazwisko + " dostaje pierwsze upomnienie");
                                    kontener.status = -1;
                                    kontener.dataUtylizacji = czas;
                                    throw new IrresponsibleSenderWithDangerousGoods(kontener);
                                }
                            }
                        }
                        Calendar c = Calendar.getInstance();
                        c.setTime(czas);
                        c.add(Calendar.DATE, 1);
                        czas = c.getTime();
                        dzien++;
                    } catch (InterruptedException | IrresponsibleSenderWithDangerousGoods e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        Scanner scan = new Scanner(System.in);
        int opcja;

        do {
            System.out.println(
                    "s25645" + "\n" +
                            "Michał Tulej" + "\n" +

                            "1.Zładowanie wskazanego kontenera ze statku na wagon/magazyn \n" +
                            "2.Załadowania na wskazany statek wskazanego kontenera \n" +
                            "3.Informacje o statku i kontenerach na nim\n" +
                            "4.Stan magazynu \n" +
                            "5.utylizacja kontenera\n" +
                            "6.Stworzenie Statku \n" +
                            "7.Stworzenie kontenera\n" +
                            "8.Dodanie Nadawcy\n" +
                            "9.Zapis\n" +
                            "10.Załadowanie z magazynu na statek kontnera\n" +
                            "11.Wyjscie"

            );
            opcja = scan.nextInt();
            switch (opcja) {
                //zladowanie kontnera do magazynu lub toru
                case 1: {

                    for (var statek : listaStatkow.stream().toList()) {
                        System.out.println(statek.id + " " + statek);
                    }
                    System.out.println("Wybierz Statek");
                    int wyborStatku = scan.nextInt();


                    for (KontenerPodstawowy kontener : listaKontenerow.stream().filter(p -> p.idStatku == wyborStatku && p.status != -1).toList()) {

                        System.out.println((kontener.id) + " " + kontener);

                    }
                    System.out.println("Wybierz id kontnera");
                    int wyborKontenera = scan.nextInt();

                    System.out.println("Gdzie chcesz rozladowac kontner: 1.Magazyn 2.Tor Kolejowy 3.Wyjscie");
                    int wyborRozladunku = scan.nextInt();

                    switch (wyborRozladunku) {

                        case 1: {

                            var kontener = listaKontenerow.stream().filter(x -> x.id == wyborKontenera).findFirst().get();
                            var statek = listaStatkow.stream().filter(x -> x.id == wyborStatku).findFirst().get();
                            var nad = listaNadawcow.stream().filter(x -> x.idNadawca == kontener.idNadawcy).findFirst().get();
                            if (nad.Ostrzerzenie < 2) {
                                if (kontener instanceof KontenerWybuchowe || kontener instanceof KontenerToksycznyCiekly || kontener instanceof KontenerToksycznySypki) {
                                    statek.iloscmaxWybTox = statek.iloscmaxWybTox - 1;
                                    System.out.println("Dodano pomyslnie specjany kontener na magazyn");
                                }

                                statek.ladownosc = statek.ladownosc - kontener.wagaBrutto;
                                magazyn.pojemnoscMagazynu += 1;
                                kontener.idStatku = 0;
                                kontener.magazyn = 1;
                                kontener.torKolejowy = 0;
                                kontener.dataNaMagazyn = new Date();
                                System.out.println("Dodano pomyslnie kontener na magazyn");
                            } else {
                                System.err.println("Za duzo ostrzerzen " + nad.nazwisko + " " + nad.imie + " nie moze dodac juz kontnera");
                            }
                        }
                        break;

                        case 2: {


                            int liczbaKonTorkol = (int) listaKontenerow.stream().filter(x -> x.torKolejowy == 1).count();
                            if (liczbaKonTorkol < 10) {
                                var kontener = listaKontenerow.stream().filter(y -> y.id == wyborKontenera && y.status != -1).findFirst().get();

                                kontener.idStatku = 0;
                                kontener.torKolejowy = 1;
                                kontener.magazyn = 0;
                                System.out.println("Dodano pomyslnie kontner na tor kolejowy");

                            } else {

                                System.out.println("Tor kolejowy zostal zapelniony, oczekiwanie 30 sekund,na odjazd");
                                Thread.sleep(30000);
                                listaKontenerow.removeAll(listaKontenerow.stream().filter(y -> y.torKolejowy == 1).toList());


                            }
                        }
                        break;
                        case 3: {
                            break;
                        }
                    }

                }
                break;

                //zaladowanie statku kontenerem
                case 2: {
                    for (int i = 0; i < listaStatkow.size(); i++) {
                        System.out.print((i + 1) + ". ");
                        System.out.println(listaStatkow.get(i));
                    }
                    System.out.println("1.Wybierz statek");
                    int wyborStatek = scan.nextInt();


                    for (int i = 0; i < listaKontenerow.size(); i++) {
                        System.out.print((i + 1) + ". ");
                        System.out.println(listaKontenerow.get(i));
                    }
                    System.out.println("1.Wybierz Kontener");
                    int wyborKontener = scan.nextInt();

                    var znalezionyKontener = listaKontenerow.stream().filter(x -> x.id == wyborKontener && x.status != -1).findFirst().get();
                    var znalezionyStatek = listaStatkow.stream().filter(x -> x.id == wyborStatek).findFirst().get();
                    var liczbaKontenerowNaStatku = listaKontenerow.stream().filter(x -> x.idStatku == wyborStatek).count();
                    var waga = znalezionyStatek.ladownosc + znalezionyKontener.wagaBrutto;

                    if (znalezionyKontener instanceof KontenerWybuchowe) {
                        if (znalezionyStatek.maxLadownosc > waga && liczbaKontenerowNaStatku < znalezionyStatek.maxWszystke) {
                            if (znalezionyStatek.iloscmaxWybTox < znalezionyStatek.maxWybTox) {
                                znalezionyKontener.idStatku = wyborStatek;
                                znalezionyStatek.ladownosc = waga;
                                znalezionyStatek.iloscmaxWybTox = znalezionyStatek.iloscmaxWybTox + 1;
                                ;
                                System.out.println("Pomsylnie przypisales kontner wybuchowy na statek");

                            } else System.err.println("Za duzo kontenerow wybuchowych na statku");
                        } else System.err.println("Za duza ladownosc na statku");
                    } else if (znalezionyKontener instanceof KontenerCiekle || znalezionyKontener instanceof KontenerCiezki) {
                        if (znalezionyStatek.maxLadownosc > waga && liczbaKontenerowNaStatku < znalezionyStatek.maxWszystke) {
                            if (znalezionyStatek.iloscmaxWybTox < znalezionyStatek.maxWybTox) {
                                znalezionyKontener.idStatku = wyborStatek;
                                znalezionyStatek.ladownosc = waga;
                                znalezionyStatek.iloscmaxWybTox = znalezionyStatek.iloscmaxWybTox + 1;
                                System.out.println("Pomyslnie przypisales kontener toksyczny na statek");
                            } else System.err.println("Za duzo kontnerow toksycznych na statku");
                        } else System.err.println("Za duza ładownosc na statku");
                    } else {
                        if (znalezionyStatek.maxLadownosc > waga && liczbaKontenerowNaStatku < znalezionyStatek.maxWszystke) {
                            znalezionyKontener.idStatku = wyborStatek;
                            znalezionyStatek.ladownosc = waga;
                            System.out.println("Pomsylnie przypisales kontner na statek");
                        } else System.err.println("Za duza ładownosc na statku");
                    }

                }
                break;
                //informacje o statkach i kontnerach na nim
                case 3: {
                    for (Statek s : listaStatkow) {
                        System.out.println((s.id) + ". " + s);
                    }
                    System.out.println("o którym statku chcesz sie dowiedziec wiecej ?");


                    int wyborr = scan.nextInt();

                    for (KontenerPodstawowy k : listaKontenerow.stream().filter(x -> x.idStatku == wyborr).toList()) {
                        System.out.println(k);
                    }


                }
                break;


                //Stan magazynu
                case 4: {

                    for (var kontener : listaKontenerow.stream().filter(x -> x.status == 1 && x.magazyn == 1).toList()) {
                        System.out.println(kontener);
                    }

                }
                break;


                //utylizacja kontnera
                case 5: {

                    for (KontenerPodstawowy k : listaKontenerow.stream().filter(x -> x.idStatku == 0).toList()) {

                        System.out.print((k.id) + ". ");
                        System.out.println(k.toString());
                    }

                    System.out.println("Który kontener chcesz zutylizowac ?");
                    int utylizacja = scan.nextInt();
                    for (KontenerPodstawowy k : listaKontenerow.stream().toList()) {

                        if (utylizacja == k.id) {
                            k.status = -1;
                            System.out.println("Zutylizowalem kontner o id: " + k.id);

                        }
                    }
                }
                break;


                //stworzenie statku
                case 6: {
                    Statek s = new Statek();

                    s.DodajStatek();
                    s.setId(s.id);
                    listaStatkow.add(s);

                    for (int i = 0; i < listaStatkow.size(); i++) {
                        System.out.print((i + 1) + ". ");
                        System.out.println(listaStatkow.get(i));
                    }

                }
                break;


                //stworzenie kontnera
                case 7: {
                    MenuKonteneru();
                    break;
                }

                //dodanie nadawcy
                case 8: {

                    Nadawca n1 = new Nadawca();
                    n1.DodajNadawce();
                    listaNadawcow.add(n1);
                }
                break;


                //zapis
                case 9: {

                    // sort -> https://stackoverflow.com/questions/36361156/null-safe-date-comparator-for-sorting-in-java-8-stream

                    FileWriter fw = new FileWriter("LISTA_KONTENEROW.txt");
                    FileWriter fw1 = new FileWriter("LISTA_STATKOW.txt");
                    FileWriter fw2 = new FileWriter("LISTA_KONTENEROW_NA_MAGAZYNIE.txt");
                    FileWriter fw3 = new FileWriter("nadawcy.txt");
                    FileWriter fw4 = new FileWriter("USUNIETE_KONTENERY.txt");


                    for (var kontener : listaKontenerow.stream().sorted(Comparator.comparing(x -> x.wagaBrutto, Comparator.nullsLast(Comparator.naturalOrder()))).toList()) {
                        fw.write(kontener.toStringToFile() + "; \n");
                    }
                    fw.close();

                    for (var statek : listaStatkow.stream().sorted(Comparator.comparing(x -> x.nazwa, Comparator.nullsLast(Comparator.reverseOrder()))).toList()) {
                        fw1.write(statek.toStringToFile() + "; \n");
                    }
                    fw1.close();

                    for (var magazyn : listaKontenerow.stream().filter(x -> x.magazyn == 1).sorted(Comparator.comparing(KontenerPodstawowy::getDataNaMagazyn).thenComparing(KontenerPodstawowy::getNadawca)).toList()) {
                        fw2.write(magazyn.toStringToFile() + "; \n");
                    }
                    fw2.close();

                    for (var nadawca : listaNadawcow.stream().toList()) {
                        fw3.write(nadawca.toStringToFile() + "; \n");
                    }
                    fw3.close();

                    for (var zutylizowane : listaKontenerow.stream().filter(x -> x.status == -1).toList()) {
                        fw4.write(zutylizowane.toStringToFile() + "; \n");
                    }
                    fw4.close();

                    System.out.println("Poprawnie zapisano plik");
                    //OdczytZpliku();
                }
                break;


                //zaladowanie kontenera z magazynu na statek
                case 10: {
                    System.out.println("Wybierz Kontener z Magazynu: ");
                    for (var kontner : listaKontenerow.stream().filter(x -> x.magazyn == 1 && x.status == 1).toList()) {
                        System.out.println(kontner.id + " " + kontner);
                    }
                    int wyborKontener = scan.nextInt();

                    for (var statek : listaStatkow.stream().toList()) {
                        System.out.println(statek.id + " " + statek);
                    }
                    System.out.println("Wybierz Statek");


                    int wyborStatek = scan.nextInt();

                    var kontner = listaKontenerow.stream().filter(x -> x.id == wyborKontener).findFirst().get();
                    kontner.idStatku = wyborStatek;
                    kontner.magazyn = 0;
                    System.out.println("Pomsylnie dodales kontner " + kontner.id + " na statek" + " " + wyborStatek);
                }
                break;

                default: {
                    break;
                }

            }
        } while (opcja != 11);
        System.exit(0);
    }

    public static void KontneryIStatki() {

        Statek s1 = new Statek(5, 1, 5, 20, 200000, 18700, "Luksja", "Rosja", "Gdansk", "gdansk");
        listaStatkow.add(s1);


        KontenerPodstawowy a1 = new KontenerPodstawowy(1, "brak", "QWE", 400, 1000, s1.id, 1400, "czarny");
        KontenerChlodniczy a2 = new KontenerChlodniczy(1, "plomba", "AA", 500, 2000, s1.id, 2500, "czarny", "AA", -30);
        KontenerToksycznySypki a3 = new KontenerToksycznySypki(2, "klodka", "ACC", 500, 4000, s1.id, 4500, "bialy", "LP", 4);
        KontenerWybuchowe a4 = new KontenerWybuchowe(1, "brak", "ACC", 400, 1000, s1.id, 1400, "czarny", "AA", 10);
        KontenerToksycznyCiekly a5 = new KontenerToksycznyCiekly(2, "lancuch", "LLLL", 500, 3200, s1.id, 3700, "czarny", 1, 1);
        KontenerToksycznyCiekly a6 = new KontenerToksycznyCiekly(3, "lancuch", "LLLL", 200, 2900, s1.id, 3100, "czarny", 1, 1);
        KontenerToksycznyCiekly a7 = new KontenerToksycznyCiekly(4, "lancuch", "LLLL", 1100, 1000, s1.id, 2100, "czarny", 1, 1);


        listaKontenerow.add(a1);
        listaKontenerow.add(a2);
        listaKontenerow.add(a3);
        listaKontenerow.add(a4);
        listaKontenerow.add(a5);
        listaKontenerow.add(a6);
        listaKontenerow.add(a7);

        s1.iloscmaxWybTox = 5;

        Statek s2 = new Statek(4, 2, 4, 20, 15000, 9900, "JBLMARKET", "Kazachstan", "Lomianki", "gdansk");
        listaStatkow.add(s2);

        KontenerPodstawowy b1 = new KontenerPodstawowy(2, "brak", "QWE", 1000, 1000, s2.id, 2000, "bialy");
        KontenerChlodniczy b2 = new KontenerChlodniczy(4, "plomba", "AA", 2000, 2000, s2.id, 4000, "czarny", "WW", 20);
        KontenerCiezki b3 = new KontenerCiezki(3, "klodka", "AAAB", 500, 2000, s2.id, 2500, "LAAA", "WQE");
        KontenerPodstawowy b4 = new KontenerPodstawowy(2, "brak", "KKK", 400, 1000, s2.id, 1400, "czarny");


        listaKontenerow.add(b1);
        listaKontenerow.add(b2);
        listaKontenerow.add(b3);
        listaKontenerow.add(b4);
        s2.iloscmaxWybTox = 0;

        Statek s3 = new Statek(2, 3, 3, 20, 13000, 10700, "PSP", "Boliwia", "Zabki", "gdansk");
        listaStatkow.add(s3);
        KontenerCiezki c1 = new KontenerCiezki(4, "klodka", "AAAB", 500, 2500, s3.id, 3000, "ALLL", "WW");
        KontenerCiekle c2 = new KontenerCiekle(4, "elektryczne", "CAA", 600, 2600, s3.id, 3200, "czerwona", 4);
        KontenerToksycznySypki c3 = new KontenerToksycznySypki(4, "klodka", "AAC", 700, 4000, s3.id, 4700, "zloty", "SS", 3);

        listaKontenerow.add(c1);
        listaKontenerow.add(c2);
        listaKontenerow.add(c3);
        s3.iloscmaxWybTox = 1;

        Statek s4 = new Statek(5, 4, 2, 20, 10000, 7300, "SONY", "Sudan", "Lublin", "gdansk");
        listaStatkow.add(s4);

        KontenerToksycznySypki d1 = new KontenerToksycznySypki(1, "klodka", "AAC", 1000, 800, s4.id, 1800, "brazowy", "SS", 3);
        KontenerToksycznySypki d2 = new KontenerToksycznySypki(2, "klodka", "bac", 400, 800, s4.id, 900, "czarny", "SA", 3);
        KontenerToksycznySypki d3 = new KontenerToksycznySypki(3, "klodka", "dde", 1200, 800, s4.id, 2000, "zloty", "AA", 5);
        KontenerToksycznySypki d4 = new KontenerToksycznySypki(1, "klodka", "ree", 700, 800, s4.id, 1500, "bialy", "AA", 2);
        KontenerToksycznyCiekly d5 = new KontenerToksycznyCiekly(2, "lancuch", "LLLL", 600, 500, s4.id, 1100, "zloty", 4, 5);
        listaKontenerow.add(d1);
        listaKontenerow.add(d2);
        listaKontenerow.add(d3);
        listaKontenerow.add(d4);
        listaKontenerow.add(d5);

        s4.iloscmaxWybTox = 5; //

        Statek s5 = new Statek(7, 5, 1, 20, 20000, 15600, "Boss", "Libia", "Szczecin", "gdansk");
        listaStatkow.add(s5);

        KontenerPodstawowy e1 = new KontenerPodstawowy(2, "brak", "QWE", 400, 1200, s5.id, 1600, "czarny");
        KontenerChlodniczy e2 = new KontenerChlodniczy(2, "plomba", "AA", 500, 1000, s5.id, 1500, "czarny", "W", -30);
        KontenerCiezki e3 = new KontenerCiezki(3, "klodka", "AAAB", 300, 3000, s5.id, 3300, "brazowy", "AA");

        KontenerCiekle e4 = new KontenerCiekle(1, "elektryczne", "CAA", 500, 2000, s5.id, 2500, "zloty", 4);
        KontenerToksycznySypki e5 = new KontenerToksycznySypki(2, "klodka", "AAC", 500, 800, s5.id, 1300, "brazowy", "WE", 2);
        KontenerWybuchowe e6 = new KontenerWybuchowe(3, "brak", "ACC", 400, 1400, s5.id, 1800, "czarny", "WAS", 2);
        KontenerWybuchowe e7 = new KontenerWybuchowe(1, "brak", "ACC", 400, 1400, s5.id, 1800, "czarny", "L", 5);
        KontenerWybuchowe e8 = new KontenerWybuchowe(1, "brak", "ACC", 400, 1400, s5.id, 1800, "czarny", "WW", 6);
        KontenerWybuchowe e9 = new KontenerWybuchowe(1, "brak", "ACC", 400, 1400, s5.id, 1800, "czarny", "SS", 4);
        s5.iloscmaxWybTox = 5;
        listaKontenerow.add(e1);
        listaKontenerow.add(e2);
        listaKontenerow.add(e3);
        listaKontenerow.add(e4);
        listaKontenerow.add(e5);
        listaKontenerow.add(e6);
        listaKontenerow.add(e7);
        listaKontenerow.add(e8);
        listaKontenerow.add(e9);

        Nadawca nad1 = new Nadawca("Jerzy", "Wolej", "Koszykowa 46", "99020211113");
        Nadawca nad2 = new Nadawca("Marek", "Polej", "Lilowa 22", "88110346567");
        Nadawca nad3 = new Nadawca("Tomek", "Wylej", "Gazowa 10", "58030484888");
        Nadawca nad4 = new Nadawca("Arek", "Rozlej", "Jasminowa 1", "89050678092");
        listaNadawcow.add(nad1);
        listaNadawcow.add(nad2);
        listaNadawcow.add(nad3);
        listaNadawcow.add(nad4);

    }

    ;

    public static void MenuKonteneru() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Dostepne kontenery");
        for (int i = 0; i < listaKontenerow.size(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(listaKontenerow.get(i));
        }
        System.out.println("Wybierz nadawce:");
        for (var nadawca : listaNadawcow.stream().toList()) {
            System.out.println(nadawca.idNadawca + " " + nadawca);
        }
        int wyborNadawcy = scan.nextInt();

        System.out.println("Dodaj kontener :: 1.Podstawowy 2.Ciezki 3.Chlodniczy 4.Ciekly 5.Wybuchowy 6.Toksyczny Sypkie 7.Toksyczny Ciekle 8.wyjscie");
        int opcjaKontener = scan.nextInt();

        switch (opcjaKontener) {

            case 1: {
                KontenerPodstawowy kp = new KontenerPodstawowy();
                kp.dodajKontener();
                kp.idNadawcy = wyborNadawcy;
                listaKontenerow.add(kp);
                break;
            }
            case 2: {
                KontenerCiezki kc = new KontenerCiezki();
                kc.dodajKontener();
                kc.idNadawcy = wyborNadawcy;
                listaKontenerow.add(kc);
                break;
            }
            case 3: {
                KontenerChlodniczy kch = new KontenerChlodniczy();
                kch.dodajKontener();
                kch.idNadawcy = wyborNadawcy;
                listaKontenerow.add(kch);
                break;
            }
            case 4: {
                KontenerCiekle kcc = new KontenerCiekle();
                kcc.dodajKontener();
                kcc.idNadawcy = wyborNadawcy;
                listaKontenerow.add(kcc);
                break;
            }
            case 5: {
                KontenerWybuchowe kw = new KontenerWybuchowe();
                kw.dodajKontener();
                kw.idNadawcy = wyborNadawcy;
                listaKontenerow.add(kw);
                break;
            }
            case 6: {
                KontenerToksycznySypki kts = new KontenerToksycznySypki();
                kts.dodajKontener();
                kts.idNadawcy = wyborNadawcy;
                listaKontenerow.add(kts);
                break;
            }
            case 7: {
                KontenerToksycznyCiekly ktc = new KontenerToksycznyCiekly();
                ktc.dodajKontener();
                ktc.idNadawcy = wyborNadawcy;
                listaKontenerow.add(ktc);
                break;
            }
            case 8: {
                return;
            }

        }

    }

    public static void OdczytZpliku() throws IOException {

        String file = "LISTA_KONTENEROW.txt";
        StringBuilder b = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                b.append(line);
            }
        }
        var obj = b.toString().split(";");

        for (var i : obj) {

            var linia = i.split("\\|");
            var kontener = linia[0];

            KontenerPodstawowy k = new KontenerPodstawowy();

            var idNadawcy = Integer.parseInt(linia[2]);
            var zabezpieczenia = linia[6];
            var certyfikaty = linia[7];
            var tara = Integer.parseInt(linia[9]);
            var wagaNetto = Integer.parseInt(linia[10]);
            var idStatku = Integer.parseInt(linia[3]);
            var wagaBrutto = Integer.parseInt(linia[11]);
            var kolor = linia[8];

            var id = linia[1];
            var magazyn = linia[4];
            var torKolejowy = linia[5];


            var status = linia[12];
            var dataUtylizacji = linia[13];
            var cieklosc = Integer.parseInt(linia[15]);
            var lepkosc = Integer.parseInt(linia[17]);
            var sypkosc = Integer.parseInt(linia[18]);
            var latwopalnosc = Integer.parseInt(linia[19]);


            if (kontener.contains("Podstawowy")) {
                k = new KontenerPodstawowy(idNadawcy, zabezpieczenia, certyfikaty, tara, wagaNetto, idStatku, wagaBrutto, kolor);
            } else if (kontener.contains("Chlodniczy")) {
                var temperatura = Integer.parseInt(linia[14]);
                var znak = linia[15];
                k = new KontenerChlodniczy(idNadawcy, zabezpieczenia, certyfikaty, tara, wagaNetto, idStatku, wagaBrutto, kolor, znak, temperatura);
            } else if (kontener.contains("Ciekly")) {
                k = new KontenerCiekle(idNadawcy, zabezpieczenia, certyfikaty, tara, wagaNetto, idStatku, wagaBrutto, kolor, cieklosc);
            } else if (kontener.contains("Ciezki")) {
                var znak = linia[15];
                k = new KontenerCiezki(idNadawcy, zabezpieczenia, certyfikaty, tara, wagaNetto, idStatku, wagaBrutto, kolor, znak);
            } else if (kontener.contains("Toksyczny Ciekly")) {
                k = new KontenerToksycznyCiekly(idNadawcy, zabezpieczenia, certyfikaty, tara, wagaNetto, idStatku, wagaBrutto, kolor, cieklosc, lepkosc);
            } else if (kontener.contains("Toksyczny Sypki")) {
                var znak = linia[15];
                k = new KontenerToksycznySypki(idNadawcy, zabezpieczenia, certyfikaty, tara, wagaNetto, idStatku, wagaBrutto, kolor, znak, sypkosc);
            } else if (kontener.contains("Wybuchowy")) {
                var znak = linia[15];
                k = new KontenerWybuchowe(idNadawcy, zabezpieczenia, certyfikaty, tara, wagaNetto, idStatku, wagaBrutto, kolor, znak, latwopalnosc);
            }

            listaKontenerow.add(k);
        }

    }

};


// status -1 to zutylizowany
// zapis
