package edu.eskisehir.solution;

import edu.eskisehir.utils.Node;
import edu.eskisehir.utils.Tree;

public class DESSolution extends Solution {
    private double alpha;
    private double beta;
    private double s0;
    private double g0;

    public DESSolution(Tree<Node> dataset, double alpha, double beta, double s0, double g0) {
        super(dataset);
        this.alpha = alpha;
        this.beta = beta;
        this.s0 = s0;
        this.g0 = g0;
    }

    @Override
    public void solve() {
        super.solve();
    }
}
