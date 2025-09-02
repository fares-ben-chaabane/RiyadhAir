package fr.benchaabane.riyadhair.presentation.account

import fr.benchaabane.riyadhair.domain.account.models.Account

/**
 * Maps domain Account model to presentation AccountUiModel.
 *
 * This extension function transforms the domain layer Account model into
 * a UI-optimized AccountUiModel for presentation layer consumption.
 * It handles data formatting, concatenation, and provides fallback values
 * to ensure consistent UI rendering.
 *
 * **Mapping Transformations:**
 * - **Name Concatenation**: Combines firstName and lastName into display name
 * - **Loyalty Level**: Direct mapping of loyalty tier information
 * - **Points Conversion**: Maps domain points to UI model fields
 * - **Phone Handling**: Provides empty string fallback for null phone numbers
 *
 * **Data Formatting:**
 * - **Full Name**: Creates user-friendly display name from separate fields
 * - **Null Safety**: Handles optional fields gracefully
 * - **Type Conversion**: Ensures proper data types for UI consumption
 * - **Default Values**: Provides sensible fallbacks for missing data
 *
 * **Use Cases:**
 * - Account screen data preparation
 * - Profile information display
 * - Domain to UI data transformation
 * - Consistent data formatting
 * - UI state management
 *
 * **Architecture Benefits:**
 * - **Separation of Concerns**: Keeps domain and UI models separate
 * - **Data Consistency**: Ensures uniform data presentation
 * - **Maintainability**: Centralized mapping logic
 * - **Testability**: Isolated transformation logic
 *
 * @return AccountUiModel formatted for presentation layer consumption
 */
internal fun Account.toUi() = AccountUiModel(
    accountName = "$firstName $lastName",
    accountLoyaltyLevel = loyaltyLevel,
    accountMiles = milesPoints,
    accountXpPoints = xpPoints,
    phoneNumber = phoneNumber.orEmpty()
)