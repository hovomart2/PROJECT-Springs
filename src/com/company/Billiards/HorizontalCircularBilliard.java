package com.company.Billiards;

import java.util.*;

public class HorizontalCircularBilliard {

    public static void main(String[] args) {

        Random random = new Random();

        //Randomly select initial position inside the circle
        double x = 1;
        double y = 1;
        while (x*x+y*y>1){
            x = random.nextDouble() * 2 - 1;
            y = random.nextDouble() * 2 - 1;
        }

        //Randomly select initial momentum
        double angle = random.nextDouble() * 2 * Math.PI;
        double px = Math.cos(angle);
        double py = Math.sin(angle);


        List<Point> list = simulateCircleBilliard(x, y, px, py);
        List<Point> listWithReversedMomentum = simulateCircleBilliard(list.get(list.size()-1).getX(), list.get(list.size()-1).getY(),
                list.get(list.size()-2).getX(), list.get(list.size()-2).getY());

        System.out.println(checkReversibility(list, listWithReversedMomentum));
    }

    public static boolean checkReversibility(List<Point> list, List<Point> list1){
        for (int i = 0; i < list.size(); i++) {
            if(Math.abs(list.get(i).getX() - list1.get(list.size()-i-1).getX()) < 0.01 &&
                    Math.abs(list.get(i).getY() - list1.get(list.size() - i-1).getY()) < 0.01){
                return false;
            }
        }
        return true;
    }

    public static List<Point> simulateCircleBilliard(double x, double y, double px, double py) {
        //number of reflections
        List<Point> reflectionPoints = new ArrayList<>();

        int n = 10;

        System.out.println("Initial position: (" + x + ", " + y + ")");
        System.out.println("Initial momentum: (" + px + ", " + py + ")\n");

        // Main simulation loop
        while (n>0) {
            if(x==px){
                x = x;
                y = 1-x*x;
            }
            else {
                //a and b of the line equation
                double a = (y - py) / (x - px);
                double b = y - a * x;

                //a,b,c of the quadratic equation which gives solution of x*x+y*y=1 and y = ax + b equation
                double a1 = 1 + a * a;
                double b1 = 2 * a * b;
                double c1 = b * b - 1;
                double discriminant = b1 * b1 - 4 * a1 * c1;

                //Determine the reflection point
                x = px > x ? (-b1 + Math.sqrt(discriminant)) / (2 * a1) : (-b1 - Math.sqrt(discriminant)) / (2 * a1);
                y = Math.sqrt(1 - x * x);
            }

            //Determine new momentum after reflection
            double temp_px = px;
            double temp_py = py;
            px = (y * y - x * x) * temp_px - 2 * x * y * temp_py;
            py = -2 * x * y * temp_px + (x * x - y * y) * temp_py;

            // add to list the updated position and momentum
            reflectionPoints.add(new Point(x,y));
            n--;
        }
        return reflectionPoints;
    }
}

