package prac.tanken.jsonplaceholder.list.usecase

import prac.tanken.jsonplaceholder.business.Item
import prac.tanken.jsonplaceholder.core.Either
import prac.tanken.jsonplaceholder.core.Failure
import prac.tanken.jsonplaceholder.datasource.NetworkSourceException
import prac.tanken.jsonplaceholder.repository.ItemsRepository

class GetItemsListUseCase(private val itemsRepository: ItemsRepository) {

    @Throws(NetworkSourceException::class)
    suspend fun execute(): Either<Failure, List<Item>> = itemsRepository.getItems()
}