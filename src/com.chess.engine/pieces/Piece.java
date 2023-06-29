package src.com.chess.engine.pieces;

import src.com.chess.engine.Color;
import src.com.chess.engine.board.Board;
import src.com.chess.engine.board.Move;

import java.util.Collection;

public abstract class Piece {

    protected final int piecePosition;
    protected final Color pieceColor;
    protected final boolean isFirstMove;

    Piece(final int piecePosition, final Color pieceColor) {
        this.piecePosition = piecePosition;
        this.pieceColor = pieceColor;
        this.isFirstMove = false; // to do!!!
    }

    public Color getPieceColor() {
        return this.pieceColor;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);
}
