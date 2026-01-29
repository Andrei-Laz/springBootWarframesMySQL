package com.example.springBootWarframesMySQL.service

import com.example.springBootWarframesMySQL.model.Mod
import com.example.springBootWarframesMySQL.repository.ModRepository
import org.springframework.stereotype.Service
import java.io.File

@Service
class ModService(private val repository: ModRepository) {
    fun getMods(): List<Mod> = repository.findAll()

    fun getById(id: Int): Mod? = repository.findById(id).orElse(null)

    fun post(mod: Mod): Mod = repository.save(mod)

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
                val modBuffer = file.readLines().drop(1).map { line -> // drop(1) para omitir la cabecera
                    val parts = line.split(";")
                    Mod(
                        modId = null,
                        name = parts[1],
                        rarity = parts[2],
                        cost = parts[3].toInt(),
                        polarity = parts[4],
                        description = parts[5]
                    )
                }
                repository.saveAll(modBuffer)
                println("Datos de mods importados exitosamente.")
            } else {
                println("El archivo CSV de mods no existe: $filePath")
            }
        } else {
            println("La base de datos de mods ya contiene datos. No se realizará la importación.")
        }
    }
}