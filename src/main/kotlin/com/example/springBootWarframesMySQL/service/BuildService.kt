package com.example.springBootWarframesMySQL.service

import com.example.springBootWarframesMySQL.model.Build
import com.example.springBootWarframesMySQL.repository.BuildRepository
import com.example.springBootWarframesMySQL.repository.WarframeRepository
import com.example.springBootWarframesMySQL.repository.ModificationRepository
import org.springframework.stereotype.Service

@Service
class BuildService(
    private val buildRepository: BuildRepository,
    private val warframeRepository: WarframeRepository,
    private val modRepository: ModificationRepository
) {
    fun getAllBuilds(): List<Build> = buildRepository.findAll()

    fun getBuildByWarframeId(warframeId: Int): List<Build> = buildRepository.findByWarframeId(warframeId)

    fun getBuildByModId(modId: Int): List<Build> = buildRepository.findByModId(modId)

    fun getBuild(warframeId: Int, modId: Int): Build? = buildRepository.findById_IdWarframeAndId_IdMod(warframeId, modId)

    fun saveBuild(warframeId: Int, modId: Int): Build? {
        val warframe = warframeRepository.findById(warframeId).orElse(null)
        val mod = modRepository.findById(modId).orElse(null)

        return if (warframe != null && mod != null) {
            val build = Build(
                id = com.example.springBootWarframesMySQL.model.BuildId(idWarframe = warframeId, idMod = modId),
                warframe = warframe,
                modification = mod
            )
            buildRepository.save(build)
        } else {
            null // No se puede crear la build si no existen warframe o mod
        }
    }

    fun deleteBuild(warframeId: Int, modId: Int) {
        buildRepository.deleteById_IdWarframeAndId_IdMod(warframeId, modId)
    }

    fun deleteBuildsByWarframeId(warframeId: Int) {
        buildRepository.deleteByWarframeId(warframeId)
    }

    fun deleteBuildsByModId(modId: Int) {
        buildRepository.deleteByModId(modId)
    }
}