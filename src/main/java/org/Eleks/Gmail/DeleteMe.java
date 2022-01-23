package org.Eleks.Gmail;

import java.util.Arrays;
import java.util.Date;

public class DeleteMe {

    public static void maxArray(int[] arrays) {
        System.out.println(Arrays.stream(arrays).max());
    }

    public static int maxArray2(int[] arrays) {
        int min = arrays[0];

        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] > min) {
                min = arrays[i];
            }
        }
        return min;
    }

    public static void main(String[] args) {
//
//        int num =4;
//
//        String emailLocation="//tr//td//span[@title]";
//        String specificEmailLocation = String.format("[%s]",num);
//        String emailForDel= emailLocation;
//        int inputPleaseIndex=4;
//        emailForDel = new StringBuilder(emailForDel).insert(inputPleaseIndex,specificEmailLocation).toString();
//        System.out.println(emailForDel);

        int[] array = {5, 6, 4, 2, -9};
        maxArray(array);
        System.out.println(maxArray2(array));
    }
}
