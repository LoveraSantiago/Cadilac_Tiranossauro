package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;

import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgFromColisao;
import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgFromMovimentador;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.NormatizadorDeAngulos;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.GraficosEnum2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.GraficoUnico;

final class CalculadorAngulo2 implements MsgFromMovimentador, MsgFromColisao {

    private float anguloCorredorGraus;
    private float anguloCalculado;
    private float anguloNorte;
    private float impulsoAngularPositivo;
    private float impulsoAngularNegativo;

    private final Body corredor;

    private final NormatizadorDeAngulos normatizador;

    private HelperRotacionador rotacaoMovimentoAtual;
    private final HelperRotacionador rotacaoComColisao;
    private final HelperRotacionador rotacaoSemColisao;

    public CalculadorAngulo2(Body corredor) {
        this.corredor = corredor;

        this.anguloNorte = 90;
        resetAngulo();

        this.normatizador = new NormatizadorDeAngulos();

        this.rotacaoComColisao = new RotacaoComColisao();
        this.rotacaoSemColisao = new RotacaoSemColisao();
        this.rotacaoMovimentoAtual = this.rotacaoSemColisao;

        resetImpulsosAngulares();
    }

    public void rotacionarEmMovimento(){
        this.rotacaoMovimentoAtual.rotacionar();
    }

    public void rotacionarParado() {
        rotacionar();
    }

    private void rotacionar(){
//        printagemDbg("ROTACIONAR PARADO INICIO");
        normatizarComponentes();
        if(isMesmoAngulo()){
            this.corredor.setAngularVelocity(0);
        }
        else{
            if((this.anguloCalculado - getAnguloCorredor_Graus() + 360) % 360 < 180){
                if(this.corredor.getAngularVelocity() <= 0){
                    this.corredor.applyAngularImpulse(this.impulsoAngularPositivo, true);
                }
            }
            else{
                if(this.corredor.getAngularVelocity() >= 0){
                    this.corredor.applyAngularImpulse(this.impulsoAngularNegativo, true);
                }
            }
        }
//        printagemDbg("ROTACIONAR PARADO FIM");
    }

    @Override
    public void colisaoAconteceu() {
        this.rotacaoMovimentoAtual = this.rotacaoComColisao;
    }

    @Override
    public void setPtFuturoProj(float x, float y) {
        throw new UnsupportedOperationException("CalculadorAngulo nao seta ponto futuro");
    }

    @Override
    public void movimentacaoEncerrada() {
        this.rotacaoMovimentoAtual = this.rotacaoSemColisao;
        resetImpulsosAngulares();
    }

    public void calcularAngulo(float ptFuturoX, float ptFuturoY){
//        printagemDbg("CALCULAR ANGULO INICIO");
        this.anguloCalculado = (float) (Math.atan2(ptFuturoY - this.corredor.getPosition().y, ptFuturoX - this.corredor.getPosition().x)) * MathUtils.radiansToDegrees;
        this.anguloCalculado = Math.round(this.anguloCalculado);

//        printagemDbg("CALCULAR ANGULO FIM");
        normatizarComponentes();
    }

    public void telaAngulada(float angulo) {
//        printagemDbg("TELA ANGULADA INICIO");
        this.anguloNorte += angulo;
        this.anguloNorte = this.normatizador.normatizar(this.anguloNorte);
        resetAngulo();
//        printagemDbg("TELA ANGULADA FIM");
        normatizarComponentes();
    }

    public void prepararParaAcao(){
        normatizarComponentes();
        definirImpulsosAngulares();
    }

    private void definirImpulsosAngulares(){
        if(GraficoUnico.getInstancia().getGraficoManager2().isGraficoEnumAtual(GraficosEnum2.PARABOLOIDE)){
            this.impulsoAngularNegativo = -25f;
            this.impulsoAngularPositivo = +25f;
        }
        else{
            resetImpulsosAngulares();
        }
    }

    private boolean isMesmoAngulo(){
        this.anguloCorredorGraus = getAnguloCorredor_Graus();
        return this.normatizador.normatizar(Math.round(this.anguloCalculado)) == this.normatizador.normatizar(this.anguloCorredorGraus);
    }

    private void resetImpulsosAngulares(){
        this.impulsoAngularNegativo = -10f;
        this.impulsoAngularPositivo = +10f;
    }

    public void resetAngulo() {
        this.anguloCalculado = this.anguloNorte;
    }

    private void normatizarComponentes(){
        this.corredor.setTransform(this.corredor.getPosition(), this.normatizador.normatizar(getAnguloCorredor_Graus()) * MathUtils.degreesToRadians);
        this.anguloCalculado = this.normatizador.normatizar(this.anguloCalculado);
//        printagemDbg("COMPONENTES NORMATIZADOS");
    }

    private int getAnguloCorredor_Graus(){
        return Math.round(this.corredor.getAngle() * MathUtils.radiansToDegrees);
    }

    //TODO: Apagar qdo tiver seguranca que esta funcionando normalmente
//    private void printagemDbg(String texto){
//        System.out.println("*****" + texto + "*****");
//        System.out.println("angulo norte      : " + this.anguloNorte);
//        System.out.println("angulo calculado  : " + this.anguloCalculado);
//        System.out.println("contador de angulo: " + (this.contadorAngulo * MathUtils.radiansToDegrees));
//        System.out.println("angulo corredor   : " + getAnguloCorredor_Graus());
//    }

    private interface HelperRotacionador{
        void rotacionar();
    }

    private class RotacaoSemColisao implements HelperRotacionador{
        @Override
        public void rotacionar() {
            rotacionar();
        }
    }

    private class RotacaoComColisao implements HelperRotacionador{
        @Override
        public void rotacionar() {
        }
    }
}
