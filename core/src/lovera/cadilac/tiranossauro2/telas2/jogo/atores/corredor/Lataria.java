package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro.telas.AjustadorDeTela;
import lovera.cadilac.tiranossauro2.componente.tela.mSpriteBatch;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;

class Lataria implements TipoDesenhavel{

    private Sprite sprite;
    private SpriteBatch spriteBatchTemp;

    public Lataria() {
        this.sprite = new Sprite(new Texture(Gdx.files.internal("redcarpeq.png")));
        this.sprite.setSize(this.sprite.getWidth() / AjustadorDeTela.ESCALA,
                            this.sprite.getHeight()/ AjustadorDeTela.ESCALA);
        this.sprite.setOriginCenter();
    }

    @Override
    public void meDesenhar(Object objeto) {
        this.spriteBatchTemp = mSpriteBatch.getInstance();

        this.spriteBatchTemp.begin();
        this.sprite.draw(this.spriteBatchTemp);
        this.spriteBatchTemp.end();
    }

    public void setPosicao(Vector2 posicao){
        this.sprite.setPosition(posicao.x, posicao.y);
    }

    public float getAltura(){
        return this.sprite.getHeight();
    }

    public float getLargura(){
        return this.sprite.getWidth();
    }
}
