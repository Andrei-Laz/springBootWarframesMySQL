package com.example.springBootWarframesMySQL.repository

import com.example.springBootWarframesMySQL.model.Build
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BuildRepository : JpaRepository<Build, Int>
