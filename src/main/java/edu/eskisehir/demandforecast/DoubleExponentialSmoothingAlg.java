package edu.eskisehir.demandforecast;

import edu.eskisehir.model.Record;

import java.util.ArrayList;
import java.util.List;

public class DoubleExponentialSmoothingAlg {

    private double alpha = 0.2;
    private double beta = 0.2;
    private double S0 = 200;
    private double G0 = 50;
    private double ST = S0;
    private double GT = G0;
    private double LastST;
    private double LastGT;
    private List<Double> forecasts;
    private double mse;

    public double getST() {
        return ST;
    }

    public void setST(double ST) {
        this.ST = ST;
    }

    public double getGT() {
        return GT;
    }

    public void setGT(double GT) {
        this.GT = GT;
    }

    public double getLastST() {
        return LastST;
    }

    public void setLastST(double lastST) {
        LastST = lastST;
    }

    public double getLastGT() {
        return LastGT;
    }

    public void setLastGT(double lastGT) {
        LastGT = lastGT;
    }


    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        if (alpha < 0 || alpha > 1) {
            throw new RuntimeException("Alpha should be in 0 - 1");
        }
        this.alpha = alpha;
    }

    public double getGamma() {
        return beta;
    }

    public void setGamma(double beta) {
        if (beta < 0 || beta > 1) {
            throw new RuntimeException("Beta should be in 0 - 1");
        }
        this.beta = beta;
    }

    public double getS0() {
        return S0;
    }

    public void setS0(double s0) {
        S0 = s0;
    }

    public double getG0() {
        return G0;
    }

    public void setG0(double g0) {
        G0 = g0;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public List<Double> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Double> forecasts) {
        this.forecasts = forecasts;
    }

    public double getMse() {
        return mse;
    }

    public void setMse(double mse) {
        this.mse = mse;
    }

    public void run(List<Record> records) {
        forecasts = new ArrayList<>();
        double totalError = 0;
        for (int i = 0; i < records.size(); i++) {
            double LastST = (alpha * records.get(i).getValue() + (1 - alpha) * (ST + GT));
            double LastGT = (beta * (LastST - ST) + (1 - beta) * GT);
            double forecast = (ST + GT);
            forecasts.add(forecast);
            ST = LastST;
            GT = LastGT;
            double demand = records.get(i).getValue();
            forecast = forecasts.get(i);
            totalError += (forecast - demand) * (forecast - demand);
        }

        mse = Math.abs(totalError / records.size());
    }
}