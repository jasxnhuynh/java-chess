package src.com.chess.pgn;

import src.com.chess.engine.board.Board;
import src.com.chess.engine.board.Move;
import src.com.chess.engine.player.Player;

public interface PGNPersistence {
    void persistGame(Game game);

    Move getNextBestMove(Board board, Player player, String gameText);
}
