package src.com.chess.engine.player;

import src.com.chess.engine.Color;
import src.com.chess.engine.board.Board;
import src.com.chess.engine.board.BoardUtils;
import src.com.chess.engine.board.Move;
import src.com.chess.engine.board.Move.KingSideCastleMove;
import src.com.chess.engine.board.Move.QueenSideCastleMove;
import src.com.chess.engine.pieces.Piece;
import src.com.chess.engine.pieces.Rook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static src.com.chess.engine.pieces.Piece.PieceType.ROOK;

public final class WhitePlayer extends Player {

    public WhitePlayer(final Board board,
                       final Collection<Move> whiteStandardLegals,
                       final Collection<Move> blackStandardLegals) {
        super(board, whiteStandardLegals, blackStandardLegals);
    }

    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
                                                    final Collection<Move> opponentLegals) {

        if(!hasCastleOpportunities()) {
            return Collections.emptyList();
        }

        final List<Move> kingCastles = new ArrayList<>();

        if(this.playerKing.isFirstMove() && this.playerKing.getPiecePosition() == 60 && !this.isInCheck()) {
            //whites king side castle
            if(this.board.getPiece(61) == null && this.board.getPiece(62) == null) {
                final Piece kingSideRook = this.board.getPiece(63);
                if(kingSideRook != null && kingSideRook.isFirstMove()) {
                    if(Player.calculateAttacksOnSquare(61, opponentLegals).isEmpty() &&
                            Player.calculateAttacksOnSquare(62, opponentLegals).isEmpty() &&
                            kingSideRook.getPieceType() == ROOK) {
                        if(!BoardUtils.isKingPawnTrap(this.board, this.playerKing, 52)) {
                            kingCastles.add(new KingSideCastleMove(this.board, this.playerKing, 62, (Rook) kingSideRook, kingSideRook.getPiecePosition(), 61));
                        }
                    }
                }
            }
            //whites queen side castle
            if(this.board.getPiece(59) == null && this.board.getPiece(58) == null &&
                    this.board.getPiece(57) == null) {
                final Piece queenSideRook = this.board.getPiece(56);
                if(queenSideRook != null && queenSideRook.isFirstMove()) {
                    if(Player.calculateAttacksOnSquare(58, opponentLegals).isEmpty() &&
                            Player.calculateAttacksOnSquare(59, opponentLegals).isEmpty() && queenSideRook.getPieceType() == ROOK) {
                        if(!BoardUtils.isKingPawnTrap(this.board, this.playerKing, 52)) {
                            kingCastles.add(new QueenSideCastleMove(this.board, this.playerKing, 58, (Rook) queenSideRook, queenSideRook.getPiecePosition(), 59));
                        }
                    }
                }
            }
        }
        return Collections.unmodifiableList(kingCastles);
    }

    @Override
    public BlackPlayer getOpponent() {
        return this.board.blackPlayer();
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
    public String toString() {
        return Color.WHITE.toString();
    }

}
