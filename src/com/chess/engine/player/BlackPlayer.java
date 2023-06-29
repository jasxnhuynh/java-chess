package src.com.chess.engine.player;

import src.com.chess.engine.Color;
import src.com.chess.engine.board.Board;
import src.com.chess.engine.board.Move;
import src.com.chess.engine.pieces.Piece;

import java.util.Collection;

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
}
