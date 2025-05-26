package br.com.quintinno.serariumapi.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.quintinno.serariumapi.entity.DiretorioEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class DiretorioInterfaceImplementacaoRepository implements DiretorioInterfaceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DiretorioEntity> verificarSeDiretorioExistente(String nome, Long codeDiretorioPai) {
        StringBuilder query = new StringBuilder("SELECT diretorioEntity ")
        .append("FROM DiretorioEntity diretorioEntity ")
        .append("WHERE diretorioEntity.nome LIKE :nome ")
        .append("AND (diretorioEntity.codeDiretorioPai IS NULL OR diretorioEntity.codeDiretorioPai = :codeDiretorioPai)");
        return entityManager.createQuery(query.toString(), DiretorioEntity.class)
                .setParameter("nome", nome)
                .setParameter("codeDiretorioPai", codeDiretorioPai)
                .getResultList();
    }

}
