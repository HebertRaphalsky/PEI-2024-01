package br.org.rfdouro.demorest.control;

import br.org.rfdouro.demorest.model.Projeto;
import br.org.rfdouro.demorest.model.ProjetoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author romulo
 */
@RestController
@RequestMapping("/projeto")
public class ProjetoController {

 /*
 insere automaticamente um repositório de dados para projetos
  */
 @Autowired
 ProjetoRepository repository;

 /*
 método que retorna a listagem de projetos ordenada por descrição
 atende no endpoint /projeto com verbo GET
  */
 @GetMapping({"", "/"})
 public List<Projeto> getProjetos() {
  return repository.findAll(Sort.by("descricao"));
 }

/*
 método que retorna a listagem de projetos ordenada por descrição
 fazendo uma pesquisa por parte da descrição que está salva no banco de dados
 atende no endpoint /projeto/pesquisa com verbo GET
 e necessita de um parâmetro chamado 'descricao'
  */
 @GetMapping("/pesquisa")
 public List<Projeto> getProjetos(String descricao) {
  return repository.procuraPorDescricao(descricao, Sort.by("descricao"));
 }

 /*
 método que recebe uma projeto enviada na requisição e a insere no banco de dados
 retorta após inserir já com o ID
 atende no endpoint /projeto com verbo POST
 a anotação @RequestBody é importante pois indica que os dados da requisição 
 serão enviados no corpo da requisição (em JSON)
  */
 @PostMapping({"", "/"})
 public Projeto insere(@RequestBody Projeto projeto) {
  return repository.save(projeto);
 }

 /*
 método que recebe uma projeto enviada na requisição (com id preenchido)
 e a atualiza no banco de dados
 retorta a projeto atualizada
 caso não tenha id na requisição retorna null
 atende no endpoint /projeto com verbo PUT
 a anotação @RequestBody é importante pois indica que os dados da requisição 
 serão enviados no corpo da requisição (em JSON)
  */
 @PutMapping({"", "/"})
 public Projeto atualiza(@RequestBody Projeto projeto) {
  if (projeto.getId() != null) {
   return repository.save(projeto);
  }
  return null;
 }

 /*
 método que recebe um id de projeto enviada na requisição 
 caso tenha enviado o id, é excluída no banco de dados
 retorta uma mensagem
 o id é passado no path (caminho da url) por isso
 se usa o @PathVariable indicativo
  */
 @DeleteMapping("/{id}")
 public String atualiza(@PathVariable("id") Long id) {
  if (id != null) {
   repository.deleteById(id);
   return "Excluído";
  }
  return null;
 }

}
