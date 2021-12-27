package edu.eskisehir.solution;

import edu.eskisehir.utils.LinkedList;

public class DRASolution extends Solution {
    private double ty;
    private double a;
    private double b = 0;
    private double f;

    public DRASolution(LinkedList<Double> dataset) {
        super(dataset);
        this.name = "Deseasonalized regression analysis";
    }

    private void calculateA() {
        a = f - (b * 12.5);
    }

    public void calculateB() {
        double periodAverage;
        int periodAverageIndex = 0;
        f = 0;
        double periodFactor = 0;
        double deaseasonDemand = 0;
        double deaseasonDemandSum = 0;
        for (int i = 0; i < actualDataset.size(); i++) {
            f += actualDataset.get(i);
        }
        f = f / 24.0;
        for (int i = 0; i < actualDataset.size(); i++) {
            periodAverageIndex++;
            if (periodAverageIndex < 13) {
                periodAverage = (actualDataset.get(i) + actualDataset.get(i + 12)) / 2.0;
            } else {
                periodAverage = (actualDataset.get(i) + actualDataset.get(i - 12)) / 2.0;
            }
            periodFactor = periodAverage / f;
            deaseasonDemand = actualDataset.get(i) / periodFactor;
            deaseasonDemandSum += actualDataset.get(i) / periodFactor;
            ty += (i + 1) * deaseasonDemand;
        }
        b = ((24.0 * ty) - (300 * deaseasonDemandSum)) / ((24.0 * 4900) - 90000);
    }

    @Override
    public void solve() {
        calculateB();
        calculateA();
        double periodAverageIndex = 0;
        double periodAverage = 0;
        double periodFactor = 0;
        double forecast = 0;
        for (int i = 0; i < actualDataset.size(); i++) {
            periodAverageIndex++;
            if (periodAverageIndex < 13) {
                periodAverage = (actualDataset.get(i) + actualDataset.get(i + 12)) / 2.0;
            } else {
                periodAverage = (actualDataset.get(i) + actualDataset.get(i - 12)) / 2.0;
            }
            periodFactor = periodAverage / f;
            forecast = periodFactor * (a + b * (i + 1));
            forecastedDataset.add(forecast);
        }
        calculateMSE();
    }
}
