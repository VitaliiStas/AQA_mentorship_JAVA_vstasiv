package org.Eleks.Gmail;

import java.util.Date;

public class DeleteMe {


    public static void main(String[] args) {

        int num =4;

        String emailLocation="//tr//td//span[@title]";
        String specificEmailLocation = String.format("[%s]",num);
        String emailForDel= emailLocation;
        int inputPleaseIndex=4;
        emailForDel = new StringBuilder(emailForDel).insert(inputPleaseIndex,specificEmailLocation).toString();
        System.out.println(emailForDel);
    }
}
