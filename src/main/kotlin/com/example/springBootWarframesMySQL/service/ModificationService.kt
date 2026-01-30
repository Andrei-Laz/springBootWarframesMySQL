package com.example.springBootWarframesMySQL.service

import com.example.springBootWarframesMySQL.model.Modification
import com.example.springBootWarframesMySQL.repository.ModificationRepository
import org.springframework.stereotype.Service
import java.io.File

@Service
class ModificationService(private val repository: ModificationRepository) {
    fun getMods(): List<Modification> = repository.findAll()

    fun getById(id: Int): Modification? = repository.findById(id).orElse(null)

    fun post(modification: Modification): Modification = repository.save(modification)

    fun delete(id: Int) {
        if (repository.existsById(id)) {
            repository.deleteById(id)
        }
    }

    fun importFromCSV() {
        // Solo importar si la base de datos está vacía
        if (repository.count() == 0L) {
            val filePath = "src/main/resources/data/mods.csv"
            val file = File(filePath)

            if (file.exists()) {
                println("El archivo de mods existe, importando datos...")
                val modificationBuffer = file.readLines().drop(1).map { line -> // drop(1) para omitir la cabecera
                    val parts = line.split(";")
                    Modification(
                        modId = null,
                        name = parts[1],
                        rarity = parts[2],
                        cost = parts[3].toInt(),
                        polarity = parts[4],
                        description = parts[5]
                    )
                }
                repository.saveAll(modificationBuffer)
                println("Datos de mods importados exitosamente.")
            } else {
                println("El archivo CSV de mods no existe: $filePath")
            }
        } else {
            println("La base de datos de mods ya contiene datos. No se realizará la importación.")
        }
    }
}