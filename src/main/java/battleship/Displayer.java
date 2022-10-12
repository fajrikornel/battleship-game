package battleship;

interface Displayer {
    void displayPlayerReport(PlayerReport player) throws IllegalAccessException;
    void displayBattlegroundReport(Battleground battleground);
}
