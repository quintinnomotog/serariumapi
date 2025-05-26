package br.com.quintinno.serariumapi.enumeration;

public enum ConstanteUtilityEnumeration {

    CHAVE_ENDERECO_DIRETORIO("ENDERECO_DIRETORIO");

    private final String chave;

    ConstanteUtilityEnumeration(String chave) {
        this.chave = chave;
    }

    public String getChave() {
        return chave;
    }
    
}
