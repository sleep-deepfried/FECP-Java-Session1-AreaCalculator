package org.example;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    public void testCalculateCircleArea() {
        double radius = 5.0;
        double expected = Math.PI * radius * radius;
        double actual = Main.calculateCircleArea(radius);
        boolean passed = Math.abs(expected - actual) < 0.01;
        logTestResultToCSV("testCalculateCircleArea", "radius=5", String.format("%.2f", expected),
                String.format("%.2f", actual), passed);
        assertEquals(expected, actual, 0.01, "Circle area should be πr²");
    }

    @Test
    public void testCalculateTriangleArea() {
        double base = 10.0;
        double height = 6.0;
        double expected = 0.5 * base * height;
        double actual = Main.calculateTriangleArea(base, height);
        boolean passed = Math.abs(expected - actual) < 0.01;
        logTestResultToCSV("testCalculateTriangleArea", "base=10,height=6", String.format("%.2f", expected),
                String.format("%.2f", actual), passed);
        assertEquals(expected, actual, 0.01, "Triangle area should be 0.5 * base * height");
    }

    @Test
    public void testCalculateRectangleArea() {
        double length = 8.0;
        double width = 4.0;
        double expected = length * width;
        double actual = Main.calculateRectangleArea(length, width);
        boolean passed = Math.abs(expected - actual) < 0.01;
        logTestResultToCSV("testCalculateRectangleArea", "length=8,width=4", String.format("%.2f", expected),
                String.format("%.2f", actual), passed);
        assertEquals(expected, actual, 0.01, "Rectangle area should be length * width");
    }

    @Test
    public void testCircleOutput() throws Exception {
        String input = "1\n5\n";
        String expected = "Area of the circle: 78.54";
        String actual = runWithInput(input);
        boolean passed = actual.contains(expected);
        logTestResultToCSV("testCircleOutput", input, expected, actual, passed);
        assertTrue(passed);
    }

    @Test
    public void testTriangleOutput() throws Exception {
        String input = "2\n10\n6\n";
        String expected = "Area of the triangle: 30.00";
        String actual = runWithInput(input);
        boolean passed = actual.contains(expected);
        logTestResultToCSV("testTriangleOutput", input, expected, actual, passed);
        assertTrue(passed);
    }

    @Test
    public void testRectangleOutput() throws Exception {
        String input = "3\n8\n4\n";
        String expected = "Area of the rectangle: 32.00";
        String actual = runWithInput(input);
        boolean passed = actual.contains(expected);
        logTestResultToCSV("testRectangleOutput", input, expected, actual, passed);
        assertTrue(passed);
    }

    // Helper to simulate input and capture output
    private String runWithInput(String input) throws Exception {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();

        System.setIn(testIn);
        System.setOut(new PrintStream(testOut));

        try {
            Main.main(new String[]{});
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }

        return testOut.toString();
    }

    // Helper to log to CSV file
    private void logTestResultToCSV(String testName, String input, String expected, String actual, boolean passed) {
        String fileName = "test-results.csv";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = dtf.format(LocalDateTime.now());
        String status = passed ? "PASS" : "FAIL";

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.printf("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"%n",
                    testName, input.trim(), expected.trim(), actual.trim().replaceAll("[\\r\\n]+", " "), status, timestamp);
        } catch (IOException e) {
            System.err.println("Failed to write to CSV: " + e.getMessage());
        }
    }
}
