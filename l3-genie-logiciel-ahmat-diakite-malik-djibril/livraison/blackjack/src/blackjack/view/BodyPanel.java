package blackjack.view;

import javax.swing.BorderFactory;
import javax.swing.*;

import blackjack.model.classes.Game;
import blackjack.model.classes.Hand;
import blackjack.model.classes.*;
import cartes.util.observer.ModelListener;
import java.util.*;

import java.awt.*;

public class BodyPanel extends JPanel implements ModelListener {
    private Hand dealer;
    private ArrayList<HandView> liste;
    private Game game;

    public BodyPanel(Game game) {
        this.game = game;
        this.game.addModelListener(this);
        this.dealer = this.game.getDealer().getHand();
        liste = new ArrayList<>();
        setBackground(Color.LIGHT_GRAY);
        setLayout(new GridLayout(2, 0));
        HandView dealerView = new HandView(dealer);
        dealerView.setBorder(BorderFactory.createTitledBorder("Croupier"));
        add(dealerView);

        JPanel player = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        for (Player p : this.game.getPlayers()) {
            p.addModelListener(this);
            HandView playerHand = new HandView(p.getHand());
            playerHand.setBorder(BorderFactory.createTitledBorder(p.getName() + "  Solde actuel : " + p.getWallet()
                    + "$" + "  Mise : " + p.getHand().getBet() + "$"));
            player.add(playerHand);
            liste.add(playerHand);
        }
        JScrollPane playerScroll = new JScrollPane(player);
        playerScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        playerScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        add(playerScroll);
    }

    @Override
    public void somethinHasChanged(Object arg0) {
        int i = 0;
        for (Player p : game.getPlayers()) {
            liste.get(i).setBorder(BorderFactory.createTitledBorder(p.getName() + "  Solde actuel : " + p.getWallet()
                    + "$" + "  Mise : " + p.getHand().getBet() + "$"));
            i++;
        }
        this.revalidate();
        this.repaint();
    }
}