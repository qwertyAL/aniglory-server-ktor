package ru.aniglory.data.model.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object TitleTable: Table() {
    val id: Column<String> = varchar("title_id", 10)
    val title: Column<String> = varchar("title", 100)
    val title_en: Column<String> = varchar("title_en", 100)
    val title_other: Column<String> = varchar("title_other", 100)
    val description: Column<String> = varchar("description", 2000)
    val type: Column<String> = varchar("type", 10)
    val year: Column<Int> = integer("year")
    val rating: Column<Double> = double("rating")
}