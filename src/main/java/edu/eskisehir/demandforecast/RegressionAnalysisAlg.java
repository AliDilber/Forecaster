package edu.eskisehir.demandforecast;

import edu.eskisehir.model.Record;

import java.util.ArrayList;
import java.util.List;

public class RegressionAnalysisAlg {
    private double a;
    private double b;
    double ty = 0;
    double y = 0;
    private List<Double> forecasts;
    private double mse;

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
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

    public void Cala() {
        a = (y / 24.0) - (b * (300 / 24.0));
    }

    public void Calb(List<Record> records) {
        ty = 0;
        y = 0;
        for (int i = 0; i < 24; i++) {
            ty += records.get(i).getValue() * (i + 1);
        }
        for (int j = 0; j < 24; j++) {
            y += records.get(j).getValue();
        }
        b = ((24 * ty) - (300 * y)) / ((24 * 4900) - 90000);
    }

    public void run(List<Record> records) {
        forecasts = new ArrayList<>();
        Calb(records);
        Cala();
        int month = 0;
        for (int i = 0; i < records.size(); i++) {
            month++;
            double forecast = a + b * month;
            forecasts.add(forecast);
        }
        double totalError = 0;
        for (int i = 0; i < records.size(); i++) {
            double demand = records.get(i).getValue();
            double forecast = forecasts.get(i);
            totalError += (forecast - demand) * (forecast - demand);
        }
        mse = Math.abs(totalError / records.size());
    }
}
