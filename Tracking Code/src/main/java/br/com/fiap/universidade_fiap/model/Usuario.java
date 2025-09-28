package br.com.fiap.universidade_fiap.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senha;
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_funcao_tab",
        joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_funcao"))
    private Set<Funcao> funcoes = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_setor_tab",
        joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_setor"))
    private Set<Setor> setores = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_endereco_tab",
        joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_endereco"))
    private Set<Endereco> enderecos = new HashSet<>();

    @Transient
    private String cep;

    @Transient
    private Long id_setor;

    public Usuario() {}

    public Usuario(Long id, String senha, String nome, Set<Funcao> funcoes, Set<Setor> setores) {
        this.id = id;
        this.senha = senha;
        this.nome = nome;
        this.funcoes = funcoes;
        this.setores = setores;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Set<Funcao> getFuncoes() { return funcoes; }
    public void setFuncoes(Set<Funcao> funcoes) { this.funcoes = funcoes; }

    public Set<Setor> getSetores() { return setores; }
    public void setSetores(Set<Setor> setores) { this.setores = setores; }

    public Set<Endereco> getEnderecos() { return enderecos; }
    public void setEnderecos(Set<Endereco> enderecos) { this.enderecos = enderecos; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public Long getId_setor() { return id_setor; }
    public void setId_setor(Long id_setor) { this.id_setor = id_setor; }
}
