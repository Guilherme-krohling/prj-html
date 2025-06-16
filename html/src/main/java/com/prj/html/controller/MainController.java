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

        @GetMapping("/landing")
        public String landing() {
            return "landing";
        }

        @GetMapping("/formulario")
        public String form(Model model) {
            model.addAttribute("indisponibilidade", new Indisponibilidade());
            model.addAttribute("titulo", "CADASTRO DE INDISPONIBILIDADES");
            model.addAttribute("link", "/cadastro");
            model.addAttribute("valor", "Cadastrar");
            return "formulario";
        }

        @GetMapping("/editar/{id}")
        public String editar(Model model, @PathVariable int id) {
            IndisponibilidadeService service = ctx.getBean(IndisponibilidadeService.class);
            Indisponibilidade antigo = service.buscarPorId(id);
            model.addAttribute("indisponibilidade", antigo);
            model.addAttribute("titulo", "EDITAR INDISPONIBILIDADE");
            model.addAttribute("link", "/editar/" + id);
            model.addAttribute("valor", "Editar");
            return "formulario";
        }

        @PostMapping("/editar/{id}")
        public String editar(Model model, @ModelAttribute Indisponibilidade novo, @PathVariable int id) {
            IndisponibilidadeService service = ctx.getBean(IndisponibilidadeService.class);
            service.atualizar(id, novo);
            return "redirect:/listar";
        }

        @PostMapping("/cadastro")
        public String cadastro(Model model, @ModelAttribute Indisponibilidade novo) {
            IndisponibilidadeService service = ctx.getBean(IndisponibilidadeService.class);
            service.inserir(novo);
            return "redirect:/listar";
        }

        @GetMapping("/listar")
        public String listar(Model model) {
            IndisponibilidadeService service = ctx.getBean(IndisponibilidadeService.class);
            List<Indisponibilidade> lista = service.listarTodas();
            model.addAttribute("indisponibilidades", lista);
            return "listar";
        }

        @PostMapping("/deletar/{id}")
        public String deletar(@PathVariable int id) {
            IndisponibilidadeService service = ctx.getBean(IndisponibilidadeService.class);
            service.deletar(id);
            return "redirect:/listar";
        }

        @InitBinder
        public void initBinder(WebDataBinder binder) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        }
    }
