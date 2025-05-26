package br.com.quintinno.serariumapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.quintinno.serariumapi.entity.ParametroEntity;

@Repository
public interface ParametroRepository extends JpaRepository<ParametroEntity, Long> {}
