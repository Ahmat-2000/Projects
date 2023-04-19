package src.vue;

import javax.swing.JComponent;
import java.awt.*;

import src.model.TriColore;
import src.util.ColorEnum;
import src.util.observer.EcouteurModele;
/**
 * C'est un écouteur du modele
 */
public class VueTriColor extends JComponent implements EcouteurModele{
    private TriColore modele;
    private Color[] COULEUR_FEUX ;
    public VueTriColor(TriColore modele)
    {
        this.COULEUR_FEUX =new Color[]{
            ColorEnum.ETEINT.getColor(),
            ColorEnum.ETEINT.getColor(),
            ColorEnum.ETEINT.getColor()
        };
        this.modele = modele;
        this.modele.ajoutEcouteur(this); // on enregistre notre vue auprès du modele
        Dimension d = new Dimension(200,350);
        setLocation(50,0);
        //setBorder(BorderFactory.createTitledBorder("feuxs"));
		setSize(d);

        this.setColorFeux(); // pour initialiser le tableau de couleurs.
    }
    /**
     * Une fonction privée qui nous permet de modifier le taleau des 
     * couleurs en activant la couleur c à la position pos.
     * @param pos
     * @param c
     */
    private void setCOULEUR_FEUX(int pos, Color c)
    {
        this.COULEUR_FEUX[0] = ColorEnum.ETEINT.getColor(); 
        this.COULEUR_FEUX[1] = ColorEnum.ETEINT.getColor(); 
        this.COULEUR_FEUX[2] = ColorEnum.ETEINT.getColor();

        this.COULEUR_FEUX[pos] = c;
    }
    /**
     * Cette méthode privée permet de changer la couleur du feu quant le model
     * change d'état.
     * Elle utilise la méthode private void setCOULEUR_FEUX(int pos, Color c)
     */
    private void setColorFeux()
    {
        switch (this.modele.getColorState()) {
            case GREEN :
                this.setCOULEUR_FEUX(2, ColorEnum.GREEN.getColor());
                break;
            case ORANGE :
                this.setCOULEUR_FEUX(1, ColorEnum.ORANGE.getColor());
                break;
            /*case RED :
                this.setCOULEUR_FEUX(0, ColorEnum.RED.getColor());
                break;*/
            default:
                this.setCOULEUR_FEUX(0, ColorEnum.RED.getColor());
                break;
        }
    }
    @Override
    /**
     * Cette méthode est appelée quand le modele change d'état
     */
    public void updateModelSomeThingHasChange(Object source) {
        this.setColorFeux();
        this.revalidate(); // pour revalider le graphique 
        this.repaint(); // pour redessiner le composant  en appellant la méthode paintComponent(Graphics graphics)
    }
    @Override
    protected void paintComponent(Graphics graphics)
    {
        Dimension d = this.getSize();
        int y = 5;
        int cercleHight = 100;
        int cercleWidth = 100;
        Graphics2D g = (Graphics2D)graphics;
        // on dessine nos trois cercles
        for (int i = 0; i < 3 ; i++) 
        {
            g.setColor(COULEUR_FEUX[i]); // on initialise la couleur du dessin
            g.fillOval(d.width/2 - cercleWidth, y, cercleWidth, cercleHight);
            y = y + cercleHight +20;
        }
    }
}
