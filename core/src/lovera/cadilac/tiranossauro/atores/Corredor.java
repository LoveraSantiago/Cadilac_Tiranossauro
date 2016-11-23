package lovera.cadilac.tiranossauro.atores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;

import lovera.cadilac.tiranossauro.contratos.Desenhavel;
import lovera.cadilac.tiranossauro.contratos.Resetavel;
import lovera.cadilac.tiranossauro.controladores.CameraManipulador;
import lovera.cadilac.tiranossauro.controladores.Fase;
import lovera.cadilac.tiranossauro.controladores.FaseManager;
import lovera.cadilac.tiranossauro.telas.AjustadorDeTela;

public final class Corredor implements Desenhavel, Resetavel {

    private float angulo;

    private final Vector2 posicaoInicial;
    private final Vector2 posicaoProjetada;
    private final Vector2 posicaoJogada;
    private final Vector2 ptFuturo;
    private final Vector2 ptPassado;

    private final Vector3 copiaPosJogada;
    private final Vector2 copiaPosJogada2;

    private final Sprite spriteCVerm;

    private final CameraManipulador cameraManipulador;

    private final FaseManager faseManager;

    private Body corredorBody;
    private World world;
    private Body pista;
    private MouseJoint mouseJoint;

    public Corredor(CameraManipulador cameraManipulador, FaseManager faseManager) {
        this.faseManager = faseManager;
        this.cameraManipulador = cameraManipulador;

        this.posicaoProjetada = new Vector2(this.cameraManipulador.getCameraProjecao().viewportWidth / 2, this.cameraManipulador.getCameraProjecao().viewportHeight / 6);
        this.ptFuturo         = new Vector2();
        this.posicaoInicial   = new Vector2();
        this.ptPassado        = new Vector2();
        this.posicaoJogada    = new Vector2();
        this.copiaPosJogada   = new Vector3();
        this.copiaPosJogada2  = new Vector2();

        this.ptFuturo      .set(this.posicaoProjetada);
        this.ptPassado     .set(this.posicaoProjetada);
        this.posicaoInicial.set(this.posicaoProjetada);
        this.posicaoJogada .set(this.posicaoProjetada);

        this.spriteCVerm = new Sprite(new Texture(Gdx.files.internal("redcarpeq.png")));
        this.spriteCVerm.setSize(this.spriteCVerm.getWidth() / AjustadorDeTela.ESCALA,
                                 this.spriteCVerm.getHeight()/ AjustadorDeTela.ESCALA);
        this.spriteCVerm.setOriginCenter();

        //INICIANDO PONTO INICIAL DE ROTACAO
        this.cameraManipulador.configurarPtDeRotacao(this.posicaoProjetada);
    }

    @Override
    public void meDesenhar(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        this.spriteCVerm.setPosition(this.posicaoProjetada.x - (this.spriteCVerm.getWidth()  / 2),
                                     this.posicaoProjetada.y - (this.spriteCVerm.getHeight() / 2));
        rotacionarCorredor();
        this.spriteCVerm.draw(spriteBatch);
        spriteBatch.end();

        calcularPosicaoJogada();
        this.ptPassado.set(this.posicaoProjetada);
    }

    private void rotacionarCorredor() {
        if(this.posicaoProjetada.x == this.ptFuturo.x && this.posicaoProjetada.y == this.ptFuturo.y) return;

        if(this.faseManager.getFaseAtual() == Fase.TELA_VOLTANDO ||
           this.faseManager.getFaseAtual() == Fase.CALCULAR_VOLTA ||
           this.faseManager.getFaseAtual() == Fase.ESCOLHENDO_GRAFICO ||
           this.faseManager.getFaseAtual() == Fase.ACEITAR_ENTRADA){

            rotacionarAposJogada();
        }
        else{
            rotacionarDuranteJogada();
        }
    }

    private void rotacionarDuranteJogada(){
        this.spriteCVerm.setRotation(-90);
        this.spriteCVerm.rotate(calcularAngulo());
    }

