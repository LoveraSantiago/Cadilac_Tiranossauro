package lovera.cadilac.tiranossauro.atores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro.contratos.MensagemBussola;


public final class Bussola extends Actor implements Disposable {

    private boolean toqueAcontecendo;
    private boolean panAcontecendo;

    private float anguloFixo;
    private float anguloMovel;
    private float ultimoAngulo;
    private float resultAnguloTemp;

    private float larguraPonteiro;
    private float metadePonteiro;
    private float ptXinicioPonteiro;

    private final Texture meiaBussola;
    private final TextureRegion setaFixa;
    private final TextureRegion setaMovel;

    private final MensagemBussola msg;

    private final Vector2 ptMedioBarra;

    public Bussola(MensagemBussola msgm) {
        this.msg = msgm;

        this.setaFixa    = new TextureRegion(new Texture(Gdx.files.internal("setafixa.png")));
        this.setaMovel   = new TextureRegion(new Texture(Gdx.files.internal("setamovel.png")));
        this.meiaBussola = new Texture(Gdx.files.internal("meiabussola.png"));

        this.ptMedioBarra = new Vector2();

        adicionarListeners();
    }

    private void adicionarListeners(){
        addListener(new ActorGestureListener(){

            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(msg.isBarraHudFixa()){
                    anguloFixo = getAngulo(ptMedioBarra.x, ptMedioBarra.y, x + getX(), y + getY());
                    ultimoAngulo = anguloFixo;
                    toqueAcontecendo = true;
                }
            }

            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
                if(msg.isBarraHudFixa()){
                    panAcontecendo = true;
                    anguloMovel = getAngulo(ptMedioBarra.x, ptMedioBarra.y, x + getX(), y + getY());

                    if(ultimoAngulo != anguloMovel){
                        msg.rotacionandoCamera(-(anguloMovel - ultimoAngulo));
                    }
                    ultimoAngulo = anguloMovel;
                }
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                anguloFixo = 0;
                anguloMovel = 0;
                toqueAcontecendo = false;
                panAcontecendo = false;
                msg.normatizarAngulo();
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.meiaBussola, getX(), getY(), getWidth(), getHeight());
        if(this.toqueAcontecendo){

            batch.draw(this.setaFixa , this.ptXinicioPonteiro, getY(), this.metadePonteiro, 0, this.larguraPonteiro, getHeight(), 1, 1, -90 + this.anguloFixo);

            if(this.panAcontecendo){
                batch.draw(this.setaMovel, this.ptXinicioPonteiro, getY(), this.metadePonteiro, 0, this.larguraPonteiro, getHeight(), 1, 1, -90 + this.anguloMovel);
            }
        }
    }

    public void calcularPontos(){
        this.larguraPonteiro = getWidth() / 8;
        this.metadePonteiro  = this.larguraPonteiro / 2;
        this.ptXinicioPonteiro = (getX() + (getWidth() / 2)) - this.metadePonteiro;
        this.ptMedioBarra.set(this.ptXinicioPonteiro + this.metadePonteiro, getY());
    }

    @Override
    public void dispose() {
        this.meiaBussola.dispose();
        this.setaFixa.getTexture().dispose();
    }

    private float getAngulo(float x0, float y0, float x1, float y1){
        this.resultAnguloTemp = (float) Math.toDegrees(Math.atan2(y1 - y0, x1 - x0));
        if(this.resultAnguloTemp < 0 && this.resultAnguloTemp > -90){
            return this.resultAnguloTemp = 0f;
        }
        else if(this.resultAnguloTemp < 0 && this.resultAnguloTemp <= -90){
            return this.resultAnguloTemp = 180f;
        }
        return this.resultAnguloTemp;
    }
}
