package io.github.dector.quotes.usecases

interface IUseCase<Data, Error> {

    fun execute(succeedCallback: (Data?) -> Unit,
                failedCallback: (Error) -> Unit = {})
}

// I resist to 3rd party async solutions in architecture (like Rx) for a long time
// But this seems to be too complicated soon
abstract class AsyncUseCase<Data, Error : Throwable>(val jobExecutor: (() -> Unit) -> Unit = AsyncUseCase.plainExecutor,
                                                     val callbackExecutor: (() -> Unit) -> Unit = AsyncUseCase.plainExecutor) :
        IUseCase<Data, Error> {

    companion object {

        val plainExecutor: (() -> Unit) -> Unit = { it() }
    }

    override fun execute(succeedCallback: (Data?) -> Unit, failedCallback: (Error) -> Unit) {
        jobExecutor {
            try {
                val data = fetchData()

                callbackExecutor { succeedCallback(data) }
            } catch (e: Exception) {
                val error = fetchError(e)

                callbackExecutor { failedCallback(error) }
            }
        }
    }

    abstract fun fetchData(): Data?

    abstract fun fetchError(e: Exception): Error
}