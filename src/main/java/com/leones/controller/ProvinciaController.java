package com.leones.controller;

import com.leones.domain.Provincia;
import com.leones.service.ProvinciaService;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ErickaT
 */
@Controller
@RequestMapping("/provincia")

public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = provinciaService.getProvincias();
        model.addAttribute("provincias", lista);
        model.addAttribute("totalProvincias", lista.size());
        return "/provincia/listado";
    }

    @Autowired
    private MessageSource messageSource;

    @PostMapping("/guardar")
    public String guardar(Provincia provincia,
            RedirectAttributes redirectAttributes) {

        provinciaService.save(provincia);
        redirectAttributes.addFlashAttribute("todoOk",
                messageSource.getMessage("mensaje.actualizado", null,
                        Locale.getDefault()));

        messageSource.getMessage("mensaje.actualizado",
                null,
                Locale.getDefault());
        return "redirect:/provincia/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(Provincia provincia, RedirectAttributes redirectAttributes) {
        provincia = provinciaService.getProvincia(provincia);
        if (provincia == null) {
            redirectAttributes.addFlashAttribute("error",
                    messageSource.getMessage("provncia.error01", null,
                            Locale.getDefault()));
        } else if (false) {
            redirectAttributes.addFlashAttribute("error",
                    messageSource.getMessage("provincia.error02", null,
                            Locale.getDefault()));
        } else if (provinciaService.delete(provincia)) {
            redirectAttributes.addFlashAttribute("todoOk",
                    messageSource.getMessage("mensaje.eliminado", null,
                            Locale.getDefault()));

        } else {
            redirectAttributes.addFlashAttribute("error",
                    messageSource.getMessage("provincia.error03", null,
                            Locale.getDefault()));
        }
        return "redirect:/provincia/listado";
    }

    @PostMapping("/modificar")
    public String modificar(Provincia provincia, Model model) {
        provincia = provinciaService.getProvincia(provincia);
        model.addAttribute("provincia", provincia);
        return "/provincia/modifica";

    }

}
