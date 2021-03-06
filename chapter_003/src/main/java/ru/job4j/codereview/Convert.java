package ru.job4j.codereview;


import java.util.*;

/**
 * Непонятно зачем определили дефолтный конструктор, здесь он создался бы автоматически.
 * Во втором методе есть лишние пустые строки.
 * Комментарии вроде как лучше писать через джавадоки.
 * В остальном вроде все ок.
 */

public class Convert {

    public Convert() {

    }

    //Converts array to list
    List<Integer> makeList(int[][] array) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                list.add(array[i][j]);
            }
        }
        return list;
    }


    //Converts list to array
    public int[][] makeArray(List<Integer> list, int rws) {
        Iterator<Integer> iterator = list.iterator();
        int cls = list.size() / rws + (list.size() % rws == 0 ? 0 : 1);


        int[][] array = new int[rws][cls];
        for (int i = 0; i < rws; i++) {
            for (int j = 0; j < cls; j++) {
                if (iterator.hasNext()) {
                    array[i][j] = iterator.next();
                } else {
                    array[i][j] = 0;
                }
            }
        }
        return array;
    }
}