package io.github.dector.quotes.usecases

interface IUseCase<Data> {

    fun execute(callback: (Data?) -> Unit)
}