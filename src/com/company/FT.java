package com.company;

public class FT {

    private int N;
    private double[] real;
    private double[] imag;

    public FT(double[] x) {
        N = x.length;

        real = new double[N];
        imag = new double[N];

        // calculate Fourier transform
        for (int k = 0; k < N; k++) {
            for (int n = 0; n < N; n++) {
                real[k] += x[n] * Math.cos(2 * Math.PI * k * n / N);
                imag[k] -= x[n] * Math.sin(2 * Math.PI * k * n / N);
            }
        }
    }

    public double[] amplitude() {
        double[] amplitude = new double[N];
        for (int k = 0; k < N; k++) {
            amplitude[k] = Math.sqrt(real[k] * real[k] + imag[k] * imag[k]) / N;
        }
        return amplitude;
    }
}