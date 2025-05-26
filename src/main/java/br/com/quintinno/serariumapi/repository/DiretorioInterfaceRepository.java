package br.com.quintinno.serariumapi.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.quintinno.serariumapi.entity.DiretorioEntity;

@Repository
public interface DiretorioInterfaceRepository {

    List<DiretorioEntity> verificarSeDiretorioExistente(String nome, Long codeDiretorioPai);

}
