package com.licitacao.constantes;

public class Constantes {

    public static final String URL_LICITACOES = "http://comprasnet.gov.br/ConsultaLicitacoes/ConsLicitacaoDia.asp";

    public static final String REGEX_HTML_FORM = "<form[^>]*>.*?</form>";

    public static final String REGEX_HTML_TR = "<tr[^>]*>.*?</tr>";

    public static final String REGEX_HTML_BR = "<br>";

    public static final String REGEX_HTML_TEXTO_PRINCIPAL = "<td><b[^>]*>.*?</b>";

    public static final String REGEX_REMOVER_TAGS = "<[^>]+>";
}
