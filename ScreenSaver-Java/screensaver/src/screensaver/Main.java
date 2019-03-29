package screensaver;
import java.awt.EventQueue;


public class Main {
    private Circle[] circles;
    private Frame frame;
    public Main(int swidth, int sheight, int N){
        circles=new Circle[N];
        int radius=50;
        for(int i = 0 ; i < N ; i ++){
            int x = (int)(Math.random()*(swidth-2*radius)) + radius;
            int y = (int)(Math.random()*(sheight-2*radius)) + radius;
            int vx = (int)(Math.random()*11) - 5;
            int vy = (int)(Math.random()*11) - 5;
            circles[i] = new Circle(x, y, radius, vx, vy);
        }
        EventQueue.invokeLater(() -> {
            frame = new Frame("气泡屏保", swidth, sheight);
            new Thread(() -> run()).start();
        });
    }
    private void run(){

        while(true){
            frame.render(circles);
            Operation.threadPause(10);
            for(Circle circle : circles)
                circle.move(0, 0, frame.getWidth(), frame.getHeight(),circles);
        }
    }

    public static void main(String[] args) {

        int Width = 800;
        int Height = 800;
        int N = 5;
        Main a  = new Main(Width, Height, N);

    }

}

