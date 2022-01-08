package com.company;

import game.Game;
import game.Player;
import graphics.Match;

public class Main {

    public static void main(String[] args) {
        Game game = new Game(new Player(true));
        Match match = new Match(game);
        match.setVisible(true);
    }
}
