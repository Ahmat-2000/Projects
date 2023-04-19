package blackjack.view;

import javax.swing.JPanel;

import blackjack.model.classes.Hand;

import java.awt.*;

import cartes.model.classes.Card;
import cartes.util.observer.ModelListener;
import cartes.view.CardView;

public class HandView extends JPanel implements ModelListener {
    private Hand hand;

    public HandView(Hand hand) {
        this.hand = hand;
        this.hand.addModelListener(this);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        setPreferredSize(new Dimension(800, 200));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int numberOfCards = this.hand.getCards().size();
        int c = (numberOfCards * 96 + 5 * numberOfCards) / 2;
        c = this.getWidth() / 2 - c;
        int i = 0;
        for (Card card : this.hand.getCards()) {
            g.drawImage(new CardView(card).getCardImage(), c + i * 96 + 5 * i, 20, null);
            i++;
        }
    }

    @Override
    public void somethinHasChanged(Object arg0) {
        this.revalidate();
        this.repaint();
    }
}
