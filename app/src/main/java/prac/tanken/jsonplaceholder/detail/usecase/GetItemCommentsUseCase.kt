package prac.tanken.jsonplaceholder.detail.usecase

import prac.tanken.jsonplaceholder.business.Comment
import prac.tanken.jsonplaceholder.core.Either
import prac.tanken.jsonplaceholder.core.Failure
import prac.tanken.jsonplaceholder.datasource.NetworkSourceException
import prac.tanken.jsonplaceholder.repository.ItemsRepository

class GetItemCommentsUseCase(private val itemsRepository: ItemsRepository) {

    @Throws(NetworkSourceException::class)
    suspend fun execute(id: String): Either<Failure, List<Comment>> = itemsRepository.getCommentsById(id)
}