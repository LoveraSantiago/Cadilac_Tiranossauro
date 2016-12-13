package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;


import com.badlogic.gdx.math.Vector2;

class CalculadorDistancia {

    public float calcularDistancia(float pt1X, float pt1Y, float pt2X, float pt2Y){
        return (float) (Math.sqrt(Math.pow(pt2X - pt1X, 2) + Math.pow(pt2Y - pt1Y, 2)));
    }

}
