package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import lovera.cadilac.tiranossauro2.utils.custom_anotacoes.Revisao;

@Revisao(
        dataModificacao = "14/02/2017",
        dataRevisao     = "14/02/2017",
        revisada = true,

        importsVerificados =  true,

        classePublica = false,

        classeFinal = false,
        justificativa_classeFinal = "A classe é uma interface",

        metodosEncapsulados = true,
        atributosEncapsulados = true,

        substituivel = false,

        descricao = "Interface do pacote permite que a classe Movimentador notifique o ponto futuro e o encerramento de movimentacao.",
        utilizadores = {Movimentador.class},
        implementadores = { CalculadorAngulo2.class, Corredor2.class}
)
interface MsgFromMovimentador {

    void setPtFuturoProj(float x, float y);
    void movimentacaoEncerrada();

}
