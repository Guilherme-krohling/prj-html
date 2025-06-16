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

import com.prj.html.model.Indisponibilidade;
import com.prj.html.model.IndisponibilidadeService;

@Controller
public class MainController {
    @Autowired
    ApplicationContext ctx;

    @GetMapping("/")
    public String landing(){
        return "landing";
    }


    @GetMapping("/formulario")
    public String form(Model model){
        model.addAttribute("Pessoa", new Indisponibilidade());
        model.addAttribute("titulo", "CADASTRO DE Indisponibilidade");
        model.addAttribute("link", "/cadastro");
        model.addAttribute("valor", "Salvar");
        return "formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(Model model, @PathVariable int id){
        IndisponibilidadeService cs = ctx.getBean(IndisponibilidadeService.class);
        Indisponibilidade velho = cs.buscarPorId(id);
        model.addAttribute("Pessoa", velho);
        model.addAttribute("titulo", "EDITAR Indisponibilidade");
        model.addAttribute("link", "/editar/" + id);
        model.addAttribute("valor", "Editar");
        return "formulario";
    }

    @PostMapping("/editar/{id}")
    public String editar(@ModelAttribute Indisponibilidade ind, @PathVariable int id){
        IndisponibilidadeService cs = ctx.getBean(IndisponibilidadeService.class);
        cs.atualizar(id, ind);
        return "redirect:/listar";
    }

    @PostMapping("/cadastro")
    public String cadastro(@ModelAttribute Indisponibilidade ind){
        IndisponibilidadeService cs = ctx.getBean(IndisponibilidadeService.class);
        cs.inserir(ind);
        return "redirect:/listar";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        IndisponibilidadeService cs = ctx.getBean(IndisponibilidadeService.class);
        List<Indisponibilidade> lista = cs.listarTodas();
        model.addAttribute("Indisponibilides", lista);
        return "listar";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable int id){
        IndisponibilidadeService cs = ctx.getBean(IndisponibilidadeService.class);
        cs.deletar(id);
        return "redirect:/listar";
    }
}
