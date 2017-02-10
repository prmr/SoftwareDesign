package module05;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BridgeDealer extends JFrame
{
	private final Deck aDeck;
	
	public BridgeDealer()
	{
		super("Bridge Hand Dealer \u00A9 Martin Robillard 2017");
		
		aDeck = new Deck();
		
		final HandPanel aNorth = new HandPanel();
		final HandPanel aSouth = new HandPanel();
		
		JPanel lPanel = new JPanel();
		lPanel.setLayout(new GridLayout(2, 1));
		lPanel.add(aNorth);
		lPanel.add(aSouth);
		setLayout(new BorderLayout());
		add(lPanel, BorderLayout.CENTER);
		
		final JButton deal = new JButton("Deal");
		add(deal, BorderLayout.SOUTH);
		deal.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				aDeck.shuffle();	
				aNorth.showHand(dealHand());
				aSouth.showHand(dealHand());
			}
		});
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args)
	{
		new BridgeDealer();
	}
	
	private Card[] dealHand()
	{
		Card[] lReturn = new Card[13];
		for( int i = 0; i < 13; i++)
		{
			lReturn[i] = aDeck.draw();
		}
		return lReturn;
	}
}

class HandPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private static final int SHIFT = 30;
	
	private JLabel aLabel = new JLabel();
	
	public HandPanel()
	{
//		setBackground(new Color(0,102,0));
//		add(aLabel);
//		CompositeIcon icon = new CompositeIcon();
//		for( int i = 0; i < 13; i++ )
//		{
//			icon.addIcon(new ShiftedIcon( CardImages.getBack(), i * SHIFT, 0));
//		}
//		aLabel.setIcon(icon);
	}
	
	public void showHand(Card[] pHand)
	{
//		CompositeIcon icon = new CompositeIcon();
//		for( int i = 0; i < 13; i++ )
//		{
//			icon.addIcon(new ShiftedIcon( CardImages.getCard(pHand[i]), i * SHIFT, 0));
//		}
//		aLabel.setIcon(icon);
	}
}