package com.company.Billiards;

import java.util.Random;

public class HorizontalStadiumBilliard{
    public static void main(String[] args) {
        simulateHorizontalStadiumBilliard();
    }

    public static void simulateHorizontalStadiumBilliard() {
        Random random = new Random();
        double L = 2.0; // Length of the line segments

        // Randomly select initial position and momentum
        double x = random.nextDouble() * 2 - 1;
        double y = random.nextDouble() * 2 - 1;
        double px = random.nextDouble() * 2 - 1; // Range: -1 to 1
        double py = random.nextDouble() * 2 - 1; // Range: -1 to 1
        double magnitude = Math.sqrt(px * px + py * py);
        px /= magnitude;
        py /= magnitude;

        // Main simulation loop
        while (true) {
            // Determine which side the particle will hit
            double hitTime;
            if (py > 0 && y + L >= 1) {
                // Particle will hit the top line segment
                hitTime = (1 - y) / py;
            } else if (py < 0 && y - L <= -1) {
                // Particle will hit the bottom line segment
                hitTime = (-1 - y) / py;
            } else {
                // Particle will hit the left or right semicircles
                double a = px * px + py * py;
                double b = 2 * (x * px + y * py);
                double c = x * x + y * y - 1;
                double discriminant = b * b - 4 * a * c;

                double t1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                double t2 = (-b - Math.sqrt(discriminant)) / (2 * a);

                if (t1 >= 0 && t1 <= 1) {
                    hitTime = t1;
                } else if (t2 >= 0 && t2 <= 1) {
                    hitTime = t2;
                } else {
                    break;
                }
            }

            // Calculate next position at intersection
            x += hitTime * px;
            y += hitTime * py;

            // Determine new momentum after reflection
            if (py > 0 && y + L >= 1) {
                // Particle reflects off the top line segment
                py = -py;
            } else if (py < 0 && y - L <= -1) {
                // Particle reflects off the bottom line segment
                py = -py;
            } else {
                // Particle reflects off the left or right semicircles
                double xc = (px * px - py * py) / (2 * px);
                double ppx = px;
                double ppy = py;
                px = (y * y - (x - xc) * (x - xc)) * ppx - 2 * (x - xc) * y * ppy;
                py = -2 * (x - xc) * y * ppx + ((x - xc) * (x - xc) - y * y) * ppy;
                magnitude = Math.sqrt(px * px + py * py);
                px /= magnitude;
                py /= magnitude;
            }

            // Print the updated position and momentum
            System.out.println("Position: (" + x + ", " + y + ")");
            System.out.println("Momentum: (" + px + ", " + py + ")\n");
        }
    }
}
