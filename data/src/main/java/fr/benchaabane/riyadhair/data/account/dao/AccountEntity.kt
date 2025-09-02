package fr.benchaabane.riyadhair.data.account.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Database entity representing a user account in the local database.
 *
 * This entity stores user account information locally for offline access and
 * caching purposes. It represents a complete user profile including personal
 * details, loyalty program information, and user preferences.
 *
 * **Database Structure:**
 * - **Table Name**: `account`
 * - **Primary Key**: `id` (String)
 * - **Data Persistence**: Local storage for offline access
 * - **Cache Management**: Stores account data for quick retrieval
 *
 * **Account Information:**
 * - **Core Details**: ID, names, email, phone, loyalty details
 * - **Loyalty Program**: Miles, XP points, loyalty level details
 * - **User Preferences**: Language, location, profile image
 * - **Account Status**: Active account information
 *
 * **Data Usage:**
 * - **Offline Support**: Provides account data without network
 * - **Quick Access**: Fast local database queries
 * - **Data Synchronization**: Local cache of remote account data
 * - **User Experience**: Immediate account information display
 *
 * **Room Integration:**
 * - **Entity Annotation**: Maps to `account` table
 * - **Primary Key**: Unique identifier for each account
 * - **Column Mapping**: Automatic field-to-column mapping
 * - **Query Support**: Enables efficient database operations
 *
 * **Business Context:**
 * - **User Profiles**: Manages user account information
 * - **Loyalty Management**: Tracks loyalty program participation
 * - **Preferences**: Stores user settings and preferences
 * - **Offline Access**: Ensures account data availability
 *
 * @property id Unique identifier for the user account (primary key)
 * @property firstName User's first name
 * @property lastName User's last name
 * @property email User's email address
 * @property phoneNumber User's phone number
 * @property loyaltyLevelName User's loyalty level name
 * @property loyaltyTier User's loyalty tier identifier
 * @property loyaltyColor Color code for loyalty level visual representation
 * @property milesPoints User's accumulated miles points
 * @property xpPoints User's accumulated XP points
 * @property profileImageUrl URL to user's profile image
 * @property preferredLanguage User's preferred language
 * @property currentLocation User's current location
 *
 * @see AccountDao
 * @see fr.benchaabane.riyadhair.domain.account.models.Account
 * @see fr.benchaabane.riyadhair.data.account.api.AccountDto
 */
@Entity(tableName = "account")
data class AccountEntity(
    @PrimaryKey val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String?,
    val loyaltyLevelName: String,
    val loyaltyTier: String,
    val loyaltyColor: String,
    val milesPoints: Int,
    val xpPoints: Int,
    val profileImageUrl: String?,
    val preferredLanguage: String,
    val currentLocation: String?
)
