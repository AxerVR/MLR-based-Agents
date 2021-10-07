package com.mlr;

import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
  @author Axel Villanueva Rodríguez.
			@TecMM Campus Zapopan
 */
class MLRGui extends JFrame {	
	private MLRAgent myAgent;
	
	private JTextField x1Field, x2Field, xField, aField;
	
	MLRGui(MLRAgent a) {
		super(a.getLocalName());
		
		myAgent = a;
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(10, 2));
		p.add(new JLabel("MLR model:"));
		p.add(new JLabel("x1 for MLR:"));
		x1Field = new JTextField(15);
		p.add(x1Field);
		p.add(new JLabel("x2 for MLR:"));
		x2Field = new JTextField(15);
		p.add(x2Field);
		p.add(new JLabel("x for gradient:"));
		xField = new JTextField(15);
		p.add(xField);
		p.add(new JLabel("alfa for gradient:"));
		aField = new JTextField(15);
		p.add(aField);
		getContentPane().add(p, BorderLayout.CENTER);
		
		JButton addButton = new JButton("Normal Equation");
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String x1 = x1Field.getText().trim();
					String x2 = x2Field.getText().trim();
					myAgent.normalEquation(Double.parseDouble(x1), Double.parseDouble(x2));
					x1Field.setText("");
					x2Field.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(MLRGui.this, "Invalid values. " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		
		JButton addButton1 = new JButton("Gradient");
		addButton1.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String x = xField.getText().trim();
					String a = aField.getText().trim();
					myAgent.gradient(Double.parseDouble(x), Double.parseDouble(a));
					xField.setText("");
					aField.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(MLRGui.this, "Invalid values. " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		p = new JPanel();
		p.add(addButton);
		p.add(addButton1);
		getContentPane().add(p, BorderLayout.SOUTH);
		
		// Make the agent terminate when the user closes 
		// the GUI using the button on the upper right corner	
		addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAgent.doDelete();
			}
		} );
		
		setResizable(false);
	}
	
	public void showGui() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		super.setVisible(true);
	}	
}
