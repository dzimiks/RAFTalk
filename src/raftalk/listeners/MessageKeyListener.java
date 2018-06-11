package raftalk.listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import raftalk.view.ChatView;

/**
 * Listener koji osluskuje da li je korisnik pritisnuo Enter pri slanju poruke.
 * 
 * @author dzimiks
 */
public class MessageKeyListener extends KeyAdapter implements KeyListener {

	private ChatView view;
	
	public MessageKeyListener(ChatView view) {
		this.view = view;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) 
			view.getActionManager().getSendListener().actionPerformed(null);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}