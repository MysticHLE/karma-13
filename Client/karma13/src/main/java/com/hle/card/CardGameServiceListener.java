package com.hle.card;


import java.util.List;

public interface CardGameServiceListener {
    public void onPlayerJoin(String gameId, String playerId);
    public void onPlayerJoinApprove(String gameId, String playerId);
    public void onPlayerLeave(String gameId, String playerId);
    public void onPlayerKick(String gameId, String playerId);
    public void onPlayerKickApprove(String gameId, String playerId);
    public void onPlayUndo(String gameId, String playId);
    public void onPlay(String gameId, Play play);
    public void onDeal(Player player, List<Card> cards);
}
