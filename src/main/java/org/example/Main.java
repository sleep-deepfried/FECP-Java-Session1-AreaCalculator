package org.example;

import java.util.Scanner;

public class Main {
    public static double calculateCircleArea(double radius) {
        return Math.PI * Math.pow(radius, 2);
    }

    public static double calculateTriangleArea(double base, double height) {
        return (base * height) / 2.0;
    }

    public static double calculateRectangleArea(double length, double width) {
        return length * width;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(
                "=== Area Calculator ===\n" +
                        "1. Circle\n" +
                        "2. Triangle\n" +
                        "3. Rectangle\n" +
                        "Choose a shape (1-3): "
        );
        int shapeInput = scanner.nextInt();

        switch (shapeInput) {
            case 1:
                System.out.print("Enter the radius: ");
                double radiusInput = scanner.nextDouble();
                double circleArea = calculateCircleArea(radiusInput);
                System.out.printf("Area of the circle: %.2f%n", circleArea);
                break;

            case 2:
                System.out.print("Enter the base: ");
                double baseInput = scanner.nextDouble();
                System.out.print("Enter the height: ");
                double heightInput = scanner.nextDouble();
                double triangleArea = calculateTriangleArea(baseInput, heightInput);
                System.out.printf("Area of the triangle: %.2f%n", triangleArea);
                break;

            case 3:
                System.out.print("Enter the length: ");
                double lengthInput = scanner.nextDouble();
                System.out.print("Enter the width: ");
                double widthInput = scanner.nextDouble();
                double rectangleArea = calculateRectangleArea(lengthInput, widthInput);
                System.out.printf("Area of the rectangle: %.2f%n", rectangleArea);
                break;

            default:
                System.out.println("Enter correct value!");
        }
    }
}
