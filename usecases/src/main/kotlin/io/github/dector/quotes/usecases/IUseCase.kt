package io.github.dector.quotes.usecases

interface IUseCase<Data, Error> {

    fun execute(callback: (UseCaseResult<Data, Error>) -> Unit)
}

class UseCaseResult<Data, Error>(val data: Data?, val error: Error? = null) {

    fun isSuccessful() = error == null
}

// I resist to 3rd party async solutions in architecture (like Rx) for a long time
// But this seems to be too complicated soon
abstract class AsyncUseCase<Data, Error : Throwable>(val jobExecutor: (() -> Unit) -> Unit = { it() },
                  val callbackExecutor: (() -> Unit) -> Unit = { it() }) : IUseCase<Data, Error> {

    override fun execute(callback: (UseCaseResult<Data, Error>) -> Unit) {
        jobExecutor {
            try {
                val data = fetchData()

                callbackExecutor { onSucceed(data, callback) }
            } catch (e: Exception) {
                callbackExecutor { onFailed(e, callback) }
            }
        }
    }

    abstract fun fetchData(): Data?

    abstract fun onSucceed(data: Data?, callback: (UseCaseResult<Data, Error>) -> Unit)

    abstract fun onFailed(e: Exception, callback: (UseCaseResult<Data, Error>) -> Unit)
}