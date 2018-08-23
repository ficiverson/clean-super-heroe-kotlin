package test.kotlin.clean.ficiverson.presentation.mapper

/**
 * Created by f.souto.gonzalez on 17/08/2018.
 */

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer layers
 *
 * @param <V> the view model input type
 * @param <D> the domain model output type
 */
interface Mapper<out V, in D> {

    fun mapToView(type: D): V

}