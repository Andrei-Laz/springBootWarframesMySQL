package com.example.springBootWarframesMySQL.model

import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table

@Embeddable
class BuildId(
    var idWarframe: Int = 0,
    var idMod: Int = 0
)

@Entity
@Table(name = "builds")
class Build(
    @EmbeddedId
    var id: BuildId = BuildId(),

    @ManyToOne
    @MapsId("idWarframe")
    @JoinColumn(name = "warframe_id")
    var warframe: Warframe? =  null,

    @ManyToOne
    @MapsId("idMod")
    @JoinColumn(name = "mod_id")
    var modification: Modification? = null,
)