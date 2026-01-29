package com.example.springBootWarframesMySQL.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "mods")
data class Mod(
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