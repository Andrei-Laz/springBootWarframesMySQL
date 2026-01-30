package com.example.springBootWarframesMySQL.repository

import com.example.springBootWarframesMySQL.model.Modification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ModificationRepository : JpaRepository<Modification, Int>