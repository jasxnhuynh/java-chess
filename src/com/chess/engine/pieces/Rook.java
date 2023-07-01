package src.com.chess.engine.pieces;

import com.google.common.collect.ImmutableList;
import src.com.chess.engine.Color;
import src.com.chess.engine.board.*;

import java.util.*;

public final class Rook extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATES = { -8, -1, 1, 8 };

    private final static Map<Integer, MoveUtils.Line[]> PRECOMPUTED_CANDIDATES = computeCandidates();

    public Rook(final Color color, final int piecePosition) {
        super(PieceType.ROOK, color, piecePosition, true);
    }

    public Rook(final Color color,
                final int piecePosition,
                final boolean isFirstMove) {
        super(PieceType.ROOK, color, piecePosition, isFirstMove);
    }

    private static Map<Integer, MoveUtils.Line[]> computeCandidates() {
        final Map<Integer, MoveUtils.Line[]> candidates = new HashMap<>();
        for (int position = 0; position < BoardUtils.NUM_SQUARES; position++) {
            List<MoveUtils.Line> lines = new ArrayList<>();
            for (int offset : CANDIDATE_MOVE_COORDINATES) {
                int destination = position;
                MoveUtils.Line line = new MoveUtils.Line();
                while (BoardUtils.isValidSquareCoordinate(destination)) {
                    if (isColumnExclusion(destination, offset)) {
                        break;
                    }
                    destination += offset;
                    if (BoardUtils.isValidSquareCoordinate(destination)) {
                        line.addCoordinate(destination);
                    }
                }
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
            if (!lines.isEmpty()) {
                candidates.put(position, lines.toArray(new MoveUtils.Line[0]));
            }
        }
        return Collections.unmodifiableMap(candidates);
    }


    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final MoveUtils.Line line : PRECOMPUTED_CANDIDATES.get(this.piecePosition)) {
            for (final int candidateDestinationCoordinate : line.getLineCoordinates()) {
                final Piece pieceAtDestination = board.getPiece(candidateDestinationCoordinate);
                if (pieceAtDestination == null) {
                    legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                } else {
                    final Color pieceColor = pieceAtDestination.getPieceColor();
                    if (this.pieceColor != pieceColor) {
                        legalMoves.add(new Move.MajorAttackMove(board, this, candidateDestinationCoordinate,
                                pieceAtDestination));
                    }
                    break;
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }

    @Override
    public int locationBonus() {
        return this.pieceColor.rookBonus(this.piecePosition);
    }

    @Override
    public Rook movePiece(final Move move) {
        return PieceUtils.INSTANCE.getMovedRook(move.getMovedPiece().getPieceColor(), move.getDestinationCoordinate());
    }

    @Override
    public String toString() {
        return this.pieceType.toString();
    }

    private static boolean isColumnExclusion(final int position,
                                             final int offset) {
        return (BoardUtils.INSTANCE.FIRST_COLUMN.get(position) && (offset == -1)) ||
                (BoardUtils.INSTANCE.EIGHTH_COLUMN.get(position) && (offset == 1));
    }

}