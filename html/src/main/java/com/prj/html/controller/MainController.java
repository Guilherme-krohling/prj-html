package com.prj.html.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.prj.html.model.Pessoa;
import com.prj.html.model.PessoaService;

@Controller
public class MainController {
    @Autowired
    ApplicationContext ctx;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/sucesso")
    public String sucesso(){
        return "sucesso";
    }

    @GetMapping("/formulario")
    public String form(Model model){
        //QUERO UM Pessoa VAZIO NA INICIALIZACAO DO FORM
        model.addAttribute("Pessoa", new Pessoa());
        model.addAttribute("titulo", "CADASTRO DE PessoaS");
        model.addAttribute("link", "/cadastro");
        model.addAttribute("valor", "Cadastrar");
        return "formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(Model model, @PathVariable int id){
        PessoaService cs = ctx.getBean(PessoaService.class);
        Pessoa velho = cs.puxarPessoa(id);
        model.addAttribute("Pessoa", velho);
        model.addAttribute("titulo", "EDITAR PessoaS");
        model.addAttribute("link", "/editar/" + id);
        model.addAttribute("valor", "Editar");
        return "formulario";
    }

    @PostMapping("/editar/{id}")
    public String editar(Model model, 
                         @ModelAttribute Pessoa cli, 
                         @PathVariable int id){
        PessoaService cs = ctx.getBean(PessoaService.class);
        cs.atualizarPessoa(id, cli);
        return "redirect:/listar";
    }

    @PostMapping("/cadastro")
    public String cadastro(Model model, @ModelAttribute Pessoa cli){
        PessoaService cs = ctx.getBean(PessoaService.class);
        cs.inserirPessoa(cli);
        return "redirect:listar";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        PessoaService cs = ctx.getBean(PessoaService.class);
        List<Pessoa> lista = cs.puxarTodasPessoas();
        model.addAttribute("Pessoas", lista);
        return "listar";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable int id){
        PessoaService cs = ctx.getBean(PessoaService.class);
        cs.deletar(id);
        return "redirect:/listar";
    }
}
