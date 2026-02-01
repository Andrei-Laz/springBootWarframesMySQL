package com.example.springBootWarframesMySQL.service

import com.example.springBootWarframesMySQL.model.Build
import com.example.springBootWarframesMySQL.model.Warframe
import com.example.springBootWarframesMySQL.model.Modification
import com.example.springBootWarframesMySQL.repository.BuildRepository
import com.example.springBootWarframesMySQL.repository.WarframeRepository
import com.example.springBootWarframesMySQL.repository.ModificationRepository
import org.springframework.stereotype.Service

@Service
class BuildService(
    private val buildRepository: BuildRepository,
    private val warframeRepository: WarframeRepository,
    private val modificationRepository: ModificationRepository
) {

    fun listarBuilds(): List<Build> =
        buildRepository.findAll()

    fun obtenerBuild(id: Int): Build =
        buildRepository.findById(id).orElseThrow()

    // Para los desplegables
    fun listarWarframes(): List<Warframe> =
        warframeRepository.findAll()

    fun listarMods(): List<Modification> =
        modificationRepository.findAll()

    fun guardarBuild(
        name: String,
        warframeId: Int,
        modIds: List<Int>
    ) {
        val warframeRef = warframeRepository.findById(warframeId).orElseThrow()

        val mods = modificationRepository.findAllById(modIds).toMutableSet()

        val nuevaBuild = Build(
            name = name,
            warframe = warframeRef,
            mods = mods
        )

        buildRepository.save(nuevaBuild)
    }

    fun actualizarBuild(
        buildId: Int,
        name: String,
        warframeId: Int,
        modIds: List<Int>
    ) {
        val build = buildRepository.findById(buildId).orElseThrow()
        val warframe = warframeRepository.findById(warframeId).orElseThrow()
        val mods = modificationRepository.findAllById(modIds).toMutableSet()

        build.name = name
        build.warframe = warframe
        build.mods.clear()
        build.mods.addAll(mods)

        buildRepository.save(build)
    }


    fun borrarBuild(id: Int) {
        if (!buildRepository.existsById(id)) {
            throw RuntimeException("Build no encontrada")
        }
        buildRepository.deleteById(id)
    }
}
