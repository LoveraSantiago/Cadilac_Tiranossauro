package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.desenhador;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.InformacaoManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.InformacaoUnico;

public abstract class DesenhadorGrafico implements TipoDesenhavel, Disposable{

    private static WraperShapeRenderer wShapeRenderer;

    private static InformacaoManager informacao;

    protected static final Vector2 pt1Desenho;
    protected static final Vector2 pt2Desenho;

    static{
        pt1Desenho = new Vector2();
        pt2Desenho = new Vector2();
    }

    public DesenhadorGrafico() {
        if(wShapeRenderer == null){
            wShapeRenderer = new WraperShapeRenderer();
        }
        if(informacao == null){
            informacao = InformacaoUnico.getInstancia().getInformacaoManager();
        }
    }

    public abstract Entrada2 getEntrada();

    //********** PARTE DO SHAPE RENDERER INICIO **********
    protected void iniciarShapeRenderer(){
        wShapeRenderer.iniciarShapeRenderer();
    }

    protected final void addLinhaToShapeRenderer(Vector2 pt1, float x2, float y2){
        addLinhaToShapeRenderer(pt1.x, pt1.y, x2, y2);
    }

    protected final void addLinhaToShapeRenderer(Vector2 pt1, Vector2 pt2){
        addLinhaToShapeRenderer(pt1.x, pt1.y, pt2.x, pt2.y);
    }

    protected final void addLinhaToShapeRenderer(float x1, float y1, float x2, float y2){
        wShapeRenderer.addLinhaToShapeRenderer(x1, y1, x2, y2);
    }

    protected final void fecharShapeRenderer(){
        wShapeRenderer.fecharShapeRenderer();
    }
    //********** PARTE DO SHAPE RENDERER FIM ************

    //********** PARTE DO INFORMACAO INICIO **********
    protected void resetarInformacao(){
        informacao.resetarInformacao();
    }

    public void addInformacao(float pt1X, float pt1Y, float pt2X, float pt2Y){
        informacao.addInformacao(pt1X, pt1Y, pt2X, pt2Y);
    }
    //********** PARTE DO INFORMACAO FIM *************
    @Override
    public void dispose() {
       wShapeRenderer.dispose();
    }
}
