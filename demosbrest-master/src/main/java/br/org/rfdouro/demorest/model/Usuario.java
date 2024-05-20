package br.org.rfdouro.demorest.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author romulo
 */
@Getter
@Setter
@Entity
@NoArgsConstructor @AllArgsConstructor
/*
indica que a tabela vinculada ao modelo será chamada de Usuario
também adiciona um índice impedindo que campos de login se repitam
*/
@Table(name = "Usuario", indexes = {
 @Index(columnList = "login", unique = true, name = "IDX_USUARIO_LOGIN")
})
public class Usuario implements Serializable {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "Usuario_ID")
 private Long id;
 @Column(name = "login", nullable = false, length = 100)
 private String login;
 private String password;
 private String nome;
 private String sobrenome;
 private String curso;
 private String periodo;


 /**
  * Esse será o campo de permissões
  * ele será uma string com nomes de permissões separados por vírgula
  * por exemplo:
  * "ADMIN,ALUNO,TAREFA,ALUNO,CONVIDADO"
  */
 private String permissoes;

 @JsonIgnore
 @ManyToAny(fetch = FetchType.LAZY)
 List<Projeto> projetosMembros;

 TIPOUSUARIO tipo;
}
