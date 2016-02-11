public class Battleground {

    public static final int FIELD_SIZE = 10;
    private byte[][] field;
    private int[][] shipMarker;

    private final int SHIPS_COUNT = 5;

    public Battleground()
    {
        field = new byte[FIELD_SIZE][FIELD_SIZE];
        shipMarker = new int[FIELD_SIZE][FIELD_SIZE];
        setShipsOnTable();
    }

    public void setShipsOnTable()
    {
        char tempDir = 'V';
        int[] iShi = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1 };
        for(int i = 0; i < iShi.length; i++) {
            if(MainClass.rand.nextInt(2) == 0)
                tempDir = 'V';
            else
                tempDir = 'H';
            int x, y;
            do {
                x = MainClass.rand.nextInt(FIELD_SIZE);
                y = MainClass.rand.nextInt(FIELD_SIZE);
            } while (!setShip(x, y, tempDir, iShi[i], i + 1));
        }
    }

    public boolean isShipAlive(int _number)
    {
        for(int i=0;i<FIELD_SIZE;i++)
            for(int j=0;j<FIELD_SIZE;j++)
                if(shipMarker[i][j] == _number) return true;
        return false;
    }

    public boolean setShip(int _x, int _y, char _dir, int _size, int _number)
    {
        int vx = 0, vy = 0;
        if  (_dir == 'H')
            vx = 1;
        else
            vy = 1;

        if(_x + _size * vx > FIELD_SIZE - 1) return false;
        if(_y + _size * vy > FIELD_SIZE - 1) return false;

        for (int i = 0; i < _size; i++)
            if(!freeWater(_x + i * vx, _y + i * vy)) return false;

        for (int i = 0; i < _size; i++) {
            field[_y + i * vy][_x + i * vx] = 1;
            shipMarker[_y + i * vy][_x + i * vx] = _number;
        }

        return true;
    }

    public boolean freeWater(int _x, int _y)
    {
        for(int i = _y - 1; i <= _y + 1;i++)
            for(int j = _x - 1; j <= _x + 1; j++)
            {
                if(i >= 0 && j >= 0 && i < FIELD_SIZE && j < FIELD_SIZE)
                    if(field[i][j] == 1) return false;
            }
        return true;
    }

    public boolean strikeThis(int _x, int _y)
    {
        int n;
        if(field[_y][_x] > 0)
        {
            n = shipMarker[_y][_x];
            field[_y][_x] = 0;
            shipMarker[_y][_x] = 0;
            if(isShipAlive(n))
                System.out.println("Блок корабля разрушен");
            else
                System.out.println("Корабль уничтожен");
            return true;
        }
        else {
            System.out.println("Промах!");
            return false;
        }
    }

    public void showField()
    {
        for(int i = 0;i<FIELD_SIZE;i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                System.out.print(field[i][j] + "  ");
            }
            System.out.print('\n');
        }
        System.out.print('\n');
    }

    public boolean isGameContinue()
    {
        for(int i=0;i<FIELD_SIZE;i++)
            for(int j=0;j<FIELD_SIZE;j++)
            {
                if(field[i][j] == 1) return true;
            }
        return false;
    }

    public void showMarkers()
    {
        for(int i = 0;i<FIELD_SIZE;i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                System.out.print(shipMarker[i][j] + "  ");
            }
            System.out.print('\n');
        }
        System.out.print('\n');
    }
}
