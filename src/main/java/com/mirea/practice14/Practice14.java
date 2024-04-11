package com.mirea.practice14;

import java.util.concurrent.Semaphore;

class Student implements Runnable {
    private String name;
    private Semaphore table;

    public Student(String name, Semaphore table) {
        this.name = name;
        this.table = table;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " waiting");
            table.acquire();
            System.out.println(name + " eating");
            Thread.sleep(3000);
            System.out.println(name + " exit");
            table.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class Practice14 {
    public static void main(String[] args) {
        System.out.println("Practical task 1.14, Student Korneev Arseniy, RIBO-04-22, Variant 2");
        Semaphore table = new Semaphore(2);
        Thread[] students = new Thread[7];
        for (int i = 0; i < 7; i++) {
            students[i] = new Thread(new Student("Student" + (i + 1), table));
            students[i].start();
        }

        try {
            for (Thread student : students) {
                student.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}