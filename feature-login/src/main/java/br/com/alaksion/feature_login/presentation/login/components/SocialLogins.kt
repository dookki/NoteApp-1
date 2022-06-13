package br.com.alaksion.feature_login.presentation.login.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.alaksion.core_ui.components.HorizontalSpacer
import br.com.alaksion.core_ui.theme.NoteSpacings
import br.com.alaksion.feature_login.R

@Composable
internal fun ColumnScope.SocialLogins(
    modifier: Modifier = Modifier,
    onGoogleClick: () -> Unit
) {
    SocialLoginCard(
        modifier = modifier.fillMaxWidth(),
        icon = R.drawable.ic_google,
        label = stringResource(id = R.string.social_login_google),
        onClick = onGoogleClick
    )
}

@Composable
internal fun SocialLoginCard(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    label: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colors.onSurface
            )
            .clickable { onClick() }
            .padding(NoteSpacings.spacings.small),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(NoteSpacings.spacings.medium),
            tint = Color.Unspecified
        )
        HorizontalSpacer(width = NoteSpacings.spacings.medium)
        Text(
            modifier = Modifier.weight(1f),
            text = label,
            fontWeight = FontWeight.Medium
        )
    }
}