package fr.benchaabane.riyadhair.designsystem.components.textfields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import fr.benchaabane.riyadhair.designsystem.icons.RiyadhAirIcons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme

/**
 * Defines the visual variants for RiyadhAir text fields.
 *
 * Each variant provides a different visual style and emphasis level
 * to accommodate various design contexts and user interface needs.
 *
 * **Variant Styles:**
 * - **Filled**: Text field with filled background and indicator line
 * - **Outlined**: Text field with outlined border for definition
 */
enum class RiyadhAirTextFieldVariant {
    Filled, Outlined
}

/**
 * A comprehensive text field component that follows RiyadhAir design system guidelines.
 *
 * This component provides a feature-rich text input experience with multiple
 * variants, states, and customization options. It integrates seamlessly with
 * Material 3 design principles and provides consistent styling across the application.
 *
 * **Features:**
 * - **Multiple Variants**: Filled and Outlined styles
 * - **Icon Support**: Leading and trailing icons with click handling
 * - **Password Fields**: Built-in password visibility toggle
 * - **Validation States**: Error and helper text support
 * - **Keyboard Options**: Configurable keyboard types
 * - **Accessibility**: Proper content descriptions and state handling
 *
 * **Text Field Variants:**
 * - **Filled**: Uses filled background with indicator line
 * - **Outlined**: Uses outlined border for clean definition
 *
 * **Icon Integration:**
 * - **Leading Icons**: Displayed before the text input
 * - **Trailing Icons**: Displayed after the text input
 * - **Password Toggle**: Automatic visibility toggle for password fields
 * - **Clickable Icons**: Support for interactive trailing icons
 *
 * **Validation States:**
 * - **Normal State**: Standard appearance with outline/fill
 * - **Error State**: Error colors and error text display
 * - **Helper State**: Helper text for additional information
 * - **Disabled State**: Reduced opacity and interaction blocking
 *
 * **Usage Examples:**
 * ```kotlin
 * // Basic text field
 * var email by remember { mutableStateOf("") }
 * RiyadhAirTextField(
 *     value = email,
 *     onValueChange = { email = it },
 *     label = "Email",
 *     placeholder = "Enter your email address"
 * )
 *
 * // Password field with icon
 * var password by remember { mutableStateOf("") }
 * RiyadhAirTextField(
 *     value = password,
 *     onValueChange = { password = it },
 *     label = "Password",
 *     isPassword = true,
 *     leadingIcon = RiyadhAirIcons.Lock
 * )
 *
 * // Error state with helper text
 * var username by remember { mutableStateOf("") }
 * RiyadhAirTextField(
 *     value = username,
 *     onValueChange = { username = it },
 *     label = "Username",
 *     errorText = "Username is required",
 *     variant = RiyadhAirTextFieldVariant.Filled
 * )
 * ```
 *
 * **Design Features:**
 * - Material 3 color scheme integration
 * - Consistent spacing and typography
 * - Proper error state handling
 * - Responsive layout with full-width support
 * - Accessible text field behavior
 *
 * **Accessibility Features:**
 * - Content descriptions for icons
 * - Error state announcements
 * - Helper text for additional context
 * - Proper keyboard navigation support
 *
 * @param value The current text value in the field
 * @param onValueChange Callback invoked when the text value changes
 * @param modifier Modifier to apply to the text field container
 * @param label Optional label text for the field
 * @param placeholder Optional placeholder text when the field is empty
 * @param helperText Optional helper text below the field
 * @param errorText Optional error text below the field (triggers error state)
 * @param leadingIcon Optional icon to display before the text input
 * @param trailingIcon Optional icon to display after the text input
 * @param onTrailingIconClick Optional click handler for the trailing icon
 * @param keyboardType The type of keyboard to display
 * @param isPassword Whether this field should handle password input
 * @param enabled Whether the text field is interactive
 * @param readOnly Whether the text field is read-only
 * @param singleLine Whether the text field should be limited to a single line
 * @param maxLines Maximum number of lines for multi-line text fields
 * @param variant The visual style variant of the text field
 */
