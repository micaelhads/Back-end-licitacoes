package com.licitacao.service;

import com.licitacao.constantes.Constantes;
import com.licitacao.entity.LicitacaoEntity;
import com.licitacao.repository.LicitacaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class CapturarLicitacoesServiceTest {


    final String HMTL_MOCK = "<form method=\"post\" name=\"Form1\">\n" +
            "\t\t\t\t<a name=\"F1\">&nbsp;</a>\n" +
            "\t\t\t\t<table border=\"0\" width=\"100%\" class=\"td\" cellpadding=\"1\" cellspacing=\"1\"><tbody><tr class=\"mensagem\"><td>1</td></tr><tr bgcolor=\"#FFFFFF\" class=\"tex3\"><td><b>MINISTÉRIO DA DEFESA<br>Comando da Aeronáutica<br>Base Aérea de Natal<br>Código da UASG: 120631<br></b><br><b>Tomada de preço Nº 7/2023<span class=\"mensagem\"> - (Lei Nº 8.666/1993)</span></b><br><b>Objeto:</b>&nbsp;Objeto: Contratação de empresa de engenharia especializada para realizar reforma da edificação que abriga o Radar  BEARN (E-036) do CLBI. PAG: 67703.000314/2023-61                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                <br><b>Edital a partir de:</b>&nbsp;27/09/2023 das 08:00 às 12:00 Hs e das 13:00 às 16:00 Hs<br><b>Endereço:</b>&nbsp;Rua do Especialista S/n, Bant Nt,emaús, Parnamirim/rn                                -                                          - Natal                                    (RN)<br><b>Telefone:</b>&nbsp; 36449241<br><b>Fax:</b>&nbsp;               <br><b>Entrega da Proposta:</b>&nbsp;16/10/2023 às 09:00Hs<br><br>\n" +
            "\t\t\t\t\t<input type=\"hidden\" name=\"origem\" value=\"2\">\n" +
            "\t\t\t\t\t<a href=\"#F1\" name=\"hist_eventos\" class=\"legenda\" onclick=\"javascript:visualizarHistoricoEventos(document.Form1,'?coduasg=120631&amp;modprp=2&amp;numprp=72023');\" title=\"Visualizar histórico de eventos publicados para a licitação\">Histórico de eventos publicados...</a>\n" +
            "\t\t\t\t\t<br><br>\n" +
            "\t\t\t\t\t<input type=\"button\" name=\"itens\" value=\"Itens e Download\" class=\"texField2\" onclick=\"javascript:VisualizarItens(document.Form1,'?coduasg=120631&amp;modprp=2&amp;numprp=72023');\" onmouseover=\"window.status='Itens da Licitação e Download do Edital';return true;\" title=\"Clique para ver os itens ou para fazer o Download do Edital\">\n" +
            "\t\t\t\t</td>\n" +
            "\t\t\t\t</tr>\n" +
            "\t\t\t\t</tbody></table>\n" +
            "\t\t\t</form>";

    @InjectMocks
    private CapturarLicitacoesService capturarLicitacoesService;

    @Mock
    private LicitacaoRepository licitacaoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRecuperarPaginaHtml() {
        // testa se html da pagina retorna não está vazio
        String html = capturarLicitacoesService.recuperarPaginaHtml();
        Assertions.assertNotNull(html);
        Assertions.assertNotEquals("",html);
    }

    @Test
    public void testMontarListaLicitacoes() {
        List<LicitacaoEntity> licitacaoEntityList = capturarLicitacoesService.montarListaLicitacoes(HMTL_MOCK);
        Assertions.assertNotNull(licitacaoEntityList);
        Assertions.assertNotEquals(0, licitacaoEntityList.size());
    }

    @Test
    public void testCapturarElementosHtml() {
        List<String> licitacoes = capturarLicitacoesService.capturarElementosHtml(Constantes.REGEX_HTML_FORM,HMTL_MOCK);
        Assertions.assertNotNull(licitacoes);
        Assertions.assertNotEquals(0, licitacoes.size());
    }

    @Test
    public void testCapturarElementoHtml() {
        String html =  capturarLicitacoesService.capturarElementoHtml(Constantes.REGEX_HTML_FORM,HMTL_MOCK);
        Assertions.assertNotNull(html);
        Assertions.assertNotEquals("",html);
    }

    @Test
    public void testRemoverTagsHtml() {
        String conteudo = capturarLicitacoesService.removerTagsHtml(HMTL_MOCK);
        Assertions.assertFalse(conteudo.matches(".*<[^>]+>.*"));
    }
}
