package com.company;

import java.util.ArrayList;
import java.util.List;

public class ConverterFloat extends Converter{
    @Override
    public List<Integer> convertBitsToSpringSystem(String bits) {
        List<Integer> springSystem = new ArrayList<>();
        for (int i = 0; i < bits.length(); i++) {
            int bit = Integer.parseInt(bits.substring(i, i+1));
            springSystem.add(bit * 2 - 1);
        }
        return springSystem;
    }

    @Override
    public double evaluateDecimalValue(String binarySequence) {
        return 0;
    }
}
