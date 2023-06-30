package src.com.chess.engine.pieces;

import com.google.common.collect.ImmutableList;
import src.com.chess.engine.Color;
import src.com.chess.engine.board.Board;
import src.com.chess.engine.board.BoardUtils;
import src.com.chess.engine.board.Move;
import src.com.chess.engine.board.Move.RegularMove;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATE = {8, 16, 7, 9};

    public Pawn(final Color pieceColor, final int piecePosition) {
        super(PieceType.PAWN, piecePosition, pieceColor);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {
            final int candidateDestinationCoordinate = this.piecePosition + (this.pieceColor.getDirection() *
                    currentCandidateOffset);

            if (BoardUtils.isValidSquareCoordinate(candidateDestinationCoordinate)) {
                continue;
            }
            if (currentCandidateOffset == 8 && board.getSquare(candidateDestinationCoordinate).isSquareOccupied()) {
                // to-do!!! promotions
                legalMoves.add(new RegularMove(board, this, candidateDestinationCoordinate));
            } else if (currentCandidateOffset == 16 && this.isFirstMove() &&
                    (BoardUtils.SEVENTH_RANK[this.piecePosition] && this.getPieceColor().isBlack()) ||
                    (BoardUtils.SECOND_RANK[this.piecePosition] && this.getPieceColor().isWhite())) {
                final int behindCandidateDestinationCoordinate = this.piecePosition +
                        (this.pieceColor.getDirection() * 8);
                if (!board.getSquare(behindCandidateDestinationCoordinate).isSquareOccupied() &&
                        !board.getSquare(candidateDestinationCoordinate).isSquareOccupied()) {
                    legalMoves.add(new RegularMove(board, this, candidateDestinationCoordinate));
                }
            } else if (currentCandidateOffset == 7 &&
                    !((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceColor.isWhite() ||
                      (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceColor.isBlack())))) {
                if (board.getSquare(candidateDestinationCoordinate).isSquareOccupied()) {
                    final Piece pieceOnCandidate = board.getSquare(candidateDestinationCoordinate).getPiece();
                    if (this.pieceColor != pieceOnCandidate.getPieceColor()) {
                        // todo
                        legalMoves.add(new RegularMove(board, this, candidateDestinationCoordinate));
                    }
                }
            } else if (currentCandidateOffset == 9 &&
                    !((BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceColor.isWhite() ||
                      (BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceColor.isBlack())))) {
                if (board.getSquare(candidateDestinationCoordinate).isSquareOccupied()) {
                    final Piece pieceOnCandidate = board.getSquare(candidateDestinationCoordinate).getPiece();
                    if (this.pieceColor != pieceOnCandidate.getPieceColor()) {
                        // todo
                        legalMoves.add(new RegularMove(board, this, candidateDestinationCoordinate));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }
    @Override
    public Pawn movePiece(Move move) {
        return new Pawn(move.getMovedPiece().getPieceColor(), move.getDestinationCoordinate());
    }
    @Override
    public String toString() {
        return PieceType.PAWN.toString();
    }
}
