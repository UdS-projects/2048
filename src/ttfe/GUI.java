package ttfe;

import java.awt.Dimension;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.Font;

import javax.swing.SwingConstants;

import ttfe.GUI;
import ttfe.MoveDirection;
import ttfe.SimulatorInterface;
import ttfe.UserInterface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * ==================================== NOTE ================================================
 * This class is not covered by our tests and you do not need to understand the code in here.
 * It is merely a help from our side to ease your work on the simulator and computer player.
 * ==================================== NOTE ================================================
 */
public class GUI implements UserInterface {

	/**
	 * The next user move as read from the keyboard input.
	 */
	private MoveDirection UserMoveChoice = null;

	/**
	 * The idle time after the game screen was updated in milliseconds.
	 * 
	 * The smaller this value is the faster the game can become if arrow keys
	 * are pressed "constantly" or a computer player is used to choose the move
	 * direction.
	 */
	private final int IDLE_TIME = 100;

	/**
	 * The width of the window.
	 */
	private final int WINDOW_WIDTH = 600;

	/**
	 * The height of a window.
	 */
	private final int WINDOW_HEIGHT = 700;
	
	/**
	 * The width of a tile.
	 */
	private final int TILE_WIDTH = 10;

	/**
	 * The height of a tile.
	 */
	private final int TILE_HEIGHT = 10;
	
	/**
	 * The main GUI frame.
	 */
	private final JFrame frame = new JFrame("2048");

	
	/**
	 * The array of tiles
	 */
	private JLabel Tiles[][];
	
	
	/**
	 * Labels in the GUI.
	 */
	private JLabel ScoreLabel;
	private JLabel MovesLabel;
	private JLabel GameLabel;

	/**
	 * The background colors for the GUI.
	 */
	private final Color OUTER_BG_COLOR = new Color(250, 248, 239);
	private final Color DEFAULT_FG_COLOR = new Color(119, 110, 101);
	private final Color STATE_BG_COLOR = new Color(187, 173, 160);
	private final Color EMPTY_TILE_COLOR = new Color(205, 192, 180);
	private final Color STATE_FG_COLOR = new Color(236, 225, 209);
	
	/**
	 * The background colors for the tiles as defined in the online version.
	 */
	private final Color TileColors[] = { EMPTY_TILE_COLOR,
			new Color(205, 192, 180), new Color(238, 228, 218),
			new Color(237, 224, 200), new Color(242, 177, 121),
			new Color(245, 149, 99), new Color(246, 124, 95),
			new Color(246, 94, 59), new Color(237, 207, 114),
			new Color(237, 204, 97), new Color(237, 200, 80),
			new Color(237, 197, 63), new Color(237, 194, 46), Color.BLACK,
			Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK };

	/**
	 * The foreground colors for the tiles as defined in the online version.
	 */
	private final Color TileFGDark = new Color(119, 110, 101);
	private final Color TileFGLight = new Color(249, 246, 242);

	/**
	 * The fonts for the tiles similar to the online version.
	 */
	private final Font TileFonts[] = { new Font("", Font.BOLD, 48),
			new Font("", Font.BOLD, 48), new Font("", Font.BOLD, 44),
			new Font("", Font.BOLD, 40), new Font("", Font.BOLD, 36) };

	/**
	 * Create the application.
	 */
	public GUI(SimulatorInterface game) {
		initialize(game);
	}

	@SuppressWarnings("serial")
	public class RoundedJPanel extends JPanel {

		/** Double values for Horizontal and Vertical radius of corner arcs */
		protected Dimension arcs = new Dimension(TILE_WIDTH, TILE_HEIGHT);

		public RoundedJPanel() {
			super();
			setOpaque(false);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int width = getWidth();
			int height = getHeight();
			Graphics2D graphics = (Graphics2D) g;

			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			// Draws the rounded opaque panel with borders.
			graphics.setColor(getBackground());
			graphics.fillRoundRect(0, 0, width, height, arcs.width, arcs.height);
			graphics.setColor(getForeground());

			// Sets strokes to default, is better.
			graphics.setStroke(new BasicStroke());
		}

