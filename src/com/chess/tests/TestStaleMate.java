package src.com.chess.tests;

import org.junit.jupiter.api.Test;
import src.com.chess.engine.Color;
import src.com.chess.engine.board.Board;
import src.com.chess.engine.board.Board.Builder;
import src.com.chess.engine.board.BoardUtils;
import src.com.chess.engine.board.Move.MoveFactory;
import src.com.chess.engine.board.MoveTransition;
import src.com.chess.engine.pieces.Bishop;
import src.com.chess.engine.pieces.King;
import src.com.chess.engine.pieces.Pawn;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestStaleMate {
    @Test
    public void testAnandKramnikStaleMate() {

        final Builder builder = new Builder();
        // Black Layout
        builder.setPiece(new Pawn(Color.BLACK, 14));
        builder.setPiece(new Pawn(Color.BLACK, 21));
        builder.setPiece(new King(Color.BLACK, 36, false, false));
        // White Layout
        builder.setPiece(new Pawn(Color.WHITE, 29));
        builder.setPiece(new King(Color.WHITE, 31, false, false));
        builder.setPiece(new Pawn(Color.WHITE, 39));
        // Set the current player
        builder.setMoveMaker(Color.BLACK);
        final Board board = builder.build();
        assertFalse(board.currentPlayer().isInStaleMate());
        final MoveTransition t1 = board.currentPlayer()
                .makeMove(MoveFactory.createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("e4"),
                        BoardUtils.INSTANCE.getCoordinateAtPosition("f5")));
        assertTrue(t1.getMoveStatus().isDone());
        assertTrue(t1.getToBoard().currentPlayer().isInStaleMate());
        assertFalse(t1.getToBoard().currentPlayer().isInCheck());
        assertFalse(t1.getToBoard().currentPlayer().isInCheckMate());
    }

    @Test
    public void testAnonymousStaleMate() {
        final Builder builder = new Builder();
        // Black Layout
        builder.setPiece(new King(Color.BLACK, 2, false, false));
        // White Layout
        builder.setPiece(new Pawn(Color.WHITE, 10));
        builder.setPiece(new King(Color.WHITE, 26, false, false));
        // Set the current player
        builder.setMoveMaker(Color.WHITE);
        final Board board = builder.build();
        assertFalse(board.currentPlayer().isInStaleMate());
        final MoveTransition t1 = board.currentPlayer()
                .makeMove(MoveFactory.createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("c5"),
                        BoardUtils.INSTANCE.getCoordinateAtPosition("c6")));
        assertTrue(t1.getMoveStatus().isDone());
        assertTrue(t1.getToBoard().currentPlayer().isInStaleMate());
        assertFalse(t1.getToBoard().currentPlayer().isInCheck());
        assertFalse(t1.getToBoard().currentPlayer().isInCheckMate());
    }

    @Test
    public void testAnonymousStaleMate2() {
        final Builder builder = new Builder();
        // Black Layout
        builder.setPiece(new King(Color.BLACK, 0, false, false));
        // White Layout
        builder.setPiece(new Pawn(Color.WHITE, 16));
        builder.setPiece(new King(Color.WHITE, 17, false, false));
        builder.setPiece(new Bishop(Color.WHITE, 19));
        // Set the current player
        builder.setMoveMaker(Color.WHITE);
        final Board board = builder.build();
        assertFalse(board.currentPlayer().isInStaleMate());
        final MoveTransition t1 = board.currentPlayer()
                .makeMove(MoveFactory.createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("a6"),
                        BoardUtils.INSTANCE.getCoordinateAtPosition("a7")));
        assertTrue(t1.getMoveStatus().isDone());
        assertTrue(t1.getToBoard().currentPlayer().isInStaleMate());
        assertFalse(t1.getToBoard().currentPlayer().isInCheck());
        assertFalse(t1.getToBoard().currentPlayer().isInCheckMate());
    }
}