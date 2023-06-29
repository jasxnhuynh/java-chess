package src.com.chess.engine.board;

import src.com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public abstract class Square {
    protected final int squareCoordinate;

    private static final Map<Integer, EmptySquare> EMPTY_SQUARES_CACHE = createAllPossibleEmptySquares();

    private static Map<Integer, EmptySquare> createAllPossibleEmptySquares() {
        final Map<Integer, EmptySquare> emptySquareMap = new HashMap<>();

        for(int i = 0; i < BoardUtils.NUM_SQUARES; i++) {
            emptySquareMap.put(i, new EmptySquare(i));
        }
        // Collections.unmodifiableMap(emptySquareMap); <- works the same
        return ImmutableMap.copyOf(emptySquareMap);
    }

    public static Square createSquare(final int squareCoordinate, final Piece piece) {
        return piece != null ? new OccupiedSquare(squareCoordinate, piece) : EMPTY_SQUARES_CACHE.get(squareCoordinate);
    }
    private Square(final int squareCoordinate) {
        this.squareCoordinate = squareCoordinate;
    }

    public abstract boolean isSquareOccupied();

    public abstract Piece getPiece();

    public static final class EmptySquare extends Square {
        private EmptySquare(final int coordinate) {
            super(coordinate);
        }
        @Override
        public String toString() {
            return "-";
        }
        @Override
        public boolean isSquareOccupied() {
            return false;
        }
        @Override
        public Piece getPiece() {
            return null;
        }
    }
    public static final class OccupiedSquare extends Square {
        private final Piece pieceOnSquare;

        private OccupiedSquare(int squareCoordinate, final Piece pieceOnSquare) {
            super(squareCoordinate);
            this.pieceOnSquare = pieceOnSquare;
        }
        @Override
        public String toString() {
            return getPiece().getPieceColor().isBlack() ? getPiece().toString().toLowerCase() : getPiece().toString();
        }
        @Override
        public boolean isSquareOccupied() {
            return true;
        }
        @Override
        public Piece getPiece() {
            return pieceOnSquare;
        }
    }
}
