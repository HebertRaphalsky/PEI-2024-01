package br.org.rfdouro.demorest.model;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author romulo
 *
 * essa classe usa o mecanismo de ferramentas do Spring para prover uma forma fácil de interagir com
 * dados da nossa aplicação
 *
 * a anotação Repository insere uma classe anônima gerenciada pela aplicação que traz métodos para
 * inserir, excluir etc entidades do tipo Projeto
 */
@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

 /**
  * procura projetos com uma determinada descrição
  * sem importar maiúsculas ou minúsculas
  * @param descricao
  * @return 
  */
 public List<Projeto> findByDescricaoIgnoreCase(String descricao);

 /**
  * pesquisa projetos com um 'pedaço' da descrição
  * independente de maiúsculas ou minúsculas
  * nesse caso usa-se uma Query
  * com as funções UPPER - para transformar em maiúscula - e CONCAT - para concatenar com os sinais de '%'
  * @param descricao
  * @return 
  */
 @Query("select T from Projeto T where UPPER(T.descricao) like UPPER(CONCAT('%', ?1,  '%'))")
 public List<Projeto> procuraPorDescricao(String descricao);
 
 /**
  * faz a mesma coisa do método anterior mas agora pode receber ordenação
  * @param descricao
  * @param sort
  * @return 
  */
 @Query("select T from Projeto T where UPPER(T.descricao) like UPPER(CONCAT('%', ?1,  '%'))")
 public List<Projeto> procuraPorDescricao(String descricao, Sort sort);
}
