package cartes.view;

import javax.imageio.ImageIO;

import cartes.model.classes.Card;
import cartes.util.observer.ModelListener;

import java.awt.*;
import java.io.File;

public class CardView implements ModelListener {
    Card card;
    String pathname;
    Image cardImage;

    public CardView(Card card) {
        this.card = card;
        this.card.addModelListener(this);
        this.buildImage();
    }

    private void buildImage() {
        this.pathname = "./src/content/images/back.png";
        if (this.card.getIsFaceUp()) {
            this.pathname = "./src/content/images/" + this.card.getSuit().name() + "-" + this.card.getValue().name()
                    + ".png";
        }
        try {
            this.cardImage = ImageIO.read(new File(this.pathname));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Image getCardImage() {
        return this.cardImage;
    }

    @Override
    public void somethinHasChanged(Object source) {
        this.buildImage();
    }
}
