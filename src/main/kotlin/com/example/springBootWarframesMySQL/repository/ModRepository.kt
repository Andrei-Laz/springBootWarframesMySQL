package com.example.springBootWarframesMySQL.repository

import com.example.springBootWarframesMySQL.model.Mod
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ModRepository : JpaRepository<Mod, Int>