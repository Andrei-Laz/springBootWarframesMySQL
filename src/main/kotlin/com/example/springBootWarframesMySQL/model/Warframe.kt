package com.example.springBootWarframesMySQL.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "warframes")
data class Warframe(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var warframeId: Int? = null,

    @Column(nullable = false)
    var name: String = "",

    @Column
    var health: Int,

    @Column
    var armor: Int,

    @Column
    var energy: Int,

    @Column
    var sprintSpeed: Double,

    @Column
    var passive: String = ""
)