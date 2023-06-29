package src.com.chess.engine.pieces;

import src.com.chess.engine.Color;
import src.com.chess.engine.board.Board;
import src.com.chess.engine.board.Move;

import java.util.List;

public abstract class Piece {

    protected final int piecePosition;
    protected final Color pieceColor;

    Piece(final int piecePosition, final Color pieceColor) {
        this.piecePosition = piecePosition;
        this.pieceColor = pieceColor;
    }

    public Color getPieceColor() {
        return this.pieceColor;
    }

    public abstract List<Move> calculateLegalMoves(final Board board);
}
