package com.company;

import java.util.List;

public abstract class Converter {

    protected Spring[] springs;

    public abstract List<Integer> convertBitsToSpringSystem(String bits);

    public void computeOscillations(List<Integer> springSystem) {
        double m = 1.0;
        double k = 1.0;
        double[] x = new double[springSystem.size()];
        double[] v = new double[springSystem.size()];
        for (int t = 0; t < 1000; t++) {
            double[] a = computeAccelerations(springSystem, x, k, m);
            for (int i = 0; i < x.length; i++) {
                v[i] += a[i];
                x[i] += v[i];
            }
        }
    }

    private double[] computeAccelerations(List<Integer> springSystem, double[] x, double k, double m) {
        double[] a = new double[x.length];
        for (int i = 0; i < springSystem.size(); i++) {
            int left = (i > 0) ? springSystem.get(i - 1) : 0;
            int right = (i < springSystem.size() - 1) ? springSystem.get(i + 1) : 0;
            a[i] = k * (left + right - 2 * springSystem.get(i)) / m;
        }
        return a;
    }

    public double[] computeFrequencyAmplitudes(double[] oscillations) {
        FT ft = new FT(oscillations);
        return ft.amplitude();
    }


    public abstract double evaluateDecimalValue(String binarySequence);


}