package src.com.chess.engine.pieces;

import src.com.chess.engine.Color;
import src.com.chess.engine.board.Board;
import src.com.chess.engine.board.Move;

import java.util.Collection;

public abstract class Piece {

    protected final PieceType pieceType;
    protected final int piecePosition;
    protected final Color pieceColor;
    protected final boolean isFirstMove;

    Piece(final PieceType pieceType, final int piecePosition, final Color pieceColor) {
        this.pieceType = pieceType;
        this.piecePosition = piecePosition;
        this.pieceColor = pieceColor;
        this.isFirstMove = false; // to do!!!
    }

    public int getPiecePosition() {
        return this.piecePosition;
    }

    public Color getPieceColor() {
        return this.pieceColor;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

    public enum PieceType {
        PAWN("P") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        ROOK("R") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        KNIGHT("N") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        BISHOP("B") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        QUEEN("Q") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        KING("K") {
            @Override
            public boolean isKing() {
                return true;
            }
        };

        private String pieceName;

        PieceType(final String pieceName) {
            this.pieceName = pieceName;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }
        public abstract boolean isKing();

    }
}
