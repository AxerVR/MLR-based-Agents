package com.mlr;

public class Gradient {
    private final double[][] dataSet;
	private double b0, b1, a, error;

    public Gradient(double[][] iDataSet) {
		b0 = 0;
		b1 = 0;
        dataSet = iDataSet;
    }
	
    private double[] learning(){
        double[] learning = new double[3];
        double aux = 0;
		
        for (int i = 0; i < dataSet.length; i++) {
            aux = dataSet[i][0] - (b0 + b1 * dataSet[i][1]);
            learning[0] += aux;
            learning[1] += dataSet[i][1] * aux;
            learning[2] += aux;
        }
		
        learning[0] = -2 * (learning[0]) / dataSet.length;
        learning[1] = -2 * (learning[1]) / dataSet.length;
        learning[2] = (learning[2] * learning[2]) * 1 / dataSet.length;
		
        return learning;
    }
	
	public void betas(){
		for (int i = 0; i < 150000 ; i++) {
            double[] aux = learning();
            b0 = (b0 - (a * aux[0]));
            b1 = (b1 - (a * aux[1]));
            error = aux[2];
        }
    }
	
	public void setAlfa(double a) {
		this.a = a;
	}
	
	public double predictY(double x){
		return b0 + b1 * x;
	}
	
	public double getError(){
		return error;
	}
	
	public double beta0(){
		return b0;
	}
	
	public double beta1(){
		return b1;
	}

}