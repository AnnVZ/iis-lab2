import java.util.List;

public class Algorithm {
    private List<Class> classes;
    private int n;
    private double[][] b;
    private double[] B;
    private double[][] a;

    Algorithm(List<Class> classes, int n) {
        this.classes = classes;
        this.n = n;
        b = new double[classes.size()][n];
        B = new double[n];
        a = new double[classes.size()][n];
    }

    void train() {
        for (int i = 0; i < classes.size(); i++) {
            for (int t = 0; t < n; t++) {
                List<List<Integer>> currentObjects = classes.get(i).getObjectsAttributes();
                for (int j = 0; j < classes.get(i).getObjectsNames().size(); j++) {
                    b[i][t] += currentObjects.get(j).get(t);
                }
                b[i][t] /= currentObjects.size();
            }
        }
        for (int t = 0; t < n; t++) {
            for (int i = 0; i < classes.size(); i++) {
                B[t] += b[i][t];
            }
            B[t] /= classes.size();
            for (int i = 0; i < classes.size(); i++) {
                a[i][t] = Math.abs(b[i][t] - B[t]);
            }
        }
    }

    String recognize(List<Integer> attributes) {
        double[] mu = new double[classes.size()];
        for (int i = 0; i < classes.size(); i++) {
            mu[i] = Integer.MIN_VALUE;
            List<List<Integer>> currentObjects = classes.get(i).getObjectsAttributes();
            for (int j = 0; j < currentObjects.size(); j++) {
                double sum1 = 0, sum2 = 0;
                for (int k = 0; k < n; k++) {
                    sum1 += Math.pow(-1, !currentObjects.get(j).get(k).equals(attributes.get(k)) ? 1 : 2) * a[i][j];
                    sum2 += a[i][k];
                }
                double value = Math.max(0, sum1 / sum2);
                if (value > mu[i]) {
                    mu[i] = value;
                }
            }
        }
        double max = mu[0];
        int maxI = 0;
        for (int i = 1; i < classes.size(); i++) {
            if (mu[i] > max) {
                max = mu[i];
                maxI = i;
            }
        }
        return classes.get(maxI).getName();
    }
}
