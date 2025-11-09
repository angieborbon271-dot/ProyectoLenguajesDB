package com.leones.controller;

import com.leones.domain.TipoCambio;
import com.leones.service.TipoCambioService;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tipoCambio")
public class TipoCambioController {

    @Autowired
    private TipoCambioService tipoCambioService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = tipoCambioService.getTiposCambio();
        model.addAttribute("tiposCambio", lista);
        model.addAttribute("totalTiposCambio", lista.size());
        return "/tipoCambio/listado";
    }

    @PostMapping("/guardar")
    public String guardar(TipoCambio tipoCambio, RedirectAttributes redirectAttributes) {
        tipoCambioService.save(tipoCambio);
        redirectAttributes.addFlashAttribute("todoOk",
                messageSource.getMessage("mensaje.actualizado", null, Locale.getDefault()));
        return "redirect:/tipoCambio/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(TipoCambio tipoCambio, RedirectAttributes redirectAttributes) {
        tipoCambio = tipoCambioService.getTipoCambio(tipoCambio);
        if (tipoCambio == null) {
            redirectAttributes.addFlashAttribute("error",
                    messageSource.getMessage("tipoCambio.error01", null, Locale.getDefault()));
        } else if (tipoCambioService.delete(tipoCambio)) {
            redirectAttributes.addFlashAttribute("todoOk",
                    messageSource.getMessage("mensaje.eliminado", null, Locale.getDefault()));
        } else {
            redirectAttributes.addFlashAttribute("error",
                    messageSource.getMessage("tipoCambio.error02", null, Locale.getDefault()));
        }
        return "redirect:/tipoCambio/listado";
    }

    @PostMapping("/modificar")
    public String modificar(TipoCambio tipoCambio, Model model) {
        tipoCambio = tipoCambioService.getTipoCambio(tipoCambio);
        model.addAttribute("tipoCambio", tipoCambio);
        return "/tipoCambio/modifica";
    }
}
