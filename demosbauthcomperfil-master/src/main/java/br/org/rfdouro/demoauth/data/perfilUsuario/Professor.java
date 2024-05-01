package br.org.rfdouro.demoauth.data.perfilUsuario;

import br.org.rfdouro.demoauth.data.Usuario;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Professor")
public class Professor extends Usuario {
 private String matricula;
 private String nome;
 private String telefone;

 public String getMatricula() {
  return matricula;
 }

 public void setMatricula(String matricula) {
  this.matricula = matricula;
 }

 public String getNome() {
  return nome;
 }

 public void setNome(String nome) {
  this.nome = nome;
 }

 public String getTelefone() {
  return telefone;
 }

 public void setTelefone(String telefone) {
  this.telefone = telefone;
 }


}
