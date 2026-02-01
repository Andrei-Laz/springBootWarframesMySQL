package com.example.springBootWarframesMySQL.repository

import com.example.springBootWarframesMySQL.model.Build
import com.example.springBootWarframesMySQL.model.BuildId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface BuildRepository : JpaRepository<Build, BuildId> {

    // Consulta personalizada por ID de Warframe
    @Query("SELECT b FROM Build b WHERE b.id.idWarframe = :warframeId")
    fun findByWarframeId(@Param("warframeId") warframeId: Int): List<Build>

    // Consulta personalizada por ID de Mod
    @Query("SELECT b FROM Build b WHERE b.id.idMod = :modId")
    fun findByModId(@Param("modId") modId: Int): List<Build>

    // Consulta personalizada para encontrar una build específica
    @Query("SELECT b FROM Build b WHERE b.id.idWarframe = :warframeId AND b.id.idMod = :modId")
    fun findById_IdWarframeAndId_IdMod(@Param("warframeId") warframeId: Int, @Param("modId") modId: Int): Build?

    // Consultas para eliminar builds - USANDO @Query con @Modifying
    @Modifying
    @Transactional
    @Query("DELETE FROM Build b WHERE b.id.idWarframe = :warframeId")
    fun deleteByWarframeId(@Param("warframeId") idWarframe: Int)

    @Modifying
    @Transactional
    @Query("DELETE FROM Build b WHERE b.id.idMod = :modId")
    fun deleteByModId(@Param("modId") idMod: Int)

    // Eliminar build específica
    @Modifying
    @Transactional
    fun deleteById_IdWarframeAndId_IdMod(idWarframe: Int, idMod: Int)
}