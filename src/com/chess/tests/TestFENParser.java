package src.com.chess.tests;

import org.junit.jupiter.api.Test;
import src.com.chess.engine.board.Board;
import src.com.chess.engine.board.BoardUtils;
import src.com.chess.engine.board.Move;
import src.com.chess.engine.board.MoveTransition;
import src.com.chess.pgn.FenUtilities;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFENParser {

    @Test
    public void testWriteFEN1() throws IOException {
        final Board board = Board.createStandardBoard();
        final String fenString = FenUtilities.createFENFromGame(board);
        assertEquals(fenString, "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    }

    @Test
    public void testWriteFEN2() throws IOException {
        final Board board = Board.createStandardBoard();
        final MoveTransition t1 = board.currentPlayer()
                .makeMove(Move.MoveFactory.createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("e2"),
                        BoardUtils.INSTANCE.getCoordinateAtPosition("e4")));
        assertTrue(t1.getMoveStatus().isDone());
        final String fenString = FenUtilities.createFENFromGame(t1.getToBoard());
        assertEquals(fenString, "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        final MoveTransition t2 = t1.getToBoard().currentPlayer()
                .makeMove(Move.MoveFactory.createMove(t1.getToBoard(), BoardUtils.INSTANCE.getCoordinateAtPosition("c7"),
                        BoardUtils.INSTANCE.getCoordinateAtPosition("c5")));
        assertTrue(t2.getMoveStatus().isDone());
        final String fenString2 = FenUtilities.createFENFromGame(t2.getToBoard());
        assertEquals(fenString2, "rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 1");

    }

}
