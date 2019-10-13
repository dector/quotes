package io.github.dector.knight.usecases

interface IUseCase<Data, Error> {

    fun execute(succeedCallback: (Data?) -> Unit,
                failedCallback: (Error) -> Unit = {})
}

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
            } catch (e: Throwable) {
                val error = fetchError(e)

                callbackExecutor { failedCallback(error) }
            }
        }
    }

    abstract fun fetchData(): Data?

    abstract fun fetchError(e: Throwable): Error
}