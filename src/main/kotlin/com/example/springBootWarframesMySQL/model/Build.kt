package com.example.springBootWarframesMySQL.model

import jakarta.persistence.*

@Entity
@Table(name = "builds")
class Build(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var buildId: Int? = null,

    @Column(nullable = false)
    var name: String = "", // e.g., "Vitality Build", "Glass Cannon"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warframe_id", nullable = false)
    var warframe: Warframe? = null,

    // Relación muchos a muchos con Mod
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "build_mod",
        joinColumns = [JoinColumn(name = "build_id")],
        inverseJoinColumns = [JoinColumn(name = "mod_id")]
    )
    var mods: MutableSet<Modification> = mutableSetOf()
) {
    constructor() : this(null, "", null, mutableSetOf())
}