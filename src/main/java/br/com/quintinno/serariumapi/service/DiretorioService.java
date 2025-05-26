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

    public DiretorioResponseTransfer create(DiretorioRequestTransfer diretorioRequestTransfer) {
        try {
            String enderecoFisicoDiretorio = this.criarDiretorioNoSistemaDeArquivos(diretorioRequestTransfer);
            diretorioRequestTransfer.setEnderecoFisico(enderecoFisicoDiretorio);
            return criarDiretorioNoBancoDeDados(diretorioRequestTransfer);
        } catch (Exception e) {
            logger.error("Erro ao tentar criar o diretório: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao tentar criar o diretório", e);
        }
    }

    private String criarDiretorioNoSistemaDeArquivos(DiretorioRequestTransfer diretorioTransfer) throws IOException {
        Path path = Paths.get(this.getDiretorioRaiz());

        if (diretorioTransfer.getCodeDiretorioPai() != null) {
            DiretorioEntity diretorioPai = diretorioRepository.findByCode(diretorioTransfer.getCodeDiretorioPai());
            if (diretorioPai == null) {
                logger.error("Diretório pai não encontrado: {}", diretorioTransfer.getCodeDiretorioPai());
                throw new RuntimeException("Diretório pai não encontrado!");
            }
            path = path.resolve(diretorioPai.getNome());
        }

        Files.createDirectories(path.resolve(diretorioTransfer.getNome()));
        logger.info("Diretório criado no sistema de arquivos: {}", path.resolve(diretorioTransfer.getNome()));
        return path.resolve(diretorioTransfer.getNome()).toString();
    }

    private DiretorioResponseTransfer criarDiretorioNoBancoDeDados(DiretorioRequestTransfer diretorioRequestTransfer) {
        DiretorioEntity diretorioEntity = new DiretorioEntity();
            diretorioEntity.setCodeDiretorioPai(diretorioRequestTransfer.getCodeDiretorioPai());
            diretorioEntity.setNome(diretorioRequestTransfer.getNome());
            diretorioEntity.setCodePublic(gerarCodePublic());
            diretorioEntity.setEnderecoFisico(diretorioRequestTransfer.getEnderecoFisico());
        return DiretorioEntity.toTransfer(diretorioRepository.save(diretorioEntity));
    }

    private String getDiretorioRaiz() {
        ParametroEntity parametroEntity = parametroRepository
                .findByChave(ConstanteUtilityEnumeration.CHAVE_ENDERECO_DIRETORIO.getChave());
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
