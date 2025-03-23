package prac.tanken.jsonplaceholder.core

sealed class Failure {
    class NetworkError: Failure()
}