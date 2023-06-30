package src.com.chess.engine.player;

import com.google.common.collect.ImmutableList;
import src.com.chess.engine.Color;
import src.com.chess.engine.board.Board;
import src.com.chess.engine.board.Move;
import src.com.chess.engine.board.Square;
import src.com.chess.engine.pieces.King;
import src.com.chess.engine.pieces.Piece;
import src.com.chess.engine.pieces.Rook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static src.com.chess.engine.board.Move.*;

public class BlackPlayer extends Player {
    public BlackPlayer(final Board board, final Collection<Move> blackStandardLegalMoves,
                       final Collection<Move> whiteStandardLegalMoves) {
        super(board, blackStandardLegalMoves, whiteStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Color getColor() {
        return Color.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.whitePlayer();
    }

    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
                                                    final Collection<Move> opponentLegals) {
        final List<Move> kingCastles = new ArrayList<>();
        if (this.playerKing.isFirstMove() && !this.isInCheck()) {
            // Black King Side Castle
            if (!this.board.getSquare(5).isSquareOccupied() &&
                    !this.board.getSquare(6).isSquareOccupied()) {
                final Square rookSquare = this.board.getSquare(7);
                if (rookSquare.isSquareOccupied() && rookSquare.getPiece().isFirstMove()) {
                    if (Player.calculateAttacksOnSquare(5, opponentLegals).isEmpty() &&
                            Player.calculateAttacksOnSquare(6, opponentLegals).isEmpty() &&
                            rookSquare.getPiece().getPieceType().isRook()) {
                        kingCastles.add(new KingSideCastleMove(this.board, this.playerKing, 6,
                                (Rook) rookSquare.getPiece(), rookSquare.getSquareCoordinate(), 5));
                    }
                }
            }
            // Black Queen Side Castle
            if (!this.board.getSquare(1).isSquareOccupied() &&
                    !this.board.getSquare(2).isSquareOccupied() &&
                    !this.board.getSquare(3).isSquareOccupied()) {
                final Square rookSquare = this.board.getSquare(0);
                if (rookSquare.isSquareOccupied() && rookSquare.getPiece().isFirstMove()) {
                    kingCastles.add(new QueenSideCastleMove(this.board, this.playerKing, 2,
                            (Rook) rookSquare.getPiece(), rookSquare.getSquareCoordinate(), 3));
                }
            }
        }
        return ImmutableList.copyOf(kingCastles);
    }
}
