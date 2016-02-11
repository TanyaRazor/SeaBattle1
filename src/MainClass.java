import java.util.Random;

public class MainClass {

    public static Random rand = new Random();
    public static Battleground bgPlayer;
    public static Battleground bgEnemy;

    public static void main(String[] args) {
        bgEnemy = new Battleground();
        bgPlayer = new Battleground();
        Player pl = new Player(bgEnemy);

        bgPlayer.showField();
        pl.showShootMap();

        while(bgEnemy.isGameContinue() && bgPlayer.isGameContinue())
        {
            while(pl.fire()) {
                pl.showShootMap();
            }
            AIturn();
        }
    }

    public static void AIturn()
    {
        int x, y;
        do {
            x=rand.nextInt(Battleground.FIELD_SIZE);
            y=rand.nextInt(Battleground.FIELD_SIZE);
            System.out.printf("Враг стрельнул в точку %d:%d\n",x,y );
        } while(bgPlayer.strikeThis(x, y));
    }
}

