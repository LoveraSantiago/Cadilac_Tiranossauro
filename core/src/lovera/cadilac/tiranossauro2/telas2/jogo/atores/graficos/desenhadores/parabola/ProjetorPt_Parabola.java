package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.parabola;

import com.badlogic.gdx.math.Vector2;

final class ProjetorPt_Parabola {

    private Vector2 ptTemp;

    public final void inverterXYDoVector2(Vector2 vector2){
        this.ptTemp.x = vector2.x;
        vector2.set(vector2.y, this.ptTemp.x);
    }
}
