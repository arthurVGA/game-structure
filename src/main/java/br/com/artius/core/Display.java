package br.com.artius.core;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {
    private final Canvas canvas;

    public Display(int width, int height) {
        this.canvas = new Canvas();
        init(new Dimension(width, height));

        pack();

        canvas.createBufferStrategy(3);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void init(Dimension screenDimension) {
        setTitle("Test Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        canvas.setPreferredSize(screenDimension);
        canvas.setFocusable(false);

        add(canvas);
    }


    public void render() {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0,canvas.getWidth(), canvas.getHeight());



        graphics.dispose();
        bufferStrategy.show();
    }

}
