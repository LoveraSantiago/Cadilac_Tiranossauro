package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.visuais;

import com.badlogic.gdx.graphics.g2d.NinePatch;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.gerais.NinePatchLeitor;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.AreaDaCamera;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.NinePatchUnico;

import static lovera.cadilac.tiranossauro2.utils.imagens.Img9Patch.FILLER_HORIZONTAL;
import static lovera.cadilac.tiranossauro2.utils.imagens.Img9Patch.FILLER_VERTICAL;

public final class FillerEixoCartesiano implements TipoDesenhavel{

    private final NinePatch fillerH;
    private final NinePatch fillerV;

    private AreaDaCamera areaDaCamera;

    public FillerEixoCartesiano() {
        NinePatchLeitor ninePatchLeitor = NinePatchUnico.getInstancia().getNinePatchLeitor();
        this.fillerH = ninePatchLeitor.getNinePatch(FILLER_HORIZONTAL);
        this.fillerV = ninePatchLeitor.getNinePatch(FILLER_VERTICAL);
    }

    @Override
    public void meDesenhar(Object objeto) {

    }
}
