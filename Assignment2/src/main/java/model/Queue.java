package model;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class Queue implements Runnable{
	private BlockingQueue<Client> clients;
	private AtomicInteger waitingPeriod;
	public int id;
	private int finalWaitingPeriod;
	private int finalProcessingPeriod;
	private int emptyQueueTime;
	private int maxNoOfClients;
	
	public Queue(int id) {
		clients = new ArrayBlockingQueue<Client>(100);
		waitingPeriod = new AtomicInteger();
		this.id = id;
		maxNoOfClients = 0;
		emptyQueueTime = 0;
	}
	
	public boolean addClient(Client c) {
		if (clients.size() <= 100) {
			clients.add(c);
			waitingPeriod.addAndGet(c.getProcessingPeriod());
			finalProcessingPeriod += c.getProcessingPeriod();
			finalWaitingPeriod += waitingPeriod.intValue();
			maxNoOfClients++;
			return true;
		}
		return false;
	}

	public void run() {
		while(true) {
			while (!clients.isEmpty()) {
				Client c = clients.peek();
				try {
					Thread.sleep(c.getProcessingPeriod()*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				clients.remove();
				waitingPeriod.addAndGet(-c.getProcessingPeriod());
			}
		}
	}
	
	public String toString() {
		String state = "Queue " + id + " contains";
		if (clients.size() == 0)
			state += " no clients.";
		else {
			state += " clients: ";
			for(Client c : clients){
				state += c.getId() + " ";
			}
		}
		state += "\n";
		return state;
	}
	
	public String getLog() {
		String s = "Average waiting time is ";
		s += (maxNoOfClients == 0) ? "0\n" : ((float)finalWaitingPeriod/maxNoOfClients+"\n"); 
		s += "Average service time is ";
		s += (maxNoOfClients == 0) ? "0\n" : ((float)finalProcessingPeriod/maxNoOfClients+"\n"); 
		s += "Empty queue time is "+emptyQueueTime;
		return s;
	}

	public BlockingQueue<Client> getClients() {
		return clients;
	}

	public void setClients(BlockingQueue<Client> clients) {
		this.clients = clients;
	}

	public AtomicInteger getWaitingPeriod() {
		return waitingPeriod;
	}

	public void setWaitingPeriod(AtomicInteger waitingPeriod) {
		this.waitingPeriod = waitingPeriod;
	}

	public int getSize() {
		return clients.size();
	}
	
	public int getEmptyQueueTime () {
        return emptyQueueTime;
    }

    public void setEmptyQueueTime (int emptyQueueTime) {
        this.emptyQueueTime = emptyQueueTime;
    }
	
}
