package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import lovera.cadilac.tiranossauro.telas.AjustadorDeTela;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoAtualizavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;

class Lataria implements TipoDesenhavel, TipoAtualizavel{


    private Sprite sprite;

    public Lataria() {
        this.sprite = new Sprite(new Texture(Gdx.files.internal("redcarpeq.png")));
        this.sprite.setSize(this.sprite.getWidth() / AjustadorDeTela.ESCALA,
                            this.sprite.getHeight()/ AjustadorDeTela.ESCALA);
        this.sprite.setOriginCenter();
    }

    @Override
    public void atualizar() {

    }

    @Override
    public void meDesenhar(Object objeto) {

    }

    public float getAltura(){
        return this.sprite.getHeight();
    }

    public float getLargura(){
        return this.sprite.getWidth();
    }
}
