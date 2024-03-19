package org.example;

import View.MainScreen;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainScreen mainScreen = new MainScreen();
                mainScreen.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}



