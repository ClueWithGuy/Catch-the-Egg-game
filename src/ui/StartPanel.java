package ui;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class StartPanel extends JPanel {

    private Image background;
    private JButton startButton;
    private JButton helpButton;
    private JButton exitButton;

    public StartPanel(GameWindow window) {

	background = new ImageIcon(
				   getClass().getResource("/resources/loading_image.png")
				   ).getImage();

        setLayout(new GridBagLayout());

        startButton = new JButton("START");
        helpButton = new JButton("HELP");
        exitButton = new JButton("EXIT");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 0, 10, 0);

        gbc.gridy = 0;
        add(startButton, gbc);

        gbc.gridy = 1;
        add(helpButton, gbc);

        gbc.gridy = 2;
        add(exitButton, gbc);

        startButton.addActionListener(e -> {
            window.startGame();
        });

        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        helpButton.addActionListener(e -> {
            window.showHelpScreen();
        });

	
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.drawImage(
            background,
            0,
            0,
            getWidth(),
            getHeight(),
            this
        );
    }
}
