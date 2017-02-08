package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoAtualizavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.gerais.AjustadorDeTela2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.ImgUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.SpriteBatchUnico;

import static lovera.cadilac.tiranossauro2.utils.imagens.Img.CARRO_VERMELHO;

final class Lataria implements TipoDesenhavel, TipoAtualizavel{

    private final Sprite sprite;
    private final SpriteBatch spriteBatchTemp;

    private Body corredor;

    public Lataria() {
        this.sprite = ImgUnico.getInstancia().getImgLeitor().getImg(CARRO_VERMELHO);
        this.sprite.setSize(this.sprite.getWidth() / AjustadorDeTela2.ESCALA,
                            this.sprite.getHeight()/ AjustadorDeTela2.ESCALA);
        this.sprite.setOriginCenter();

        this.spriteBatchTemp = SpriteBatchUnico.getInstancia().getSpriteBatchManager().getSpriteBatch();
    }

    @Override
    public void meDesenhar(Object objeto) {
        atualizar();
        this.spriteBatchTemp.begin();
        this.sprite.draw(this.spriteBatchTemp);
        this.spriteBatchTemp.end();
    }

    @Override
    public void atualizar() {
        this.sprite.setPosition(this.corredor.getPosition().x - getMetadeLargura(), this.corredor.getPosition().y - getMetadeAltura());
        this.sprite.setRotation(this.corredor.getAngle() * MathUtils.radiansToDegrees);
    }


    public float getAltura(){
        return this.sprite.getHeight();
    }

    private float getMetadeAltura(){
        return getAltura() / 2;
    }

    public float getLargura(){
        return this.sprite.getWidth();
    }

    private float getMetadeLargura(){
        return getLargura() / 2;
    }

    public void setCorredor(Body corredor) {
        this.corredor = corredor;
    }
}
