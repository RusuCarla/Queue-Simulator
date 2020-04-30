package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SimulatorFrame extends JFrame{
	private JPanel panel;
	private int WIDTH, HEIGHT;
	
	private JPanel inputPanel;
	private JPanel resultPanel;
	private JPanel arrPanel;
	private JPanel procPanel;
	private JPanel noOfPanel;
	private JPanel timePanel;
	
	private JLabel maxArrTimeLabel;
	private JLabel minArrTimeLabel;
	private JLabel maxProcTimeLabel;
	private JLabel minProcTimeLabel;
	private JLabel noOfQueuesLabel;
	private JLabel noOfClientsLabel;
	private JLabel timeLimitLabel;
	
	private JTextField maxArrTimeTf;
	private JTextField minArrTimeTf;
	private JTextField maxProcTimeTf;
	private JTextField minProcTimeTf;
	private JTextField noOfQueuesTf;
	private JTextField noOfClientsTf;
	private JTextField timeLimitTf;
	
	private JButton startButton;
	private JLabel timerLabel;
	
	private JLabel queuesLabel;
	private JList queues;
	private DefaultListModel list;
	private JLabel logLabel;
	private JTextArea log;
	JScrollPane queuesPanel; 
	JScrollPane logPanel;
	private JLabel peakTimeLabel;
	
	public SimulatorFrame() {
		panel = new JPanel();
		
		Font font1 = new Font("SansSerif", Font.PLAIN, 20);
		Font font2 = new Font("SansSerif", Font.PLAIN, 16);
		
		maxArrTimeLabel = new JLabel("<html><p>Maximum arrival time:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></html>");
		minArrTimeLabel = new JLabel("<html><p>Minimum arrival time:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></html>");
		maxProcTimeLabel = new JLabel("<html><p>Maximum processing time:</p></html>");
		minProcTimeLabel = new JLabel("<html><p>Minimum processing time:</p></html>");
		noOfQueuesLabel = new JLabel("<html><p>Number of queues:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></html>");
		noOfClientsLabel = new JLabel("<html><p>Number of clients:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></html>");
		timeLimitLabel = new JLabel("<html><p>Simulation interval:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></html>");
		
		maxArrTimeLabel.setFont(font1);
		minArrTimeLabel.setFont(font1);
		maxProcTimeLabel.setFont(font1);
		minProcTimeLabel.setFont(font1);
		noOfQueuesLabel.setFont(font1);
		noOfClientsLabel.setFont(font1);
		timeLimitLabel.setFont(font1);
		
		maxArrTimeTf = new JTextField();
		minArrTimeTf = new JTextField();
		maxProcTimeTf = new JTextField();
		minProcTimeTf = new JTextField();
		noOfQueuesTf = new JTextField();
		noOfClientsTf = new JTextField();
		timeLimitTf = new JTextField();
		
		maxArrTimeTf.setFont(font1);
		minArrTimeTf.setFont(font1);
		maxProcTimeTf.setFont(font1);
		minProcTimeTf.setFont(font1);
		noOfQueuesTf.setFont(font1);
		noOfClientsTf.setFont(font1);
		timeLimitTf.setFont(font1);
		
		maxArrTimeTf.setPreferredSize(new Dimension(50, 30));
		minArrTimeTf.setPreferredSize(new Dimension(50, 30));
		maxProcTimeTf.setPreferredSize(new Dimension(50, 30));
		minProcTimeTf.setPreferredSize(new Dimension(50, 30));
		noOfQueuesTf.setPreferredSize(new Dimension(50, 30));
		noOfClientsTf.setPreferredSize(new Dimension(50, 30));
		timeLimitTf.setPreferredSize(new Dimension(50, 30));
		
		startButton = new JButton("Start");
		timerLabel = new JLabel("0");
		
		startButton.setFont(font1);
		timerLabel.setFont(font1);
		
		queuesLabel = new JLabel("Log of events:");
		queuesLabel.setFont(font1);
		list = new DefaultListModel();
		queues = new JList(list);
		logLabel= new JLabel("Queue evolution:") ;
		logLabel.setFont(font1);
		log = new JTextArea();
		
		inputPanel = new JPanel();
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
				
		arrPanel = new JPanel();
		arrPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10));
		arrPanel.add(maxArrTimeLabel);
		arrPanel.add(maxArrTimeTf);
		arrPanel.add(minArrTimeLabel);
		arrPanel.add(minArrTimeTf);
		
		procPanel = new JPanel();
		procPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10));
		procPanel.add(maxProcTimeLabel);
		procPanel.add(maxProcTimeTf);
		procPanel.add(minProcTimeLabel);
		procPanel.add(minProcTimeTf);
		
		noOfPanel = new JPanel();
		noOfPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10));
		noOfPanel.add(noOfClientsLabel);
		noOfPanel.add(noOfClientsTf);
		noOfPanel.add(noOfQueuesLabel);
		noOfPanel.add(noOfQueuesTf);
		
		timePanel = new JPanel();
		timePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10));
		timePanel.add(timeLimitLabel);
		timePanel.add(timeLimitTf);
		timePanel.add(startButton);
		timePanel.add(timerLabel);
		
		inputPanel.add(arrPanel);
		inputPanel.add(procPanel);
		inputPanel.add(noOfPanel);
		inputPanel.add(timePanel);
		
		resultPanel = new JPanel();
		resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
		
		queues.setBackground(Color.WHITE);
		queues.setFont(font2);
		queuesPanel = new JScrollPane(queues);
		queuesPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        queuesPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        queuesPanel.setPreferredSize(new Dimension(750, 250));
        
        log.setBackground(Color.WHITE);
        log.setFont(font2);
        logPanel = new JScrollPane(log);
        logPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        logPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        logPanel.setPreferredSize(new Dimension(750, 250));
        
        peakTimeLabel = new JLabel("Peak time is ?");
        peakTimeLabel.setFont(font1);
        
        resultPanel.add(queuesLabel);
        resultPanel.add(logPanel);
        resultPanel.add(logLabel);
        resultPanel.add(queuesPanel);
        resultPanel.add(peakTimeLabel);
        
		WIDTH = 900;
		HEIGHT = 850;
		this.setContentPane(panel);
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("Queue simulator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(inputPanel);
		this.add(resultPanel);
	}
	
	public void listener(ActionListener a) {
        startButton.addActionListener(a);
    }
    
     public DefaultListModel getList() {
        return list;
    }
    
    public JTextArea getLog() {
        return log;
    }

	public JTextField getMaxArrTime() {
		return maxArrTimeTf;
	}

	public JTextField getMinArrTime() {
		return minArrTimeTf;
	}

	public JTextField getMaxProcTime() {
		return maxProcTimeTf;
	}
	
	public JTextField getMinProcTime() {
		return minProcTimeTf;
	}

	public JTextField getNoOfQueues() {
		return noOfQueuesTf;
	}

	public JTextField getNoOfClients() {
		return noOfClientsTf;
	}

	public JTextField getTimeLimit() {
		return timeLimitTf;
	}

	public JLabel getTimerLabel() {
		return timerLabel;
	}

	public JLabel getPeakTimeLabel() {
		return peakTimeLabel;
	}

	public void setPeakTimeLabel(JLabel peakTimeLabel) {
		this.peakTimeLabel = peakTimeLabel;
	}
}
