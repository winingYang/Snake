package ui;

import game.GameManager;
import game.Point;
import game.Snake;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public final class View extends JPanel {

    private final GameManager manager = GameManager.getINSTANCE();

    public View() {
        super();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                turnByMouse(e);
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                turnByMouse(e);
            }
        });
    }

    private void turnByMouse(MouseEvent e) {
        // 只有一个蛇时，点击鼠标左键，蛇向鼠标所在位置移动
        Snake snake = manager.isOneSnake();
        if (snake != null) {
            Point head = snake.getHead();
            Point target = new Point(e.getX() / (double) Point.MULTIPLE, e.getY() / (double) Point.MULTIPLE);
            double newAngle = head.getAngle(target);
            snake.setAngle(newAngle);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // 画背景
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, Point.DRAW_SIZE_X, Point.DRAW_SIZE_Y);
        manager.onDraw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Point.DRAW_SIZE_X, Point.DRAW_SIZE_Y);
    }
}
