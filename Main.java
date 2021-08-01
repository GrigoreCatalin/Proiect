public class Main {
    public static void main(String[] args) {

        PlayerStatus.setGameName(" ----- Combat weapons -----");
        System.out.println(PlayerStatus.getGameName());

        PlayerStatus playerOne = new PlayerStatus("Catalin", 2, 40000);
        playerOne.setHealth(22);
        playerOne.artifactCode(4);
        playerOne.setWeaponInHand("sniper");
        playerOne.weaponAndUpdateScore();
        playerOne.setPositionX(12.5);
        playerOne.setPositionY(11.5);
        playerOne.movePlayerTo(6.2, 9.1);

        System.out.println(" ");

        PlayerStatus playerTwo = new PlayerStatus("Mihai", 2, 50000);
        playerTwo.setHealth(20);
        playerTwo.artifactCode(24);
        playerTwo.setWeaponInHand("knife");
        playerTwo.weaponAndUpdateScore();
        playerTwo.setPositionX(15.5);
        playerTwo.setPositionY(14.5);
        playerTwo.movePlayerTo(8.2, 16.1);

        playerOne.shouldAttackOpponent(playerTwo);
    }
}
