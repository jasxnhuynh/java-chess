package src.com.chess.tests;

import src.com.chess.engine.Color;
import src.com.chess.engine.board.Board;
import src.com.chess.engine.board.Board.Builder;
import src.com.chess.engine.board.BoardUtils;
import src.com.chess.engine.board.Move;
import src.com.chess.engine.board.MoveTransition;
import src.com.chess.engine.pieces.*;
import src.com.chess.engine.player.ai.MoveStrategy;
import src.com.chess.engine.player.ai.StockAlphaBeta;
import src.com.chess.pgn.FenUtilities;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAlphaBeta {

    @Test
    public void testOpeningDepth4BlackMovesFirst() {
        final Board.Builder builder = new Board.Builder();
        // Black Layout
        builder.setPiece(new Rook(Color.BLACK, 0));
        builder.setPiece(new Knight(Color.BLACK, 1));
        builder.setPiece(new Bishop(Color.BLACK, 2));
        builder.setPiece(new Queen(Color.BLACK, 3));
        builder.setPiece(new King(Color.BLACK, 4, false, false));
        builder.setPiece(new Bishop(Color.BLACK, 5));
        builder.setPiece(new Knight(Color.BLACK, 6));
        builder.setPiece(new Rook(Color.BLACK, 7));
        builder.setPiece(new Pawn(Color.BLACK, 8));
        builder.setPiece(new Pawn(Color.BLACK, 9));
        builder.setPiece(new Pawn(Color.BLACK, 10));
        builder.setPiece(new Pawn(Color.BLACK, 11));
        builder.setPiece(new Pawn(Color.BLACK, 12));
        builder.setPiece(new Pawn(Color.BLACK, 13));
        builder.setPiece(new Pawn(Color.BLACK, 14));
        builder.setPiece(new Pawn(Color.BLACK, 15));
        // White Layout
        builder.setPiece(new Pawn(Color.WHITE, 48));
        builder.setPiece(new Pawn(Color.WHITE, 49));
        builder.setPiece(new Pawn(Color.WHITE, 50));
        builder.setPiece(new Pawn(Color.WHITE, 51));
        builder.setPiece(new Pawn(Color.WHITE, 52));
        builder.setPiece(new Pawn(Color.WHITE, 53));
        builder.setPiece(new Pawn(Color.WHITE, 54));
        builder.setPiece(new Pawn(Color.WHITE, 55));
        builder.setPiece(new Rook(Color.WHITE, 56));
        builder.setPiece(new Knight(Color.WHITE, 57));
        builder.setPiece(new Bishop(Color.WHITE, 58));
        builder.setPiece(new Queen(Color.WHITE, 59));
        builder.setPiece(new King(Color.WHITE, 60, false, false));
        builder.setPiece(new Bishop(Color.WHITE, 61));
        builder.setPiece(new Knight(Color.WHITE, 62));
        builder.setPiece(new Rook(Color.WHITE, 63));
        // Set the current player
        builder.setMoveMaker(Color.BLACK);
        final Board board = builder.build();
        System.out.println(FenUtilities.createFENFromGame(board));
        final MoveStrategy alphaBeta = new StockAlphaBeta(4);
        final Move bestMove = alphaBeta.execute(board);
        assertEquals(bestMove, Move.MoveFactory
                .createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("e7"), BoardUtils.INSTANCE.getCoordinateAtPosition("e5")));
    }

    @Test
    public void advancedLevelProblem2NakamuraShirov() {
        final Board.Builder builder = new Board.Builder();
        // Black Layout
        builder.setPiece(new King(Color.BLACK, 5, false, false));
        builder.setPiece(new Pawn(Color.BLACK, 10));
        builder.setPiece(new Rook(Color.BLACK, 25));
        builder.setPiece(new Bishop(Color.BLACK, 29));
        // White Layout
        builder.setPiece(new Knight(Color.WHITE, 27));
        builder.setPiece(new Rook(Color.WHITE, 36));
        builder.setPiece(new Pawn(Color.WHITE, 39));
        builder.setPiece(new King(Color.WHITE, 42, false, false));
        builder.setPiece(new Pawn(Color.WHITE, 46));
        // Set the current player
        builder.setMoveMaker(Color.WHITE);

        final Board board = builder.build();
        final MoveStrategy alphaBeta = new StockAlphaBeta(6);
        final Move bestMove = alphaBeta.execute(board);
        assertEquals(bestMove, Move.MoveFactory
                .createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("d5"), BoardUtils.INSTANCE.getCoordinateAtPosition("c7")));
    }

    @Test
    public void eloTest1() {
        final Board.Builder builder = new Board.Builder();
        // Black Layout
        builder.setPiece(new Rook(Color.BLACK, 0));
        builder.setPiece(new Bishop(Color.BLACK, 2));
        builder.setPiece(new King(Color.BLACK, 6, false, false));
        builder.setPiece(new Pawn(Color.BLACK, 14));
        builder.setPiece(new Knight(Color.BLACK, 18));
        builder.setPiece(new Pawn(Color.BLACK, 20));
        builder.setPiece(new Rook(Color.BLACK, 21));
        builder.setPiece(new Pawn(Color.BLACK, 23));
        builder.setPiece(new Queen(Color.BLACK, 24));
        builder.setPiece(new Pawn(Color.BLACK, 26));
        builder.setPiece(new Bishop(Color.BLACK, 33));
        // White Layout
        builder.setPiece(new Pawn(Color.WHITE, 16));
        builder.setPiece(new Pawn(Color.WHITE, 35));
        builder.setPiece(new Knight(Color.WHITE, 42));
        builder.setPiece(new Knight(Color.WHITE, 45));
        builder.setPiece(new Pawn(Color.WHITE, 48));
        builder.setPiece(new Pawn(Color.WHITE, 49));
        builder.setPiece(new Queen(Color.WHITE, 51));
        builder.setPiece(new Bishop(Color.WHITE, 52));
        builder.setPiece(new Pawn(Color.WHITE, 53));
        builder.setPiece(new Pawn(Color.WHITE, 54));
        builder.setPiece(new Pawn(Color.WHITE, 55));
        builder.setPiece(new Rook(Color.WHITE, 56));
        builder.setPiece(new King(Color.WHITE, 60, false, false));
        builder.setPiece(new Rook(Color.WHITE, 63));
        // Set the current player
        builder.setMoveMaker(Color.BLACK);
        final Board board = builder.build();
        final String fen = FenUtilities.createFENFromGame(board);
        System.out.println(fen);
        final MoveStrategy alphaBeta = new StockAlphaBeta(8);
        final Move bestMove = alphaBeta.execute(board);
        assertEquals(bestMove, Move.MoveFactory
                .createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("c8"), BoardUtils.INSTANCE.getCoordinateAtPosition("a6")));
    }

    @Test
    public void testQualityDepth6() {
        final Board board = FenUtilities.createGameFromFEN("4k2r/1R3R2/p3p1pp/4b3/1BnNr3/8/P1P5/5K2 w - - 1 0");
        final MoveStrategy alphaBeta = new StockAlphaBeta(7);
        final Move bestMove = alphaBeta.execute(board);
        assertEquals(bestMove, Move.MoveFactory
                .createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("f7"), BoardUtils.INSTANCE.getCoordinateAtPosition("e7")));
    }

    @Test
    public void testQualityTwoDepth6() {
        final Board board = FenUtilities.createGameFromFEN("6k1/3b3r/1p1p4/p1n2p2/1PPNpP1q/P3Q1p1/1R1RB1P1/5K2 b - - 0-1");
        final MoveStrategy alphaBeta = new StockAlphaBeta(6);
        final Move bestMove = alphaBeta.execute(board);
        assertEquals(bestMove, Move.MoveFactory
                .createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("h4"), BoardUtils.INSTANCE.getCoordinateAtPosition("f4")));
    }

    @Test
    public void testQualityThreeDepth6() {
        final Board board = FenUtilities.createGameFromFEN("r2r1n2/pp2bk2/2p1p2p/3q4/3PN1QP/2P3R1/P4PP1/5RK1 w - - 0 1");
        final MoveStrategy alphaBeta = new StockAlphaBeta(7);
        final Move bestMove = alphaBeta.execute(board);
        assertEquals(bestMove, Move.MoveFactory
                .createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("g4"), BoardUtils.INSTANCE.getCoordinateAtPosition("g7")));
    }

    @Test
    public void testQualityFourDepth6() {
        final Board board = FenUtilities.createGameFromFEN("r1b1k2r/pp3pbp/1qn1p1p1/2pnP3/3p1PP1/1P1P1NBP/P1P5/RN1QKB1R b KQkq - 2 11");
        final MoveStrategy alphaBeta = new StockAlphaBeta(6);
        final Move bestMove = alphaBeta.execute(board);
        assertEquals(bestMove, Move.MoveFactory
                .createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("e8"), BoardUtils.INSTANCE.getCoordinateAtPosition("g8")));
    }

    @Test
    public void eloTest2() {
        final Builder builder = new Builder();
        // Black Layout
        builder.setPiece(new Knight(Color.BLACK, 2));
        builder.setPiece(new Queen(Color.BLACK, 3));
        builder.setPiece(new Knight(Color.BLACK, 5));
        builder.setPiece(new King(Color.BLACK, 6, false, false));
        builder.setPiece(new Pawn(Color.BLACK, 13));
        builder.setPiece(new Pawn(Color.BLACK, 15));
        builder.setPiece(new Pawn(Color.BLACK, 20));
        builder.setPiece(new Pawn(Color.BLACK, 22));
        builder.setPiece(new Pawn(Color.BLACK, 24));
        builder.setPiece(new Bishop(Color.BLACK, 25));
        builder.setPiece(new Pawn(Color.BLACK, 27));
        builder.setPiece(new Pawn(Color.BLACK, 33));
        // White Layout
        builder.setPiece(new Queen(Color.WHITE, 23));
        builder.setPiece(new Pawn(Color.WHITE, 28));
        builder.setPiece(new Knight(Color.WHITE, 30));
        builder.setPiece(new Pawn(Color.WHITE, 31));
        builder.setPiece(new Pawn(Color.WHITE, 35));
        builder.setPiece(new Pawn(Color.WHITE, 38));
        builder.setPiece(new Pawn(Color.WHITE, 41));
        builder.setPiece(new Knight(Color.WHITE, 46));
        builder.setPiece(new Pawn(Color.WHITE, 48));
        builder.setPiece(new Pawn(Color.WHITE, 53));
        builder.setPiece(new Bishop(Color.WHITE, 54));
        builder.setPiece(new King(Color.WHITE, 62, false, false));
        // Set the current player
        builder.setMoveMaker(Color.WHITE);
        final Board board = builder.build();
        System.out.println(FenUtilities.createFENFromGame(board));
        final MoveStrategy alphaBeta = new StockAlphaBeta(8);
        final Move bestMove = alphaBeta.execute(board);
        assertEquals(bestMove, Move.MoveFactory
                .createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("h5"), BoardUtils.INSTANCE.getCoordinateAtPosition("g6")));
    }

    @Test
    public void eloTest3() {
        final Board.Builder builder = new Board.Builder();
        // Black Layout
        builder.setPiece(new Rook(Color.BLACK, 11));
        builder.setPiece(new Pawn(Color.BLACK, 14));
        builder.setPiece(new Pawn(Color.BLACK, 16));
        builder.setPiece(new Pawn(Color.BLACK, 17));
        builder.setPiece(new Pawn(Color.BLACK, 20));
        builder.setPiece(new Pawn(Color.BLACK, 22));
        builder.setPiece(new King(Color.BLACK, 25, false, false));
        builder.setPiece(new Knight(Color.BLACK, 33));
        // White Layout
        builder.setPiece(new Bishop(Color.WHITE, 19));
        builder.setPiece(new Pawn(Color.WHITE, 26));
        builder.setPiece(new King(Color.WHITE, 36, false, false));
        builder.setPiece(new Rook(Color.WHITE, 46));
        builder.setPiece(new Pawn(Color.WHITE, 49));
        builder.setPiece(new Pawn(Color.WHITE, 53));
        // Set the current player
        builder.setMoveMaker(Color.WHITE);
        final Board board = builder.build();
        System.out.println(FenUtilities.createFENFromGame(board));
        final MoveStrategy alphaBeta = new StockAlphaBeta(6);
        final Move bestMove = alphaBeta.execute(board);
        assertEquals(bestMove, Move.MoveFactory
                .createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("g3"), BoardUtils.INSTANCE.getCoordinateAtPosition("g6")));
    }

    @Test
    public void blackWidowLoss1() {
        final Board board = FenUtilities.createGameFromFEN("r2qkb1r/3p1pp1/p1n1p2p/1p1bP3/P2p4/1PP5/5PPP/RNBQNRK1 w kq - 0 13");
        final MoveStrategy alphaBeta = new StockAlphaBeta(6);
        final Move bestMove = alphaBeta.execute(board);
        assertEquals(bestMove, Move.MoveFactory
                .createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("c3"), BoardUtils.INSTANCE.getCoordinateAtPosition("d4")));
    }

    @Test
    public void testCheckmateHorizon() {
        final Board.Builder builder = new Board.Builder();
        // Black Layout
        builder.setPiece(new Rook(Color.BLACK, 11));
        builder.setPiece(new Pawn(Color.BLACK, 16));
        builder.setPiece(new Bishop(Color.BLACK, 27));
        builder.setPiece(new King(Color.BLACK, 29, false, false));
        // White Layout
        builder.setPiece(new Rook(Color.WHITE, 17));
        builder.setPiece(new Rook(Color.WHITE, 26));
        builder.setPiece(new Pawn(Color.WHITE, 35));
        builder.setPiece(new Pawn(Color.WHITE, 45));
        builder.setPiece(new Bishop(Color.WHITE, 51));
        builder.setPiece(new Pawn(Color.WHITE, 54));
        builder.setPiece(new Pawn(Color.WHITE, 55));
        builder.setPiece(new King(Color.WHITE, 63, false, false));
        // Set the current player
        builder.setMoveMaker(Color.WHITE);
        final Board board = builder.build();
        final MoveStrategy alphaBeta = new StockAlphaBeta(4);
        final Move bestMove = alphaBeta.execute(board);
        assertEquals(bestMove, Move.MoveFactory
                .createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("g2"), BoardUtils.INSTANCE.getCoordinateAtPosition("g4")));
    }

    @Test
    public void testBlackInTrouble() {
        final Builder builder = new Builder();
        // Black Layout
        builder.setPiece(new King(Color.BLACK, 7, false, false));
        builder.setPiece(new Pawn(Color.BLACK, 8));
        builder.setPiece(new Pawn(Color.BLACK, 9));
        builder.setPiece(new Pawn(Color.BLACK, 10));
        builder.setPiece(new Queen(Color.BLACK, 11));
        builder.setPiece(new Rook(Color.BLACK, 14));
        builder.setPiece(new Pawn(Color.BLACK, 15));
        builder.setPiece(new Bishop(Color.BLACK, 17));
        builder.setPiece(new Knight(Color.BLACK, 18));
        builder.setPiece(new Pawn(Color.BLACK, 19));
        builder.setPiece(new Pawn(Color.BLACK, 21));
        // White Layout
        builder.setPiece(new Knight(Color.WHITE, 31));
        builder.setPiece(new Pawn(Color.WHITE, 35));
        builder.setPiece(new Rook(Color.WHITE, 36));
        builder.setPiece(new Queen(Color.WHITE, 46));
        builder.setPiece(new Pawn(Color.WHITE, 48));
        builder.setPiece(new Pawn(Color.WHITE, 53));
        builder.setPiece(new Pawn(Color.WHITE, 54));
        builder.setPiece(new Pawn(Color.WHITE, 55));
        builder.setPiece(new King(Color.WHITE, 62, false, false));
        // Set the current player
        builder.setMoveMaker(Color.WHITE);
        final Board board = builder.build();
        final MoveStrategy alphaBeta = new StockAlphaBeta(4);
        final Move bestMove = alphaBeta.execute(board);
        assertEquals(bestMove, Move.MoveFactory
                .createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("e4"), BoardUtils.INSTANCE.getCoordinateAtPosition("e8")));
    }

    @Test
    public void findMate3() {
        final Board board = FenUtilities.createGameFromFEN("5rk1/5Npp/8/3Q4/8/8/8/7K w - - 0");
        final MoveStrategy alphaBeta = new StockAlphaBeta(6);
        final Move bestMove = alphaBeta.execute(board);
        assertEquals(bestMove, Move.MoveFactory
                .createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("f7"), BoardUtils.INSTANCE.getCoordinateAtPosition("h6")));
        final MoveTransition t1 = board.currentPlayer()
                .makeMove(bestMove);
        assertTrue(t1.getMoveStatus().isDone());
    }

    @Test
    public void runawayPawnMakesIt() {
        final Board board = FenUtilities.createGameFromFEN("2k5/8/8/8/p7/8/8/4K3 b - - 0 1");
        final MoveStrategy alphaBeta = new StockAlphaBeta(5);
        final Move bestMove = alphaBeta.execute(board);
        assertEquals(bestMove, Move.MoveFactory
                .createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("a4"), BoardUtils.INSTANCE.getCoordinateAtPosition("a3")));
        final MoveTransition t1 = board.currentPlayer()
                .makeMove(bestMove);
        assertTrue(t1.getMoveStatus().isDone());
    }

    @Test
    public void testMackHackScenario() {
        final Board board = FenUtilities.createGameFromFEN("1r1k1r2/p5Q1/2p3p1/8/1q1p2n1/3P2P1/P3RPP1/4RK2 b - - 0 1");
        final MoveStrategy alphaBeta = new StockAlphaBeta(8);
        final Move bestMove = alphaBeta.execute(board);
        assertEquals(bestMove, Move.MoveFactory
                .createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("f8"), BoardUtils.INSTANCE.getCoordinateAtPosition("f2")));
        final MoveTransition t1 = board.currentPlayer()
                .makeMove(bestMove);
        assertTrue(t1.getMoveStatus().isDone());
    }

    @Test
    public void testAutoResponseVsPrinChess() {
        final Board board = FenUtilities.createGameFromFEN("r2q1rk1/p1p2pp1/3p1b2/2p2QNb/4PB1P/6R1/PPPR4/2K5 b - - 0 1");
        final MoveStrategy alphaBeta = new StockAlphaBeta(6);
        final Move bestMove = alphaBeta.execute(board);
        assertEquals(bestMove, Move.MoveFactory
                .createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("h5"), BoardUtils.INSTANCE.getCoordinateAtPosition("g6")));
        final MoveTransition t1 = board.currentPlayer()
                .makeMove(bestMove);
        assertTrue(t1.getMoveStatus().isDone());
    }

    @Test
    public void testBratcoKopec1() {
        final Board board = FenUtilities.createGameFromFEN("1k1r4/pp1b1R2/3q2pp/4p3/2B5/4Q3/PPP2B2/2K5 b - - 0 1");
        final MoveStrategy alphaBeta = new StockAlphaBeta(6);
        final Move bestMove = alphaBeta.execute(board);
        assertEquals(bestMove, Move.MoveFactory
                .createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("d6"), BoardUtils.INSTANCE.getCoordinateAtPosition("d1")));
        final MoveTransition t1 = board.currentPlayer()
                .makeMove(bestMove);
        assertTrue(t1.getMoveStatus().isDone());
    }

    @Test
    public void testBratcoKopec2() {
        final Board board = FenUtilities.createGameFromFEN("3r1k2/4npp1/1ppr3p/p6P/P2PPPP1/1NR5/5K2/2R5 w - - 0 1");
        final MoveStrategy alphaBeta = new StockAlphaBeta(6);
        final Move bestMove = alphaBeta.execute(board);
        assertEquals(bestMove, Move.MoveFactory
                .createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("e4"), BoardUtils.INSTANCE.getCoordinateAtPosition("e5")));
        final MoveTransition t1 = board.currentPlayer()
                .makeMove(bestMove);
        assertTrue(t1.getMoveStatus().isDone());
    }

    @Test
    public void testGPT1() {
        final Board board = FenUtilities.createGameFromFEN("r1b1k2r/pp2bppp/2n5/2pqN3/3p1B2/2PP1N2/P1P2PPP/R2QKB1R b KQkq - 0 9");
        final MoveStrategy alphaBeta = new StockAlphaBeta(6);
        final Move bestMove = alphaBeta.execute(board);
        assertEquals(bestMove, Move.MoveFactory
                .createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("g7"), BoardUtils.INSTANCE.getCoordinateAtPosition("g5")));
        final MoveTransition t1 = board.currentPlayer()
                .makeMove(bestMove);
        assertTrue(t1.getMoveStatus().isDone());
    }

    @Test
    public void testBratcoKopec19() {
        final Board board = FenUtilities.createGameFromFEN("3rr3/2pq2pk/p2p1pnp/8/2QBPP2/1P6/P5PP/4RRK1 b - -");
        final MoveStrategy alphaBeta = new StockAlphaBeta(2);
        final Move bestMove = alphaBeta.execute(board);
        assertEquals(bestMove, Move.MoveFactory
                .createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("d6"), BoardUtils.INSTANCE.getCoordinateAtPosition("d5")));
        final MoveTransition t1 = board.currentPlayer()
                .makeMove(bestMove);
        assertTrue(t1.getMoveStatus().isDone());
    }

}
