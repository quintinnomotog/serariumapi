package br.com.quintinno.serariumapi.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.quintinno.serariumapi.entity.DiretorioEntity;
import br.com.quintinno.serariumapi.repository.DiretorioRepository;
import br.com.quintinno.serariumapi.repository.ParametroRepository;
import br.com.quintinno.serariumapi.transfer.DiretorioTransfer;

@Service
public class DiretorioService {

    private static final Logger logger = LoggerFactory.getLogger(DiretorioService.class);

    private final DiretorioRepository diretorioRepository;

    private final ParametroRepository paaraParametroRepository;

    public DiretorioService(DiretorioRepository diretorioRepository, ParametroRepository parametroRepository) {
        this.diretorioRepository = diretorioRepository;
        this.paaraParametroRepository = parametroRepository;
    }

    public DiretorioTransfer create(DiretorioTransfer diretorioTransfer) {
        try {
            this.criarDiretorioNoSistemaDeArquivos(diretorioTransfer);
            return criarDiretorioNoBancoDeDados(diretorioTransfer);
        } catch (Exception e) {
            logger.error("Erro ao criar diretório: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao criar diretório", e);
        }
    }

    private void criarDiretorioNoSistemaDeArquivos(DiretorioTransfer diretorioTransfer) throws IOException {
        Path path = Paths.get("null");
    }

    private DiretorioTransfer criarDiretorioNoBancoDeDados(DiretorioTransfer diretorioTransfer) {
        DiretorioEntity diretorioEntity = new DiretorioEntity();
            diretorioEntity.setCodeDiretorioPai(diretorioTransfer.getCodeDiretorioPai());
            diretorioEntity.setNome(diretorioTransfer.getNome());
            diretorioEntity.setRotulo(diretorioTransfer.getRotulo());
            diretorioEntity.setTamanho(diretorioTransfer.getTamanho());
        diretorioRepository.save(diretorioEntity);
        return DiretorioEntity.toTransfer(diretorioEntity);
    }

}
