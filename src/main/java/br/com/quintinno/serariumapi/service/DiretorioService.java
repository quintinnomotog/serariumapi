package br.com.quintinno.serariumapi.service;

import org.springframework.stereotype.Service;

import br.com.quintinno.serariumapi.entity.DiretorioEntity;
import br.com.quintinno.serariumapi.repository.DiretorioRepository;
import br.com.quintinno.serariumapi.transfer.DiretorioTransfer;

@Service
public class DiretorioService {

    private final DiretorioRepository diretorioRepository;

    public DiretorioService(DiretorioRepository diretorioRepository) {
        this.diretorioRepository = diretorioRepository;
    }

    public void criarDiretorio(DiretorioTransfer diretorioTransfer) {
        DiretorioEntity diretorioEntity = new DiretorioEntity();
            diretorioEntity.setCodeDiretorioPai(diretorioTransfer.getCodeDiretorioPai());
            diretorioEntity.setNome(diretorioTransfer.getNome());
            diretorioEntity.setRotulo(diretorioTransfer.getRotulo());
            diretorioEntity.setTamanho(diretorioTransfer.getTamanho());
        diretorioRepository.save(diretorioEntity);
    }

}
