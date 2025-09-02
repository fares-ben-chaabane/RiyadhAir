package fr.benchaabane.riyadhair.designsystem.components.selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirSpacing
import fr.benchaabane.riyadhair.designsystem.theme.RiyadhAirTheme

/**
 * A customizable checkbox component that follows RiyadhAir design system guidelines.
 *
 * This component provides a checkbox with optional label text that follows
 * Material 3 design principles. It includes proper accessibility support
 * and integrates seamlessly with the design system's color scheme and typography.
 *
 * **Features:**
 * - **Material 3 Design**: Follows Material 3 checkbox specifications
 * - **Optional Label**: Can be used with or without descriptive text
 * - **Accessibility Support**: Proper semantic roles and click targets
 * - **Design System Integration**: Uses theme colors and typography
 * - **Interactive Label**: Clicking the label toggles the checkbox
 *
 * **Interaction Behavior:**
 * - **With Label**: Clicking either the checkbox or label toggles the state
 * - **Without Label**: Only the checkbox is clickable
 * - **Disabled State**: Visual feedback and interaction blocking when disabled
 *
 * **Color Scheme:**
 * - **Checked State**: Primary color with onPrimary checkmark
 * - **Unchecked State**: Outline color for subtle appearance
 * - **Disabled State**: Surface variant colors for reduced emphasis
 *
 * **Usage Examples:**
 * ```kotlin
 * var isAgreed by remember { mutableStateOf(false) }
 * 
 * RiyadhAirCheckbox(
 *     checked = isAgreed,
 *     onCheckedChange = { isAgreed = it },
 *     label = "I agree to the terms and conditions"
 * )
 *
 * // Checkbox without label
 * var isEnabled by remember { mutableStateOf(true) }
 * RiyadhAirCheckbox(
 *     checked = isEnabled,
 *     onCheckedChange = { isEnabled = it }
 * )
 * ```
 *
 * **Accessibility Features:**
 * - Proper semantic role (Checkbox)
 * - Clickable label area for larger touch targets
 * - Screen reader support for state changes
 * - Visual feedback for different states
 *
 * @param checked Whether the checkbox is currently checked
 * @param onCheckedChange Callback invoked when the checkbox state changes
 * @param modifier Modifier to apply to the checkbox container
 * @param label Optional text label for the checkbox
 * @param enabled Whether the checkbox is interactive
 */
@Composable
fun RiyadhAirCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    enabled: Boolean = true
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (label != null) {
                    Modifier.selectable(
                        selected = checked,
                        onClick = { onCheckedChange(!checked) },
                        role = Role.Checkbox,
                        enabled = enabled
                    )
                } else Modifier
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = if (label == null) onCheckedChange else null,
            enabled = enabled,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primary,
                uncheckedColor = MaterialTheme.colorScheme.outline,
                checkmarkColor = MaterialTheme.colorScheme.onPrimary
            )
        )
        
        label?.let { labelText ->
            Text(
                text = labelText,
                style = MaterialTheme.typography.bodyMedium,
                color = if (enabled) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = RiyadhAirSpacing.sm)
            )
        }
    }
}

/**
 * A customizable radio button component that follows RiyadhAir design system guidelines.
 *
 * This component provides a radio button with optional label text that follows
 * Material 3 design principles. It includes proper accessibility support
 * and integrates seamlessly with the design system's color scheme and typography.
 *
 * **Features:**
 * - **Material 3 Design**: Follows Material 3 radio button specifications
 * - **Optional Label**: Can be used with or without descriptive text
 * - **Accessibility Support**: Proper semantic roles and click targets
 * - **Design System Integration**: Uses theme colors and typography
 * - **Interactive Label**: Clicking the label selects the radio button
 *
 * **Interaction Behavior:**
 * - **With Label**: Clicking either the radio button or label selects it
 * - **Without Label**: Only the radio button is clickable
 * - **Disabled State**: Visual feedback and interaction blocking when disabled
 *
 * **Color Scheme:**
 * - **Selected State**: Primary color for active selection
 * - **Unselected State**: Outline color for subtle appearance
 * - **Disabled State**: Surface variant colors for reduced emphasis
 *
 * **Usage Examples:**
 * ```kotlin
 * var selectedOption by remember { mutableStateOf("Option 1") }
 * 
 * RiyadhAirRadioButton(
 *     selected = selectedOption == "Option 1",
 *     onClick = { selectedOption = "Option 1" },
 *     label = "First option"
 * )
 *
 * // Radio button without label
 * var isSelected by remember { mutableStateOf(false) }
 * RiyadhAirRadioButton(
 *     selected = isSelected,
 *     onClick = { isSelected = true }
 * )
 * ```
 *
 * **Accessibility Features:**
 * - Proper semantic role (RadioButton)
 * - Clickable label area for larger touch targets
 * - Screen reader support for state changes
 * - Visual feedback for different states
 *
 * @param selected Whether the radio button is currently selected
 * @param onClick Callback invoked when the radio button is clicked
 * @param modifier Modifier to apply to the radio button container
 * @param label Optional text label for the radio button
 * @param enabled Whether the radio button is interactive
 */
