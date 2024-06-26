package br.org.rfdouro.demorest.config;

import br.org.rfdouro.demorest.filter.AutFilterAdmin;
import br.org.rfdouro.demorest.filter.AutFilterAluno;
import br.org.rfdouro.demorest.filter.AutFilterConvidado;
import br.org.rfdouro.demorest.filter.AutFilterProjeto;
import br.org.rfdouro.demorest.model.Usuario;
import br.org.rfdouro.demorest.model.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 *
 * @author romulo
 */
@Configuration
public class AppConfig implements CommandLineRunner {

 //injetamos um filtro de autenticações de projetos
 @Autowired
 private AutFilterProjeto autFilterProjeto;
 //injetamos um filtro de autenticações
 @Autowired
 private AutFilterAdmin autFilterAdmin;

 @Autowired
 private AutFilterAluno autFilterAluno;

 @Autowired
 private AutFilterConvidado autFilterConvidado;
 
 //atributo para controle de transações
 @Autowired
 private PlatformTransactionManager transactionManager;
 //repositório de usuários
 @Autowired
 UsuarioRepository usuarioRepository;

 /**
  * adicionamos o filtro de login na navegação para projetos
  *
  * @return
  */
 @Bean
 public FilterRegistrationBean<AutFilterProjeto> filterRegistrationBean() {
  FilterRegistrationBean<AutFilterProjeto> registrationBean = new FilterRegistrationBean<>();

  registrationBean.setFilter(autFilterProjeto);
  //aplica-se apenas ao endpoint projeto
  registrationBean.addUrlPatterns("/projeto/*");
  //define a ordem de precedencia do filtro
  registrationBean.setOrder(1);
  return registrationBean;
 }
 
 /**
  * adicionamos o filtro de login na navegação para projetos
  *
  * @return
  */
 @Bean
 public FilterRegistrationBean<AutFilterAdmin> filterRegistrationBeanAdmin() {
  FilterRegistrationBean<AutFilterAdmin> registrationBean = new FilterRegistrationBean<>();

  registrationBean.setFilter(autFilterAdmin);
  //aplica-se apenas ao endpoint admin
  registrationBean.addUrlPatterns("/admin/*");
  //define a ordem de precedencia do filtro
  registrationBean.setOrder(1);
  return registrationBean;
 }

 @Bean
 public FilterRegistrationBean<AutFilterAluno> filterRegistrationBeanAluno() {
  FilterRegistrationBean<AutFilterAluno> registrationBean = new FilterRegistrationBean<>();

  registrationBean.setFilter(autFilterAluno);
  //aplica-se apenas ao endpoint admin
  registrationBean.addUrlPatterns("/aluno/*");
  //define a ordem de precedencia do filtro
  registrationBean.setOrder(1);
  return registrationBean;
 }

 @Bean
 public FilterRegistrationBean<AutFilterConvidado> filterRegistrationBeanConvidado() {
  FilterRegistrationBean<AutFilterConvidado> registrationBean = new FilterRegistrationBean<>();

  registrationBean.setFilter(autFilterConvidado);
  //aplica-se apenas ao endpoint admin
  registrationBean.addUrlPatterns("/convidado/*");
  //define a ordem de precedencia do filtro
  registrationBean.setOrder(1);
  return registrationBean;
 }
 
 /**
  * método responsável por inserir usuários iniciais no banco
  */
 @Transactional
 private void addUsers() {
  //pesquisa -> https://www.baeldung.com/spring-programmatic-transaction-management
  DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
  definition.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
  definition.setTimeout(-1);

  TransactionStatus status = transactionManager.getTransaction(definition);

  try {

   Usuario u = new Usuario();
   u.setLogin("admin");
   u.setPassword("1234");
   u.setPermissoes("ADMIN,PROJETO");
   usuarioRepository.save(u);

   u = new Usuario();
   u.setLogin("usuario1");
   u.setPassword("1234");
   u.setPermissoes("ALUNO,PROJETO");
   usuarioRepository.save(u);

   u = new Usuario();
   u.setLogin("usuario2");
   u.setPassword("1234");
   u.setPermissoes("PROJETO");
   usuarioRepository.save(u);

   transactionManager.commit(status);

  } catch (Exception ex) {
   transactionManager.rollback(status);
  }
 }
 
 /**
  * método executado no momento da execução da aplicação
  * 
  * @param args
  * @throws Exception 
  */
 @Override
 public void run(String... args) throws Exception {
  addUsers();
 }
}
