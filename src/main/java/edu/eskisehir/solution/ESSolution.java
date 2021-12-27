package edu.eskisehir.solution;

import edu.eskisehir.utils.LinkedList;

public class ESSolution extends Solution {
    private double alpha = 0.2;

    public ESSolution(LinkedList<Double> dataset) {
        super(dataset);
        this.name = "Exponential smoothing";
    }

    @Override
    public void solve() {
        double lastForecast = actualDataset.get(0);
        forecastedDataset.add(lastForecast);
        for (int i = 1; i < actualDataset.size(); i++) {
            double forecast = (alpha * actualDataset.get(i - 1) + (1 - alpha) * lastForecast);
            forecastedDataset.add(forecast);
            lastForecast = forecast;
        }

        calculateMSE();
    }
}
