package com.company;

public class IrresponsibleSenderWithDangerousGoods extends Throwable {

    public IrresponsibleSenderWithDangerousGoods(BasicCont kont) {

        System.out.println("ID: " + kont.id + "\n" +
                "Delete date: " + kont.deleteDate + "\n" +
                "On storage date: " + kont.storageDate);
    }
}
