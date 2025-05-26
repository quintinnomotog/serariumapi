package br.com.quintinno.serariumapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.quintinno.serariumapi.entity.ArquivoEntity;

@Repository
public interface ArquivoRepository extends JpaRepository<Long, ArquivoEntity> {}
