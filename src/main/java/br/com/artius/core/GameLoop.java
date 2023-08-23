package br.com.artius.core;

public class GameLoop implements Runnable {
    private static final double UPDATE_PER_SECOND = 1d / 60d;
    private long nextStatTime;
    public long fps;
    public long ups;
    private final Game game;

    public GameLoop(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        boolean isRunning = true;
        long lastUpdate = System.currentTimeMillis();
        long currentTime;
        double acumulator = 0;
        nextStatTime = System.currentTimeMillis() + 1000;
        while(isRunning) {
            currentTime = System.currentTimeMillis();
            acumulator += ((currentTime - lastUpdate) / 1000d);
            lastUpdate = currentTime;

            if(acumulator >= UPDATE_PER_SECOND) {
                while (acumulator >= UPDATE_PER_SECOND) {
                    update();
                    acumulator -= UPDATE_PER_SECOND;
                }
                render();
            }
            printFPS();
        }
    }

    private void printFPS() {
        if(System.currentTimeMillis() > nextStatTime) {
            System.out.printf("FPS: %d | UPS: %d %n", fps, ups);
            fps = 0;
            ups = 0;
            nextStatTime = System.currentTimeMillis() + 1000;
        }
    }


    public void update() {
        game.update();
        ups++;
    }

    public void render(){
        game.render();
        fps++;

    }
}
