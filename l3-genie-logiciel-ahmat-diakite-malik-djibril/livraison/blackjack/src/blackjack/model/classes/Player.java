package blackjack.model.classes;

import blackjack.model.enums.Status;
import blackjack.model.interfaces.Strategy;

public abstract class Player extends AbstractPlayer {
    protected float wallet;
    protected Dealer dealer;
    protected Strategy strategy;
    protected boolean endTurn;
    protected Status status;
    protected String name;

    public Player(Dealer dealer, float wallet, String name) {
        super();
        this.endTurn = false;
        this.wallet = wallet;
        this.dealer = dealer;
        this.name = name;
    }

    /**
     * Retourne le nom du joueur
     * 
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Met a jour le status du joueur a la fin de la partie
     * 
     * @param s
     */
    public void setWinningStatus(Status s) {
        status = s;
    }

    /**
     * Retourne le status du joueur a la fin du jeu
     * 
     * @return status
     */
    public Status getWinningStatus() {
        return status;
    }

    /**
     * Recupere le portefueil du joueur
     * 
     * @return wallet
     */
    public final float getWallet() {
        return this.wallet;
    }

    /**
     * Met a jour le portefueil du joueur
     * 
     * @param wallet
     */
    public final void setWallet(float wallet) {
        this.wallet = wallet;
        fireChange();
    }

    /**
     * Retourne le dealer du joueur
     * 
     * @return dealer
     */
    public final Dealer getDealer() {
        return this.dealer;
    }

    /**
     * Retourne la strategie du joueur
     * 
     * @return strategy
     */
    public Strategy getStrategy() {
        return this.strategy;
    }

    /**
     * Met a jour la strategie du joueur
     * 
     * @param strategy
     */
    public void setSrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Verifie si le joueur peut effectuer une actions
     * 
     * @return
     */
    protected final boolean canTakeDecision() {
        return strategy != null && !isEndPlayerTurn();
    }

    /**
     * Applique l'action choisie par le joueur
     */
    public final void applyDecision() {
        if (canTakeDecision()) {
            this.strategy.applyDecision(this);
        }
    }

    /**
     * Met fin au tour du joueur
     */
    public void endPlayerTurn() {
        this.endTurn = true;
    }

    /**
     * Verifie si le tour du joueur est termine
     * 
     * @return
     */
    public boolean isEndPlayerTurn() {
        return endTurn == true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see blackjack.model.classes.AbstractPlayer#play()
     */
    @Override
    public abstract void play();
}
