package br.com.quintinno.serariumapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.quintinno.serariumapi.entity.DiretorioEntity;

@Repository
public interface DiretorioRepository extends JpaRepository<DiretorioEntity, Long> {
    DiretorioEntity findByCode(Long code);
    List<DiretorioEntity> findByCodeDiretorioPai(Long codeDiretorioPai);
}
