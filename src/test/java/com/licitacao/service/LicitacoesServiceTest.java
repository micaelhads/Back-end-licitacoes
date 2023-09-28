package com.licitacao.service;

import com.licitacao.entity.LicitacaoEntity;
import com.licitacao.exeptions.InvalidParameterException;
import com.licitacao.repository.LicitacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class LicitacoesServiceTest {

    @InjectMocks
    private LicitacoesService licitacoesService;
    @Mock
    private LicitacaoRepository licitacaoRepository;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetLicitacoes() {
        List<LicitacaoEntity> licitacoes = new ArrayList<>();
        LicitacaoEntity licitacao1 = new LicitacaoEntity();
        licitacoes.add(licitacao1);
        when(licitacaoRepository.findAll()).thenReturn(licitacoes);
        List<LicitacaoEntity> result = licitacoesService.getLicitacoes();
        assertEquals(1, result.size());
        assertEquals(licitacao1, result.get(0));
    }

    @Test
    public void testAtualizarComLicitacaoValida() {
        LicitacaoEntity licitacao = new LicitacaoEntity();
        licitacao.setCodUASG(1L);
        when(licitacaoRepository.save(licitacao)).thenReturn(licitacao);
        licitacoesService.atualizar(licitacao);
        verify(licitacaoRepository, times(1)).save(licitacao);
    }

    @Test
    public void testAtualizarComLicitacaoNula() {
        LicitacaoEntity licitacao = null;
        assertThrows(InvalidParameterException.class, () -> licitacoesService.atualizar(licitacao));
    }

    @Test
    public void testAtualizarComLicitacaoSemId() {
        LicitacaoEntity licitacao = new LicitacaoEntity();
        assertThrows(InvalidParameterException.class, () -> licitacoesService.atualizar(licitacao));
    }
}
