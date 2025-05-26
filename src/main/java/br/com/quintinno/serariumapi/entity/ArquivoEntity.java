package br.com.quintinno.serariumapi.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_arquivo")
public class ArquivoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code", nullable = false)
    private Long code;

    @ManyToOne
    @JoinColumn(name = "id_diretorio", nullable = false)
    private DiretorioEntity diretorioEntity;

    @Column(name = "nome", length = 255, unique = true, nullable = false)
    private String nome;

    @Column(name = "rotulo", length = 100, unique = true, nullable = false)
    private String rotulo;

    @Column(name = "tamanho", length = 10)
    private String tamanho;

    @Column(name = "extensao", length = 5)
    private String extensao;

    @Column(name = "endereco_fisico", length = 255, nullable = false)
    private String enderecoFisico;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public ArquivoEntity() {}

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public DiretorioEntity getDiretorioEntity() {
        return diretorioEntity;
    }

    public void setDiretorioEntity(DiretorioEntity diretorioEntity) {
        this.diretorioEntity = diretorioEntity;
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

    public String getExtensao() {
        return extensao;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }

    public String getEnderecoFisico() {
        return enderecoFisico;
    }

    public void setEnderecoFisico(String enderecoFisico) {
        this.enderecoFisico = enderecoFisico;
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
