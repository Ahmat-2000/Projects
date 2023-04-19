package blackjack.view;

import java.awt.*;
import javax.swing.*;

import javax.swing.JFrame;

import blackjack.controller.HomeController;
import blackjack.model.classes.Game;

public class GUI extends JFrame {
    private JPanel panelFram;

    public GUI() {
        super();
        try {
            panelFram = (JPanel) this.getContentPane();
            this.setTitle("BlackJack");
            this.setSize(1000, 900);
            // this.setResizable(false);
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null); // pour le positionnement de la fếnetre par rapport à l'écran.
            /*----------------------------------------------------------------------------------------------- */
            ImageIcon icon = new ImageIcon("./src/content/images/Icon.png");
            panelFram.setLayout(null);
            setMinimumSize(new Dimension(700, 730));
            setIconImage(icon.getImage()); // on change l'icon de la fênetre avec ce setter
        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
    }

    public JPanel getPanelFram() {
        return panelFram;
    }

    public static void main(String[] args) {
        GUI window = new GUI();
        HomeController controller = new HomeController(window);
        controller.home();
        window.setVisible(true);
    }
}
