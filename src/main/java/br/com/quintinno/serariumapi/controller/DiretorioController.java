package br.com.quintinno.serariumapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.quintinno.serariumapi.service.DiretorioService;
import br.com.quintinno.serariumapi.transfer.DiretorioRequestTransfer;
import br.com.quintinno.serariumapi.transfer.DiretorioResponseTransfer;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/diretorio")
public class DiretorioController {

    private final DiretorioService diretorioService;

    public DiretorioController(DiretorioService diretorioService) {
        this.diretorioService = diretorioService;
    }

    @PostMapping
    public DiretorioResponseTransfer create(@Valid @RequestBody DiretorioRequestTransfer diretorioTransfer) {
        return diretorioService.create(diretorioTransfer);
    }

}
