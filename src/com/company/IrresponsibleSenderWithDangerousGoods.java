package com.company;

public class IrresponsibleSenderWithDangerousGoods extends Throwable {

    public IrresponsibleSenderWithDangerousGoods(KontenerPodstawowy kont) {

        System.out.println("Id: " + kont.id + "\n" +
                "Data Utylizacji: " + kont.dataUtylizacji + "\n" +
                "Data Przyjscia na Magazyn: " + kont.dataNaMagazyn);
    }
}
