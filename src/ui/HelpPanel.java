package ui;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Image;
import javax.swing.ImageIcon;

public class HelpPanel extends JPanel {

    private Image background;
    private JButton backButton;

    public HelpPanel(GameWindow window) {

        background = new ImageIcon(
            getClass().getResource("/resources/background.png")
        ).getImage();

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 0, 10, 0);

        backButton = new JButton("BACK");

	gbc.gridy = 5;
	gbc.insets = new Insets(400, 0, 10, 0);
	add(backButton, gbc);

        backButton.addActionListener(e -> {
            window.showStartScreen();
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

        g.setColor(java.awt.Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 28));

        g.drawString("HOW TO PLAY", 200, 100);

        g.setFont(new Font("Arial", Font.PLAIN, 18));

        g.drawString("Move the basket with:", 150, 160);
        g.drawString("Arrow Keys or A / D", 150, 190);
        g.drawString("You can also use your mouse / touchpad.", 150, 220);

        g.drawString("White Egg   +5", 150, 280);
        g.drawString("Orange Egg +10", 150, 310);
        g.drawString("Golden Egg +25", 150, 340);

        g.drawString("Avoid the chicken shit!", 150, 390);
        g.drawString("Missing 3 eggs = Game Over", 150, 420);
        g.drawString("Eggs become faster as your score increases.", 150, 450);
    }
}
