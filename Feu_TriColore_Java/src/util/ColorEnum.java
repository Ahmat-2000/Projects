package src.util;
import java.awt.*;
/**
 * Une enumération de couleurs.
 */
public enum ColorEnum {
    GREEN   (new Color(  0, 180,   0)),
    RED     (new Color(255,   0,   0)),
    ORANGE  (new Color(255, 160,   0)),
    ETEINT  (Color.white);

    private final Color couleur;
    private ColorEnum(Color couleur)
    {
        this.couleur = couleur;
    }
    public Color getColor()
    {
        return this.couleur;
    }
}
