package model;
import java.util.*;

import javax.swing.text.View;

import view.SimulatorFrame;

public class SimulationManager implements Runnable{

	//data read from UI
	public int timeLimit = 10; //maximum processing time - read from UI
	public int maxProcessingTime = 3;
	public int minProcessingTime= 2;
	public int maxArrivalTime= 5;
	public int minArrivalTime= 0;
	public int numberOfQueues= 3;
	public int numberOfClients= 10;
	public int finalMaxProcessingTime;
	public int peakTime;
	
	//entity responsible with queue management and client distribution
	private Scheduler scheduler;
	//frame for displaying simulation
	private SimulatorFrame view;
	//pool of tasks (client shopping in the store)
	private ArrayList<Client> generatedClients = new ArrayList<Client>();
	
	public SimulationManager() {}
	
	public SimulationManager(int timeLimit, int maxProcessingTime, int minProcessingTime, int maxArrivalTime, int minArrivalTime, int numberOfQueues, int numberOfClients, SimulatorFrame view) {		
		
		this.view = view;
		this.view.setVisible(true);
		scheduler = new Scheduler(numberOfQueues, numberOfClients, view);
		this.timeLimit = timeLimit;
		this.maxProcessingTime = maxProcessingTime;
		this.minProcessingTime = minProcessingTime;
		this.maxArrivalTime = maxArrivalTime;
		this.minArrivalTime = minArrivalTime;
		this.numberOfQueues = numberOfQueues;
		this.numberOfClients = numberOfClients;		
		generateNRandomClients();
		finalMaxProcessingTime = 0;
		peakTime = 0;
	}
	
	private void generateNRandomClients() {
		Random random = new Random();
		for(int i=0; i<numberOfClients; i++) {
			int processingTime = random.nextInt((maxProcessingTime-minProcessingTime)+1)+minProcessingTime;
			int arrivalTime    = random.nextInt((maxArrivalTime-minArrivalTime)+1)+minArrivalTime;
			Client c = new Client(arrivalTime, processingTime, 0);
			generatedClients.add(c);
		}
		Collections.sort(generatedClients);
		int i = 1;
		view.getLog().append("Clients with arrival time and processing time generated: ");
		for(Client c : generatedClients) {
			c.setId(i);
			i++;
			view.getLog().append(c.getArrivalTime() + "-" + c.getProcessingPeriod() + " | ");
		}
		view.getLog().append("\n");
	}

	public void run() {
		int currentTime = 0;
		int stop = 0;
		while (currentTime < timeLimit && stop <2) {
			int processingTime = scheduler.getWaitingTimeQueues();
			peakTime = (processingTime>finalMaxProcessingTime)? currentTime : peakTime;
			finalMaxProcessingTime = (processingTime>finalMaxProcessingTime)? processingTime : finalMaxProcessingTime;
			scheduler.queuesStates();
			
			int i = 0;
			while(i<generatedClients.size()) {
				Client c = generatedClients.get(i);
				if (c.getArrivalTime() == currentTime) {
					scheduler.dispatchClient(c);
					generatedClients.remove(c);
				}
				else
					i++;
			}
			if(generatedClients.size()==0)
				stop++;
			currentTime++;
			if (currentTime == timeLimit) {
				int extraTime = scheduler.extraProcessing() + timeLimit - 1;
				if (timeLimit<extraTime)
					timeLimit = extraTime;
			}
			view.getLog().append(currentTime+"\n");
			view.getTimerLabel().setText(currentTime+"");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (stop<2)
				scheduler.setEmptyQueues();
		}
		view.getPeakTimeLabel().setText("Peak time is "+peakTime);
		scheduler.printLog();
	}
}
