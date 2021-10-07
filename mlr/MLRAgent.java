package com.mlr;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class MLRAgent extends Agent {
	
	private MLRGui myGui;

  protected void setup() {
	  
	myGui = new MLRGui(this);
	myGui.showGui();
  }
  
  // Put agent clean-up operations here
	protected void takeDown() {
		// Close the GUI
		myGui.dispose();
		// Printout a dismissal message
		System.out.println("MLR-agent " + getAID().getName() + " terminating.");
	}
  
  public void normalEquation(double x1, double x2) {
		addBehaviour(new OneShotBehaviour() {
			public void action() {
				double[][] dataSetMLR = new double[][]{
						{41.9,  29.1,   251.3},
						{43.4,  29.3,   251.3},
						{43.9,  29.5,   248.3},
						{44.50, 29.7,   267.5},
						{47.3,  29.9,   273},
						{47.50, 30.3,   276.5},
						{47.9,  30.5,   270.3},
						{50.2,  30.7,   274.9},
						{52.8,  30.8,   285},
						{53.2,  30.9,   290},
						{56.7,  31.5,   297},
						{57,    31.7,   302.5},
						{63.5,  31.9,   304.5},
						{65.3,  32,     309.5},
						{71.1,  32.1,   321.7},
						{77,    32.5,   330.7},
						{77.8,  32.9,   349}
				};

				MLR mlr = new MLR(dataSetMLR);
				
				System.out.println("With Normal Equation Approach:");
				System.out.println("Y = " + mlr.beta(0) + " + "+ mlr.beta(1) + "(" + x1 + ") + " + mlr.beta(2) + "(" + x2 + ")");
				System.out.println("Y = " + mlr.predictY(x1, x2));
		
			}
		} );
	}
	
	public void gradient(double x, double alfa) {
		addBehaviour(new OneShotBehaviour() {
			public void action() {
				double[][] dataSetSLR = new double[][]{
						{651,23},
						{762,26},
						{856,30},
						{1063,34},
						{1190,43},
						{1298,48},
						{1421,52},
						{1440,57},
						{1518,58}
				};
				
				Gradient slr = new Gradient(dataSetSLR);
				slr.setAlfa(alfa);
				slr.betas();
				
				System.out.println("\n\nWith Gradient Descent:");
				System.out.println("Error = " + slr.getError());
				System.out.println("Y = " + slr.beta0() + " + "+ slr.beta1() + "(" + x + ")");
				System.out.println("Y = " + slr.predictY(x));
			}
		} );
	}
}