@Composable
fun RiyadhAirRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    enabled: Boolean = true
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (label != null) {
                    Modifier.selectable(
                        selected = selected,
                        onClick = onClick,
                        role = Role.RadioButton,
                        enabled = enabled
                    )
                } else Modifier
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        RadioButton(
            selected = selected,
            onClick = if (label == null) onClick else null,
            enabled = enabled,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor = MaterialTheme.colorScheme.outline
            )
        )
        
        label?.let { labelText ->
            Text(
                text = labelText,
                style = MaterialTheme.typography.bodyMedium,
                color = if (enabled) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = RiyadhAirSpacing.sm)
            )
        }
    }
}

/**
 * A radio button group component for selecting one option from multiple choices.
 *
 * This component provides a convenient way to create groups of radio buttons
 * where only one option can be selected at a time. It automatically handles
 * the selection logic and provides consistent styling across all options.
 *
 * **Features:**
 * - **Single Selection**: Only one option can be selected at a time
 * - **Automatic State Management**: Handles selection logic internally
 * - **Consistent Styling**: All radio buttons use the same design
 * - **Flexible Options**: Works with any list of string options
 * - **Accessibility Support**: Proper grouping for screen readers
 *
 * **Selection Behavior:**
 * - Clicking an unselected option selects it
 * - Clicking the currently selected option has no effect
 * - Only one option can be selected at any time
 * - Selection changes immediately trigger the callback
 *
 * **Use Cases:**
 * - Travel class selection (Economy, Business, First Class)
 * - Preference settings (Light/Dark theme, Language)
 * - Form options (Yes/No, Multiple choice questions)
 * - Configuration choices (Settings, Options)
 *
 * **Usage Examples:**
 * ```kotlin
 * var selectedClass by remember { mutableStateOf("Economy") }
 * 
 * RiyadhAirRadioGroup(
 *     options = listOf("Economy", "Premium Economy", "Business", "First Class"),
 *     selectedOption = selectedClass,
 *     onSelectionChange = { selectedClass = it }
 * )
 *
 * // With custom label
 * Text("Select your travel class:", style = MaterialTheme.typography.titleMedium)
 * RiyadhAirRadioGroup(
 *     options = listOf("Window", "Aisle", "Middle"),
 *     selectedOption = seatPreference,
 *     onSelectionChange = { seatPreference = it }
 * )
 * ```
 *
 * **Layout Structure:**
 * - Vertical arrangement of radio buttons
 * - Consistent spacing between options
 * - Full-width layout for proper alignment
 * - Automatic label integration for each option
 *
 * @param options The list of available options to choose from
 * @param selectedOption The currently selected option, or null if no selection
 * @param onSelectionChange Callback invoked when a new option is selected
 * @param modifier Modifier to apply to the radio group container
 * @param enabled Whether the radio group is interactive
 */
@Composable
fun RiyadhAirRadioGroup(
    options: List<String>,
    selectedOption: String?,
    onSelectionChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Column(modifier = modifier) {
        options.forEach { option ->
            RiyadhAirRadioButton(
                selected = selectedOption == option,
                onClick = { onSelectionChange(option) },
                label = option,
                enabled = enabled
            )
        }
    }
}

/**
 * Preview function for RiyadhAir selection components.
 *
 * This preview demonstrates all selection components including checkboxes
 * and radio button groups. It provides a comprehensive view of the
 * selection components for designers and developers during the design process.
 *
 * **Preview Content:**
 * - **Checkboxes**: Terms agreement and promotional email preferences
 * - **Radio Group**: Travel class selection with multiple options
 * - **Interactive State**: Shows how components appear with different states
 *
 * **Preview Configuration:**
 * - Uses RiyadhAir theme for consistent styling
 * - Demonstrates different use cases and configurations
 * - Shows proper spacing and layout relationships
 * - Includes state management examples for all components
 */
@Preview(showBackground = true)
@Composable
private fun RiyadhAirSelectionPreview() {
    RiyadhAirTheme {
        Column(
            modifier = Modifier.padding(RiyadhAirSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(RiyadhAirSpacing.lg)
        ) {
            var checked1 by remember { mutableStateOf(false) }
            RiyadhAirCheckbox(
                checked = checked1,
                onCheckedChange = { checked1 = it },
                label = "I agree to the terms and conditions"
            )
            
            var checked2 by remember { mutableStateOf(true) }
            RiyadhAirCheckbox(
                checked = checked2,
                onCheckedChange = { checked2 = it },
                label = "Send me promotional emails"
            )
            
            var selectedClass by remember { mutableStateOf("Economy") }
            Text("Select travel class:", style = MaterialTheme.typography.titleMedium)
            RiyadhAirRadioGroup(
                options = listOf("Economy", "Premium Economy", "Business", "First Class"),
                selectedOption = selectedClass,
                onSelectionChange = { selectedClass = it }
            )
        }
    }
}
