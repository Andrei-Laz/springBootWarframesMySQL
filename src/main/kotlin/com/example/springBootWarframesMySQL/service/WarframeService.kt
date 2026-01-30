package com.example.springBootWarframesMySQL.service

import com.example.springBootWarframesMySQL.model.Warframe
import com.example.springBootWarframesMySQL.repository.WarframeRepository
import org.springframework.stereotype.Service
import java.io.File

@Service
class   WarframeService(private val repository: WarframeRepository) {
    fun getWarframes(): List<Warframe> = repository.findAll()

    fun getById(id: Int): Warframe? = repository.findById(id).orElse(null)

    fun post(warframe: Warframe): Warframe = repository.save(warframe)

    fun delete(id: Int) {
        if (repository.existsById(id)) {
            repository.deleteById(id)
        }
    }

    fun importFromCSV() {
        // Solo importar si la base de datos está vacía
        if (repository.count() == 0L) {
            val filePath = "src/main/resources/data/warframes.csv"
            val file = File(filePath)

            if (file.exists()) {
                println("El archivo existe, importando datos...")
                val warframeBuffer = file.readLines().drop(1).map { line -> // drop(1) para omitir la cabecera
                    val parts = line.split(";")
                    Warframe(
                        warframeId = null,
                        name = parts[1],
                        health = parts[2].toInt(),
                        armor = parts[3].toInt(),
                        energy = parts[4].toInt(),
                        sprintSpeed = parts[5].toDouble(),
                        passive = parts[6]
                    )
                }
                repository.saveAll(warframeBuffer)
                println("Datos importados exitosamente.")
            } else {
                println("El archivo CSV no existe: $filePath")
            }
        } else {
            println("La base de datos ya contiene datos. No se realizará la importación.")
        }
    }
}