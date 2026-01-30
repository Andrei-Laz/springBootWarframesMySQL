package com.example.springBootWarframesMySQL.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "mods")
/*Necessary refactor of every single instance of the mod wording for a Mod class reference due to
the already existing operator in SpringBoot called mod
 */
data class Modification(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var modId: Int? = null,

    @Column(nullable = false)
    var name: String = "",

    @Column
    var rarity: String,

    @Column
    var cost: Int,

    @Column
    var polarity: String,

    @Column
    var description: String,
)