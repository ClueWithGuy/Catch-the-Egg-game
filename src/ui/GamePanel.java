package ui;

import game.Game;
import entities.Chicken;
import entities.Basket;
import entities.FallingObject;
import entities.Egg;
import entities.BrokenEgg;
import entities.ChickenShit;
import entities.SplashedShit;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;


public class GamePanel extends JPanel {

    private Image background;
    private Game game;
    private Timer gameTimer;
    private BufferedImage chickenImage;
    private Image whiteEggImage;
    private Image orangeEggImage;
    private Image goldenEggImage;
    private Image basketImage;
    private Image shitImage;

    private Image whiteEggBrokenImage;
    private Image orangeEggBrokenImage;
    private Image goldenEggBrokenImage;
    private Image shitSplashedImage;
    
    private final int chickenWidth = 50;
    private final int chickenHeight = 75;

    private javax.swing.JButton restartButton;
    private javax.swing.JButton exitButton;

    public GamePanel() {
	game = new Game();
	background = new ImageIcon(
				   getClass().getResource("/resources/background.png")
				   ).getImage();
	basketImage = new ImageIcon(
				    getClass().getResource("/resources/basket.png")
				    ).getImage();
	whiteEggImage = new ImageIcon(
				      getClass().getResource("/resources/white_egg.png")
				      ).getImage();
	orangeEggImage = new ImageIcon(
				       getClass().getResource("/resources/orange_egg.png")
				       ).getImage();
	goldenEggImage = new ImageIcon(
				       getClass().getResource("/resources/golden_egg.png")
				       ).getImage();
	shitImage = new ImageIcon(
				  getClass().getResource("/resources/shit.png")
				  ).getImage();
	shitSplashedImage = new ImageIcon(
					  getClass().getResource("/resources/shit_splashed.png")
					  ).getImage();
	whiteEggBrokenImage = new ImageIcon(
					    getClass().getResource("/resources/white_egg_broke.png")
					    ).getImage();
	orangeEggBrokenImage = new ImageIcon(
					     getClass().getResource("/resources/orange_egg_broke.png")
					     ).getImage();
	goldenEggBrokenImage = new ImageIcon(
					     getClass().getResource("/resources/golden_egg_broke.png")
					     ).getImage();
	
	chickenImage = loadChickenImage();
	gameTimer = new Timer(16, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    game.update();
		    boolean gameOver = game.isGameOver();
		    restartButton.setVisible(gameOver);
		    exitButton.setVisible(gameOver);
		    repaint();
		}});

	addMouseMotionListener(new MouseMotionAdapter() {
		@Override
		public void mouseMoved(MouseEvent e) {
		    double scale = Math.min(
					    (double) getWidth() / 600,
					    (double) getHeight() / 600
					    );

		    int offsetX = (int) ((getWidth() - 600 * scale) / 2);
		    int mouseX = (int) ((e.getX() - offsetX) / scale);
		    int basketX = mouseX - game.getBasket().getWidth() / 2;

		    basketX = Math.max(0, Math.min(600 - game.getBasket().getWidth(), basketX));
		    game.getBasket().setX(basketX);
		    repaint();
		}});
	
	setupKeyBindings();

	setLayout(null);
	restartButton = new javax.swing.JButton("Restart");
	exitButton = new javax.swing.JButton("Exit");
	restartButton.setVisible(false);
	exitButton.setVisible(false);

	restartButton.addActionListener(e -> {
		game.reset();
		restartButton.setVisible(false);
		exitButton.setVisible(false);
		repaint();
	    });

	exitButton.addActionListener(e -> {
		System.exit(0);
	    });

	add(restartButton);
	add(exitButton);
	gameTimer.start();
    }

    public void startGame() {
	game.startGame();
    }

    private void setupKeyBindings() {
    getInputMap(WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke("LEFT"),
        "moveLeft"
    );

    getActionMap().put("moveLeft", new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            game.moveBasketLeft();
        }
    });

    getInputMap(WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke("RIGHT"),
        "moveRight"
    );

    getActionMap().put("moveRight", new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            game.moveBasketRight();
        }
    });

    getInputMap(WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke("A"),
        "moveLeft"
    );

    getInputMap(WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke("D"),
        "moveRight"
    );
    }

    private BufferedImage loadChickenImage() {
    try {
        BufferedImage image = ImageIO.read(
					   getClass().getResource("/resources/chicken.png")
					   );

        for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
                int rgb = image.getRGB(x, y);

                int red = (rgb >> 16) & 0xff;
                int green = (rgb >> 8) & 0xff;
                int blue = rgb & 0xff;

                if(red > 240 && green > 240 && blue > 240){
                    image.setRGB(x, y, 0x00000000);
                }
            }
        }

        return image;

    } catch(IOException e) {
        System.out.println("Could not load chicken image.");
        return null;
    }
    }

    @Override
    public void doLayout() {

	double scale = Math.min(
				(double) getWidth() / 600,
				(double) getHeight() / 600
				);
	int gameWidth = (int) (600 * scale);
	int gameHeight = (int) (600 * scale);

	int offsetX = (getWidth() - gameWidth) / 2;
	int offsetY = (getHeight() - gameHeight) / 2;

	int buttonWidth = (int) (100 * scale);
	int buttonHeight = (int) (35 * scale);
	int gap = (int) (20 * scale);

	int centerX = offsetX + gameWidth / 2;
	int buttonY = offsetY + (int) (350 * scale);

	restartButton.setBounds(
				centerX - gap / 2 - buttonWidth,
				buttonY,
				buttonWidth,
				buttonHeight
				);

	exitButton.setBounds(
			     centerX + gap / 2,
			     buttonY,
			     buttonWidth,
			     buttonHeight
			     );
    }

    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g.create();

	double scale = Math.min(
				(double) getWidth() / 600,
				(double) getHeight() / 600
				);
	int offsetX = (int) ((getWidth() - 600 * scale) / 2);
	int offsetY = (int) ((getHeight() - 600 * scale) / 2);

	g2.translate(offsetX, offsetY);
	g2.scale(scale, scale);
	
	g2.drawImage(background, 0, 0, 600, 600, this);

	g2.setColor(java.awt.Color.RED);
	g2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
	g2.drawString(
		      "Score: " + game.getState().getScore(),
		      20,
		      35
		      );

	g2.drawString(
		      "Missed: " + game.getState().getMissedEggs()
		      + "/" + game.getState().getMaxMissedEggs(),
		      20,
		      65
		      );
	
	Chicken[] chickens = game.getChickens();
	for (Chicken chicken : chickens) {
        g2.drawImage(
            chickenImage,
            chicken.getX(),
            chicken.getY(),
            chickenWidth,
            chickenHeight,
            this
        );
        }

	Basket basket = game.getBasket();
	g2.drawImage(
		     basketImage,
		     basket.getX(),
		     basket.getY(),
		     basket.getWidth(),
		     50,
		     this
		     );

	for(FallingObject object : game.getFallingObjects()) {

	    if(object instanceof Egg) {
		Egg egg = (Egg) object;
		Image eggImage;

		if(egg.getPoints() == 5) {
		    eggImage = whiteEggImage;
		} else if(egg.getPoints() == 10) {
		    eggImage = orangeEggImage;
		} else {
		    eggImage = goldenEggImage;
		}

		g2.drawImage(
			     eggImage,
			     egg.getX(),
			     egg.getY(),
			     40,
			     40,
			     this
			     );
	    }else if(object instanceof ChickenShit) {
		g2.drawImage(
			     shitImage,
			     object.getX(),
			     object.getY(),
			     40,
			     40,
			     this
			     );
	    }
	}

	for(BrokenEgg brokenEgg : game.getBrokenEggs()) {
	    Image brokenImage;

	    if(brokenEgg.getPoints() == 5) {
		brokenImage = whiteEggBrokenImage;
	    } else if(brokenEgg.getPoints() == 10) {
		brokenImage = orangeEggBrokenImage;
	    } else {
		brokenImage = goldenEggBrokenImage;
	    }
	    g2.drawImage(
			 brokenImage,
			 brokenEgg.getX(),
			 brokenEgg.getY(),
			 40,
			 40,
			 this
			 );
	}

	for(SplashedShit splash : game.getSplashedShits()) {

	    g2.drawImage(
			 shitSplashedImage,
			 splash.getX(),
			 splash.getY(),
			 40,
			 40,
			 this
			 );
	}

	if (game.isGameOver()) {
	    g2.setColor(new java.awt.Color(0, 0, 0, 170));
	    g2.fillRect(0, 0, 600, 600);

	    g2.setColor(java.awt.Color.RED);
	    g2.setFont(
		       new java.awt.Font("Arial", java.awt.Font.BOLD, 40)
		       );

	    g2.drawString("GAME OVER", 155, 270);
	    g2.setColor(java.awt.Color.WHITE);
	    g2.setFont(
		       new java.awt.Font("Arial", java.awt.Font.PLAIN, 20)
		       );

	    g2.drawString(
			  "Final Score: " + game.getState().getScore(),
			  190,
			  315
			  );
	}
	
	g2.dispose();
    }

}
