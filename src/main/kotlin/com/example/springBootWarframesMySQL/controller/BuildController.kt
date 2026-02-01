package com.example.springBootWarframesMySQL.controller

import com.example.springBootWarframesMySQL.service.BuildService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

class BuildForm(
    var name: String = "",
    var warframeId: Int = 0,
    var modIds: List<Int> = emptyList()
)


@Controller
@RequestMapping("/builds")
class BuildController(
    private val buildService: BuildService
) {

    // Lista de builds
    @GetMapping
    fun listarBuilds(model: Model): String {
        model.addAttribute("builds", buildService.listarBuilds())
        return "builds"
    }

    // Detalle de una build
    @GetMapping("/{id}")
    fun detalleBuild(@PathVariable id: Int, model: Model): String {
        val build = buildService.obtenerBuild(id)
        model.addAttribute("build", build)
        return "detalleBuild"
    }

    // Formulario (GET)
    @GetMapping("/add")
    fun mostrarFormulario(model: Model): String {
        model.addAttribute("form", BuildForm())
        model.addAttribute("warframes", buildService.listarWarframes())
        model.addAttribute("mods", buildService.listarMods())
        model.addAttribute("edit", false)

        return "formularioBuild"
    }

    // Procesar formulario (POST)
    @PostMapping("/guardar")
    fun guardarBuild(@ModelAttribute("form") form: BuildForm): String {
        buildService.guardarBuild(
            form.name,
            form.warframeId,
            form.modIds
        )
        return "redirect:/builds"
    }

    @GetMapping("/editar/{id}")
    fun editarBuild(@PathVariable id: Int, model: Model): String {
        val build = buildService.obtenerBuild(id)

        model.addAttribute("build", build)
        model.addAttribute("warframes", buildService.listarWarframes())
        model.addAttribute("mods", buildService.listarMods())
        model.addAttribute("edit", true)

        return "formularioBuild"
    }

    @PostMapping("/actualizar")
    fun actualizarBuild(
        @RequestParam buildId: Int,
        @RequestParam name: String,
        @RequestParam warframeId: Int,
        @RequestParam modIds: List<Int>
    ): String {
        buildService.actualizarBuild(buildId, name, warframeId, modIds)
        return "redirect:/builds"
    }


    @GetMapping("/borrar/{id}")
    fun borrarBuild(@PathVariable id: Int): String {
        buildService.borrarBuild(id)
        return "redirect:/builds"
    }

}
