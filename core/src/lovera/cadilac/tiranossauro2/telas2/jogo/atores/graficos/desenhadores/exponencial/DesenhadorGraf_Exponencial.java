package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.exponencial;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.Corredor2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.Arredondador;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes.EquacaoExponencial2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.Informacao;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.DirecaoEnum;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.InformacaoManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;

//LINK DE AJUDA: https://www.desmos.com/calculator/3fisjexbvp
public class DesenhadorGraf_Exponencial implements TipoDesenhavel, Disposable{

    private DirecaoEnum lado;

    private float helperContador;
    private float contador;

    private final ShapeRenderer shapeRenderer;
    private final Matrix4 matrizCameraProjecao;

    private final EquacaoExponencial2 eqExponencial;
    private final Entrada2 entrada2;
    private final Informacao informacao;
    private final Arredondador arredondador;
    private final CameraManager cameraManager;
    private final ProjetorPt_Exponencial projetorPt;
    private final Corredor2 corredorP;

    private final Vector2 ptToque;
    private final Vector2 posicaoCorredor;

    private final Vector2 pt1Desenho;
    private final Vector2 pt2Desenho;

    public DesenhadorGraf_Exponencial(Entrada2 entrada2) {
        this.entrada2 = entrada2;
        this.informacao = InformacaoManager.getInstancia().getInformacao();
        this.arredondador = new Arredondador();
        this.corredorP = CorredorManager.getInstancia().getCorredorP();
        this.posicaoCorredor = this.corredorP.getPosicaoJogo();
        this.cameraManager = CameraUnico.getCameraManager();
        this.matrizCameraProjecao = this.cameraManager.getCameraProjecao().combined;

        this.shapeRenderer = new ShapeRenderer();

        this.eqExponencial = new EquacaoExponencial2();
        this.projetorPt = new ProjetorPt_Exponencial();
        this.ptToque = new Vector2();
        this.pt1Desenho = new Vector2();
        this.pt2Desenho = new Vector2();
    }

    @Override
    public void meDesenhar(Object objeto) {
        resetarComponentes();
        definirDirecao();
        desenharExponencial();
    }

    private void resetarComponentes(){
        this.informacao.resetarInformacao();

        this.ptToque.set(this.entrada2.getPtToque());
//        this.ptToque.set(15.851f, 1.333f);
    }

    private void definirDirecao() {
        this.lado = this.posicaoCorredor.x < this.ptToque.x ? DirecaoEnum.DIREITA : DirecaoEnum.ESQUERDA;
    }

    private void desenharExponencial() {
        this.shapeRenderer.setProjectionMatrix(this.matrizCameraProjecao);
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        this.shapeRenderer.setColor(1.0f, 0.4f, 0f, 0f);

        Gdx.gl.glLineWidth(60);

        this.eqExponencial.setB(this.posicaoCorredor.y - this.ptToque.y);
        this.pt1Desenho.set(this.posicaoCorredor);

        if(this.lado == DirecaoEnum.DIREITA){
            procedimentoADireita();
        }
        else{
            procedimentoAEsquerda();
        }
        this.shapeRenderer.end();
    }

    private void procedimentoADireita() {
        this.helperContador = getProporcaoDoGraficoPeloToque();

        for(this.contador = 1;
            this.contador <= this.helperContador;
            this.contador = this.contador + .1f) {

            this.pt2Desenho.set(this.posicaoCorredor.x + this.contador, this.posicaoCorredor.y + (this.eqExponencial.getY(this.contador)));

            this.shapeRenderer.line(this.pt1Desenho.x, this.pt1Desenho.y, this.pt2Desenho.x, this.pt2Desenho.y);

            addToComponentes(this.pt1Desenho.x, this.pt1Desenho.y, this.pt2Desenho.x, this.pt2Desenho.y);

            this.pt1Desenho.set(this.pt2Desenho);
        }
        this.corredorP.setPtFuturoProj(this.projetorPt.calcularPtFuturoDireita_Horizontal(this.eqExponencial, 1, this.posicaoCorredor));//8 valor ajustado olhometro
    }

    private void procedimentoAEsquerda() {
        this.ptToque.x = this.projetorPt.espelharEsquerdaPDireita(this.ptToque.x, this.posicaoCorredor.x);
        this.helperContador = getProporcaoDoGraficoPeloToque();

        for(this.contador = 1;
            this.contador <= this.helperContador;
            this.contador = this.contador + .1f) {

            this.pt2Desenho.set(this.posicaoCorredor.x + this.contador, (this.posicaoCorredor.y + (this.eqExponencial.getY(this.contador))));
            this.pt2Desenho.x = this.projetorPt.espelharEsquerdaPDireita(this.pt2Desenho.x, this.posicaoCorredor.x);

            this.shapeRenderer.line(this.pt1Desenho.x, this.pt1Desenho.y, this.pt2Desenho.x, this.pt2Desenho.y);

            addToComponentes(this.pt1Desenho.x, this.pt1Desenho.y, this.pt2Desenho.x, this.pt2Desenho.y);

            this.pt1Desenho.set(this.pt2Desenho);
        }
        this.corredorP.setPtFuturoProj(this.projetorPt.calcularPtFuturoEsquerda_Horizontal(this.eqExponencial, 1, this.posicaoCorredor));
    }

    private float getProporcaoDoGraficoPeloToque(){
        //(Tamanho do espaco horizontal entre o toque e a posicao do jogador) * (Tamanho do espaco horizontal entre o topo da pela e a posicao y do jogador) /100
        //dessa forma pega o tamanho proporcional
        return this.eqExponencial.getX((this.ptToque.x - this.posicaoCorredor.x) * (this.cameraManager.getMaiorPtYDaCameraProjecao() - this.posicaoCorredor.y) / 10);
    }

    private void addToComponentes(float pt1X, float pt1Y, float pt2X, float pt2Y){
        this.informacao.addInformacao(pt1X, pt1Y, pt2X, pt2Y);
    }

    @Override
    public void dispose() {
        this.shapeRenderer.dispose();
    }
}
