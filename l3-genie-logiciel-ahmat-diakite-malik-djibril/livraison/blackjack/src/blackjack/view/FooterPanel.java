package blackjack.view;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

import blackjack.model.classes.*;
import blackjack.model.strategies.*;

public class FooterPanel extends JPanel {
	protected ArrayList<JButton> butonList;
	protected JButton somme1, somme5, somme10, somme25, somme100, resterButon, tirerButon;
	protected boolean isDeal;
	protected Game game;

	public FooterPanel(Game game) {
		this.game = game;
		boolean isDeal = false;
		this.setBackground(Color.LIGHT_GRAY);
		setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		somme1 = new JButton("1");
		somme5 = new JButton("5");
		somme25 = new JButton("25");
		somme10 = new JButton("10");
		somme100 = new JButton("100");

		butonList = new ArrayList<JButton>();
		butonList.add(somme1);
		butonList.add(somme10);
		butonList.add(somme100);
		butonList.add(somme5);
		butonList.add(somme25);
		resterButon = new JButton("Rester");
		tirerButon = new JButton("Tirer");

		for (JButton b : butonList) {
			add(b);
		}
		add(resterButon);
		add(tirerButon);
		disableOrEnableButton();
		buttonControl();
		tirerButton();
		resterButon();
	}

	public void disableOrEnableButton() {
		if (isDeal == true) {
			for (JButton b : butonList) {
				b.setEnabled(false);
			}
			resterButon.setEnabled(true);
			tirerButon.setEnabled(true);
		} else {
			for (JButton b : butonList) {
				b.setEnabled(true);
			}
			resterButon.setEnabled(false);
			tirerButon.setEnabled(false);
		}
	}

	/*
	 * @void
	 * Controlle les boutons sommeX pour permettre au joueur de miser une somme
	 */
	public void buttonControl() {
		for (JButton b : butonList) {
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Player human = game.getHumPlayer();
					float wallet = human.getWallet();
					wallet -= Float.parseFloat(b.getText());
					human.setWallet(wallet);
					human.getHand().setBet(Integer.parseInt(b.getText()));
					game.initializeBotBet();
					game.run();
					game.run();

					isDeal = true;
					disableOrEnableButton();
				}
			});
		}
	}

	public void tirerButton() {
		tirerButon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player human = game.getHumPlayer();
				if (!human.isEndPlayerTurn()) {
					human.setSrategy(new Hit());
				} else {
					game.setState(game.getDealerTurnState());
				}
				game.run();
			}
		});
	}

	public void resterButon() {
		resterButon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Buuton Clicked!");
				Player human = game.getHumPlayer();
				if (!human.isEndPlayerTurn()) {
					human.setSrategy(new Stand());
				} else {
					game.setState(game.getDealerTurnState());
				}
				game.run();
			}
		});
	}

	/**
	 */
	public void ajouteArgent() {
		// ajout de la somme misée
	}
}