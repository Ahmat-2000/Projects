package blackjack.model.interfaces;

import blackjack.model.classes.Player;

public interface Strategy {

    /**
     * Applique l'action choisie par le joueur: rester, tirer...
     * 
     * @param player
     */
    public void applyDecision(Player player);
}
