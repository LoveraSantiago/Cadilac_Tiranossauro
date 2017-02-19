package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.visuais;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.ImgUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.SpriteBatchUnico;

import static lovera.cadilac.tiranossauro2.utils.imagens.Img.DIGITAL;

class Digital implements TipoDesenhavel{

    private final Sprite digital;
    private final SpriteBatch spriteBatch;

    private final Vector2 posicao;

    public Digital() {
        this.digital = ImgUnico.getInstancia().getImgLeitor().getImg(DIGITAL);
        this.spriteBatch = SpriteBatchUnico.getInstancia().getSpriteBatchManager().getSpriteBatch();

        this.posicao = new Vector2();
    }

    public void setPosicao(Vector2 posicao){
        this.posicao.set(posicao);
    }

    @Override
    public void meDesenhar(Object objeto) {
        this.spriteBatch.begin();
        this.spriteBatch.draw(this.digital, this.posicao.x - 1.5f, this.posicao.y - 2.5f, 3, 5);
        this.spriteBatch.end();
    }
}
