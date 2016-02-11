import java.util.Scanner;

public class Player {

    Scanner sc = new Scanner(System.in);
    char[][] shootMas = new char[Battleground.FIELD_SIZE][Battleground.FIELD_SIZE];
    Battleground oppositeBG;

    public Player(Battleground _opBG)
    {
        oppositeBG = _opBG;
        for(int i = 0;i<Battleground.FIELD_SIZE;i++)
            for (int j = 0; j < Battleground.FIELD_SIZE; j++) {
                shootMas[i][j] = '•';
            }
    }

    public boolean fire()
    {
        System.out.println("Введите координаты выстрела (format: x y)");
        int x, y;
        x = sc.nextInt() - 1;
        y = sc.nextInt() - 1;
        if(oppositeBG.strikeThis(x, y)) {
            shootMas[y][x] = 'X';
            return true;
        }
        else {
            shootMas[y][x] = 'O';
            return false;
        }

    }

    public void showShootMap()
    {
        System.out.println("   1  2  3  4  5  6  7  8  9  10");
        for(int i = 0;i<Battleground.FIELD_SIZE;i++) {
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < Battleground.FIELD_SIZE; j++) {
                System.out.print(shootMas[i][j] + "  ");
            }
            System.out.print('\n');
        }
        System.out.print('\n');
    }

}
