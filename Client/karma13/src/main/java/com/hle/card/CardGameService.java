package com.hle.card;

import java.util.List;

// Makes all relevant server calls. Used by the card game to facilitate state on the server and the client.
public abstract class CardGameService {
    public abstract void deal(String gameId);
    public abstract String joinGame(Player player, Player existingPlayer);
    public abstract void approvePlayer(String gameId, Player player);
    public abstract void kickPlayer(String gameId, Player player);
    public abstract void leaveGame(String gameId, Player player);
    public abstract void play(String gameId, Player player, Play play, String playChecksum);
    public abstract void undoPlay(Play play);
    public abstract List<Play> listPlays(String gameId);
    public abstract Play getPlay(String playId);
}
