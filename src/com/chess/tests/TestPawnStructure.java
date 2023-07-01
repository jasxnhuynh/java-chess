package src.com.chess.tests;

import org.junit.jupiter.api.Test;
import src.com.chess.engine.Color;
import src.com.chess.engine.board.Board;
import src.com.chess.engine.board.Board.Builder;
import src.com.chess.engine.pieces.King;
import src.com.chess.engine.pieces.Pawn;
import src.com.chess.engine.player.ai.PawnStructureAnalyzer;
import src.com.chess.engine.player.ai.StandardBoardEvaluator;
import src.com.chess.pgn.FenUtilities;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPawnStructure {

    @Test
    public void testIsolatedPawnsOnStandardBoard() {
        final Board board = Board.createStandardBoard();
        assertEquals(PawnStructureAnalyzer.get().isolatedPawnPenalty(board.whitePlayer()), 0);
        assertEquals(PawnStructureAnalyzer.get().isolatedPawnPenalty(board.blackPlayer()), 0);
    }

    @Test
    public void testIsolatedPawnByExample1() {
        final Board board = FenUtilities.createGameFromFEN("r1bq1rk1/pp2bppp/1np2n2/6B1/3P4/1BNQ4/PP2NPPP/R3R1K1 b - - 0 13");
        assertEquals(PawnStructureAnalyzer.get().isolatedPawnPenalty(board.whitePlayer()), PawnStructureAnalyzer.ISOLATED_PAWN_PENALTY);
        assertEquals(PawnStructureAnalyzer.get().isolatedPawnPenalty(board.blackPlayer()), 0);
    }

    @Test
    public void testIsolatedPawnByExample2() {
        final Board board = FenUtilities.createGameFromFEN("r1bq1rk1/p3bppp/1np2n2/6B1/3P4/1BNQ4/PP2NPPP/R3R1K1 b - - 0 1");
        assertEquals(PawnStructureAnalyzer.get().isolatedPawnPenalty(board.whitePlayer()), PawnStructureAnalyzer.ISOLATED_PAWN_PENALTY);
        assertEquals(PawnStructureAnalyzer.get().isolatedPawnPenalty(board.blackPlayer()), PawnStructureAnalyzer.ISOLATED_PAWN_PENALTY * 2);
    }

    @Test
    public void testIsolatedPawnByExample3() {
        final Builder builder = new Builder();
        // Black Layout
        builder.setPiece(new King(Color.BLACK, 4, false, false));
        builder.setPiece(new Pawn(Color.BLACK, 12));
        builder.setPiece(new Pawn(Color.BLACK, 20));
        builder.setPiece(new Pawn(Color.BLACK, 28));
        builder.setPiece(new Pawn(Color.BLACK, 8));
        builder.setPiece(new Pawn(Color.BLACK, 16));
        // White Layout
        builder.setPiece(new Pawn(Color.WHITE, 52));
        builder.setPiece(new King(Color.WHITE, 60, false, false));
        builder.setMoveMaker(Color.WHITE);
        // Set the current player
        final Board board = builder.build();
        System.out.println(FenUtilities.createFENFromGame(board));

        assertEquals(PawnStructureAnalyzer.get().isolatedPawnPenalty(board.whitePlayer()), PawnStructureAnalyzer.ISOLATED_PAWN_PENALTY);
        assertEquals(PawnStructureAnalyzer.get().isolatedPawnPenalty(board.blackPlayer()), PawnStructureAnalyzer.ISOLATED_PAWN_PENALTY * 5);
    }

    @Test
    public void testIsolatedPawnByExample4() {
        final Board board = FenUtilities.createGameFromFEN("4k3/2p1p1p1/8/8/8/8/2P1P1P1/4K3 w KQkq -");
        assertEquals(PawnStructureAnalyzer.get().isolatedPawnPenalty(board.whitePlayer()), PawnStructureAnalyzer.ISOLATED_PAWN_PENALTY * 3);
        assertEquals(PawnStructureAnalyzer.get().isolatedPawnPenalty(board.blackPlayer()), PawnStructureAnalyzer.ISOLATED_PAWN_PENALTY * 3);
        final StandardBoardEvaluator boardEvaluator = StandardBoardEvaluator.get();
        assertEquals(boardEvaluator.evaluate(board, 1), 0);
    }

    @Test
    public void testIsolatedPawnByExample5() {
        final Board board = FenUtilities.createGameFromFEN("6k1/p6p/8/8/8/8/P6P/6K1 b - - 0 1");
        assertEquals(PawnStructureAnalyzer.get().isolatedPawnPenalty(board.whitePlayer()), PawnStructureAnalyzer.ISOLATED_PAWN_PENALTY * 2);
        assertEquals(PawnStructureAnalyzer.get().isolatedPawnPenalty(board.blackPlayer()), PawnStructureAnalyzer.ISOLATED_PAWN_PENALTY * 2);
        final StandardBoardEvaluator boardEvaluator = StandardBoardEvaluator.get();
        assertEquals(boardEvaluator.evaluate(board, 1), 0);
    }

    @Test
    public void testIsolatedPawnByExample6() {
        final Board board = FenUtilities.createGameFromFEN("6k1/4p3/4p3/8/8/4P3/4P3/6K1 b - - 0 1");
        assertEquals(PawnStructureAnalyzer.get().isolatedPawnPenalty(board.whitePlayer()), PawnStructureAnalyzer.ISOLATED_PAWN_PENALTY * 2);
        assertEquals(PawnStructureAnalyzer.get().isolatedPawnPenalty(board.blackPlayer()), PawnStructureAnalyzer.ISOLATED_PAWN_PENALTY * 2);
        final StandardBoardEvaluator boardEvaluator = StandardBoardEvaluator.get();
        assertEquals(boardEvaluator.evaluate(board, 1), 0);
    }

    @Test
    public void testDoubledPawnByExample1() {
        final Board board = Board.createStandardBoard();
        assertEquals(PawnStructureAnalyzer.get().doubledPawnPenalty(board.whitePlayer()), 0);
        assertEquals(PawnStructureAnalyzer.get().doubledPawnPenalty(board.blackPlayer()), 0);
        final StandardBoardEvaluator boardEvaluator = StandardBoardEvaluator.get();
        assertEquals(boardEvaluator.evaluate(board, 1), 0);
    }

    @Test
    public void testDoubledPawnByExample2() {
        final Board board = FenUtilities.createGameFromFEN("6k1/4p3/4p3/8/8/4P3/4P3/6K1 b - - 0 1");
        assertEquals(PawnStructureAnalyzer.get().doubledPawnPenalty(board.whitePlayer()), PawnStructureAnalyzer.DOUBLED_PAWN_PENALTY * 2);
        assertEquals(PawnStructureAnalyzer.get().doubledPawnPenalty(board.blackPlayer()), PawnStructureAnalyzer.DOUBLED_PAWN_PENALTY * 2);
        final StandardBoardEvaluator boardEvaluator = StandardBoardEvaluator.get();
        assertEquals(boardEvaluator.evaluate(board, 1), 0);
    }

    @Test
    public void testDoubledPawnByExample3() {
        final Board board = FenUtilities.createGameFromFEN("6k1/8/8/P7/P7/P7/8/6K1 b - - 0 1");
        assertEquals(PawnStructureAnalyzer.get().doubledPawnPenalty(board.whitePlayer()), PawnStructureAnalyzer.DOUBLED_PAWN_PENALTY * 3);
        assertEquals(PawnStructureAnalyzer.get().doubledPawnPenalty(board.blackPlayer()), 0);
    }

    @Test
    public void testDoubledPawnByExample4() {
        final Board board = FenUtilities.createGameFromFEN("6k1/8/8/P6p/P6p/P6p/8/6K1 b - - 0 1");
        assertEquals(PawnStructureAnalyzer.get().doubledPawnPenalty(board.whitePlayer()), PawnStructureAnalyzer.DOUBLED_PAWN_PENALTY * 3);
        assertEquals(PawnStructureAnalyzer.get().doubledPawnPenalty(board.blackPlayer()), PawnStructureAnalyzer.DOUBLED_PAWN_PENALTY * 3);
        assertEquals(PawnStructureAnalyzer.get().isolatedPawnPenalty(board.whitePlayer()), PawnStructureAnalyzer.ISOLATED_PAWN_PENALTY * 3);
        assertEquals(PawnStructureAnalyzer.get().isolatedPawnPenalty(board.blackPlayer()), PawnStructureAnalyzer.ISOLATED_PAWN_PENALTY * 3);
    }


}
