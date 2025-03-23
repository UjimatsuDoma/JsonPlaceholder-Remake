package prac.tanken.jsonplaceholder.datasource

import prac.tanken.jsonplaceholder.business.Comment
import prac.tanken.jsonplaceholder.business.Item
import prac.tanken.jsonplaceholder.core.Either
import prac.tanken.jsonplaceholder.core.Failure
import prac.tanken.jsonplaceholder.repository.ItemsApi
import prac.tanken.jsonplaceholder.repository.mapper.ItemsMapper

class NetworkDataSource(
    private val itemsApi: ItemsApi,
    private val mapper: ItemsMapper
) {

    suspend fun getItems(): Either<Failure, List<Item>> =
        try {
            Either.Right(itemsApi.getItems().await().map { mapper.map(it) })
        } catch (e: Exception) {
            Either.Left(Failure.NetworkError())
        }

    suspend fun getComments(id: String): Either<Failure, List<Comment>> =
        try {
            Either.Right(itemsApi.getItemComments(id).await().map { mapper.map(it) })
        } catch (e: Exception) {
            Either.Left(Failure.NetworkError())
        }
}