package com.example.springBootWarframesMySQL.controller

import com.example.springBootWarframesMySQL.model.Build
import com.example.springBootWarframesMySQL.service.BuildService
import com.example.springBootWarframesMySQL.service.WarframeService
import com.example.springBootWarframesMySQL.service.ModificationService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class BuildController(
    private val buildService: BuildService,
    private val warframeService: WarframeService,
    private val modService: ModificationService
) {

    @GetMapping("/builds")
    fun listar(model: Model): String {
        model.addAttribute("builds", buildService.getAllBuilds())
        return "builds"
    }

    @GetMapping("/builds/warframe/{warframeId}")
    fun listarPorWarframe(@PathVariable warframeId: Int, model: Model): String {
        val builds = buildService.getBuildByWarframeId(warframeId)
        val warframe = warframeService.getById(warframeId)
        model.addAttribute("builds", builds)
        model.addAttribute("warframe", warframe)
        return "buildsPorWarframe"
    }

    @GetMapping("/builds/nueva")
    fun nuevaBuild(model: Model): String {
        model.addAttribute("warframes", warframeService.getWarframes())
        model.addAttribute("mods", modService.getMods())
        model.addAttribute("build", Build())
        model.addAttribute("titulo", "Nueva Build")
        return "formularioBuild"
    }

    @PostMapping("/builds/guardar")
    fun guardarBuild(
        @RequestParam("warframeId") warframeId: Int,
        @RequestParam("modId") modId: Int
    ): String {
        buildService.saveBuild(warframeId, modId)
        return "redirect:/builds"
    }

    @GetMapping("/builds/borrar/{warframeId}/{modId}")
    fun borrarBuild(@PathVariable warframeId: Int, @PathVariable modId: Int): String {
        buildService.deleteBuild(warframeId, modId)
        return "redirect:/builds"
    }
}