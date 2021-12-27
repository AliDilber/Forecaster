package edu.eskisehir.solution;

import edu.eskisehir.utils.LinkedList;

public class RASolution extends Solution {
    private double a;
    private double b;
    private double ty = 0;
    private double y = 0;

    public RASolution(LinkedList<Double> dataset) {
        super(dataset);
        this.name = "Regression analysis";
    }

    public void calculateA() {
        a = (y / 24.0) - (b * (300 / 24.0));
    }

    public void calculateB() {
        ty = 0;
        y = 0;
        for (int i = 0; i < 24; i++) {
            ty += actualDataset.get(i) * (i + 1);
        }
        for (int j = 0; j < 24; j++) {
            y += actualDataset.get(j);
        }
        b = ((24 * ty) - (300 * y)) / ((24 * 4900) - 90000);
    }

    @Override
    public void solve() {
        calculateB();
        calculateA();
        int month = 0;
        for (int i = 0; i < actualDataset.size(); i++) {
            month++;
            double forecast = a + b * month;
            forecastedDataset.add(forecast);
        }
        calculateMSE();
    }
}
