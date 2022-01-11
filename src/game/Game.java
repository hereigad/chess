package game;

import chess.*;

public class Game {
    private final Piece[][] board;
    private final Player player;

    public Game(Player player) {
        this.player = player;
        board = new Piece[8][8];
        getBoard()[7][0] = new Rook(new int[]{7,0}, player.getSide());
        getBoard()[7][1] = new Knight(new int[]{7,1}, player.getSide());
        getBoard()[7][2] = new Bishop(new int[]{7,2}, player.getSide());
        getBoard()[7][5] = new Bishop(new int[]{7,5}, player.getSide());
        getBoard()[7][6] = new Knight(new int[]{7,6}, player.getSide());
        getBoard()[7][7] = new Rook(new int[]{7,7}, player.getSide());
        getBoard()[0][0] = new Rook(new int[]{0,0}, !player.getSide());
        getBoard()[0][1] = new Knight(new int[]{0,1}, !player.getSide());
        getBoard()[0][2] = new Bishop(new int[]{0,2}, !player.getSide());
        getBoard()[0][5] = new Bishop(new int[]{0,5}, !player.getSide());
        getBoard()[0][6] = new Knight(new int[]{0,6}, !player.getSide());
        getBoard()[0][7] = new Rook(new int[]{0,7}, !player.getSide());
        if(player.getSide()) {
            getBoard()[7][3] = new Queen(new int[]{7,3}, player.getSide());
            getBoard()[7][4] = new King(new int[]{7,4}, player.getSide());

            getBoard()[0][3] = new Queen(new int[]{0,3}, !player.getSide());
            getBoard()[0][4] = new King(new int[]{0,4}, !player.getSide());
        } else {
            getBoard()[7][3] = new King(new int[]{7,3}, player.getSide());
            getBoard()[7][4] = new Queen(new int[]{7,4}, player.getSide());

            getBoard()[0][3] = new King(new int[]{0,3}, !player.getSide());
            getBoard()[0][4] = new Queen(new int[]{0,4}, !player.getSide());
        }
        for(int i = 0; i < 8; i++) {
            getBoard()[6][i] = new Pawn(new int[]{6,i}, player.getSide());
            getBoard()[1][i] = new Pawn(new int[]{1,i}, !player.getSide());
            for(int j = 2; j < 6; j++) {
                getBoard()[j][i] = null;
            }
        }
    }

    public Piece[][] getBoard() {
        return board;
    }

    public Player getPlayer() {
        return player;
    }
}
