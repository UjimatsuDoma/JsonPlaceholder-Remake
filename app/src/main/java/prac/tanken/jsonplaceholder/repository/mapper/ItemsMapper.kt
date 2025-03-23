package prac.tanken.jsonplaceholder.repository.mapper

import prac.tanken.jsonplaceholder.business.Comment
import prac.tanken.jsonplaceholder.business.Item
import prac.tanken.jsonplaceholder.datasource.dto.CommentResponseDto
import prac.tanken.jsonplaceholder.datasource.dto.ItemsResponseDto

class ItemsMapper {
    fun map(itemsResponseDto: ItemsResponseDto): Item =
        Item(itemsResponseDto.id, itemsResponseDto.title, itemsResponseDto.body)

    fun map(commentResponseDto: CommentResponseDto): Comment =
        Comment(commentResponseDto.id, commentResponseDto.name, commentResponseDto.email, commentResponseDto.body)
}