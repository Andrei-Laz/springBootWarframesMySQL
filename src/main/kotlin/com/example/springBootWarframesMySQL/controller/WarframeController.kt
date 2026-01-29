package com.example.springBootWarframesMySQL.controller

import com.example.springBootWarframesMySQL.model.Warframe
import com.example.springBootWarframesMySQL.service.WarframeService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class WarframeController(private val warframeService: WarframeService) {

    @GetMapping("/warframes")
    fun listar(model: Model): String {
        model.addAttribute("warframes", warframeService.getWarframes())
        return "warframes"
    }

    @GetMapping("/warframe/{id}")
    fun detalle(@PathVariable id: Int, model: Model): String {
        val warframe = warframeService.getById(id) ?: return "errorWarframe"
        model.addAttribute("warframe", warframe)
        return "detalleWarframe"
    }

    @GetMapping("/warframes/nuevo")
    fun nuevoWarframe(model: Model): String {
        val warframeVacio = Warframe(
            warframeId = null,
            name = "",
            health = 0,
            armor = 0,
            energy = 0,
            sprintSpeed = 0.0,
            passive = ""
        )
        model.addAttribute("warframe", warframeVacio)
        model.addAttribute("titulo", "Nuevo Warframe")
        return "formularioWarframe"
    }

    @GetMapping("/warframes/editar/{id}")
    fun editarWarframe(@PathVariable id: Int, model: Model): String {
        val warframe = warframeService.getById(id) ?: return "redirect:/warframes"
        model.addAttribute("warframe", warframe)
        model.addAttribute("titulo", "Editar Warframe")
        return "formularioWarframe"
    }

    @PostMapping("/warframes/guardar")
    fun guardarWarframe(@ModelAttribute warframe: Warframe): String {
        warframeService.post(warframe)
        return "redirect:/warframes"
    }

    @GetMapping("/warframes/borrar/{id}")
    fun borrarWarframe(@PathVariable id: Int): String {
        warframeService.delete(id)
        return "redirect:/warframes"
    }

    // Ruta para ejecutar la importación
    @GetMapping("/importar")
    fun importarDatos(): String {
        warframeService.importFromCSV()
        return "redirect:/warframes"
    }
}