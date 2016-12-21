package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao;

public final class Distancia {

    private float espacoPercorrido;

    public void addEspacoPercorrido(float pt1X, float pt1Y, float pt2X, float pt2Y){
        System.out.println("incremento " + ((float) (Math.sqrt(Math.pow(pt2X - pt1X, 2) + Math.pow(pt2Y - pt1Y, 2)))));
        this.espacoPercorrido += (float) (Math.sqrt(Math.pow(pt2X - pt1X, 2) + Math.pow(pt2Y - pt1Y, 2)));
    }

    public void resetEspacoPercorrido(){
        this.espacoPercorrido = 0f;
    }

    public Float getEspacoPercorrido() {
        return espacoPercorrido;
    }
}
