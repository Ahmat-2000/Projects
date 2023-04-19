package src.model;

import src.util.ColorEnum;
import src.util.observer.AbstractModeleEcoutable;

public class TriColore extends AbstractModeleEcoutable{
    private ColorEnum colorState ;

    public TriColore() {
        this.colorState = ColorEnum.RED; // l'état initial est rouge
    }
    /**
     * Pour modifier l'état du modele
     * @param colorState
     */
    private void setColorState(ColorEnum colorState) {
        this.colorState = colorState;
        this.fireChangement();
    }
    /**
     * Elle retourne l'état courant du modele
     * @return this.colorState
     */
    public ColorEnum getColorState() {
        return this.colorState;
    }
    /**
     * Elle change l'état du modèle en fonction de l'état courant
     */
    public void changeColor()
    {
        switch (this.colorState) {
            case GREEN :
                this.setColorState(ColorEnum.ORANGE);
                break;
            case ORANGE :
                this.setColorState(ColorEnum.RED);
                break;
            case RED :
                this.setColorState(ColorEnum.GREEN);
                this.fireChangement();
                break;
            default : 
                this.setColorState(ColorEnum.RED);
                break;
        }
    }
}
