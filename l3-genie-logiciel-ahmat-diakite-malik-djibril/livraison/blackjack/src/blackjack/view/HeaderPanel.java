package blackjack.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import blackjack.controller.*;
import blackjack.model.classes.Game;
import cartes.model.classes.Deck;
import cartes.view.*;

public class HeaderPanel extends JPanel {
    private Deck deck;

    public HeaderPanel(Game game,HomeController homeControl) {
        this.deck = game.getDeck();
        setLayout(new BorderLayout());
        add(new DeckView(this.deck), BorderLayout.WEST);
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 30));
        JLabel soldLabel = new JLabel("Blackjack");
        p.add(soldLabel);
        JButton homeButon = new JButton("Accueil");
        p.add(homeButon);
        p.setPreferredSize(new Dimension(200, 0));
        p.setBackground(Color.LIGHT_GRAY);
        setBackground(Color.LIGHT_GRAY);
        this.setPreferredSize(new Dimension(0, 220));
        add(p, BorderLayout.EAST);
        
        homeButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	homeControl.getPanelFram().removeAll();
            	homeControl.getPanelFram().updateUI();
                homeControl.homeWindow();
                homeControl.home();
            }
        });
    }
}
