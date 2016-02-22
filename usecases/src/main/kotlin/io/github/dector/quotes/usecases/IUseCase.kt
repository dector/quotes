package io.github.dector.quotes.usecases

interface IUseCase<Data, Error> {

    fun execute(callback: (UseCaseResult<Data, Error>) -> Unit)
}

class UseCaseResult<Data, Error>(val data: Data?, val error: Error? = null) {

    fun isSuccessful() = error == null
}