package screensaver;

public class Circle {

    public int x, y;
    private int r;
    public int vx, vy;

    public Circle(int x, int y, int r, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx;
        this.vy = vy;
    }

    public int getradius() {
        return r;
    }

    public void move(int minx, int miny, int maxx, int maxy, Circle[] circles) {
        x += vx;
        y += vy;
        checkCollision(minx, miny, maxx, maxy);
        for (int i = 0; i < circles.length; i++) {
            chekcollisionball(circles[i]);
        }
    }

    private void checkCollision(int minx, int miny, int maxx, int maxy) {
        if (x - r < minx) {
            x = r;
            vx = -vx;
        }
        else if (x + r >= maxx) {
            x = maxx - r;
            vx = -vx;
        }
        else if (y - r < miny) {
            y = r;
            vy = -vy;
        }
        else if (y + r >= maxy) {
            y = maxy - r;
            vy = -vy;
        }
    }

    private void chekcollisionball(Circle c) {
        if (((x - c.x) * (x - c.x) + (y - c.y) * (y - c.y)) <= r * r) {
            vx = -vx;
            vy = -vy;
            c.vx = -c.vx;
            c.vy = -c.vy;
        }
    }
}
