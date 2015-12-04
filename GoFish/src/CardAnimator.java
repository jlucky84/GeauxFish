import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class CardAnimator extends JPanel implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JOptionPane x = new JOptionPane("You clicked " + e.getComponent().getName());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		Component item = e.getComponent();
		item.setBackground(Color.green);
		System.out.println("Mouse over " + item.getName());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel xyz =(JLabel) e.getSource();//
		xyz.setBackground(Color.gray);
		//xyz.setVisible(true);
	
		System.out.println("Mouse exited " + xyz.getName());
		//this.setBackground(Color.gray);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}
	
	
	
	public CardAnimator(){
		addMouseListener(this);
		
	}

}
