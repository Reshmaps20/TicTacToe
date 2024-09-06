package com.katalearn.tictactoegame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.katalearn.tictactoegame.service.PlayerMove;
import com.katalearn.tictactoegame.service.TicTacToeService;

@RestController
@RequestMapping("/tictactoe")
public class TicTacToeController {

	@Autowired
	TicTacToeService ticTacToeService;

	//API endpoint for the player to make a move
	@PostMapping("/playgame")
	public String makeMove(@RequestBody PlayerMove playerMove) {
		return ticTacToeService.makeMove(playerMove);
	}

	// API endpoint to reset the game
	@PostMapping("/reset")
	public String resetGame() {
		return ticTacToeService.resetGame();
	}

}