@Composable
fun RiyadhAirTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    helperText: String? = null,
    errorText: String? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    variant: RiyadhAirTextFieldVariant = RiyadhAirTextFieldVariant.Outlined
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val isError = errorText != null
    
    val visualTransformation = when {
        isPassword && !passwordVisible -> PasswordVisualTransformation()
        else -> VisualTransformation.None
    }
    
    Column(modifier = modifier) {
        when (variant) {
            RiyadhAirTextFieldVariant.Filled -> {
                TextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier.fillMaxWidth(),
                    label = label?.let { { Text(it) } },
                    placeholder = placeholder?.let { { Text(it) } },
                    leadingIcon = leadingIcon?.let { icon ->
                        { Icon(imageVector = icon, contentDescription = null) }
                    },
                    trailingIcon = when {
                        isPassword -> {
                            {
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        imageVector = if (passwordVisible) 
                                            RiyadhAirIcons.Visibility 
                                        else 
                                            RiyadhAirIcons.VisibilityOff,
                                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                                    )
                                }
                            }
                        }
                        trailingIcon != null -> {
                            {
                                if (onTrailingIconClick != null) {
                                    IconButton(onClick = onTrailingIconClick) {
                                        Icon(imageVector = trailingIcon, contentDescription = null)
                                    }
                                } else {
                                    Icon(imageVector = trailingIcon, contentDescription = null)
                                }
                            }
                        }
                        else -> null
                    },
                    visualTransformation = visualTransformation,
                    keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                    enabled = enabled,
                    readOnly = readOnly,
                    singleLine = singleLine,
                    maxLines = maxLines,
                    isError = isError,
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                        errorIndicatorColor = MaterialTheme.colorScheme.error
                    )
                )
            }
            
            RiyadhAirTextFieldVariant.Outlined -> {
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier.fillMaxWidth(),
                    label = label?.let { { Text(it) } },
                    placeholder = placeholder?.let { { Text(it) } },
                    leadingIcon = leadingIcon?.let { icon ->
                        { Icon(imageVector = icon, contentDescription = null) }
                    },
                    trailingIcon = when {
                        isPassword -> {
                            {
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        imageVector = if (passwordVisible) 
                                            RiyadhAirIcons.Visibility 
                                        else 
                                            RiyadhAirIcons.VisibilityOff,
                                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                                    )
                                }
                            }
                        }
                        trailingIcon != null -> {
                            {
                                if (onTrailingIconClick != null) {
                                    IconButton(onClick = onTrailingIconClick) {
                                        Icon(imageVector = trailingIcon, contentDescription = null)
                                    }
                                } else {
                                    Icon(imageVector = trailingIcon, contentDescription = null)
                                }
                            }
                        }
                        else -> null
                    },
                    visualTransformation = visualTransformation,
                    keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                    enabled = enabled,
                    readOnly = readOnly,
                    singleLine = singleLine,
                    maxLines = maxLines,
                    isError = isError,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                        errorBorderColor = MaterialTheme.colorScheme.error
                    )
                )
            }
        }
        
        // Helper or error text
        when {
            errorText != null -> {
                Text(
                    text = errorText,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(start = RiyadhAirSpacing.lg, top = RiyadhAirSpacing.xs)
                )
            }
            helperText != null -> {
                Text(
                    text = helperText,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = RiyadhAirSpacing.lg, top = RiyadhAirSpacing.xs)
                )
            }
        }
    }
}

/**
 * Preview function for RiyadhAirTextField components.
 *
 * This preview demonstrates various text field configurations including
 * basic input, password fields, and error states. It provides a comprehensive
 * view of the text field components for designers and developers during
 * the design process.
 *
 * **Preview Content:**
 * - **Basic Text Field**: Email input with label and placeholder
 * - **Password Field**: Password input with visibility toggle
 * - **Error State**: Text field with error message display
 *
 * **Preview Configuration:**
 * - Uses RiyadhAir theme for consistent styling
 * - Demonstrates different use cases and configurations
 * - Shows proper spacing and layout relationships
 * - Includes state management examples for all text fields
 */
@Preview(showBackground = true)
@Composable
private fun RiyadhAirTextFieldPreview() {
    RiyadhAirTheme {
        Column(
            modifier = Modifier.padding(RiyadhAirSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.lg)
        ) {
            var text1 by remember { mutableStateOf("") }
            RiyadhAirTextField(
                value = text1,
                onValueChange = { text1 = it },
                label = "Email",
                placeholder = "Enter your email"
            )
            
            var text2 by remember { mutableStateOf("") }
            RiyadhAirTextField(
                value = text2,
                onValueChange = { text2 = it },
                label = "Password",
                placeholder = "Enter your password",
                isPassword = true
            )
            
            var text3 by remember { mutableStateOf("Error example") }
            RiyadhAirTextField(
                value = text3,
                onValueChange = { text3 = it },
                label = "With Error",
                errorText = "This field has an error"
            )
        }
    }
}
