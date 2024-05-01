package br.org.rfdouro.demoauth.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.org.rfdouro.demoauth.data.AutorizacaoRepository;
import br.org.rfdouro.demoauth.data.Usuario;
import br.org.rfdouro.demoauth.data.UsuarioRepository;
import br.org.rfdouro.demoauth.data.perfilUsuario.Administrador;
import br.org.rfdouro.demoauth.data.perfilUsuario.Aluno;
import br.org.rfdouro.demoauth.data.perfilUsuario.Professor;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AutorizacaoRepository autorizacaoRepository;

    @GetMapping({ "", "/", "/index" })
    public String admin(Model model) {
        model.addAttribute("listaUsuarios", usuarioRepository.findAll(Sort.by("login")));
        return "admin/index";
    }

    @GetMapping({ "/usuario/cadastro" })
    public String abrecadastrousuario(Model model) {
        model.addAttribute("listaAutorizacoes", autorizacaoRepository.findAll(Sort.by("nome")));
        return "admin/cadusuario";
    }

    //@PostMapping(value = "/usuario/cadastro", consumes = "application/x-www-form-urlencoded")
     @PostMapping(value = "/usuario/cadastro", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String cadastrar(Usuario usuario, Administrador adm, Professor prof, Aluno alu, String tipo) {
        // usuarioRepository.save(usuario);
        //Usuario u = new Usuario();
        switch (tipo) {
            case "Administrador":
                usuarioRepository.save(adm);
                break;
            case "Aluno":
                usuarioRepository.save(alu);
                break;
            case "Professor":
                usuarioRepository.save(prof);
                break;
            default:
                break;
        }
        return "redirect:/admin";
    }

    @GetMapping({ "/usuario/delete/{id}" })
    public String deleteusuario(@PathVariable(name = "id", required = true) Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/admin/";
    }

    @PostMapping({ "/usuario/add" })
    public String addusuario(String login, String senha, String tipo, String email, String nome, String telefone) {
        switch (tipo) {
            case "Administrador":
                Administrador adm = new Administrador();
                adm.setLogin(login);
                adm.setSenha(senha);
                usuarioRepository.save(adm);
                break;

            case "Aluno":
                Aluno alu = new Aluno();
                alu.setLogin(login);
                alu.setSenha(senha);
                alu.setEmail(email); // Defina o email
                alu.setNome(nome); // Defina o nome
                alu.setTelefone(telefone); // Defina o telefone
                usuarioRepository.save(alu);
                break;

            case "Professor":
                Professor prof = new Professor();
                prof.setLogin(login);
                prof.setSenha(senha);
                prof.setNome(nome);
                prof.setTelefone(telefone);
                usuarioRepository.save(prof);
                break;
        }
        return "redirect:/admin/";
    }
}
