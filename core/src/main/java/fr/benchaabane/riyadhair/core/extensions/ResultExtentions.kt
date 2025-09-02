package fr.benchaabane.riyadhair.core.extensions

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.coroutines.cancellation.CancellationException

/**
 * Executes a suspending block and catches any exceptions, re-throwing cancellation exceptions.
 *
 * This function is a safe wrapper around `runCatching` that properly handles
 * coroutine cancellation. When a coroutine is cancelled, the cancellation
 * exception is re-thrown to maintain proper cancellation behavior, while
 * other exceptions are caught and wrapped in a Result.
 *
 * **Key Features:**
 * - Preserves coroutine cancellation semantics
 * - Catches and wraps other exceptions in Result
 * - Uses contracts for compiler optimization
 *
 * **Usage:**
 * ```kotlin
 * val result = runSuspendCatching {
 *     // Suspending operation that might throw
 *     apiService.fetchData()
 * }
 *
 * result.onSuccess { data ->
 *     // Handle successful result
 * }.onFailure { exception ->
 *     // Handle non-cancellation exceptions
 * }
 * ```
 *
 * **Exception Handling:**
 * - CancellationException: Re-thrown immediately
 * - Other exceptions: Caught and wrapped in Result.failure()
 *
 * @param T The type of value returned by the block
 * @param block The suspending block to execute
 * @return Result containing the success value or failure exception
 */
@OptIn(ExperimentalContracts::class)
inline fun <T> runSuspendCatching(block: () -> T): Result<T> {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return runCatching(block).onFailure {
        if (it is CancellationException) {
            throw it
        }
    }
}

/**
 * Recovers from a failed Result by applying a transformation function.
 *
 * This function provides a safe way to recover from failures by transforming
 * exceptions into alternative values. It maintains the same cancellation
 * behavior as `runSuspendCatching` for consistency.
 *
 * **Behavior:**
 * - If the Result is successful, returns it unchanged
 * - If the Result failed, applies the transform function to the exception
 * - The transform function is executed safely using `runSuspendCatching`
 *
 * **Usage:**
 * ```kotlin
 * val result = apiCall()
 *     .recoverSuspendCatching { exception ->
 *         when (exception) {
 *             is NetworkException -> fallbackData
 *             is ValidationException -> defaultData
 *             else -> throw exception // Re-throw unexpected exceptions
 *         }
 *     }
 * ```
 *
 * @param R The type of the recovered value
 * @param T The original Result type (must be assignable to R)
 * @param transform Function to transform exceptions into recovery values
 * @return Result containing either the original success value or the recovered value
 */
@ExperimentalContracts
inline fun <R, T : R> Result<T>.recoverSuspendCatching(
    transform: (exception: Throwable) -> R,
): Result<R> {
    return when (val exception = exceptionOrNull()) {
        null -> this
        else -> runSuspendCatching { transform(exception) }
    }
}

/**
 * Transforms a successful Result value using a safe transformation function.
 *
 * This function applies a transformation to the success value of a Result,
 * while maintaining safe exception handling. If the transformation fails,
 * the exception is caught and wrapped in a Result.failure().
 *
 * **Behavior:**
 * - If the Result is successful, applies the transform function safely
 * - If the Result failed, returns the failure unchanged
 * - If the transform function throws, catches the exception and returns failure
 *
 * **Usage:**
 * ```kotlin
 * val result = apiCall()
 *     .mapSuspendCatching { data ->
 *         // Transform data safely
 *         data.toDomainModel()
 *     }
 *     .onSuccess { domainModel ->
 *         // Handle transformed domain model
 *     }
 *     .onFailure { exception ->
 *         // Handle transformation failure
 *     }
 * ```
 *
 * @param R The type of the transformed value
 * @param T The original Result type
 * @param transform Function to transform the success value
 * @return Result containing either the transformed value or the original failure
 */
inline fun <R, T> Result<T>.mapSuspendCatching(transform: (value: T) -> R): Result<R> =
    runSuspendCatching { transform(this.getOrThrow()) }












