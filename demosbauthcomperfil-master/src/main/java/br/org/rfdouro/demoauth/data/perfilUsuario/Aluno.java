package br.org.rfdouro.demoauth.data.perfilUsuario;

import br.org.rfdouro.demoauth.data.Usuario;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Aluno")
public class Aluno extends Usuario {
 private String matricula;
 private String nome;
 private String email;
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

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

 public String getTelefone() {
  return telefone;
 }

 public void setTelefone(String telefone) {
  this.telefone = telefone;
 }


}
