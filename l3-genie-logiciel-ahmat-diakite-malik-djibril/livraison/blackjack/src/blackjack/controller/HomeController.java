package blackjack.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import blackjack.model.classes.Game;
import blackjack.view.BodyPanel;
import blackjack.view.FooterPanel;
import blackjack.view.GUI;
import blackjack.view.HeaderPanel;

public class HomeController {
    private JPanel panelFram;
    private Game game;
    private JButton start;
    private JLabel label;
    private JTextField playerNumber;

    /**
     * @param homPanel
     */
    public HomeController(GUI window) {
        panelFram = window.getPanelFram();
        homeWindow();
    }
    public JPanel getPanelFram()
    {
    	return this.panelFram;
    }
    public void homeWindow()
    {
    	panelFram.setLayout(null);
    	label = new JLabel("Entrez le nombre de joueur");
        label.setFont(new Font("Serif", Font.BOLD, 14));
        playerNumber = new JTextField(1);
        start = new JButton("Start");
        label.setBounds(300, 300, 300, 40);
        playerNumber.setBounds(300, 370, 300, 40);
        start.setBounds(350, 440, 100, 20);
        panelFram.add(label);
        panelFram.add(playerNumber);
        panelFram.add(start);
        panelFram.validate();
    }
    private void textFieldListener() {
        playerNumber.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = playerNumber.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l <= 1) {
                    playerNumber.setEditable(true);
                    label.setText("");
                } else {
                    label.setForeground(Color.red);
                    label.setText("* Enter only numeric digits(0-9)");
                }
            }
        });
    }

    private void buttonListener() {
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int number = Integer.parseInt(playerNumber.getText());
                    if (number >= 1 && number <= 4) {
                        HomeController.this.panelFram.removeAll();
                        HomeController.this.panelFram.updateUI();
                        HomeController.this.startGame(number);
                    } else {
                        label.setForeground(Color.red);
                        label.setText("Entrez un nombre entre 1 et 4 !!");
                    }
                } catch (Exception a) {
                    label.setForeground(Color.red);
                    label.setText("Veuillez entrer un nombre !!");
                }
            }
        });

    }

    private void startGame(int numberOfPlayers) {
        this.game = new Game(numberOfPlayers);
        //game.run();
        panelFram.setLayout(new BorderLayout(20, 30));
        HeaderPanel header = new HeaderPanel(game,this);
        BodyPanel body = new BodyPanel(game);
        FooterPanel footer = new FooterPanel(game);
        /*----------------------------------------------------------------------------------------------- */
        /*----------------------------------------------------------------------------------------------- */
        panelFram.setLayout(new BorderLayout(20, 30));
        panelFram.add(header, BorderLayout.NORTH);
        panelFram.add(body, BorderLayout.CENTER);
        panelFram.add(footer, BorderLayout.SOUTH);
    }

    public void home() {
        textFieldListener();
        buttonListener();
    }
}