package br.com.quintinno.serariumapi.transfer;

public class DiretorioTransfer {

    private Long code;

    private Long codeDiretorioPai;

    private String nome;

    private String rotulo;

    private String tamanho;

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

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

}
