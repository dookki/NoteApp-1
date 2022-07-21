package br.com.alaksion.feature_login.presentation.success

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import br.com.alaksion.core_ui.components.WeightSpacer
import br.com.alaksion.core_ui.theme.NoteSpacings
import br.com.alaksion.feature_login.R
import com.example.navigation.AuthRouter

@Composable
internal fun RegistrationSuccessScreen(
    navigator: NavController
) {
    RegistrationSuccessContent {
        navigator.navigate(AuthRouter.Routes.Login.path) {
            popUpTo(AuthRouter.Routes.Login.path)
        }
    }
}

@Composable
private fun RegistrationSuccessContent(
    goToHome: () -> Unit
) {
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(NoteSpacings.spacings.large)
        ) {
            WeightSpacer(weight = 1f)
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.account_created),
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
            WeightSpacer(weight = 1f)
            Button(
                onClick = goToHome,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.go_to_login))
            }
        }
    }
}