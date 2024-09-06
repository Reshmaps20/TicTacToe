package com.katalearn.tictactoegame.service;

import org.springframework.stereotype.Service;

import com.katalearn.tictactoegame.constants.GameConstants;

@Service
public class TicTacToeService {

	private char[][] board = new char[3][3];
	private boolean gameWon = false;
	private boolean gameDraw = false;

	public TicTacToeService() {
		initializeBoard();
	}

	private void initializeBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			}
		}
	}

	public String makeMove(PlayerMove playerMove) {

		int row = playerMove.getRow();
		int col = playerMove.getColumn();
		char currentPlayer = playerMove.getPlayer();

		if (gameWon || gameDraw) {
			return GameConstants.GAME_OVER;
		}

		if (!isValidMove(row, col)) {
			return GameConstants.INVALID_MOVE;
		}

		board[row][col] = currentPlayer;

		if (checkWin(currentPlayer)) {
			gameWon = true;
			return String.format(GameConstants.WINNER, currentPlayer, boardToString());
		}

		if (isBoardFull()) {
			gameDraw = true;
			return GameConstants.DRAW;
		}
		return String.format(GameConstants.MOVE_COMPELETED, boardToString());
	}

	private boolean isValidMove(int row, int col) {
		return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
	}

	private boolean checkWin(char currentPlayer) {
		return (checkRowsColumns(currentPlayer) || checkDiagonals(currentPlayer));
	}

	private boolean checkRowsColumns(char currentPlayer) {
		for (int i = 0; i < 3; i++) {
			if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer)
					|| (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkDiagonals(char currentPlayer) {
		return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer)
				|| (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
	}

	private boolean isBoardFull() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}

	private String boardToString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			sb.append(board[i][0]).append(" ").append(board[i][1]).append(" ").append(board[i][2]).append("\n");
		}
		return sb.toString();
	}

	public String resetGame() {
		initializeBoard();
		gameWon = false;
		gameDraw = false;
		return GameConstants.GAME_RESET;
	}

}
