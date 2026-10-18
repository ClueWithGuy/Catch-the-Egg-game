package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class GameWindow extends JFrame {

    private CardLayout cardLayout;
    private JPanel container;
    private GamePanel gamePanel;

    public GameWindow() {

        setTitle("Catch the Egg");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
        setSize(600, 600);
        setResizable(true);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        StartPanel startPanel = new StartPanel(this);
        gamePanel = new GamePanel();
	HelpPanel helpPanel = new HelpPanel(this);

        container.add(startPanel, "START");
        container.add(gamePanel, "GAME");
	container.add(helpPanel, "HELP");

        add(container);

        cardLayout.show(container, "START");
    }

    public void startGame() {
	gamePanel.startGame();
        cardLayout.show(container, "GAME");
    }

    public void showHelpScreen() {
	cardLayout.show(container, "HELP");
    }

    public void showStartScreen() {
	cardLayout.show(container, "START");
    }


}
