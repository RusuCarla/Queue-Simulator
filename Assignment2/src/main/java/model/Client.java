package model;

/**
 * This is the client class. It keeps track of the arrival time and processing time of each client.
 * @author Carla
 *
 */
public class Client implements Comparable<Client>{
		private int arrivalTime;
		private int processingPeriod;
		private int id;
		/**
		 * Constructor of client
		 * @param arrivalTime
		 * @param processingPeriod
		 */
		public Client(int arrivalTime, int processingPeriod, int id) {
			this.arrivalTime = arrivalTime;
			this.processingPeriod = processingPeriod;
			this.id = id;
		}
		public int getArrivalTime() {
			return arrivalTime;
		}
		public void setArrivalTime(int arrivalTime) {
			this.arrivalTime = arrivalTime;
		}
		public int getProcessingPeriod() {
			return processingPeriod;
		}
		public void setProcessingPeriod(int processingPeriod) {
			this.processingPeriod = processingPeriod;
		}
		public int getId() {
	        return id;
	    }
		public void setId(int id) {
	        this.id = id;
	    }
		public int compareTo(Client c) {
			return this.arrivalTime-c.arrivalTime;
		}
}
