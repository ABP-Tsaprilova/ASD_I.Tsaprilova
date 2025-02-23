package main;
import java.util.Scanner;
import labs.Lab_1;
import labs.Lab_2;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of lab:");
        int lab = scanner.nextInt();

        switch (lab) {
            case 1:
                Lab_1.lab_1();
             break;

        }
    }
}