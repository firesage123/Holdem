package gameCode;

public class HoldemRunner {
	public static void main(String[] args) {
		PlayingTable playingTable = new PlayingTable(5, 5000);
		playingTable.playGame();
	}
}
