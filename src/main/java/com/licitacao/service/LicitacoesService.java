package com.licitacao.service;

import com.licitacao.entity.LicitacaoEntity;
import com.licitacao.exeptions.InvalidParameterException;
import com.licitacao.repository.LicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicitacoesService {
    @Autowired
    private LicitacaoRepository licitacaoRepository;

    public List<LicitacaoEntity> getLicitacoes(){
        return licitacaoRepository.findAll();
    }

    public void atualizar(LicitacaoEntity licitacaoEntity){
        if(licitacaoEntity == null || licitacaoEntity.getCodUASG() == null){
            throw new InvalidParameterException("Licitação está nula ou não possui um id");
        }
        licitacaoRepository.save(licitacaoEntity);
    }
}
