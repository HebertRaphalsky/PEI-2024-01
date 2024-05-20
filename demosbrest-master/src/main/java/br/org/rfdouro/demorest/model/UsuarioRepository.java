package br.org.rfdouro.demorest.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
 *
 * @author romulo
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

 public Usuario findByLogin(String login);

 public List<Usuario> findAllByTipoOrderByLogin(TIPOUSUARIO tipo);
}
