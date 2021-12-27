package edu.eskisehir.demandforecast;

import edu.eskisehir.model.Record;

import java.util.ArrayList;
import java.util.List;

public class ExponentialSmoothingAlg {
    private double alpha = 0.2;
    private List<Double> forecasts;
    private double mse;

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        if (alpha < 0 || alpha > 1) {
            throw new RuntimeException("Alpha should be in 0 - 1");
        }
        this.alpha = alpha;
    }

    public double getMse() {
        return mse;
    }

    public List<Double> getForecasts() {
        return forecasts;
    }

    public void run(List<Record> records) {
        forecasts = new ArrayList<>();
        double lastForecast = records.get(0).getValue();
        forecasts.add(lastForecast);
        for (int i = 1; i < records.size(); i++) {
            double forecast = (alpha * records.get(i - 1).getValue() + (1 - alpha) * lastForecast);
            forecasts.add(forecast);
            lastForecast = forecast;
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