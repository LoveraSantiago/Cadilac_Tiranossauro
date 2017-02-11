package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades;

public final class Area {
    
    private float x;
    private float y;
    private float w;
    private float h;

    public Area() {
    }

    public Area(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public float getX() {
        return x;
    }

    public Area setX(float x) {
        this.x = x;
        return this;
    }

    public float getY() {
        return y;
    }

    public Area setY(float y) {
        this.y = y;
        return this;
    }

    public float getW() {
        return w;
    }

    public Area setW(float w) {
        this.w = w;
        return this;
    }

    public float getH() {
        return h;
    }

    public Area setH(float h) {
        this.h = h;
        return this;
    }

    public Area setAll(float x, float y, float w, float h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        return this;
    }
}
