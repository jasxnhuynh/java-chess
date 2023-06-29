package src.com.chess.engine.pieces;

import com.google.common.collect.ImmutableList;
import src.com.chess.engine.Color;
import src.com.chess.engine.board.Board;
import src.com.chess.engine.board.Move;
import src.com.chess.engine.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{

    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};

    Knight(final int piecePosition, final Color pieceColor) {
        super(piecePosition, pieceColor);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {

        int candidateDestinationCoordinate;
        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidate : CANDIDATE_MOVE_COORDINATES) {
            candidateDestinationCoordinate = this.piecePosition + currentCandidate;

            if (true /* isValidSquareCoordinate */) {
                final Square candidateDestinationSquare = board.getSquare(candidateDestinationCoordinate);
                if(!candidateDestinationSquare.isSquareOccupied()) {
                    legalMoves.add(new Move());
                } else {
                    final Piece pieceAtDestination = candidateDestinationSquare.getPiece();
                    final Color pieceColor = pieceAtDestination.getPieceColor();
                    if (this.pieceColor != pieceColor) {
                        legalMoves.add(new Move());
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }
}
