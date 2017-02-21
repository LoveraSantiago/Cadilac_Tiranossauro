package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.desenhador;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.Corredor2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.WrapperPosicaoJogador;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.Rotacionador;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.InformacaoManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.visuais.Digitais;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.visuais.FillerEixoCartesiano;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.AreaDaCamera;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.InformacaoUnico;

public abstract class DesenhadorGrafico implements TipoDesenhavel, Disposable{

    private static WraperShapeRenderer wShapeRenderer;
    private static InformacaoManager informacao;
    private static Rotacionador rotacionador;
    private static FillerEixoCartesiano filler;
    private static Digitais digitais;

    protected static final Vector2 pt1Desenho;
    protected static final Vector2 pt2Desenho;

    protected final Entrada2 entrada;

    protected static Corredor2 corredor;
    protected static WrapperPosicaoJogador posJog;

    static{
        pt1Desenho = new Vector2();
        pt2Desenho = new Vector2();
    }

    public DesenhadorGrafico(Entrada2 entrada) {
        wShapeRenderer = wShapeRenderer == null ? new WraperShapeRenderer()                                        : wShapeRenderer;
        informacao     = informacao     == null ? InformacaoUnico.getInstancia().getInformacaoManager()            : informacao;
        rotacionador   = rotacionador   == null ? new Rotacionador()                                               : rotacionador;
        corredor       = corredor       == null ? CorredorUnico.getInstancia().getCorredorManager().getCorredorP() : corredor;
        posJog         = posJog         == null ? corredor != null ? corredor.getWrapperPosicaoJogador() : posJog  : posJog;
        filler         = filler         == null ? new FillerEixoCartesiano()                                       : filler;
        digitais       = digitais       == null ? new Digitais()                                                  : digitais;

        this.entrada = entrada;
    }

    //********** PARTE DO SHAPE RENDERER INICIO **********
    protected final void iniciarShapeRenderer(){
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
    protected final void resetarInformacao(){
        informacao.resetarInformacao();
    }

    protected final void addInformacao(float pt1X, float pt1Y, float pt2X, float pt2Y){
        informacao.addInformacao(pt1X, pt1Y, pt2X, pt2Y);
    }
    //********** PARTE DO INFORMACAO FIM *************

    //********** PARTE DO ROTACIONADOR INICIO **********
    protected final void rotacionarEAtualizar(Vector2 pt1, Vector2 pt2){
        rotacionarEAtualizar(pt1.x, pt1.y, pt2);
    }

    protected final void rotacionarEAtualizar(float pt1x, float pt1y, Vector2 pt2){
        rotacionador.atualizarAnguloDoJogo();
        rotacionador.rotacionar(pt1x, pt1y, pt2);
    }

    protected final float getXRotacionado(){
        return rotacionador.getResultX();
    }

    protected final float getYRotacionado(){
        return rotacionador.getResultY();
    }
    //********** PARTE DO ROTACIONADOR FIM *************

    //********** PARTE DO FILLER INICIO **********
    protected final void desenharFiller(float alturaMax, float largura){
        filler.set(alturaMax, largura);
    }
    //********** PARTE DO FILLER FIM **********

    //********** PARTE DAS DIGITAIS INICIO **********
    protected void desenharDigital(){
        if(this.entrada.isToqueAcontecendo()){
            digitais.desenharDigital(this.entrada.getPtToque());
        }
    }

    protected void desenharDigitais(){
        if(this.entrada.isToqueAcontecendo()){
            digitais.desenharDigitais(this.entrada.getPtSuperior(), this.entrada.getPtLateral());
        }
    }
    //********** PARTE DAS DIGITAIS FIM **********

    public void updateAreaCamera(AreaDaCamera area){
        filler.configurarArea(area);
    }

    public final Entrada2 getEntrada() {
        return this.entrada;
    }

    @Override
    public void dispose() {
       wShapeRenderer.dispose();
    }
}
