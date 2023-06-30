package src.com.chess.engine.player;

import com.google.common.collect.ImmutableList;
import src.com.chess.engine.Color;
import src.com.chess.engine.board.Board;
import src.com.chess.engine.board.Move;
import src.com.chess.engine.board.Square;
import src.com.chess.engine.pieces.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WhitePlayer extends Player {
    public WhitePlayer(final Board board, final Collection<Move> whiteStandardLegalMoves,
                       final Collection<Move> blackStandardLegalMoves) {
        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);
    }
    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Color getColor() {
        return Color.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.blackPlayer();
    }

    @Override
    protected Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentLegals) {

        final List<Move> kingCastles = new ArrayList<>();
        if (this.playerKing.isFirstMove() && !this.isInCheck()) {
            // White King Side Castle
            if (!this.board.getSquare(61).isSquareOccupied() &&
                    !this.board.getSquare(62).isSquareOccupied()) {
                final Square rookSquare = this.board.getSquare(63);
                if (rookSquare.isSquareOccupied() && rookSquare.getPiece().isFirstMove()) {
                    if (Player.calculateAttacksOnSquare(61, opponentLegals).isEmpty() &&
                        Player.calculateAttacksOnSquare(62, opponentLegals).isEmpty() &&
                        rookSquare.getPiece().getPieceType().isRook()) {
                        kingCastles.add(null);
                    }
                }
            }
            if (!this.board.getSquare(59).isSquareOccupied() &&
                !this.board.getSquare(58).isSquareOccupied() &&
                !this.board.getSquare(57).isSquareOccupied()) {
                final Square rookSquare = this.board.getSquare(56);
                if (rookSquare.isSquareOccupied() && rookSquare.getPiece().isFirstMove()) {
                    kingCastles.add(null);
                }
            }
        }
        return ImmutableList.copyOf(kingCastles);
    }
}
