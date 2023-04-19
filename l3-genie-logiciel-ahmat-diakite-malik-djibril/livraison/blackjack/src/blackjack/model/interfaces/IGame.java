package blackjack.model.interfaces;

import java.util.ArrayList;

import blackjack.model.classes.Dealer;
import blackjack.model.classes.Player;
import cartes.model.classes.Deck;

public interface IGame {

    /**
     * Retourne le paquet utiliser dans le jeu
     * 
     * @return Deck
     */
    public Deck getDeck();

    /**
     * Execute un ensemble d'actions selon l'etat actuel
     */
    public void run();

    /**
     * Retourne le dealer du jeu
     * 
     * @return Dealer
     */
    public Dealer getDealer();

    /**
     * Retourne la liste des joueurs dans le jeu
     * 
     * @return une liste de Player
     */
    public ArrayList<Player> getPlayers();
}
