package main;

import main.init.EntityManager;

import javax.swing.*;
import java.io.FileNotFoundException;

//MADE BY YUANNO GAME CORPARATION
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Another Rpg");

        GamePanel gamePanel = new GamePanel();
        EntityManager.setGamePanel(gamePanel);
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
