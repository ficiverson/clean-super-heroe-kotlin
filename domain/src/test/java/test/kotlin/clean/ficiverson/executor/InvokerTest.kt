package test.kotlin.clean.ficiverson.executor

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import test.kotlin.clean.ficiverson.mock.instrumentation.InvokerInstruments.givenAnInvoker
import test.kotlin.clean.ficiverson.mock.instrumentation.InvokerInstruments.givenAnInvokerAnCancelTasks
import test.kotlin.clean.ficiverson.mock.instrumentation.UseCaseInstruments.givenAGenericSuccessResultUseCase

/**
 * Created by f.souto.gonzalez on 27/08/2018.
 */
class InvokerTest {

    @Test
    fun `that can create a use case invoker`() {
        val invoker = givenAnInvoker()
        val useCase = givenAGenericSuccessResultUseCase()
        val params = Unit

        invoker.execute(useCase, params, LocalOnly) {
            assertThat(it is Success).isTrue()
            assertThat((it as Success).data).isEqualTo("Awesome Result")
        }
    }

    @Test
    fun `that can cancel a job`() {
        val invoker = givenAnInvokerAnCancelTasks()
        val useCase = givenAGenericSuccessResultUseCase()
        val params = Unit

        invoker.execute(useCase, params, LocalOnly, {})
        invoker.cancelAllAsync()
        assertThat(invoker.isPendingTask()).isFalse()
    }

}
