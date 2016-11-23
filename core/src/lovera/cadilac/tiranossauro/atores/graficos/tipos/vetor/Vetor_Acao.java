package lovera.cadilac.tiranossauro.atores.graficos.tipos.vetor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro.atores.Corredor;
import lovera.cadilac.tiranossauro.atores.graficos.equacoes.EquacaoDaReta;
import lovera.cadilac.tiranossauro.atores.graficos.utils.acoes.Acao;
import lovera.cadilac.tiranossauro.atores.graficos.utils.acoes.VelocidadeAcao;
import lovera.cadilac.tiranossauro.atores.graficos.utils.contratos.MensagemGraficos;
import lovera.cadilac.tiranossauro.atores.graficos.utils.Direcao;

final class Vetor_Acao extends Acao {

    private final EquacaoDaReta equacaoDaReta;

    public Vetor_Acao(Corredor corredor, MensagemGraficos msg) {
        super(corredor, msg);

        this.equacaoDaReta = new EquacaoDaReta();
    }

    @Override
    public void acao() {
        if(!caminhoPercorridoCompleto()){
            this.corredor.getPosicaoProjetada().set(this.corredor.getCorredorBody().getPosition());
        }
        else{
            finalizarAcao();
        }
    }

    @Override
    protected void acaoHorizontalADireita(){
        super.contador += VelocidadeAcao.PASSO * Math.min(Gdx.graphics.getDeltaTime(), 0.1f);

        calculoHorizontalADireita(super.contador, super.corredor.getPosicaoProjetada(), super.corredor.getPosicaoInicial());
        acaoHorizontalADireita_PtFuturo(super.contador);
    }

    private void acaoHorizontalADireita_PtFuturo(float contador){
        contador += Gdx.graphics.getDeltaTime();

        calculoHorizontalADireita(contador, super.corredor.getPtFuturoProj(), super.corredor.getPosicaoInicial());
    }

    private void calculoHorizontalADireita(float contador, Vector2 ptResult, Vector2 ptParametro){
        ptResult.x = ptParametro.x + contador;
        ptResult.y = this.equacaoDaReta.getY(ptParametro.x + contador);

        this.corredor.getMouseJoint().setTarget(ptResult);
    }

    @Override
    protected void acaoHorizontalAEsquerda(){
        super.contador -= VelocidadeAcao.PASSO * Math.min(Gdx.graphics.getDeltaTime(), 0.1f);

        calculoHorizontalEsquerda(super.contador, super.corredor.getPosicaoProjetada(), super.corredor.getPosicaoInicial());
        acaoHorizontalAEsquerda(super.contador);
    }

    private void acaoHorizontalAEsquerda(float contador){
        contador -= Gdx.graphics.getDeltaTime();

        calculoHorizontalEsquerda(contador, super.corredor.getPtFuturoProj(), super.corredor.getPosicaoInicial());
    }

    private void calculoHorizontalEsquerda(float contador, Vector2 ptResult, Vector2 ptParametro){
        ptResult.x = ptParametro.x + contador;
        ptResult.y = this.equacaoDaReta.getY(ptParametro.x + contador);

        this.corredor.getMouseJoint().setTarget(ptResult);
    }

    @Override
    protected void acaoVerticalADireita() {
        acaoVerticalEmComum();
    }

    @Override
    protected void acaoVerticalAEsquerda() {
        acaoVerticalEmComum();
    }

    private void acaoVerticalEmComum(){
        super.contador += VelocidadeAcao.PASSO * Math.min(Gdx.graphics.getDeltaTime(), 0.1f);

        calculoVerticalEmComum(super.contador, super.corredor.getPosicaoProjetada(), super.corredor.getPosicaoInicial());
        acaoVerticalEmComum_PtFuturo(super.contador);
    }

    private void acaoVerticalEmComum_PtFuturo(float contador){
        contador += Gdx.graphics.getDeltaTime();

        calculoVerticalEmComum(contador, super.corredor.getPtFuturoProj(), super.corredor.getPosicaoInicial());
    }

    private void calculoVerticalEmComum(float contador, Vector2 ptResult, Vector2 ptParametro){
        ptResult.x = this.equacaoDaReta.getX(ptParametro.y + contador);
        ptResult.y = ptParametro.y + contador;

        this.corredor.getMouseJoint().setTarget(ptResult);
    }

    public void setPosicaoFinal(float x, float y) {
        super.posicaoFinal.set(x, y);
    }

    public void calcularPontoFinal(){
        definirEquacaoDaReta();
        definirLado();
        analiseDeEixo();
    }

    private void definirEquacaoDaReta(){
        this.equacaoDaReta.definirEquacaoDaReta(super.corredor.getPosicaoProjX(), super.corredor.getPosicaoProjY(),
                super.posicaoFinal.x, super.posicaoFinal.y);
    }

    private void definirLado(){
        setLado(super.posicaoFinal.x < super.corredor.getPosicaoProjX() ? Direcao.ESQUERDA : Direcao.DIREITA);
    }

    @Override
    protected void analiseDeEixo(){
        if(getLado() == Direcao.DIREITA){
            analiseEixoCasoDireita();
        }
        else{
            analiseEixoCasoEsquerda();
        }
    }
}
