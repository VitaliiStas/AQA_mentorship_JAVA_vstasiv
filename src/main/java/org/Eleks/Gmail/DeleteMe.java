package org.Eleks.Gmail;

import java.util.Date;

public class DeleteMe {


    public static void main(String[] args) {
         Date date1 =new Date("Mon, Sep 6, 2021, 4:59 PM");
         Date date2 =new Date("Wed, Sep 1, 2021, 4:28 PM");
         Date date3 =new Date("Tue, Aug 31, 2021, 9:00 PM");
         Boolean bol = date3.before(date1);
        System.out.println(date1.compareTo(date3));
        System.out.println(date3.compareTo(date1));
        System.out.println(date3.compareTo(date3));
        System.out.println(bol);

    }
}
