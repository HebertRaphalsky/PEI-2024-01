package br.org.rfdouro.demoauth.services;

import org.springframework.stereotype.Service;

import br.org.rfdouro.demoauth.data.perfilUsuario.Professor;

@Service
public class FindService {
 public String DadosUser(Professor u){
  return u.getNome();
 }
}
