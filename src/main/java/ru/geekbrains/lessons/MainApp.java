package ru.geekbrains.lessons;

import java.util.Arrays;
import java.util.Random;

public class MainApp {
    public static void main(String[] args) {

        Random random = new Random();
        ChainingHashMap<Integer, String> map = new ChainingHashMap<>(5);
        map.put(2, "B");
        map.put(4, "D");
        map.put(5, "E");
        map.put(12, "L");
        map.put(15, "O");
        map.put(18, "R");
        map.put(20, "T");
        map.put(22, "V");
        map.put(23, "W");
        map.put(25, "Y");
        map.print();

        int[] removed = { 4, 5, 12, 23 };
        System.out.println("Тест удаления эл-тов: " + Arrays.toString(removed));
        System.out.println();
        for (int i : removed) {
            map.remove(i);
        }
        map.print();

        System.out.println("Вывод хэш-таблицы: ");
        System.out.println(map);

        /*for (int i = 0; i < 10 ; i++) {
            map.put(random.nextInt(26), "a");
        }*/

    }
}
