package prac.tanken.jsonplaceholder.datasource.dto

data class ItemsResponseDto(
    val userId: String,
    val id: String,
    val title: String,
    val body: String
)