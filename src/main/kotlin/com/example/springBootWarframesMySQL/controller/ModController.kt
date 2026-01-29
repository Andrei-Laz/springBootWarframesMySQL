package com.example.springBootWarframesMySQL.controller

import com.example.springBootWarframesMySQL.model.Mod
import com.example.springBootWarframesMySQL.service.ModService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class ModController(private val modService: ModService) {

    @GetMapping("/mods")
    fun listar(model: Model): String {
        model.addAttribute("mods", modService.getMods())
        return "mods"
    }

    @GetMapping("/mod/{id}")
    fun detalle(@PathVariable id: Int, model: Model): String {
        val mod = modService.getById(id) ?: return "errorMod"
        model.addAttribute("mod", mod)
        return "detalleMod"
    }

    @GetMapping("/mods/nuevo")
    fun nuevoMod(model: Model): String {
        val modVacio = Mod(
            modId = null,
            name = "",
            rarity = "",
            cost = 0,
            polarity = "",
            description = ""
        )
        model.addAttribute("mod", modVacio)
        model.addAttribute("titulo", "Nueva Mod")
        return "formularioMod"
    }

    @GetMapping("/mods/editar/{id}")
    fun editarMod(@PathVariable id: Int, model: Model): String {
        val mod = modService.getById(id) ?: return "redirect:/mods"
        model.addAttribute("mod", mod)
        model.addAttribute("titulo", "Editar Mod")
        return "formularioMod"
    }

    @PostMapping("/mods/guardar")
    fun guardarMod(@ModelAttribute mod: Mod): String {
        modService.post(mod)
        return "redirect:/mods"
    }

    @GetMapping("/mods/borrar/{id}")
    fun borrarMod(@PathVariable id: Int): String {
        modService.delete(id)
        return "redirect:/mods"
    }

    // Ruta para ejecutar la importación
    @GetMapping("/importarMods")
    fun importarDatos(): String {
        modService.importFromCSV()
        return "redirect:/mods"
    }
}