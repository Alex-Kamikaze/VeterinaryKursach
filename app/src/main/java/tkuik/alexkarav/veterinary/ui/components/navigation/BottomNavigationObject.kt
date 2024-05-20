package tkuik.alexkarav.veterinary.ui.components.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationElement(
    val label: String,
    val name: String = label.lowercase(),
    val iconDefault: ImageVector,
    val iconChosen: ImageVector
)