package br.org.rfdouro.demoauth.services;

import org.springframework.stereotype.Service;

import br.org.rfdouro.demoauth.data.Autorizacao;

import br.org.rfdouro.demoauth.data.Usuario;

@Service
public class MascarService {

 public boolean ehAutorizado(Usuario usuario, String nomePermissao) {
  boolean ret = false;
  for (Autorizacao a : usuario.getAutorizacoes()) {
   if (a.getNome().equals(nomePermissao)) {
    ret = true;
   }
  }
  return ret;
 }


 
}
