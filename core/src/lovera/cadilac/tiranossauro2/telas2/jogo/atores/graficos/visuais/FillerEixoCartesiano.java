package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.visuais;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.gerais.NinePatchLeitor;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.WrapperPosicaoJogador;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.AreaDaCamera;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.NinePatchUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.SpriteBatchUnico;
import lovera.cadilac.tiranossauro2.utils.Debugagem;

import static lovera.cadilac.tiranossauro2.utils.imagens.Img9Patch.FILLER_HORIZONTAL;
import static lovera.cadilac.tiranossauro2.utils.imagens.Img9Patch.FILLER_VERTICAL;

//todo analisar que Classe tem muita similaridade com AreaJogavel, EixoCartesino e FillerEixoCartesiano
public final class FillerEixoCartesiano implements TipoDesenhavel{

    private final NinePatch fillerH;
    private final NinePatch fillerV;

    private AreaDaCamera areaDaCamera;

    private final SpriteBatch spriteBatch;
    private final WrapperPosicaoJogador posJogador;

    public FillerEixoCartesiano() {
        NinePatchLeitor ninePatchLeitor = NinePatchUnico.getInstancia().getNinePatchLeitor();
        this.fillerH = ninePatchLeitor.getNinePatch(FILLER_HORIZONTAL);
        this.fillerV = ninePatchLeitor.getNinePatch(FILLER_VERTICAL);

        this.spriteBatch = SpriteBatchUnico.getInstancia().getSpriteBatchManager().getSpriteBatch();
        this.posJogador = CorredorUnico.getInstancia().getCorredorManager().getCorredorP().getWrapperPosicaoJogador();
    }

    public void configurarArea(AreaDaCamera areaDaCamera){
        this.areaDaCamera = areaDaCamera;
    }

    public void set(float altura, float largura){
        this.altura = altura;
        this.largura = largura;
        meDesenhar(null);
    }

    float altura;
    float largura;

    @Override
    public void meDesenhar(Object objeto) {
        this.spriteBatch.begin();

        if(this.largura > this.posJogador.getX()){
            this.fillerH.draw(this.spriteBatch, this.posJogador.getX(), this.posJogador.getY() - .5f, this.largura - this.posJogador.getX(), 1);
        }
        else{
            this.fillerH.draw(this.spriteBatch, this.largura, this.posJogador.getY() - .5f, this.posJogador.getX() - this.largura, 1);
        }
        this.fillerV.draw(this.spriteBatch, this.posJogador.getX() - .5f, this.areaDaCamera.getY(), 1, altura);
        this.spriteBatch.end();
    }
}