    private void rotacionarAposJogada(){
        this.angulo = Math.round(this.angulo);

        if(this.angulo < 90){
            this.angulo += 1;
            this.spriteCVerm.setRotation(-90);
            this.spriteCVerm.rotate(this.angulo);
        }
        else if(this.angulo > 90){
            this.angulo -= 1;
            this.spriteCVerm.setRotation(-90);
            this.spriteCVerm.rotate(this.angulo);
        }
        else{
            this.ptFuturo.set(this.posicaoProjetada);
        }
    }

    private float calcularAngulo(){
        this.angulo = (float) Math.toDegrees(Math.atan2(this.ptFuturo.y - this.posicaoProjetada.y, this.ptFuturo.x - this.posicaoProjetada.x));
        if(this.angulo < 0 && this.angulo > -90){
            return this.angulo += 270;
        }
        else if(this.angulo < 0 && this.angulo <= -90){
            return this.angulo += 90;
        }
        return this.angulo;
    }

    private void calcularPosicaoJogada(){
        this.ptPassado.set(this.cameraManipulador.reangularEmRelacaoAoPonto(this.posicaoProjetada, this.ptPassado));
        this.posicaoJogada.x += this.ptPassado.x;
        this.posicaoJogada.y += this.ptPassado.y;
    }

    @Override
    public void resetMe() {
        this.posicaoProjetada.set(this.posicaoInicial);
        this.ptFuturo        .set(this.posicaoInicial);
        this.ptPassado       .set(this.posicaoInicial);

        this.cameraManipulador.resetMe();
    }

    public float getAngulo() {
        return angulo;
    }

    public final Vector2 getPosicaoProjetada(){
        return this.posicaoProjetada;
    }

    public final Vector3 getPosicaoJogada(){
        return this.copiaPosJogada.set(this.posicaoJogada,0);
    }

    public final Vector2 getPosicaoJogada(Vector2 param){
        return this.copiaPosJogada2.set(this.posicaoJogada.x, this.posicaoJogada.y);
    }

    public final Vector2 getPosicaoInicial(){ return this.posicaoInicial; }

    public final float getPosicaoProjX(){
        return this.posicaoProjetada.x;
    }

    public void setPosicaoProjX(float x){
        this.posicaoProjetada.x = x;
    }

    public final float getPosicaoProjY(){
        return this.posicaoProjetada.y;
    }

    public void setPosicaoProjY(float y){
        this.posicaoProjetada.y = y;
    }

    public void setPosicaoProj(float x, float y){
        setPosicaoProjX(x);
        setPosicaoProjY(y);
    }

    public final float getPosicaoJogX(){
        return this.posicaoJogada.x;
    }

    public final float getPosicaoJogY(){
        return this.posicaoJogada.y;
    }

    public final Vector2 getPtFuturoProj(){
        return this.ptFuturo;
    }

    public final void setPtFuturoProj(Vector2 ptFuturo){
        setPtFuturoProj(ptFuturo.x, ptFuturo.y);
    }

    public final void setPtFuturoProj(float x, float y){
        this.ptFuturo.set(x, y);
    }

    public CameraManipulador getCameraManipulador(){
        return this.cameraManipulador;
    }

    public float getWidth(){
        return this.spriteCVerm.getWidth();
    }

    public float getHeight(){
        return this.spriteCVerm.getHeight();
    }

    public void setBox2dCoisas(Body corredorBody, World world, Body pista) {
        this.corredorBody = corredorBody;
        this.world = world;
        this.pista = pista;
    }

    public void criarMouseJoint(){
        MouseJointDef def = new MouseJointDef();
        def.bodyA = this.pista;
        def.bodyB = this.corredorBody;
        def.collideConnected = true;
        def.target.set(this.corredorBody.getPosition());
        def.maxForce = 1000.0f * this.corredorBody.getMass();

        this.mouseJoint = (MouseJoint) world.createJoint(def);
        this.corredorBody.setAwake(true);
    }

    public void destruirMouseJoint(){
        this.corredorBody.setLinearVelocity(0,0);
        this.world.destroyJoint(this.mouseJoint);
    }

    public MouseJoint getMouseJoint() {
        return mouseJoint;
    }

    public Body getCorredorBody() {
        return corredorBody;
    }
}
