package com.example.springBootWarframesMySQL.repository

import com.example.springBootWarframesMySQL.model.Warframe
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WarframeRepository : JpaRepository<Warframe, Int>
