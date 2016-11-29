package lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.menu_graficos;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;

class VolanteControle implements TipoSingleton{

    private static VolanteControle controle;

    private float larguraPonteiro;
    private float metadePonteiro;
    private float ptXinicioPonteiro;
    private final Vector2 ptMedioBarra;

    public VolanteControle() {
        this.ptMedioBarra = new Vector2();
    }

    @Override
    public void inicializar() {
        controle = this;
    }

    public static VolanteControle getInstancia() {
        return controle;
    }

    public void calcularPontos(float x, float y, float width){
        this.larguraPonteiro = width / 8;
        this.metadePonteiro  = this.larguraPonteiro / 2;
        this.ptXinicioPonteiro = (x + (width / 2)) - this.metadePonteiro;
        this.ptMedioBarra.set(this.ptXinicioPonteiro + this.metadePonteiro, y);
    }

    public float getPtXinicioPonteiro() {
        return ptXinicioPonteiro;
    }

    public float getMetadePonteiro() {
        return metadePonteiro;
    }

    public float getLarguraPonteiro() {
        return larguraPonteiro;
    }

    public Vector2 getPtMedioBarra() {
        return ptMedioBarra;
    }
}
