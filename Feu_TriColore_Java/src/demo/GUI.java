package src.demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import javax.swing.JFrame;
import src.model.TriColore;
import src.vue.VueTriColor;
public class GUI extends JFrame {
    public JPanel panelFram;
    VueTriColor vue;
    TriColore feuxTrieColor;
    public GUI(VueTriColor vue , TriColore feuxTrieColor) {
        this.vue = vue;
        this.feuxTrieColor = feuxTrieColor;
        this.setTitle("TriColore");
        this.setSize(200, 450);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // pour le positionnement de la fếnetre par rapport à l'écran.
        /*----------------------------------------------------------------------------------------------- */
        panelFram = (JPanel) this.getContentPane();
        panelFram.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelFram.setLayout(null);
       
        JButton next = new JButton("NEXT");
        panelFram.setBackground(Color.black);
        next.setFont(new Font(Font.SERIF, Font.BOLD,24));
        next.setBounds(40, 360, 120, 50);
        panelFram.add(next);
        panelFram.add(this.vue);

        next.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                feuxTrieColor.changeColor();
            }
            
        } );
    }

    public JPanel getPanelFram() {
        return panelFram;
    }

    public static void main(String[] args) {
        TriColore feuxTrieColor = new TriColore();
        VueTriColor vue = new VueTriColor(feuxTrieColor);
        JFrame window = new GUI( vue, feuxTrieColor);
        window.setVisible(true);
    }
}
