package edu.eskisehir.solution;

import edu.eskisehir.utils.Node;
import edu.eskisehir.utils.Tree;

public abstract class Solution {
    private double MSE;
    private Tree<Node> actualDataset;
    private Tree<Node> forecastedDataset;

    public Solution(Tree<Node> dataset)
    {
     this.actualDataset = dataset;
    }

    public void solve() {
        // will be filled for each of the solution
    }

    private void calculateMSE()
    {
        // MSE will be calculated after the calculation
    }

    public double getMSE()
    {
        return MSE;
    }
}
