package com.company;

import java.util.Stack;

public class SpringArray {

    public static Spring equivalentSpring(String springExpr) {
        Stack<Spring> inSeriesStack = new Stack<>();
        Stack<Spring> inParallelStack = new Stack<>();
        for (int i = 0; i < springExpr.length(); i++) {
            char c = springExpr.charAt(i);
            if (c == '{') {
                inSeriesStack.push(new Spring());
            } else if (c == '[') {
                inParallelStack.push(new Spring());
            } else if (c == '}') {
                Spring s = inSeriesStack.pop();
                if (!inSeriesStack.empty()) {
                    inSeriesStack.peek().inSeries(s);
                }
            } else if (c == ']') {
                Spring s = inParallelStack.pop();
                if (!inParallelStack.empty()) {
                    inParallelStack.peek().inParallel(s);
                }
            }
        }
        while (!inParallelStack.empty()) {
            inSeriesStack.peek().inParallel(inParallelStack.pop());
        }
        if (inSeriesStack.empty()) {
            return new Spring();
        }
        return inSeriesStack.pop();
    }


    public static Spring equivalentSpring(String springExpr, Spring[] springs){
        Stack<Spring> springStack = new Stack<>();

        for (int i = 0; i < springExpr.length(); i++) {
            char c = springExpr.charAt(i);
            if (c == '{') {
                springStack.push(springs[i]);
            } else if (c == '[') {
                springStack.push(null);
            } else if (c == '}') {
                double equivalentStiffness = 0;
                while (springStack.peek() != null) {
                    equivalentStiffness += springStack.pop().getK();
                }
                springStack.pop();
                springStack.push(new Spring(equivalentStiffness));
            } else if (c == ']') {
                double equivalentStiffness = 0;
                while (springStack.peek() != null) {
                    equivalentStiffness += springStack.pop().getK();
                }
                springStack.pop();
                springStack.push(new Spring(1.0 / equivalentStiffness));
            }
        }
        return springStack.pop();
    }
}