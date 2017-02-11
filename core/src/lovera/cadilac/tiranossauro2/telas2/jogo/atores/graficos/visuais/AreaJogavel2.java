package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.visuais;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.gerais.NinePatchLeitor;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.WrapperPosicaoJogador;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.AreaDaCamera;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.GraficosEnum2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.NinePatchUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.SpriteBatchUnico;

import static lovera.cadilac.tiranossauro2.utils.imagens.Img9Patch.AREA;

//TODO talvez possa ser classe de package
public final class AreaJogavel2 implements TipoDesenhavel{

    private final NinePatch ninePatch;

    private final SpriteBatch spriteBatch;

    private float x;
    private float y;
    private float w;
    private float h;

    private final WrapperPosicaoJogador posJogador;

    private AreaDaCamera areaDaCameraTemp;

    public AreaJogavel2() {
        NinePatchLeitor ninePatchLeitor = NinePatchUnico.getInstancia().getNinePatchLeitor();
        this.ninePatch = ninePatchLeitor.getNinePatch(AREA);
        this.ninePatch.scale(.1f, .1f);

        this.spriteBatch = SpriteBatchUnico.getInstancia().getSpriteBatchManager().getSpriteBatch();
        this.posJogador = CorredorUnico.getInstancia().getCorredorManager().getCorredorP().getWrapperPosicaoJogador();
    }

    @Override
    public void meDesenhar(Object objeto) {
        this.spriteBatch.begin();
        this.ninePatch.draw(this.spriteBatch, this.x, this.y, this.w, this.h);
        this.spriteBatch.end();
    }


    //todo trocar por um segundo objeto area
    private void setarTamanhoArea(float x, float y, float w, float h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    //TODO talvez quebrar esse metodo em 2 sets e criar interface para visuais ou algo assim
    public void configurarAreaJogavel(GraficosEnum2 graficoEnum, AreaDaCamera areaDaCamera){
        this.areaDaCameraTemp = areaDaCamera;

        if(graficoEnum == GraficosEnum2.VETOR){
            setarTamanhoArea(this.areaDaCameraTemp.getX(), this.posJogador.getY(), this.areaDaCameraTemp.getW(), this.areaDaCameraTemp.getH() - (this.posJogador.getY() - this.areaDaCameraTemp.getY()));
        }
        else if(graficoEnum == GraficosEnum2.PARABOLOIDE){
            setarTamanhoArea(this.areaDaCameraTemp.getX(), this.posJogador.getY() + 15, this.areaDaCameraTemp.getW(), this.areaDaCameraTemp.getH() - (this.posJogador.getY() - this.areaDaCameraTemp.getY()));
        }
        else{
            setarTamanhoArea(this.areaDaCameraTemp.getX(), this.areaDaCameraTemp.getY(), this.areaDaCameraTemp.getW(), this.posJogador.getY() - this.areaDaCameraTemp.getY());
        }
    }
}
