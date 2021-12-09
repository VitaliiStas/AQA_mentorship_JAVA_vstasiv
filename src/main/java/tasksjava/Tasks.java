package tasksjava;

import com.google.common.primitives.Chars;
import org.Eleks.Gmail.api.SendEmailByApi;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Tasks {
    //Method witch take the string and re
//    public static void characterCount(Object object) {
//        String inputString = object.toString();
//        if (inputString.length() > 10000) {
//            throw new RuntimeException("inputString is to long");
//        } else {
//            Set<Character> set;
//            int count = 0;
//            Map<Character, Integer> result = new HashMap<>();
//            List<Character> list = new ArrayList<>();
//            for (int i = 0; i < inputString.length(); i++) {
//                list.add(inputString.toLowerCase().charAt(i));
//            }
//            set = Set.copyOf(list);
//            for (Character chars : set) {
//                for (Character listChar : list) {
//                    if (chars.equals(listChar)) {
//                        count++;
//                    }
//                }
//                result.put(chars, count);
//                count = 0;
//            }
//            System.out.println(result);
//        }
//    }
    public static void characterCount(Object object) {
        Function<Object,Map<Object, Long>> function = (obj) -> {
            Map<Object, Long> res = new HashMap<>();
            List<Character> list= Chars.asList(obj.toString()
                    .toLowerCase(Locale.ROOT)
                    .toCharArray());
//            Set<Character> set = Set.copyOf(list);
            Set<Character> set = new HashSet<>(list);
            for (Object element : set) {
                res.put(element, (list.stream()
                        .filter(element::equals)
                        .count()));
            }return res;
        };
        System.out.println("Letters count match:\n"+function.apply(object)+"\n");
    }


    //    public static boolean listDuplicateChecking(List<Object> objects) {
//        boolean res = false;
//        //if found duplicates return true
//        Set<Object> objectSet = new HashSet<>();
//        for (Object step : objects) {
//            objectSet.add(step);
//            if (!objectSet.add(step)) {
//                res = true;
//            }
//        }
//        System.out.println(res);
//        return res;
//    }

//    public boolean listDuplicateChecking(List<Object> objects) {
//        boolean res = false;
//        for (Object obj : objects) {
//            if (objects.stream().filter(obj::equals).count() > 1) {
//                res = true;
//            }
//        }
//        System.out.println(res);
//        return res;
//    }

//todo set proper type
    public boolean listDuplicateChecking(List<?> objects) {
        Predicate<List<?>> p = (objList) -> {
            for (Object objForSeach : objects) {
                if (objects.stream()
                        .filter(objForSeach::equals)
                        .count() > 1){
                    return true;
                }
            }
            return false;
        };
        return p.test(objects);
    }


    public static void main(String[] args) throws InterruptedException {
//        Tasks.characterCount(RandomStringUtils.randomAlphabetic(1000));
//        Tasks.characterCount("RandomStringUtils.randomAlphabetic(1000)");
//        Tasks.characterCount(12345);
//        Tasks.characterCount('a');
//        Tasks.characterCount(true);
//        Tasks.characterCount(RandomStringUtils.randomAlphabetic(100000));
//
//        System.out.println(new Tasks().listDuplicateChecking(Arrays.asList(1,1, 2, 3, 4, 5)));
//        System.out.println("123"=="123");
//        System.out.println(2 == 2 );
//        System.out.println(2000 == 2000);
//        System.out.println(new String("123").intern() == new String("123").intern());

        //        System.out.println(new Tasks().listDuplicateChecking(Arrays.asList("Hello", "World!", "How", "Are", "Hello")));
//new SendEmailByApi().sendEmailByApi("vitalii.stasiv.eleks@gmail.com");

        Thread t = Thread.currentThread(); // получаем главный поток
//        t.sleep(10000);
        System.out.println(t);
        Thread t1 = new Thread("Thread1");
        Thread t2 = new Thread("Thread2");
//        t.start();
        t2.start();
        System.out.println(t2.getName());
        t1.join();
        t2.join();


    }



}


