package edu.eskisehir.solution;

import edu.eskisehir.utils.LinkedList;

public class DESSolution extends Solution {
    private double alpha = 0.2;
    private double beta = 0.2;
    private double S0 = 200;
    private double G0 = 50;
    private double ST = S0;
    private double GT = G0;

    public DESSolution(LinkedList<Double> dataset) {
        super(dataset);
        this.name = "Double-exponential smoothing";
    }

    @Override
    public void solve() {
        for (int i = 0; i < actualDataset.size(); i++) {
            double LastST = (alpha * actualDataset.get(i) + (1 - alpha) * (ST + GT));
            double LastGT = (beta * (LastST - ST) + (1 - beta) * GT);
            double forecast = (ST + GT);
            forecastedDataset.add(forecast);
            ST = LastST;
            GT = LastGT;
        }
        calculateMSE();
    }
}
