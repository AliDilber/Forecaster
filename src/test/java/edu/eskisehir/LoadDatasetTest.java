package edu.eskisehir;

import edu.eskisehir.model.Record;

import edu.eskisehir.utils.DatasetIO;
import org.junit.Test;


import java.io.IOException;

import java.util.List;

import edu.eskisehir.demandforecast.ExponentialSmoothingAlg;
import edu.eskisehir.demandforecast.DeseasonalizedRegressionAnalysisAlg;
import edu.eskisehir.demandforecast.DoubleExponentialSmoothingAlg;
import edu.eskisehir.demandforecast.RegressionAnalysisAlg;

import java.util.stream.Collectors;

public class LoadDatasetTest {

    @Test
    public void loadTest1() throws IOException {
        List<Record> dataset = DatasetIO.readDataset("Dataset1.csv");
        ExponentialSmoothingAlg alg = new ExponentialSmoothingAlg();
        alg.run(dataset);
        System.out.println(dataset.stream().map(Record::getValue).collect(Collectors.toList()));
        System.out.println(alg.getForecasts());
        System.out.println("MSE: " + alg.getMse());
    }

    @Test
    public void loadTest2() throws IOException {
        List<Record> dataset = DatasetIO.readDataset("Dataset1.csv");
        DoubleExponentialSmoothingAlg alg = new DoubleExponentialSmoothingAlg();
        alg.run(dataset);
        System.out.println(dataset.stream().map(Record::getValue).collect(Collectors.toList()));
        System.out.println(alg.getForecasts());
        System.out.println("MSE: " + alg.getMse());
    }

    @Test
    public void loadTest3() throws IOException {
        List<Record> dataset = DatasetIO.readDataset("Dataset1.csv");
        RegressionAnalysisAlg alg = new RegressionAnalysisAlg();
        alg.run(dataset);
        System.out.println(dataset.stream().map(Record::getValue).collect(Collectors.toList()));
        System.out.println(alg.getForecasts());
        System.out.println("MSE: " + alg.getMse());
    }

    @Test
    public void loadTest4() throws IOException {
        List<Record> dataset = DatasetIO.readDataset("Dataset1.csv");
        DeseasonalizedRegressionAnalysisAlg alg = new DeseasonalizedRegressionAnalysisAlg();
        alg.run(dataset);
        System.out.println(dataset.stream().map(Record::getValue).collect(Collectors.toList()));
        System.out.println(alg.getForecasts());
        System.out.println("MSE: " + alg.getMse());
    }
}

