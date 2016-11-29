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

    private boolean toqueAcontecendo;//ok
    private boolean panAcontecendo;//ok

    private float anguloFixo;
    private float anguloMovel;
    private float ultimoAngulo;
    private float resultAnguloTemp;

    private float larguraPonteiro;//ok
    private float metadePonteiro;//ok
    private float ptXinicioPonteiro;//ok

    private final Texture meiaBussola;//ok
    private final TextureRegion setaFixa;//ok
    private final TextureRegion setaMovel;//ok

    private final MensagemBussola msg;

    private final Vector2 ptMedioBarra;//ok

    public Bussola(MensagemBussola msgm) {
        this.msg = msgm;

        this.setaFixa    = new TextureRegion(new Texture(Gdx.files.internal("setafixa.png")));//ok
        this.setaMovel   = new TextureRegion(new Texture(Gdx.files.internal("setamovel.png")));//ok
        this.meiaBussola = new Texture(Gdx.files.internal("meiabussola.png"));//ok

        this.ptMedioBarra = new Vector2();//ok

        adicionarListeners();//ok
    }

    private void adicionarListeners(){
        addListener(new ActorGestureListener(){

            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(msg.isBarraHudFixa()){//ok
                    anguloFixo = getAngulo(ptMedioBarra.x, ptMedioBarra.y, x + getX(), y + getY());
                    ultimoAngulo = anguloFixo;
                    toqueAcontecendo = true;
                }
            }

            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
                if(msg.isBarraHudFixa()){//ok
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
    public void draw(Batch batch, float parentAlpha) {//ok
        batch.draw(this.meiaBussola, getX(), getY(), getWidth(), getHeight());//ok
        if(this.toqueAcontecendo){//ok

            batch.draw(this.setaFixa , this.ptXinicioPonteiro, getY(), this.metadePonteiro, 0, this.larguraPonteiro, getHeight(), 1, 1, -90 + this.anguloFixo);//ok

            if(this.panAcontecendo){//ok
                batch.draw(this.setaMovel, this.ptXinicioPonteiro, getY(), this.metadePonteiro, 0, this.larguraPonteiro, getHeight(), 1, 1, -90 + this.anguloMovel);//ok
            }//ok
        }//ok
    }//ok

    public void calcularPontos(){//ok
        this.larguraPonteiro = getWidth() / 8;//ok
        this.metadePonteiro  = this.larguraPonteiro / 2;//ok
        this.ptXinicioPonteiro = (getX() + (getWidth() / 2)) - this.metadePonteiro;//ok
        this.ptMedioBarra.set(this.ptXinicioPonteiro + this.metadePonteiro, getY());//ok
    }

    @Override
    public void dispose() {
        this.meiaBussola.dispose();//ok
        this.setaFixa.getTexture().dispose();//ok
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
