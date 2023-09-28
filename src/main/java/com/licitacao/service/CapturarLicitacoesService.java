package com.licitacao.service;

import com.licitacao.constantes.Constantes;
import com.licitacao.entity.LicitacaoEntity;
import com.licitacao.exeptions.GenericException;
import com.licitacao.repository.LicitacaoRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CapturarLicitacoesService {
    @Autowired
    LicitacaoRepository licitacaoRepository;

    /**
     * método para realizar a leitura da pagina de licitações e buscar os dados
     * @throws IOException
     */
    public void capturarLicitacoes() {
        String html = recuperarPaginaHtml();
        List<LicitacaoEntity> licitacaoEntityList = montarListaLicitacoes(html);
        licitacaoRepository.saveAll(licitacaoEntityList);
    }

    public String recuperarPaginaHtml(){
        Document document = null;
        try {
            document = Jsoup.connect(Constantes.URL_LICITACOES).get();
            document.outputSettings().syntax(Document.OutputSettings.Syntax.html);
            document.outputSettings().escapeMode(Entities.EscapeMode.base);
            document.outputSettings().prettyPrint(true);
        } catch (IOException e) {
            throw new GenericException("Erro ao tentar fazer a leitura da pagina das licitações");
        }
        String html = document.outerHtml().replaceAll("&nbsp;"," ");
        html = html.replaceAll("\n","");
        return html;
    }

    public List<LicitacaoEntity> montarListaLicitacoes(String html){
        if(html == null || html.isEmpty()){
            throw new GenericException("A pagina html foi retornada em branco");
        }
        List<String> licitacoes = capturarElementosHtml(Constantes.REGEX_HTML_FORM, html);
        List<LicitacaoEntity> licitacaoEntityList = new ArrayList<>();
        for (String licitacao : licitacoes) {
            LicitacaoEntity licitacaoEntity = new LicitacaoEntity();
            List<String> tableLicitacao = capturarElementosHtml(Constantes.REGEX_HTML_TR, licitacao);
            for (String componenteLicitacao : tableLicitacao) {
                String descricaoLicitacao = capturarElementoHtml(Constantes.REGEX_HTML_TEXTO_PRINCIPAL, componenteLicitacao);
                String[] itens = componenteLicitacao.split(Constantes.REGEX_HTML_BR);
                if (!descricaoLicitacao.isEmpty()) {
                    // descricao licitacao
                    licitacaoEntity.setDescricaoLicitacao(removerTagsHtml(descricaoLicitacao).trim());
                    licitacaoEntity.setVisualizado(false);
                    // separar itens da licitacao
                    for (String item : itens) {
                        item = removerTagsHtml(item);
                        if (item.contains(":")) {
                            // Divide a string com base em ":" para capturar o valor de cada item da licitacao
                            String[] partes = item.split(": ");
                            switch (partes[0].trim()) {
                                case "Código da UASG":
                                    if(partes[1] != null && !partes[1].isEmpty() ){
                                        licitacaoEntity.setCodUASG(Long.valueOf(partes[1].trim()));
                                        System.out.println(partes[1]);
                                    }
                                    break;
                                case "Objeto":
                                    if(partes[2] != null && !partes[2].isEmpty() ){
                                        licitacaoEntity.setObjeto(partes[2].trim());
                                        System.out.println(partes[2]);
                                    }
                                    break;
                                case "Edital a partir de":
                                    if(partes[1] != null && !partes[1].isEmpty() ){
                                        licitacaoEntity.setDataEdital(partes[1].trim());
                                        System.out.println(partes[1]);
                                    }
                                    break;
                                case "Endereço":
                                    if(partes[1] != null && !partes[1].isEmpty() ){
                                        licitacaoEntity.setEndereco(partes[1].trim());
                                        System.out.println(partes[1]);
                                    }
                                    break;
                                case "Telefone":
                                    if(partes[1] != null && !partes[1].isEmpty() ){
                                        licitacaoEntity.setTelefone(partes[1].trim());
                                        System.out.println(partes[1]);
                                    }
                                    break;
                                case "Fax":
                                    if(partes[1] != null && !partes[1].isEmpty() ){
                                        licitacaoEntity.setFax(partes[1].trim());
                                        System.out.println(partes[1]);
                                    }
                                    break;
                                case "Entrega da Proposta":
                                    if(partes[1] != null && !partes[1].isEmpty() ){
                                        licitacaoEntity.setDataEntregaProposta(partes[1].trim());
                                        System.out.println(partes[1]);
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
            licitacaoEntityList.add(licitacaoEntity);
        }
        return licitacaoEntityList;
    }
    public List<String> capturarElementosHtml(String regex, String html){
        List<String> conteudoHtml = new ArrayList<>();
        try{
            Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
            Matcher matcher = pattern.matcher(html);
            while (matcher.find()) {
                String componenteLicitacao = matcher.group();
                conteudoHtml.add(componenteLicitacao);
            }
        }catch (Exception e){
            throw new GenericException("Ocorreu um erro ao processar o arquivo html");
        }
        return conteudoHtml;
    }

    public String capturarElementoHtml(String regex, String html){
        String conteudoHtml = "";
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            String componenteLicitacao = matcher.group();
            conteudoHtml += componenteLicitacao;
        }
        return conteudoHtml;
    }

    public String removerTagsHtml(String html) {
        // Expressão regular para encontrar e remover tags HTML
        String regex = Constantes.REGEX_REMOVER_TAGS;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);
        String textoSemTags = matcher.replaceAll("");

        return  textoSemTags;
    }
}
