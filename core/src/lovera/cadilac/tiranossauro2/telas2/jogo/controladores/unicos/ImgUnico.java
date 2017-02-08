package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.gerais.ImgLeitor;

public class ImgUnico implements TipoSingleton{

    private static ImgUnico imgUnico;
    private final ImgLeitor imgLeitor;

    public ImgUnico() {
        this.imgLeitor = new ImgLeitor();
    }

    @Override
    public void inicializar() {
        imgUnico = this;
    }

    public static ImgUnico getInstancia() {
        return imgUnico;
    }

    public ImgLeitor getImgLeitor() {
        return imgLeitor;
    }
}
