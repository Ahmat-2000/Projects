package cartes.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import cartes.model.classes.Card;
import cartes.model.classes.Deck;
import cartes.util.observer.ModelListener;

public class DeckView extends JPanel implements ModelListener {
    private Deck deck;

    public DeckView(Deck deck) {
        this.deck = deck;
        this.deck.addModelListener(this);
        setPreferredSize((new Dimension(200, 0)));
        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int i = 0;
        for (Card card : this.deck.getCards()) {
            g.drawImage(new CardView(card).getCardImage(), 10 + i, 10 + i, null);
            i++;
        }
    }

    @Override
    public void somethinHasChanged(Object source) {
        this.revalidate();
        this.repaint();
    }
}
