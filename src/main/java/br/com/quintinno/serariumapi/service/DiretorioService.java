package br.com.quintinno.serariumapi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.quintinno.serariumapi.entity.DiretorioEntity;
import br.com.quintinno.serariumapi.entity.ParametroEntity;
import br.com.quintinno.serariumapi.enumeration.ConstanteUtilityEnumeration;
import br.com.quintinno.serariumapi.exception.DiretorioCadastradoNoBancoDeDadosException;
import br.com.quintinno.serariumapi.repository.DiretorioInterfaceImplementacaoRepository;
import br.com.quintinno.serariumapi.repository.DiretorioRepository;
import br.com.quintinno.serariumapi.repository.ParametroRepository;
import br.com.quintinno.serariumapi.transfer.DiretorioRequestTransfer;
import br.com.quintinno.serariumapi.transfer.DiretorioResponseTransfer;

@Service
public class DiretorioService {

    private static final Logger logger = LoggerFactory.getLogger(DiretorioService.class);

    private final DiretorioRepository diretorioRepository;

    private final ParametroRepository parametroRepository;

    private final DiretorioInterfaceImplementacaoRepository diretorioInterfaceImplementacaoRepository;

    public DiretorioService(
            DiretorioRepository diretorioRepository, 
            ParametroRepository parametroRepository, 
            DiretorioInterfaceImplementacaoRepository diretorioInterfaceImplementacaoRepository) {
        this.diretorioRepository = diretorioRepository;
        this.parametroRepository = parametroRepository;
        this.diretorioInterfaceImplementacaoRepository = diretorioInterfaceImplementacaoRepository;
    }

    public DiretorioResponseTransfer create(DiretorioRequestTransfer diretorioRequestTransfer) {
        try {
            diretorioRequestTransfer.setEnderecoFisico(this.criarDiretorioNoSistemaDeArquivos(diretorioRequestTransfer));
            return criarDiretorioNoBancoDeDados(diretorioRequestTransfer);
        } catch (IOException e) {
            logger.error("Erro ao criar diretório no sistema de arquivos", e);
            throw new RuntimeException("Erro ao criar diretório no sistema de arquivos", e);
        }
    }

    private String criarDiretorioNoSistemaDeArquivos(DiretorioRequestTransfer diretorioTransfer) throws IOException {
        Path path = Paths.get(this.getDiretorioRaiz());

        if (diretorioTransfer.getCodeDiretorioPai() != null) {
            DiretorioEntity diretorioPai = diretorioRepository.findByCode(diretorioTransfer.getCodeDiretorioPai());
            if (diretorioPai == null) {
                logger.error("Diretório pai não encontrado: {%s}", diretorioTransfer.getCodeDiretorioPai());
                throw new RuntimeException("Diretório pai não encontrado!");
            }
            path = path.resolve(diretorioPai.getNome());
        }

        Files.createDirectories(path.resolve(diretorioTransfer.getNome()));
        logger.info("Diretório criado no sistema de arquivos: {}", path.resolve(diretorioTransfer.getNome()));
        return path.resolve(diretorioTransfer.getNome()).toString();
    }

    private DiretorioResponseTransfer criarDiretorioNoBancoDeDados(DiretorioRequestTransfer diretorioRequestTransfer) {
        List<DiretorioEntity> diretorioList = diretorioInterfaceImplementacaoRepository
                .verificarSeDiretorioExistente(diretorioRequestTransfer.getNome(), diretorioRequestTransfer.getCodeDiretorioPai());
        if (!diretorioList.isEmpty()) {
            logger.error("Diretório já cadastrado no Banco de Dados: {}", diretorioRequestTransfer.getNome());
            throw new DiretorioCadastradoNoBancoDeDadosException(String.format("Diretório já cadastrado no Banco de Dados!", diretorioRequestTransfer.getNome()));
        }
        DiretorioEntity diretorioEntity = new DiretorioEntity();
            diretorioEntity.setCodeDiretorioPai(diretorioRequestTransfer.getCodeDiretorioPai());
            diretorioEntity.setNome(diretorioRequestTransfer.getNome());
            diretorioEntity.setCodePublic(gerarCodePublic());
            diretorioEntity.setTamanho("0");
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

    public List<DiretorioResponseTransfer> recuperarTodosOsDiretoriosDeUmDiretorioPai(Long codigoDiretorioPai) {
        return diretorioRepository.
                findByCodeDiretorioPai(codigoDiretorioPai)
                .stream()
                .map(DiretorioEntity::toTransfer)
                .toList();
    }

}
