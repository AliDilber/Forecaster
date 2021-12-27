package edu.eskisehir.solution;

import edu.eskisehir.utils.LinkedList;

public abstract class Solution {
    protected String name;
    protected double MSE;
    protected LinkedList<Double> actualDataset;
    protected LinkedList<Double> forecastedDataset = new LinkedList<>();

    public Solution(LinkedList<Double> dataset)
    {
     this.actualDataset = dataset;
    }

    public LinkedList<Double> getForecastedDataset() {
        return forecastedDataset;
    }

    public String getName() {
        return name;
    }

    public void solve() {
        // will be filled for each of the solution
    }

    protected void calculateMSE()
    {
        // MSE will be calculated after the calculation
        double totalError = 0;
        for (int i = 0; i< actualDataset.size(); i++) {
            double demand = actualDataset.get(i);
            double forecast = forecastedDataset.get(i);
            totalError += Math.pow(forecast - demand, 2);
        }
        this.MSE = totalError / actualDataset.size();
    }

    public double getMSE()
    {
        return MSE;
    }
}
