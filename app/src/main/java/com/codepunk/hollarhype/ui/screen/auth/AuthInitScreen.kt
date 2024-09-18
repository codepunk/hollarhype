package com.codepunk.hollarhype.ui.screen.auth

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import arrow.core.right
import arrow.eval.Eval
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.domain.model.User
import com.codepunk.hollarhype.ui.preview.ScreenPreviews
import com.codepunk.hollarhype.ui.theme.HollarhypeTheme
import com.codepunk.hollarhype.ui.theme.buttonCornerRadius
import com.codepunk.hollarhype.ui.theme.largePadding
import com.codepunk.hollarhype.ui.theme.layoutMarginWidth
import com.codepunk.hollarhype.ui.theme.standardButtonWidth
import com.codepunk.hollarhype.ui.theme.xLargePadding
import com.codepunk.hollarhype.util.consume

@Composable
fun AuthInitScreen(
    modifier: Modifier = Modifier,
    state: AuthState,
    onEvent: (AuthEvent) -> Unit = {}
) {
    val user = state.authenticatedUser.consume { authenticatedUser ->
        if (authenticatedUser.isLeft()) {
            onEvent(AuthEvent.NavigateToAuthOptions)
        }
    }

    // Silent authentication was successful
    val layoutMargin = layoutMarginWidth()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = layoutMargin,
                end = layoutMargin
            )
    ) {
        Image(
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(
                    start = xLargePadding,
                    end = xLargePadding
                ),
            painter = painterResource(R.drawable.hh_logo),
            contentDescription = stringResource(id = R.string.app_name)
        )
    }
}

@ScreenPreviews
@Composable
fun AuthInitScreenPreviews() {
    HollarhypeTheme {
        Scaffold { padding ->
            AuthInitScreen(
                modifier = Modifier.padding(padding),
                state = AuthState(
                    authenticatedUser = Eval.now(User().right())
                )
            )
        }
    }
}
