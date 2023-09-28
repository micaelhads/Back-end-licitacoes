package com.licitacao.controller;

import com.licitacao.entity.LicitacaoEntity;
import com.licitacao.service.CapturarLicitacoesService;
import com.licitacao.service.LicitacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/licitacoes")
@CrossOrigin
public class LicitacoesController {

    @Autowired
    CapturarLicitacoesService capturarLicitacoesService;

    @Autowired
    LicitacoesService licitacoesService;
    @GetMapping("/capturarSalvarLicitacoes")
    public ResponseEntity<String> capturarLicitacoes() {
        capturarLicitacoesService.capturarLicitacoes();
        return ResponseEntity.ok().body("Captura relaizada com sucesso!");
    }
    @GetMapping("/getlicitacoes")
    public ResponseEntity<List<LicitacaoEntity>> getLicitacoes(){
        return ResponseEntity.ok().body(licitacoesService.getLicitacoes());
    }

    @PostMapping("/gravar")
    public ResponseEntity<String> gravarLicitacao(@RequestBody LicitacaoEntity licitacaoEntity){
        licitacoesService.atualizar(licitacaoEntity);
        return ResponseEntity.ok().body("Gravação relaizada com sucesso!");
    }
}
