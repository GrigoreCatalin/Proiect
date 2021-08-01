import java.lang.Math;

public class PlayerStatus {
    private final String nickName;
    private int score;
    private int lives;
    private int health;
    private String weaponInHand;
    private double positionX;
    private double positionY;
    public static String gameName;

    //Comportament
    public PlayerStatus(String nickname, int lives, int score) {
        this(nickname, lives);
        this.score = score;
    }

    public PlayerStatus(String nickName, int lives) {
        this(nickName);
        this.lives = lives;
    }

    public PlayerStatus(String nickname) {
        this.nickName = nickname;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void artifactCode(int artiFactCode) {
        if (isPerfectNumber(artiFactCode)) {
            this.score += 5000;
            ++this.lives;
            this.health = 100;
        } else if (isPrimeNumber(artiFactCode) && artiFactCode != 1) {
            this.score += 1000;
            this.health += 25;
            livesForPrimeNumber(this.health);
        } else if (isEvenNumber(artiFactCode) && isDiv(artiFactCode)) {
            this.score -= 3000;
            this.health -= 25;
            healthForEvenNumber(this.health);
        } else {
            this.score += artiFactCode;
        }
        System.out.println("Score player: " + this.score + "\n" + "Live player: " + this.lives + "\n" + "Health player: " + this.health);
    }

    public boolean setWeaponInHand(String weaponInHand) {
        int priceKnife = 1000;
        int priceSniper = 10000;
        int priceKalashnikov = 20000;

        if (this.lives == 0) {
            return false;
        }

        if (weaponInHand.equals("knife") && (this.score >= priceKnife)) {
            this.weaponInHand = weaponInHand;
            this.score -= priceKnife;
            return true;
        } else if (weaponInHand.equals("sniper") && (this.score >= priceSniper)) {
            this.weaponInHand = weaponInHand;
            this.score -= priceSniper;
            return true;
        } else if (weaponInHand.equals("kalashnikov") && (this.score >= priceKalashnikov)) {
            this.weaponInHand = weaponInHand;
            this.score -= priceKalashnikov;
            return true;
        } else {
            return false;
        }
    }

    public void weaponAndUpdateScore() {
        System.out.println("Weapon: " + this.weaponInHand + "\n" + "Update score: " + this.score);
    }

    public String getWeaponInHand() {
        return this.weaponInHand;
    }

    public double getPositionX() {
        return this.positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return this.positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public static String getGameName() {
        return gameName;
    }

    static void setGameName(String gameName) {
        PlayerStatus.gameName = gameName;
    }

    public void movePlayerTo(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public String getNickName() {
        return nickName;
    }

    public boolean shouldAttackOpponent(PlayerStatus opponent) {

        if ((this.lives == 0) && (opponent.lives == 0)) {
            System.out.println("Both players are dead");
            return false;
        } else if (this.lives == 0) {
            System.out.println("The winner is playerTwo");
            return false;
        } else if (opponent.lives == 0) {
            System.out.println("The winner is playerOne");
            return false;
        }

        if ((this.weaponInHand == null) || (opponent.weaponInHand == null)){
            System.out.println("The weapon can't be changed.");
            return false;
        }

        if (this.weaponInHand.equals(opponent.weaponInHand)) {
            if (this.getProbability() >= opponent.getProbability()) {
                System.out.println("\n" + "The winner is playerOne");
                return true;
            } else {
                System.out.println("\n" + "The winner is playerTwo");
                return false;
            }
        } else if (getDistance(opponent) > 1000) {
            if (this.weaponInHand.equals("sniper")) {
                System.out.println("\n" + "The winner is playerOne");
                return true;
            } else if (this.weaponInHand.equals("kalashnikov") && opponent.weaponInHand.equals("knife")) {
                System.out.println("\n" + "The winner is playerOne");
                return true;
            } else {
                System.out.println("\n" + "The winner is playerTwo");
                return false;
            }
        } else {
            if (this.weaponInHand.equals("kalashnikov")) {
                System.out.println("\n" + "The winner is playerOne");
                return true;
            } else if (this.weaponInHand.equals("sniper") && opponent.weaponInHand.equals("knife")) {
                System.out.println("\n" + "The winner is playerOne");
                return true;
            } else {
                System.out.println("\n" + "The winner is playerTwo");
                return false;
            }
        }
    }

    //Reguli de joc
    private double getProbability() {
        return (3 * this.health + this.score / 1000) / 4;

    }

    private double getDistance(PlayerStatus opponent) {
        double distance = Math.sqrt(Math.pow((this.positionX - opponent.positionX),2) + Math.pow((this.positionY - opponent.positionY),2));
        System.out.println("Distance: " + distance);
        return distance;
    }

    private void livesForPrimeNumber(int health) {
        if (health > 100) {
            this.health = 100;
        } else {
            this.lives += 2;
        }
    }

    private void healthForEvenNumber(int health) {
        if (health <= 0) {
            --this.lives;
            this.health = 100;
        }
        if (this.lives == 0) {
            this.health = 0;
            System.out.println("\n" + "Game over" + "\n");
        }
    }

    //Metode:
    //Metoda numar perfect
    private boolean isPerfectNumber(int number) {
        int sum = 0;
        for (int i = 1; i < number; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        return sum == number;
    }

    //Metoda numar prim
    private boolean isPrimeNumber(int number) {
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    //Metoda numar par
    private boolean isEvenNumber(int number) {
        return number % 2 == 0;
    }

    //Metoda suma cifrelor divizibila cu 3
    private boolean isDiv(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum % 3 == 0;
    }
}