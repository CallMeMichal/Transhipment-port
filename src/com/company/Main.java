package com.company;

import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Ship> shipList = new ArrayList<Ship>();
    static ArrayList<BasicCont> contList = new ArrayList<BasicCont>();
    static ArrayList<Sender> senderList = new ArrayList<Sender>();


    static Date time = new Date();

    static Storage storage = new Storage(100);
    static RailwayTrack track = new RailwayTrack();


    public static void main(String[] args) throws InterruptedException, IOException {
        ContAndShips();


        new Thread(new Runnable() {
            public void run() {
                int day = 1;
                while (true) {

                    try {
                        System.out.println("day " + day + " " + time);
                        Thread.sleep(5000);
                        for (BasicCont container : contList.stream().filter(x -> x.storage == 1 && x.status == 1).toList()) {
                            if (container instanceof ExplCont) {
                                Calendar c = Calendar.getInstance();
                                c.setTime(container.storageDate);
                                c.add(Calendar.DATE, 5);
                                var timeLimit = c.getTime();
                                if (timeLimit.before(time)) {
                                    var senderr = senderList.stream().filter(x -> x.senderId == container.senderId).findFirst().get();

                                    senderr.warnings += 1;
                                    System.out.println("The explosive container is stored for more than 5 days, disposal occurs sender " + senderr.name + " " + senderr.surname + " gets  " + senderr.warnings + " reminder");
                                    container.status = -1;
                                    container.deleteDate = time;
                                    throw new IrresponsibleSenderWithDangerousGoods(container);
                                }

                            } else if (container instanceof ToxLiqCont) {

                                Calendar c = Calendar.getInstance();
                                c.setTime(container.storageDate);
                                c.add(Calendar.DATE, 10);
                                var timeLimit = c.getTime();
                                if (timeLimit.before(time)) {
                                    var senderr = senderList.stream().filter(x -> x.senderId == container.senderId).findFirst().get();
                                    senderr.warnings += 1;
                                    container.status = -1;
                                    container.deleteDate = time;
                                    System.out.println("Container Toxic Liquid is stored more than 10 days, disposal occurs, sender " + senderr.name + " " + senderr.surname + " gets first warning");
                                    throw new IrresponsibleSenderWithDangerousGoods(container);

                                }

                            } else if (container instanceof ToxicLooCont) {
                                Calendar c = Calendar.getInstance();
                                c.setTime(container.storageDate);
                                c.add(Calendar.DATE, 14);
                                var timeLimit = c.getTime();
                                if (timeLimit.before(time)) {
                                    var senderr = senderList.stream().filter(x -> x.senderId == container.senderId).findFirst().get();
                                    senderr.warnings += 1;
                                    System.out.println("Container Toxic Liquid is stored more than 14 days, disposal occurs, sender " + senderr.name + " " + senderr.surname + " gets first warning");
                                    container.status = -1;
                                    container.deleteDate = time;
                                    throw new IrresponsibleSenderWithDangerousGoods(container);
                                }
                            }
                        }
                        Calendar c = Calendar.getInstance();
                        c.setTime(time);
                        c.add(Calendar.DATE, 1);
                        time = c.getTime();
                        day++;
                    } catch (InterruptedException | IrresponsibleSenderWithDangerousGoods e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        Scanner scan = new Scanner(System.in);
        int option;

        do {
            System.out.println(
                    "s25645" + "\n" +
                            "Michał Tulej" + "\n" +

                            "1.Loading the designated container from the ship onto a wagon/storage facility \n" +
                            "2.Loading the designated container onto the designated vessel \n" +
                            "3.Information about the ship and the containers on it\n" +
                            "4.Stock \n" +
                            "5.Container disposal\n" +
                            "6.Creating a Ship \n" +
                            "7.Creating a container\n" +
                            "8.Adding a Sender\n" +
                            "9.Record\n" +
                            "10.Loading from the warehouse to the contner's ship\n" +
                            "11.Output"

            );
            option = scan.nextInt();
            switch (option) {

                case 1: {

                    for (var ship : shipList.stream().toList()) {
                        System.out.println(ship.id + " " + ship);
                    }
                    System.out.println("Select Ship");
                    int shipChoice = scan.nextInt();


                    for (BasicCont container : contList.stream().filter(p -> p.shipId == shipChoice && p.status != -1).toList()) {

                        System.out.println((container.id) + " " + container);

                    }
                    System.out.println("Select container ID");
                    int selectedCont = scan.nextInt();

                    System.out.println("Where do you want to unload the counter: 1.Storage 2.Railway track 3.Exit");
                    int unloadChoice = scan.nextInt();

                    switch (unloadChoice) {

                        case 1: {

                            var container = contList.stream().filter(x -> x.id == selectedCont).findFirst().get();
                            var ship = shipList.stream().filter(x -> x.id == shipChoice).findFirst().get();
                            var nad = senderList.stream().filter(x -> x.senderId == container.senderId).findFirst().get();
                            if (nad.warnings < 2) {
                                if (container instanceof ExplCont || container instanceof ToxLiqCont || container instanceof ToxicLooCont) {
                                    ship.numberMaxExt = ship.numberMaxExt - 1;
                                    System.out.println("Special container for storage added successfully");
                                }

                                ship.capacity = ship.capacity - container.bruttoWeight;
                                storage.StorageCap += 1;
                                container.shipId = 0;
                                container.storage = 1;
                                container.railTrack = 0;
                                container.storageDate = new Date();
                                System.out.println("Added storage container successfully");
                            } else {
                                System.err.println("Too many warnings " + nad.surname + " " + nad.name + " can no longer add a counter");
                            }
                        }
                        break;

                        case 2: {


                            int ContrNumberRail = (int) contList.stream().filter(x -> x.railTrack == 1).count();
                            if (ContrNumberRail < 10) {
                                var container = contList.stream().filter(y -> y.id == selectedCont && y.status != -1).findFirst().get();

                                container.shipId = 0;
                                container.railTrack = 1;
                                container.storage = 0;
                                System.out.println("Successfully added a rail track counterpart");

                            } else {

                                System.out.println("The railroad track has been filled, waiting 30 seconds,for departure");
                                Thread.sleep(30000);
                                contList.removeAll(contList.stream().filter(y -> y.railTrack == 1).toList());


                            }
                        }
                        break;
                        case 3: {
                            break;
                        }
                    }

                }
                break;

                case 2: {
                    for (int i = 0; i < shipList.size(); i++) {
                        System.out.print((i + 1) + ". ");
                        System.out.println(shipList.get(i));
                    }
                    System.out.println("1.Choose a ship");
                    int shipChoice = scan.nextInt();


                    for (int i = 0; i < contList.size(); i++) {
                        System.out.print((i + 1) + ". ");
                        System.out.println(contList.get(i));
                    }
                    System.out.println("1.Select Container");
                    int containerChoice = scan.nextInt();

                    var findCont = contList.stream().filter(x -> x.id == containerChoice && x.status != -1).findFirst().get();
                    var findShip = shipList.stream().filter(x -> x.id == shipChoice).findFirst().get();
                    var numberShipCont = contList.stream().filter(x -> x.shipId == shipChoice).count();
                    var weight = findShip.capacity + findCont.bruttoWeight;

                    if (findCont instanceof ExplCont) {
                        if (findShip.maxCapacity > weight && numberShipCont < findShip.maxAll) {
                            if (findShip.numberMaxExt < findShip.maxExpTox) {
                                findCont.shipId = shipChoice;
                                findShip.capacity = weight;
                                findShip.numberMaxExt = findShip.numberMaxExt + 1;
                                ;
                                System.out.println("You succesful attributed the explosive counter to the ship");

                            } else System.err.println("Too many explosive containers on the ship");
                        } else System.err.println("Too much capacity on board");
                    } else if (findCont instanceof LiquidCont || findCont instanceof HeavyCont) {
                        if (findShip.maxCapacity > weight && numberShipCont < findShip.maxAll) {
                            if (findShip.numberMaxExt < findShip.maxExpTox) {
                                findCont.shipId = shipChoice;
                                findShip.capacity = weight;
                                findShip.numberMaxExt = findShip.numberMaxExt + 1;
                                System.out.println("You have ingeniously assigned a toxic container to a ship");
                            } else System.err.println("Too many toxic-containers on the ship");
                        } else System.err.println("Too much cargo capacity on the ship");
                    } else {
                        if (findShip.maxCapacity > weight && numberShipCont < findShip.maxAll) {
                            findCont.shipId = shipChoice;
                            findShip.capacity = weight;
                            System.out.println("You mistakenly attributed the counterpart to the ship");
                        } else System.err.println("Too much cargo capacity on the ship");
                    }

                }
                break;

                case 3: {
                    for (Ship s : shipList) {
                        System.out.println((s.id) + ". " + s);
                    }
                    System.out.println("Which ship do you want to know more about ?");


                    int choice = scan.nextInt();

                    for (BasicCont k : contList.stream().filter(x -> x.shipId == choice).toList()) {
                        System.out.println(k);
                    }


                }
                break;


                case 4: {

                    for (var container : contList.stream().filter(x -> x.status == 1 && x.storage == 1).toList()) {
                        System.out.println(container);
                    }

                }
                break;

                case 5: {

                    for (BasicCont k : contList.stream().filter(x -> x.shipId == 0).toList()) {

                        System.out.print((k.id) + ". ");
                        System.out.println(k.toString());
                    }

                    System.out.println("Which container do you want to dispose of ?");
                    int disposal = scan.nextInt();
                    for (BasicCont k : contList.stream().toList()) {

                        if (disposal == k.id) {
                            k.status = -1;
                            System.out.println("I disposed of a contner with id: " + k.id);

                        }
                    }
                }
                break;

                case 6: {
                    Ship s = new Ship();

                    s.addShip();
                    s.setId(s.id);
                    shipList.add(s);

                    for (int i = 0; i < shipList.size(); i++) {
                        System.out.print((i + 1) + ". ");
                        System.out.println(shipList.get(i));
                    }

                }
                break;


                case 7: {
                    ContainerMenu();
                    break;
                }


                case 8: {

                    Sender n1 = new Sender();
                    n1.addSender();
                    senderList.add(n1);
                }
                break;


                case 9: {



                    FileWriter fw = new FileWriter("CONTAINERS_LIST.txt");
                    FileWriter fw1 = new FileWriter("SHIP_LIST.txt");
                    FileWriter fw2 = new FileWriter("STORAGE_CONTAINERS.txt");
                    FileWriter fw3 = new FileWriter("SENDER.txt");
                    FileWriter fw4 = new FileWriter("DELETED_CONTAINERS.txt");


                    for (var container : contList.stream().sorted(Comparator.comparing(x -> x.bruttoWeight, Comparator.nullsLast(Comparator.naturalOrder()))).toList()) {
                        fw.write(container.toStringToFile() + "; \n");
                    }
                    fw.close();

                    for (var ship : shipList.stream().sorted(Comparator.comparing(x -> x.name, Comparator.nullsLast(Comparator.reverseOrder()))).toList()) {
                        fw1.write(ship.toStringToFile() + "; \n");
                    }
                    fw1.close();

                    for (var storage : contList.stream().filter(x -> x.storage == 1).sorted(Comparator.comparing(BasicCont::getStorageDate).thenComparing(BasicCont::getSender)).toList()) {
                        fw2.write(storage.toStringToFile() + "; \n");
                    }
                    fw2.close();

                    for (var sender : senderList.stream().toList()) {
                        fw3.write(sender.toStringToFile() + "; \n");
                    }
                    fw3.close();

                    for (var deleted : contList.stream().filter(x -> x.status == -1).toList()) {
                        fw4.write(deleted.toStringToFile() + "; \n");
                    }
                    fw4.close();

                    System.out.println("Correctly saved the file");
                }
                break;


                case 10: {
                    System.out.println("Select Container from the Warehouse: ");
                    for (var container : contList.stream().filter(x -> x.storage == 1 && x.status == 1).toList()) {
                        System.out.println(container.id + " " + container);
                    }
                    int contChoice = scan.nextInt();

                    for (var ship : shipList.stream().toList()) {
                        System.out.println(ship.id + " " + ship);
                    }
                    System.out.println("Select Ship");


                    int shipChoice = scan.nextInt();

                    var container = contList.stream().filter(x -> x.id == contChoice).findFirst().get();
                    container.shipId = shipChoice;
                    container.storage = 0;
                    System.out.println("You mistakenly added container " + container.id + " on ship" + " " + shipChoice);
                }
                break;

                default: {
                    break;
                }

            }
        } while (option != 11);
        System.exit(0);
    }

    public static void ContAndShips() {

        Ship s1 = new Ship(5, 1, 5, 20, 200000, 18700, "Luksja", "Rosja", "Gdansk", "gdansk");
        shipList.add(s1);


        BasicCont a1 = new BasicCont(1, "brak", "QWE", 400, 1000, s1.id, 1400, "czarny");
        ColdCont a2 = new ColdCont(1, "plomba", "AA", 500, 2000, s1.id, 2500, "czarny", "AA", -30);
        ToxicLooCont a3 = new ToxicLooCont(2, "klodka", "ACC", 500, 4000, s1.id, 4500, "bialy", "LP", 4);
        ExplCont a4 = new ExplCont(1, "brak", "ACC", 400, 1000, s1.id, 1400, "czarny", "AA", 10);
        ToxLiqCont a5 = new ToxLiqCont(2, "lancuch", "LLLL", 500, 3200, s1.id, 3700, "czarny", 1, 1);
        ToxLiqCont a6 = new ToxLiqCont(3, "lancuch", "LLLL", 200, 2900, s1.id, 3100, "czarny", 1, 1);
        ToxLiqCont a7 = new ToxLiqCont(4, "lancuch", "LLLL", 1100, 1000, s1.id, 2100, "czarny", 1, 1);


        contList.add(a1);
        contList.add(a2);
        contList.add(a3);
        contList.add(a4);
        contList.add(a5);
        contList.add(a6);
        contList.add(a7);

        s1.numberMaxExt = 5;

        Ship s2 = new Ship(4, 2, 4, 20, 15000, 9900, "JBLMARKET", "Kazachstan", "Lomianki", "gdansk");
        shipList.add(s2);

        BasicCont b1 = new BasicCont(2, "brak", "QWE", 1000, 1000, s2.id, 2000, "bialy");
        ColdCont b2 = new ColdCont(4, "plomba", "AA", 2000, 2000, s2.id, 4000, "czarny", "WW", 20);
        HeavyCont b3 = new HeavyCont(3, "klodka", "AAAB", 500, 2000, s2.id, 2500, "LAAA", "WQE");
        BasicCont b4 = new BasicCont(2, "brak", "KKK", 400, 1000, s2.id, 1400, "czarny");


        contList.add(b1);
        contList.add(b2);
        contList.add(b3);
        contList.add(b4);
        s2.numberMaxExt = 0;

        Ship s3 = new Ship(2, 3, 3, 20, 13000, 10700, "PSP", "Boliwia", "Zabki", "gdansk");
        shipList.add(s3);
        HeavyCont c1 = new HeavyCont(4, "klodka", "AAAB", 500, 2500, s3.id, 3000, "ALLL", "WW");
        LiquidCont c2 = new LiquidCont(4, "elektryczne", "CAA", 600, 2600, s3.id, 3200, "czerwona", 4);
        ToxicLooCont c3 = new ToxicLooCont(4, "klodka", "AAC", 700, 4000, s3.id, 4700, "zloty", "SS", 3);

        contList.add(c1);
        contList.add(c2);
        contList.add(c3);
        s3.numberMaxExt = 1;

        Ship s4 = new Ship(5, 4, 2, 20, 10000, 7300, "SONY", "Sudan", "Lublin", "gdansk");
        shipList.add(s4);

        ToxicLooCont d1 = new ToxicLooCont(1, "klodka", "AAC", 1000, 800, s4.id, 1800, "brazowy", "SS", 3);
        ToxicLooCont d2 = new ToxicLooCont(2, "klodka", "bac", 400, 800, s4.id, 900, "czarny", "SA", 3);
        ToxicLooCont d3 = new ToxicLooCont(3, "klodka", "dde", 1200, 800, s4.id, 2000, "zloty", "AA", 5);
        ToxicLooCont d4 = new ToxicLooCont(1, "klodka", "ree", 700, 800, s4.id, 1500, "bialy", "AA", 2);
        ToxLiqCont d5 = new ToxLiqCont(2, "lancuch", "LLLL", 600, 500, s4.id, 1100, "zloty", 4, 5);
        contList.add(d1);
        contList.add(d2);
        contList.add(d3);
        contList.add(d4);
        contList.add(d5);

        s4.numberMaxExt = 5; //

        Ship s5 = new Ship(7, 5, 1, 20, 20000, 15600, "Boss", "Libia", "Szczecin", "gdansk");
        shipList.add(s5);

        BasicCont e1 = new BasicCont(2, "brak", "QWE", 400, 1200, s5.id, 1600, "czarny");
        ColdCont e2 = new ColdCont(2, "plomba", "AA", 500, 1000, s5.id, 1500, "czarny", "W", -30);
        HeavyCont e3 = new HeavyCont(3, "klodka", "AAAB", 300, 3000, s5.id, 3300, "brazowy", "AA");

        LiquidCont e4 = new LiquidCont(1, "elektryczne", "CAA", 500, 2000, s5.id, 2500, "zloty", 4);
        ToxicLooCont e5 = new ToxicLooCont(2, "klodka", "AAC", 500, 800, s5.id, 1300, "brazowy", "WE", 2);
        ExplCont e6 = new ExplCont(3, "brak", "ACC", 400, 1400, s5.id, 1800, "czarny", "WAS", 2);
        ExplCont e7 = new ExplCont(1, "brak", "ACC", 400, 1400, s5.id, 1800, "czarny", "L", 5);
        ExplCont e8 = new ExplCont(1, "brak", "ACC", 400, 1400, s5.id, 1800, "czarny", "WW", 6);
        ExplCont e9 = new ExplCont(1, "brak", "ACC", 400, 1400, s5.id, 1800, "czarny", "SS", 4);
        s5.numberMaxExt = 5;
        contList.add(e1);
        contList.add(e2);
        contList.add(e3);
        contList.add(e4);
        contList.add(e5);
        contList.add(e6);
        contList.add(e7);
        contList.add(e8);
        contList.add(e9);

        Sender nad1 = new Sender("Jerzy", "Wolej", "Koszykowa 46", "99020211113");
        Sender nad2 = new Sender("Marek", "Polej", "Lilowa 22", "88110346567");
        Sender nad3 = new Sender("Tomek", "Wylej", "Gazowa 10", "58030484888");
        Sender nad4 = new Sender("Arek", "Rozlej", "Jasminowa 1", "89050678092");
        senderList.add(nad1);
        senderList.add(nad2);
        senderList.add(nad3);
        senderList.add(nad4);

    }

    ;

    public static void ContainerMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Available containers");
        for (int i = 0; i < contList.size(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(contList.get(i));
        }
        System.out.println("Select a shipper:");
        for (var sender : senderList.stream().toList()) {
            System.out.println(sender.senderId + " " + sender);
        }
        int senderChoice = scan.nextInt();

        System.out.println("Add Container :: 1.Basic 2.Heavy 3.Cold 4.Liquid 5.Explosive 6.Toxic loose 7.Toxic liquid 8.exit");
        int ContainerOpt = scan.nextInt();

        switch (ContainerOpt) {

            case 1: {
                BasicCont kp = new BasicCont();
                kp.addCont();
                kp.senderId = senderChoice;
                contList.add(kp);
                break;
            }
            case 2: {
                HeavyCont kc = new HeavyCont();
                kc.addCont();
                kc.senderId = senderChoice;
                contList.add(kc);
                break;
            }
            case 3: {
                ColdCont kch = new ColdCont();
                kch.addCont();
                kch.senderId = senderChoice;
                contList.add(kch);
                break;
            }
            case 4: {
                LiquidCont kcc = new LiquidCont();
                kcc.addCont();
                kcc.senderId = senderChoice;
                contList.add(kcc);
                break;
            }
            case 5: {
                ExplCont kw = new ExplCont();
                kw.addCont();
                kw.senderId = senderChoice;
                contList.add(kw);
                break;
            }
            case 6: {
                ToxicLooCont kts = new ToxicLooCont();
                kts.addCont();
                kts.senderId = senderChoice;
                contList.add(kts);
                break;
            }
            case 7: {
                ToxLiqCont ktc = new ToxLiqCont();
                ktc.addCont();
                ktc.senderId = senderChoice;
                contList.add(ktc);
                break;
            }
            case 8: {
                return;
            }

        }

    }

    public static void fileRead() throws IOException {

        String file = "CONTAINERS_LIST.txt";
        StringBuilder b = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                b.append(line);
            }
        }
        var obj = b.toString().split(";");

        for (var i : obj) {

            var line = i.split("\\|");
            var cont = line[0];

            BasicCont k = new BasicCont();

            var senderID = Integer.parseInt(line[2]);
            var security = line[6];
            var certificates = line[7];
            var tara = Integer.parseInt(line[9]);
            var nettoWeight = Integer.parseInt(line[10]);
            var shipId = Integer.parseInt(line[3]);
            var bruttoWeight = Integer.parseInt(line[11]);
            var color = line[8];

            var id = line[1];
            var storage = line[4];
            var railway = line[5];


            var status = line[12];
            var delDate = line[13];
            var liquid = Integer.parseInt(line[15]);
            var stickiness = Integer.parseInt(line[17]);
            var loose = Integer.parseInt(line[18]);
            var latvability = Integer.parseInt(line[19]);


            if (cont.contains("Basic")) {
                k = new BasicCont(senderID, security, certificates, tara, nettoWeight, shipId, bruttoWeight, color);
            } else if (cont.contains("Cooling")) {
                var temperatura = Integer.parseInt(line[14]);
                var znak = line[15];
                k = new ColdCont(senderID, security, certificates, tara, nettoWeight, shipId, bruttoWeight, color, znak, temperatura);
            } else if (cont.contains("Liquid")) {
                k = new LiquidCont(senderID, security, certificates, tara, nettoWeight, shipId, bruttoWeight, color, liquid);
            } else if (cont.contains("Heavy")) {
                var znak = line[15];
                k = new HeavyCont(senderID, security, certificates, tara, nettoWeight, shipId, bruttoWeight, color, znak);
            } else if (cont.contains("Toxic Liquid")) {
                k = new ToxLiqCont(senderID, security, certificates, tara, nettoWeight, shipId, bruttoWeight, color, liquid, stickiness);
            } else if (cont.contains("Toxic loose")) {
                var znak = line[15];
                k = new ToxicLooCont(senderID, security, certificates, tara, nettoWeight, shipId, bruttoWeight, color, znak, loose);
            } else if (cont.contains("Explosive")) {
                var znak = line[15];
                k = new ExplCont(senderID, security, certificates, tara, nettoWeight, shipId, bruttoWeight, color, znak, latvability);
            }

            contList.add(k);
        }

    }

};

