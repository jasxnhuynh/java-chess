package src.com.chess.gui;

import src.com.chess.engine.board.Board;
import src.com.chess.engine.board.BoardUtils;
import src.com.chess.engine.board.Square;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Table {

    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private final Board chessBoard;
    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(600, 600);
    private final static Dimension BOARD_PANEL_DIMENSION = new Dimension(400, 350);
    private final static Dimension SQUARE_PANEL_DIMENSION = new Dimension(10, 10);
    private static String defaultPieceImagesPath = "art/";
    private final Color lightTileColor = Color.decode("#FFFACD");
    private final Color darkTileColor = Color.decode("#593E1A");

    public Table() {
        this.gameFrame = new JFrame("JavaChess");
        this.gameFrame.setLayout(new BorderLayout());
        final JMenuBar tableMenuBar = createTableMenuBar();
        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.chessBoard = Board.createStandardBoard();
        this.boardPanel = new BoardPanel();
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
        this.gameFrame.setVisible(true);
    }

    private JMenuBar createTableMenuBar() {
        final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createFileMenu());
        return tableMenuBar;
    }

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem openPGN = new JMenuItem("Load PGN File");
        openPGN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open PGN File");
            }
        });
        fileMenu.add(openPGN);
        final JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);
        return fileMenu;
    }
    private class BoardPanel extends JPanel {
        final List<SquarePanel> boardSquares;
        BoardPanel() {
            super(new GridLayout(8, 8));
            this.boardSquares = new ArrayList<>();
            for (int i = 0; i < BoardUtils.NUM_SQUARES; i++) {
                final SquarePanel squarePanel = new SquarePanel(this, i);
                this.boardSquares.add(squarePanel);
                add(squarePanel);
            }
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();
        }
    }
    private class SquarePanel extends JPanel {
        private final int squareID;
        SquarePanel(final BoardPanel boardPanel, final int squareID) {
            super(new GridBagLayout());
            this.squareID = squareID;
            setPreferredSize(SQUARE_PANEL_DIMENSION);
            assignSquareColor();
            assignSquarePieceIcon(chessBoard);
            validate();
        }

        private void assignSquarePieceIcon(final Board board) {
            this.removeAll();
            if (board.getSquare(this.squareID).isSquareOccupied()) {
                try {
                    final BufferedImage image = ImageIO.read(new File(defaultPieceImagesPath +
                            board.getSquare(this.squareID).getPiece().getPieceColor().toString().substring(0, 1) +
                            board.getSquare(this.squareID).getPiece().toString() + ".gif"));
                    add(new JLabel(new ImageIcon(image)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        private void assignSquareColor() {
            if (BoardUtils.EIGHTH_RANK[this.squareID] ||
                    BoardUtils.SIXTH_RANK[this.squareID] ||
                    BoardUtils.FOURTH_RANK[this.squareID] ||
                    BoardUtils.SECOND_RANK[this.squareID]) {
                setBackground(this.squareID % 2 == 0 ? lightTileColor : darkTileColor);
            } else if (BoardUtils.SEVENTH_RANK[this.squareID] ||
                    BoardUtils.FIFTH_RANK[this.squareID] ||
                    BoardUtils.THIRD_RANK[this.squareID] ||
                    BoardUtils.FIRST_RANK[this.squareID]) {
                setBackground(this.squareID % 2 != 0 ? lightTileColor : darkTileColor);
            }
        }
    }
}
