package br.com.quintinno.serariumapi.transfer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DiretorioResponseTransfer {

    private Long code;

    private Long codeDiretorioPai;

    private String codePublic;

    @NotBlank(message = "O nome do diretório não pode estar vazio!")
    @NotNull(message = "O nome do diretório não pode ser nulo!")
    @Size(max = 255, message = "O nome do diretório não pode ter mais de 255 caracteres!")
    private String nome;

    public Long getCodeDiretorioPai() {
        return codeDiretorioPai;
    }

    public void setCodeDiretorioPai(Long codeDiretorioPai) {
        this.codeDiretorioPai = codeDiretorioPai;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getCodePublic() {
        return codePublic;
    }

    public void setCodePublic(String codePublic) {
        this.codePublic = codePublic;
    }

}
