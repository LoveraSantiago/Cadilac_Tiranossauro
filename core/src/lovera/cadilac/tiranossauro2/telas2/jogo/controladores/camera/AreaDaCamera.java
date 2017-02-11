package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera;

public final class AreaDaCamera {
    
    private float x;
    private float y;
    private float w;
    private float h;

    public AreaDaCamera() {
    }

    public AreaDaCamera setAll(float x, float y, float w, float h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        return this;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getW() {
        return w;
    }

    public float getH() {
        return h;
    }
}
