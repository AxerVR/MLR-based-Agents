package com.mlr;

public class MLR {
    private final double[][] dataSet, betas;
    private double[][] X, Y, XT;
    private final int rows, cols;

    private void XMatrix(){
        X = new double[rows][cols];

        for (int i = 0; i < rows; i++){
            for (int j = 1; j < cols; j++){
                X[i][0] = 1;
                X[i][j] = dataSet[i][j - 1];
            }
        }
    }

    private void YMatrix(){
        Y = new double[rows][1];

        for (int i = 0; i < rows; i++){
            Y[i][0] = dataSet[i][cols - 1];
        }
    }

    private void XTMatrix(){
        XT = new double[cols][rows];

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                XT[j][i] = X[i][j];
            }
        }
    }

    private double[][] matrixMult(double[][] A, double[][] B){
        double[][] result = new double[A.length][B[0].length];

        if(A[0].length != B.length) return result; //Cols in A must be same rows in B in order for A * B.

        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < B[0].length; j++){
                for(int k = 0; k < A[0].length; k++){
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return result;
    }

    public static double[][] identity(int n){
        double[][] identity = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                identity[i][j] = 0;
            }
            identity[i][i] = 1;
        }
        return identity;
    }

    public double[][] inverse(double[][] A){
        double[][] identity = identity(A.length);

        if (A.length != A[0].length) return identity; //must be an n by n matrix

        double pivote;
        double aux;

        for (int i = 0; i < A.length; i++) {
            pivote = A[i][i];

            for (int j = 0; j < A.length; j++) {
                A[i][j] = A[i][j] / pivote;
                identity[i][j] = identity[i][j] / pivote;
            }

            for (int j = 0; j < A.length; j++) {
                if (i != j){
                    aux = A[j][i];

                    for (int k = 0; k < A.length; k++) {
                        A[j][k] = A[j][k] - aux * A[i][k];
                        identity[j][k] = identity[j][k] - aux * identity[i][k];
                    }
                }
            }
        }

        return identity;
    }

    public MLR(double[][] iDataSet){
        dataSet = iDataSet;
        rows = iDataSet.length;
        cols = iDataSet[0].length;

        XMatrix();
        YMatrix();
        XTMatrix();

        betas = matrixMult(inverse(matrixMult(XT, X)), matrixMult(XT, Y));
    }

    public double beta(int n) {
        return betas[n][0];
    }

    public double predictY (double x1, double x2) {
        return beta(0) + (beta(1) * x1) + (beta(2) * x2);
    }

    /*public void printDataSet(){
        for (int i = 0; i < betas.length; i++){
            for (int j = 0; j < betas[0].length; j++){
                System.out.print(betas[i][j] + " | ");
            }
            System.out.println();
        }
    }*/
}
