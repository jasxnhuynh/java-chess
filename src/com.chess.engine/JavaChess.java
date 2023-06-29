package src.com.chess.engine;

import src.com.chess.engine.board.Board;

public class JavaChess {
    public static void main(String[] args) {
        Board board = Board.createStandardBoard();
        System.out.println(board);

    }
}
