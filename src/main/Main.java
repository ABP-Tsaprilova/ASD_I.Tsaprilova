package main;
import java.util.Scanner;
import labs.Lab_1;
import labs.Lab_2;
import labs.Lab_3;
import labs.Lab_4;
import labs.Lab_5;
import labs.Lab_6;
import labs.Lab_7;
import labs.Lab_8;
import labs.Lab_9;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of lab:");
        int lab = scanner.nextInt();

        switch (lab) {
            case 1:
                Lab_1.lab_1();
                break;
            case 2:
                Lab_2.lab_2();
                break;
            case 3:
                Lab_3.lab_3();
                break;
            case 4:
                Lab_4.lab_4();
                break;
            case 5:
                Lab_5.lab_5();
                break;
            case 6:
                Lab_6.lab_6();
                break;
            case 7:
                Lab_7.lab_7();
                break;
            case 8:
                Lab_8.lab_8();
                break;
            case 9:
                Lab_9.lab_9();
                break;


        }
    }
}