package edu.eskisehir.demandforecast;

import edu.eskisehir.model.Record;


import java.util.ArrayList;
import java.util.List;

public class DeseasonalizedRegressionAnalysisAlg {
    private List<Double> forecasts;
    private double mse;
    private double ty;
    private double y;
    private double a;
    private double b = 0;
    double f;


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

    public void Cala() {
        a = f - (b * 12.5);
    }

    public void Calb(List<Record> records) {
        double periodAverage;
        int periodAverageIndex = 0;
        f = 0;
        double periodFactor = 0;
        double deaseasonDemand = 0;
        double deaseasonDemandSum = 0;
        for (int i = 0; i < records.size(); i++) {
            f += records.get(i).getValue();
        }
        f = f / 24.0;
        for (int i = 0; i < records.size(); i++) {
            periodAverageIndex++;
            if (periodAverageIndex < 13) {
                periodAverage = (records.get(i).getValue() + records.get(i + 12).getValue()) / 2.0;
            } else {
                periodAverage = (records.get(i).getValue() + records.get(i - 12).getValue()) / 2.0;
            }
            periodFactor = periodAverage / f;
            deaseasonDemand = records.get(i).getValue() / periodFactor;
            deaseasonDemandSum += records.get(i).getValue() / periodFactor;
            ty += (i + 1) * deaseasonDemand;
        }
        b = ((24.0 * ty) - (300 * deaseasonDemandSum)) / ((24.0 * 4900) - 90000);
    }

    public void run(List<Record> records) {
        forecasts = new ArrayList<>();
        Calb(records);
        Cala();
        double periodAverageIndex=0;
        double periodAverage=0;
        double periodFactor=0;
        double forecast=0;
        double demand=0;
        for (int i = 0; i < records.size(); i++) {
            periodAverageIndex++;
            if (periodAverageIndex < 13) {
                periodAverage = (records.get(i).getValue() + records.get(i + 12).getValue()) / 2.0;
            } else {
                periodAverage = (records.get(i).getValue() + records.get(i - 12).getValue()) / 2.0;
            }
            periodFactor = periodAverage / f;
            forecast = periodFactor * (a+ b*(i+1));
            forecasts.add(forecast);
        }


        double totalError = 0;
        for (int i = 0; i< records.size(); i++) {
            demand = records.get(i).getValue();
            forecast = forecasts.get(i);
            totalError += (forecast - demand) * (forecast - demand);
        }
        mse = Math.abs(totalError / records.size());
    }
}

