package prac.tanken.jsonplaceholder.repository

import prac.tanken.jsonplaceholder.business.Comment
import prac.tanken.jsonplaceholder.business.Item
import prac.tanken.jsonplaceholder.core.Either
import prac.tanken.jsonplaceholder.core.Failure
import prac.tanken.jsonplaceholder.datasource.NetworkDataSource

interface ItemsRepository {

    suspend fun getItems(): Either<Failure, List<Item>>
    suspend fun getCommentsById(id: String): Either<Failure, List<Comment>>

    class NetworkRepository(private val network: NetworkDataSource) : ItemsRepository {

        override suspend fun getItems(): Either<Failure, List<Item>> = network.getItems()

        override suspend fun getCommentsById(id: String): Either<Failure, List<Comment>> = network.getComments(id)
    }
}