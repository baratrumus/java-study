package multithread.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * @author Ilya Ivannikov (voldores@mail.ru)
 * @version $Id$
 * @since 0.1
 */

public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private double delta = 5;
    double valueX;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            valueX = this.rect.getX();
            if (valueX > PingPong.LIMITX - delta * 4) {
               delta *= -1;
            }
            if (valueX < 0) {
                delta *= -1;
            }
            this.rect.setX(valueX + delta);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }

        }
    }
}
