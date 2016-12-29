package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades;

public class NormatizadorDeAngulos {

    public float normatizar(float angulo){
        if(angulo < 0){
            while(angulo < 0){
                angulo += 360;
            }
            return angulo;
        }
        else if(angulo > 0){
            while(angulo > 360){
                angulo -= 360;
            }
            return angulo;
        }
        return angulo;
    }
}
