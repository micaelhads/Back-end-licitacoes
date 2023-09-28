package com.licitacao.repository;

import com.licitacao.entity.LicitacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicitacaoRepository extends JpaRepository<LicitacaoEntity, Long> {
}
