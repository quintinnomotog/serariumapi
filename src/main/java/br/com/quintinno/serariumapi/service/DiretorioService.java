package br.com.quintinno.serariumapi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.quintinno.serariumapi.entity.DiretorioEntity;
import br.com.quintinno.serariumapi.entity.ParametroEntity;
import br.com.quintinno.serariumapi.enumeration.ConstanteUtilityEnumeration;
import br.com.quintinno.serariumapi.repository.DiretorioRepository;
import br.com.quintinno.serariumapi.repository.ParametroRepository;
import br.com.quintinno.serariumapi.transfer.DiretorioRequestTransfer;
import br.com.quintinno.serariumapi.transfer.DiretorioResponseTransfer;

@Service
public class DiretorioService {

    private static final Logger logger = LoggerFactory.getLogger(DiretorioService.class);

    private final DiretorioRepository diretorioRepository;

    private final ParametroRepository parametroRepository;

    public DiretorioService(DiretorioRepository diretorioRepository, ParametroRepository parametroRepository) {
        this.diretorioRepository = diretorioRepository;
        this.parametroRepository = parametroRepository;
    }

    public DiretorioResponseTransfer create(DiretorioRequestTransfer diretorioTransfer) {
        try {
            this.criarDiretorioNoSistemaDeArquivos(diretorioTransfer);
            return criarDiretorioNoBancoDeDados(diretorioTransfer);
        } catch (Exception e) {
            logger.error("Erro ao criar diretório: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao criar diretório", e);
        }
    }

    private void criarDiretorioNoSistemaDeArquivos(DiretorioRequestTransfer diretorioTransfer) throws IOException {
        final Path path = Paths.get(this.getDiretorioRaiz());
        Files.createDirectories(path.resolve(diretorioTransfer.getNome()));
        logger.info("Diretório criado no sistema de arquivos: {}", path.resolve(diretorioTransfer.getNome()));
    }

    private DiretorioResponseTransfer criarDiretorioNoBancoDeDados(DiretorioRequestTransfer diretorioRequestTransfer) {
        DiretorioEntity diretorioEntity = new DiretorioEntity();
            diretorioEntity.setCodeDiretorioPai(diretorioRequestTransfer.getCodeDiretorioPai());
            diretorioEntity.setNome(diretorioRequestTransfer.getNome());
            diretorioEntity.setCodePublic(gerarCodePublic());
        return DiretorioEntity.toTransfer(diretorioRepository.save(diretorioEntity));
    }

    private String getDiretorioRaiz() {
        ParametroEntity parametroEntity = parametroRepository.findByChave(ConstanteUtilityEnumeration.CHAVE_ENDERECO_DIRETORIO.getChave());
        if (parametroEntity == null) {
            logger.error("Parâmetro do diretório raiz não encontrado!"); 
            throw new RuntimeException("Parâmetro do diretório raiz não encontrado!");
        }
        return parametroEntity.getValor();
    }

    private String gerarCodePublic() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
