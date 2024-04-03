package main;

import main.init.EntityManager;
import main.screens.TitleScreen;
import main.ui.Screen;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

//MADE BY YUANNO GAME CORPARATION
public class Main {
    public static Fonts fonts;
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
        try {
            fonts = new Fonts();
        }
        catch (Exception e)
        {
            System.out.println("TRIED TO LOAD FONTS, DID NOT WORK");
        }
        gamePanel.setupGame();
        gamePanel.startGameThread();

    }
}
