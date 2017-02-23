package ru.stqa.pft.sandbox;

import java.util.Objects;

public class Equality {
    public static void main(String[] args) {
        String s1 = "firefox";
        String s2 = new String(s1);

        System.out.println(s1 == s2); // сравнение ссылок - физтческое
        System.out.println(Objects.equals(s1, s2)); // сравнение содержимого объектов - логическое
    }
}
