package gameCode;

public class HoldemRunner {
	public static void main(String[] args) {
		PlayingTable playingTable = new PlayingTable(5, 5000);
		playingTable.dealHoleCards();
		for (int i = 1; i <= 3; i++)
			playingTable.dealCommunityCards(i);
		playingTable.printPlayingTable();
	}
}
