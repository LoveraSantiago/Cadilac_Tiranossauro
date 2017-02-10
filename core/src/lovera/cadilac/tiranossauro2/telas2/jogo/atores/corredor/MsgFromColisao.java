package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import lovera.cadilac.tiranossauro2.utils.custom_anotacoes.Revisao;

@Revisao(
        dataModificacao = "04/02/2017",
        dataRevisao     = "04/02/2017",
        revisada = true,

        importsVerificados = true,

        classePublica = false,

        classeFinal = false,
        justificativa_classeFinal = "A classe é uma interface.",

        metodosEncapsulados = true,
        atributosEncapsulados = true,

        substituivel = false,

        descricao = "Interface do pacote permite a classe Colisao notifique colisao para as classes que precisarao modificar algum comportamento dentro de si",
        utilizadores = {Colisao.class},
        implementadores = {CalculadorAngulo2.class, Movimentador.class}
)
interface MsgFromColisao {

    void colisaoAconteceu();
}
