package lovera.cadilac.tiranossauro.atores.graficos.utils.acoes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import lovera.cadilac.tiranossauro.atores.Corredor;
import lovera.cadilac.tiranossauro.atores.graficos.utils.Direcao;
import lovera.cadilac.tiranossauro.atores.graficos.utils.contratos.MensagemGraficos;
import lovera.cadilac.tiranossauro.controladores.Fase;

public abstract class Acao {

    private Direcao eixo;
    private Direcao lado;

    protected float contador;

    protected final Corredor corredor;
    
    protected final Vector2 posicaoFinal;
    protected Vector2 ptToque;

    private final MensagemGraficos msg;

    public Acao(Corredor corredor, MensagemGraficos msg) {
        this.corredor = corredor;
        this.msg = msg;

        this.posicaoFinal = new Vector2();
    }

    public void acao(){
        if(!caminhoPercorridoCompleto()){
            percorrerCaminho();
        }
        else{
            finalizarAcao();
        }
    }

    private void percorrerCaminho(){
        if(this.eixo == Direcao.HORIZONTAL){

            if(this.lado == Direcao.ESQUERDA){
                acaoHorizontalAEsquerda();
            }
            else {
                acaoHorizontalADireita();
            }
        }
        else {

            if(this.lado == Direcao.ESQUERDA){
                acaoVerticalAEsquerda();
            }
            else{
                acaoVerticalADireita();
            }
        }
    }

    abstract protected void acaoHorizontalADireita();
    abstract protected void acaoHorizontalAEsquerda();
    abstract protected void acaoVerticalADireita();
    abstract protected void acaoVerticalAEsquerda();
    abstract protected void calcularPontoFinal();

    protected void finalizarAcao(){
        this.corredor.setPosicaoProj(this.posicaoFinal.x, this.posicaoFinal.y);
        this.msg.setFaseToFaseManager(Fase.CALCULAR_VOLTA);
        this.contador = 0;

        this.corredor.destruirMouseJoint();
    }

    protected boolean caminhoPercorridoCompleto() {
        return this.corredor.getPosicaoProjY() >= this.posicaoFinal.y;
    }

    protected  void analiseDeEixo(){
        analiseEixoCasoDireita();
    }

    protected void analiseEixoCasoDireita(){
        this.eixo = this.posicaoFinal.x - this.corredor.getPosicaoProjX() > this.posicaoFinal.y - this.corredor.getPosicaoProjY() ?
                Direcao.HORIZONTAL : Direcao.VERTICAL;
    }

    protected void analiseEixoCasoEsquerda(){
        this.eixo = this.corredor.getPosicaoProjX() - this.posicaoFinal.x > this.posicaoFinal.y - this.corredor.getPosicaoProjY() ?
                Direcao.HORIZONTAL : Direcao.VERTICAL;
    }

    public void setPtToque(Vector2 ptToque){
        this.ptToque = ptToque;
    }

    public void setPosicaoFinal(Vector2 posicaoFinal){
        this.posicaoFinal.set(posicaoFinal.x, posicaoFinal.y);
    }

    protected void setEixo(Direcao eixo){
        this.eixo = eixo;
    }

    protected Direcao getEixo(){
        return this.eixo;
    }

    public  void setLado(Direcao lado){
        this.lado = lado;
    }

    protected Direcao getLado(){
        return this.lado;
    }

    protected Vector3 getPosicao_CameraProjecao(){
        return this.corredor.getCameraManipulador().getCameraProjecao().position;
    }

    protected Vector3 getPosicao_CameraJogo(){
        return this.corredor.getCameraManipulador().getCameraJogo().position;
    }
}
