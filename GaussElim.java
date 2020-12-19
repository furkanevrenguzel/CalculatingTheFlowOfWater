package project;

public class GaussElim {

  public static double[] solve(double[][] A, double[] b) {
        int n = b.length;

        for (int p = 0; p < n; p++) {

          
        int max = p;   // Find swap and row
        for (int i = p + 1; i < n; i++)
        {
            if (Math.abs(A[i][p]) > Math.abs(A[max][p]))
            {
                max = i;
            }
        }
	        double[] temp = A[p]; // Singular 
	        A[p] = A[max];
	        A[max] = temp;
	        double t = b[p];
	        b[p] = b[max];
	        b[max] = t;

   
        for (int i = p + 1; i < n; i++) // Pivot with in A and 
        {
            double alpha = A[i][p] / A[p][p];
            b[i] -= alpha * b[p];
            for (int j = p; j < n; j++) 
            {
                A[i][j] -= alpha * A[p][j];
            }
        }
    }

        double[] x = new double[n]; // Back subst
        for (int i = n - 1; i >= 0; i--)
        {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++)
            {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }
        return x;
    }
}