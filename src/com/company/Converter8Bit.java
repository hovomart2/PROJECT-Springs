package com.company;

import java.util.ArrayList;
import java.util.List;

public class Converter8Bit extends Converter {

    private static final int BITS_PER_SPRING = 8;
    private static final double SPRING_CONSTANT = 1.0;
    private static final double UNIT_MASS = 1.0;

    public List<Integer> convertBitsToSpringSystem(String bits) {
        List<Integer> springSystem = new ArrayList<>();
        for (int i = 0; i < bits.length(); i += BITS_PER_SPRING) {
            int sum = 0;
            for (int j = 0; j < BITS_PER_SPRING; j++) {
                if (i + j < bits.length()) {
                    sum = (sum << 1) + (bits.charAt(i + j) - '0');
                }
            }
            springSystem.add(sum);
        }
        springs = new Spring[springSystem.size()];
        for (int i = 0; i < springSystem.size(); i++) {
            springs[i] = new Spring(SPRING_CONSTANT);
        }
        return springSystem;
    }

    @Override
    public double evaluateDecimalValue(String binarySequence) {
        return 0.0;
    }
}

