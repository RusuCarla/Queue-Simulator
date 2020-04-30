package model;
import java.util.*;

import javax.swing.JOptionPane;

import view.SimulatorFrame;

public class Scheduler {
	private ArrayList<Queue> queues;
	private int maxNoQueues;
	private int maxClientsPerQueue;
	private ArrayList<Thread> threads;
	private SimulatorFrame view;
	
	public Scheduler(int maxNoQueues, int maxClientsPerQueue, SimulatorFrame view) {
		this.maxClientsPerQueue = maxClientsPerQueue;
		this.maxNoQueues = maxNoQueues;
		this.view = view;
		queues = new ArrayList<Queue>();
		threads = new ArrayList<Thread>();
		for (int i =0; i<maxNoQueues; i++) {
			Queue q = new Queue(i);
			queues.add(q);
			this.view.getList().addElement("Queue"+ i+": empty");
			Thread t = new Thread(q);
			threads.add(t);
		}
		for(int i =0; i < maxNoQueues; i++) {
			threads.get(i).start();
		}
	}
	
	public void dispatchClient(Client c) {
		Queue q= shortestQueue();
		q.addClient(c);
		view.getList().setElementAt(q.toString(), q.id);
		view.getLog().append("Queue "+q.id+" adds client "+c.getId()+"\n");
	}
	
	private Queue shortestQueue() {
		Queue smallestQueue = new Queue(maxClientsPerQueue);
		int min = Integer.MAX_VALUE;
		for(Queue q : queues) {
			if(q.getWaitingPeriod().intValue() < min) {
				min = q.getWaitingPeriod().intValue();
				smallestQueue = q;
			}
		}
		return smallestQueue;
	}

	public void queuesStates() {
		for(Queue q: queues)
        {
            view.getList().setElementAt(q.toString(), q.id);
            view.getLog().append(q.toString());
        }
	}
	
	public int extraProcessing() {
		int extraTime = 0;
		for(Queue q: queues)
        {
           if (extraTime<q.getWaitingPeriod().intValue())
        	   extraTime = q.getWaitingPeriod().intValue();
        }
		return extraTime;
	}
	
	public int getWaitingTimeQueues() {
		int processingTime = 0;
		for (Queue q : queues) {
            processingTime += q.getWaitingPeriod().intValue();
        }
		return processingTime;
	}
	
	public void setEmptyQueues()
    {
        for (Queue q : queues)
            if(q.getSize()==0)
                q.setEmptyQueueTime(q.getEmptyQueueTime()+1);
    }
	
	public void printLog() {
		for(Queue q: queues)
        {
			String log = q.getLog();
            String queueId = "Queue " + q.id + " statistic";
            JOptionPane.showMessageDialog(view, log, queueId, JOptionPane.INFORMATION_MESSAGE);
        }
	}
	
	public ArrayList<Queue> getQueues() {
		return queues;
	}
}