		public void paintChildren(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			super.paintChildren(g2);
		}
	}

	private final class SynchronizedKeyListener implements KeyListener {
		private final GUI G;

		public SynchronizedKeyListener(GUI G) {
			this.G = G;
		}

		@Override
		public void keyTyped(KeyEvent e) {
			/* Do nothing */
		}

		@Override
		public void keyReleased(KeyEvent e) {
			/* Do nothing */
		}

		@Override
		public void keyPressed(KeyEvent e) {
			synchronized (G) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					UserMoveChoice = MoveDirection.WEST;
					break;
				case KeyEvent.VK_RIGHT:
					UserMoveChoice = MoveDirection.EAST;
					break;
				case KeyEvent.VK_UP:
					UserMoveChoice = MoveDirection.NORTH;
					break;
				case KeyEvent.VK_DOWN:
					UserMoveChoice = MoveDirection.SOUTH;
					break;
				default:
					return;
				}
				G.notify();
			}
		}
	}
	
	private JLabel createLabel(String Text, Font F, Color FG) {
		JLabel Label = new JLabel(Text, SwingConstants.CENTER);
		Label.setFont(F);
		Label.setForeground(FG);
		return Label;
	}
	
	private JPanel createMainPanel() {
		JPanel MainPanel = new JPanel();
		MainPanel.setLayout(new BorderLayout(0, 0));
		MainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		MainPanel.setBackground(OUTER_BG_COLOR);
		return MainPanel;
	}
	
	private JPanel createHeaderPanel() {
		JPanel HeaderPanel = new RoundedJPanel();
		HeaderPanel.setLayout(new GridLayout(1, 0, 0, 0));
		HeaderPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		HeaderPanel.setBackground(OUTER_BG_COLOR);
		return HeaderPanel;
	}
	
	private JPanel createStatePanel() {
		JPanel StatePanel = new JPanel();
		GridBagLayout gbl_StatePanel = new GridBagLayout();
		gbl_StatePanel.columnWidths = new int[] { 140, 140};
		gbl_StatePanel.rowHeights = new int[] { 57 };
		StatePanel.setLayout(gbl_StatePanel);
		StatePanel.setBackground(OUTER_BG_COLOR);
		return StatePanel;
	}
	
	private JPanel createStateContentPanel(JPanel StatePanel, int gridX) {
		JPanel StateContentPanel = new RoundedJPanel();
		StateContentPanel.setBackground(STATE_BG_COLOR);
		StateContentPanel.setLayout(new GridLayout(2, 1, 10, -3));
		
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = gridX;
		gbc_lblNewLabel.gridy = 0;
		StatePanel.add(StateContentPanel, gbc_lblNewLabel);
		
		return StateContentPanel;
	}
	
	private JPanel createBoardPanel(int W, int H) {
		JPanel BoardPanel = new RoundedJPanel();
		BoardPanel.setLayout(new GridLayout(H, W, 10, 10));
		BoardPanel.setBackground(STATE_BG_COLOR);
		BoardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 7, 9));
		return BoardPanel;
	}
	
	private JPanel createTilePanel() {
		JPanel TilePanel = new RoundedJPanel();
		TilePanel.setLayout(new GridLayout(1, 1, 10, 10));
		TilePanel.setBackground(EMPTY_TILE_COLOR);
		return TilePanel;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(SimulatorInterface game) {

		JPanel MainPanel = createMainPanel();
		frame.getContentPane().add(MainPanel);

		JPanel HeaderPanel = createHeaderPanel();
		MainPanel.add(HeaderPanel, BorderLayout.NORTH);

		GameLabel = createLabel("2048", new Font("", Font.BOLD, 62), DEFAULT_FG_COLOR);
		HeaderPanel.add(GameLabel);
		
		JPanel StatePanel = createStatePanel();
		HeaderPanel.add(StatePanel);

		JPanel ScorePanel = createStateContentPanel(StatePanel, 0);
		JPanel MovesPanel = createStateContentPanel(StatePanel, 1);

		JLabel ScoreText = createLabel("Score",new Font("", Font.BOLD, 14), STATE_FG_COLOR);
		ScorePanel.add(ScoreText);
		
		ScoreLabel = createLabel("", new Font("", Font.BOLD, 24), Color.WHITE);
		ScorePanel.add(ScoreLabel);
		
		JLabel MovesText = createLabel("Moves",new Font("", Font.BOLD, 14), STATE_FG_COLOR);
		MovesPanel.add(MovesText);
		
		MovesLabel = createLabel("", new Font("", Font.BOLD, 24), Color.WHITE);
		MovesPanel.add(MovesLabel);


		int W = game.getBoardWidth();
		int H = game.getBoardHeight();
		
		JPanel BoardPanel = createBoardPanel(W, H);
		MainPanel.add(BoardPanel, BorderLayout.CENTER);

		Tiles = new JLabel[W][H];
		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				JPanel TilePanel = createTilePanel();
				JLabel TileLabel = new JLabel("", SwingConstants.CENTER);
				Tiles[x][y] = TileLabel;
				TilePanel.add(TileLabel);
				BoardPanel.add(TilePanel);
			}
		}

		// Finally, set some default options and pack the window so the content will appear.
		frame.pack();
		
		frame.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		frame.addKeyListener(new SynchronizedKeyListener(this));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		// frame.setResizable(false);
		frame.setVisible(true);
	}

	@Override
	public String getUserInput(String Question, String[] PossibleAnswers) {
		while (true) {
			String Answer= JOptionPane.showInputDialog(Question, "");

			for (String PossibleAnswer : PossibleAnswers)
				if (PossibleAnswer.equals(Answer))
					return Answer;
		}
	}

	@Override
	public MoveDirection getUserMove() {
		synchronized (this) {
			UserMoveChoice = null;
			try {
				while (UserMoveChoice == null)
					this.wait();
				return UserMoveChoice;
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		return null;
	}

	private int log2(int Value) {
		int r = 0;
		while (Value != 0) {
			Value >>= 1;
			r++;
		}
		return r;
	}

	@Override
	public void updateScreen(SimulatorInterface Game) {
		int W = Game.getBoardWidth();
		int H = Game.getBoardHeight();

		for (int x = 0; x < W; x++) {
			for (int y = 0; y < H; y++) {
				int Piece = Game.getPieceAt(x, y);
				JLabel Tile = Tiles[x][y];

				String Text = "";
				if (Piece != 0)
					Text += Piece;
				Tile.setText(Text);

				Tile.getParent().setBackground(TileColors[0]);

				if (Piece == 0)
					continue;

				int l2 = Math.min(log2(Piece), 18);
				Tile.getParent().setBackground(TileColors[l2]);

				if (l2 > 3)
					Tile.setForeground(TileFGLight);
				else
					Tile.setForeground(TileFGDark);

				Tile.setFont(TileFonts[Math.min(Text.length(), 4)]);
			}
		}
		
		ScoreLabel.setText("" + Game.getPoints());
		MovesLabel.setText("" + Game.getNumMoves());
		
		if (IDLE_TIME > 0) {
			try {
				Thread.sleep(IDLE_TIME);
			} catch (InterruptedException e) {
				/* Do nothing */
			}
		}
	}

	@Override
	public void showGameOverScreen(SimulatorInterface Game) {
		JOptionPane.showMessageDialog(frame,
				"Game Over, after " + Game.getNumMoves() + " moves and with "
						+ Game.getPoints() + " points!", "GAME OVER",
				JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void showMessage(String Msg) {
		JOptionPane.showMessageDialog(frame, Msg);
	}

}
