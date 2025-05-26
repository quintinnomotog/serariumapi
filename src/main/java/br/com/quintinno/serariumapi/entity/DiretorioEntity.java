package br.com.quintinno.serariumapi.entity;

import java.time.LocalDateTime;

import br.com.quintinno.serariumapi.transfer.DiretorioResponseTransfer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_diretorio")
public class DiretorioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code", nullable = false)
    private Long code;

    @Column(name = "code_diretorio_pai")
    private Long codeDiretorioPai;

    @Column(name = "nome", length = 255, unique = true, nullable = false)
    private String nome;

    @Column(name = "code_public", length = 255, unique = true, nullable = false)
    private String codePublic;

    @Column(name = "tamanho", length = 10)
    private String tamanho;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public DiretorioEntity() {}

    public DiretorioEntity(Long codeDiretorioPai, String nome, String tamanho) {
        this.codeDiretorioPai = codeDiretorioPai;
        this.nome = nome;
        this.tamanho = tamanho;
    }

    public static DiretorioResponseTransfer toTransfer(DiretorioEntity diretorioEntity) {
        DiretorioResponseTransfer diretorioResponseTransfer = new DiretorioResponseTransfer();
            diretorioResponseTransfer.setCode(diretorioEntity.getCode());
            diretorioResponseTransfer.setCodeDiretorioPai(diretorioEntity.getCodeDiretorioPai());
            diretorioResponseTransfer.setCodePublic(diretorioEntity.getCodePublic());
            diretorioResponseTransfer.setNome(diretorioEntity.getNome());
        return diretorioResponseTransfer;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

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

    public String getCodePublic() {
        return codePublic;
    }

    public void setCodePublic(String codePublic) {
        this.codePublic = codePublic;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

}
