package com.company;

public class Spring {
    private double k;

    public Spring() {
        this.k = 1;
    }

    public Spring(double k) {
        this.k = k;
    }

    public double getK() {
        return k;
    }

    private void setK(double k) {
        this.k = k;
    }

    public double[] move(double t, double dt, double x0, double v0) {
        int n = (int) (t / dt);
        double[] positions = new double[n+1];
        double x = x0;
        double v = v0;
        double omega = Math.sqrt(k);
        double A = x0;
        double phi = Math.atan(v0/(A*omega));
        for (int i = 0; i <= n; i++) {
            positions[i] = A*Math.sin(omega*i*dt + phi);
        }
        return positions;
    }

    double[] move(double t, double dt, double x0) {
        return move(t, dt, x0, 0.0);
    }

    double[] move(double t0, double t1, double dt, double x0, double v0) {
        return move(t0, t1, dt, x0, v0, 1.0);
    }

    public double[] move(double t0, double t1, double dt, double x0, double v0, double m) {
        int n = (int) ((t1 - t0) / dt);
        double[] positions = new double[n+1];
        double x = x0;
        double v = v0;
        double omega = Math.sqrt(k/m);
        double A = x0;
        double phi = Math.atan(v0/(A*omega));
        for (int i = 0; i <= n; i++) {
            positions[i] = A*Math.sin(omega*(i*dt + t0) + phi);
        }
        return positions;
    }


    public Spring inSeries(Spring that) {
        double equivalentStiffness = this.k + that.k;
        return new Spring(equivalentStiffness);
    }

    public Spring inParallel(Spring that) {
        double equivalentStiffness = 1.0 / ((1.0 / this.k) + (1.0 / that.k));
        return new Spring(equivalentStiffness);
    }
}
