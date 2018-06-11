package raftalk.view;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;

import raftalk.client.Client;
import raftalk.client.ClientThread;
import raftalk.listeners.ActionManager;
import raftalk.listeners.ChatViewWindowListener;
import raftalk.listeners.MessageKeyListener;

/**
 * GUI za prikaz prostora za chat.
 * 
 * @author milossmi
 */
@SuppressWarnings("serial")
public class ChatView extends JFrame {

	private static ChatView instance = null;
	
	private JPanel panel;
	private JTextField message;
	private JTextArea chatArea;
	
	@SuppressWarnings("unused")
	private DefaultCaret caret;
	private ActionManager actionManager;
	private String clientName;
	private Client client;
	
	private ChatView() throws Exception {
		client = new Client(Login.getClient());
		addWindowListener(new ChatViewWindowListener(this));
		actionManager = new ActionManager();
		client.openConnection();
		clientName = client.getName();
		
		try {
            this.setIconImage(ImageIO.read(new File("images/raf-logo.png")));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
		
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		setSize(600, 600);
		setTitle("RAFTalk Chat - @" + clientName);
		setResizable(false);
		setLocationRelativeTo(null);
		initialize();
		new Thread(new ClientThread()).start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initialize() {
		panel = new JPanel();
		panel.setLayout(null);
		
		chatArea = new JTextArea(5, 20);
		chatArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		chatArea.setBackground(new Color(238, 238, 238));
		chatArea.setEditable(false);
		chatArea.setLineWrap(true);
		chatArea.setWrapStyleWord(true);
		
		JScrollPane scroll = new JScrollPane(chatArea);
		scroll.setBounds(10, 10, 580, 515);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		message = new JTextField(50);
		message.setBounds(10, 530, 500, 30);
		message.setColumns(10);
		message.addKeyListener(new MessageKeyListener(this));
		
		JButton send = new JButton("Send");
		send.setBounds(520, 530, 70, 30);
		send.addActionListener(actionManager.getSendListener());
		
		panel.add(scroll);
		panel.add(message);		
		panel.add(send);

		add(panel);
	}

	public static ChatView getInstance() {
		if (instance == null) {
			try {
				instance = new ChatView();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return instance;
	}

	public JTextField getMessage() {
		return message;
	}

	public JTextArea getChatArea() {
		return chatArea;
	}

	public Client getClient() {
		return client;
	}

	public ActionManager getActionManager() {
		return actionManager;
	}
}