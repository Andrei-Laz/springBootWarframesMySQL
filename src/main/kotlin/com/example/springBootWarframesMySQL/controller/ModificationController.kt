package com.example.springBootWarframesMySQL.controller

import com.example.springBootWarframesMySQL.model.Modification
import com.example.springBootWarframesMySQL.service.ModificationService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class ModificationController(private val modificationService: ModificationService) {

    @GetMapping("/mods")
    fun listar(model: Model): String {
        model.addAttribute("mods", modificationService.getMods())
        return "mods"
    }

    @GetMapping("/mod/{id}")
    fun detalle(@PathVariable id: Int, model: Model): String {
        val mod = modificationService.getById(id) ?: return "errorMod"
        model.addAttribute("modification", mod)
        return "detalleMod"
    }

    @GetMapping("/mods/nuevo")
    fun nuevoMod(model: Model): String {
        val modificationVacio = Modification(
            modId = null,
            name = "",
            rarity = "",
            cost = 0,
            polarity = "",
            description = ""
        )
        /*have to change the name of what is ususally mod to **modification** in the attributeNme because
        thymeleaf SpringBoot already has an operator called mod which confuses the parser*/
        model.addAttribute("modification", modificationVacio)
        model.addAttribute("titulo", "Nueva Mod")
        return "formularioMod"
    }

    @GetMapping("/mods/editar/{id}")
    fun editarMod(@PathVariable id: Int, model: Model): String {
        val mod = modificationService.getById(id) ?: return "redirect:/mods"
        model.addAttribute("modification", mod)
        model.addAttribute("titulo", "Editar Mod")
        return "formularioMod"
    }

    @PostMapping("/mods/guardar")
    fun guardarMod(@ModelAttribute modification: Modification): String {
        modificationService.post(modification)
        return "redirect:/mods"
    }

    @GetMapping("/mods/borrar/{id}")
    fun borrarMod(@PathVariable id: Int): String {
        modificationService.delete(id)
        return "redirect:/mods"
    }

    // Ruta para ejecutar la importación
    @GetMapping("/importarMods")
    fun importarDatos(): String {
        modificationService.importFromCSV()
        return "redirect:/mods"
    }
}