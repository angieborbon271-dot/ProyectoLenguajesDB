package com.leones.controller;

import com.leones.domain.TipoActividad;
import com.leones.service.TipoActividadService;
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
@RequestMapping("/tipoActividad")
public class TipoActividadController {

    @Autowired
    private TipoActividadService tipoActividadService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = tipoActividadService.getTiposActividad();
        model.addAttribute("tiposActividad", lista);
        model.addAttribute("totalTiposActividad", lista.size());
        return "/tipoActividad/listado";
    }

    @PostMapping("/guardar")
    public String guardar(TipoActividad tipoActividad, RedirectAttributes redirectAttributes) {
        tipoActividadService.save(tipoActividad);
        redirectAttributes.addFlashAttribute("todoOk",
                messageSource.getMessage("mensaje.actualizado", null, Locale.getDefault()));
        return "redirect:/tipoActividad/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(TipoActividad tipoActividad, RedirectAttributes redirectAttributes) {
        tipoActividad = tipoActividadService.getTipoActividad(tipoActividad);
        if (tipoActividad == null) {
            redirectAttributes.addFlashAttribute("error",
                    messageSource.getMessage("tipoActividad.error01", null, Locale.getDefault()));
        } else if (tipoActividadService.delete(tipoActividad)) {
            redirectAttributes.addFlashAttribute("todoOk",
                    messageSource.getMessage("mensaje.eliminado", null, Locale.getDefault()));
        } else {
            redirectAttributes.addFlashAttribute("error",
                    messageSource.getMessage("tipoActividad.error02", null, Locale.getDefault()));
        }
        return "redirect:/tipoActividad/listado";
    }

    @PostMapping("/modificar")
    public String modificar(TipoActividad tipoActividad, Model model) {
        tipoActividad = tipoActividadService.getTipoActividad(tipoActividad);
        model.addAttribute("tipoActividad", tipoActividad);
        return "/tipoActividad/modifica";
    }
}
