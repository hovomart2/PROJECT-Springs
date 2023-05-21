package com.company.Billiards;

import java.util.Random;


public class VerticalBilliard {
    private static final double GRAVITY = 10.0; // Acceleration due to gravity

    public static void main(String[] args) {
        simulateVerticalBilliard();
    }

    public static void simulateVerticalBilliard() {
        Random random = new Random();

        //Randomly select initial position and momentum
        double x = random.nextDouble() * 2 - 1;
        double y = random.nextDouble() * 2 - 1;
        double px = random.nextDouble() * 5 + 5; // Range: 5 to 10
        double py = random.nextDouble() * 5 + 5; // Range: 5 to 10
        double magnitude = Math.sqrt(px * px + py * py);
        px /= magnitude;
        py /= magnitude;

        // Main simulation loop
        while (true) {
            //Calculate next intersection time
            double t = findIntersectionTime(x, y, px, py);

            //Update position and momentum based on the intersection time
            x += t * px;
            y += t * py;
            double ppx = px;
            double ppy = py;
            px = (y * y - x * x) * ppx - 2 * x * y * ppy;
            py = -2 * x * ppx + (x * x - y * y) * ppy;
            magnitude = Math.sqrt(px * px + py * py);
            px /= magnitude;
            py /= magnitude;
            py -= GRAVITY * t;

            // Print the updated position and momentum
            System.out.println("Position: (" + x + ", " + y + ")");
            System.out.println("Momentum: (" + px + ", " + py + ")\n");
        }
    }

    public static double findIntersectionTime(double x, double y, double px, double py) {
        // Use numerical methods to find the intersection time
        double t = 0.0;
        double tolerance = 1e-6; // Tolerance for convergence
        int maxIterations = 1000; // Maximum number of iterations
        double dt = 0.01; // Time step for derivative approximation

        for (int i = 0; i < maxIterations; i++) {
            double fy = y + t * py - 0.5 * GRAVITY * t * t - 1.0; // Intersection condition with the circle

            if (Math.abs(fy) < tolerance) {
                // Found an intersection point within the tolerance
                return t;
            }

            double fyPrime = (y + (t + dt) * py - 0.5 * GRAVITY * (t + dt) * (t + dt) - 1.0 - fy) / dt;
            t -= fy / fyPrime;

            if (Math.abs(fyPrime) < tolerance) {
                // Derivative is close to zero, convergence is not possible
                break;
            }
        }

        // No intersection found within the maximum number of iterations
        return -1.0;
    }
}