package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.SimulationManager;
import view.SimulatorFrame;

public class Controller {
	private SimulationManager model;
	private SimulatorFrame view;
	
	public Controller(SimulationManager model, SimulatorFrame view) {
		this.model = model;
		this.view = view;
		view.listener(new Simulation());
	}
	
	public class Simulation implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			view.getList().removeAllElements();
			view.getLog().setText("");
			String s = "Invalid input. ";
			
			if (	view.getMaxArrTime().getText().equals("") ||
					view.getMinArrTime().getText().equals("") ||
					view.getMaxProcTime().getText().equals("") ||
					view.getMinProcTime().getText().equals("") ||
					view.getNoOfQueues().getText().equals("") ||
					view.getNoOfClients().getText().equals("") ||
					view.getTimeLimit().getText().equals("")) {
				s += "Input fields cannot remain empty.";
				JOptionPane.showMessageDialog(view, s, "Error", JOptionPane.ERROR_MESSAGE);
			}else if (!view.getMaxArrTime().getText().matches("[0-9]*")  ||
					!view.getMinArrTime().getText().matches("[0-9]*")  ||
					!view.getMaxProcTime().getText().matches("[0-9]*")  ||
					!view.getMinProcTime().getText().matches("[0-9]*")  ||
					!view.getNoOfQueues().getText().matches("[0-9]*")  ||
					!view.getNoOfClients().getText().matches("[0-9]*")  ||
					!view.getTimeLimit().getText().matches("[0-9]*")) {
				s += "Input fields must only contain positive numbers.";
				JOptionPane.showMessageDialog(view, s, "Error", JOptionPane.ERROR_MESSAGE);		
			}else{
			
				int maxArrTime = Integer.parseInt(view.getMaxArrTime().getText());
				int minArrTime = Integer.parseInt(view.getMinArrTime().getText());
				int maxProcTime = Integer.parseInt(view.getMaxProcTime().getText());
				int minProcTime = Integer.parseInt(view.getMinProcTime().getText());
				int noOfQueues = Integer.parseInt(view.getNoOfQueues().getText());
				int noOfClients = Integer.parseInt(view.getNoOfClients().getText());
				int timeLimit = Integer.parseInt(view.getTimeLimit().getText());
				
				if (	
					(maxArrTime<minArrTime) ||
					(maxProcTime<minProcTime) ||
					(timeLimit<maxProcTime)) {
					s += "Intervals should be mathemathically correct and maximum processing time should be smaller than the simulation interval";
					JOptionPane.showMessageDialog(view, s, "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					model = new SimulationManager(timeLimit, maxProcTime, minProcTime, maxArrTime, minArrTime, noOfQueues, noOfClients, view);
					Thread t = new Thread(model);
					t.start();
				}
			}
		}
		
	}
	
	 public static void main(String[] args) {
	        SimulationManager model = new SimulationManager();
	        SimulatorFrame view = new SimulatorFrame();
	        Controller controller = new Controller(model, view);
	        view.setVisible(true);
	    }
}
