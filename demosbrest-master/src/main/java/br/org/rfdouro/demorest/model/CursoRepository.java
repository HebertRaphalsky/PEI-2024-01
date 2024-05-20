package br.org.rfdouro.demorest.model;

import org.springframework.data.jpa.repository.JpaRepository;
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
public interface CursoRepository extends JpaRepository<Curso, Long> {

 /**
  * procura cursos
  * sem importar maiúsculas ou minúsculas
  * @param curso
  * @return 
  */
  public Curso findByCurso(String curso);

}
