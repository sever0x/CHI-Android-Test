package com.shdwraze.chi.ui.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.shdwraze.chi.R
import com.shdwraze.chi.data.model.User

@Composable
fun UserDetail(users: List<User>, userId: Int, modifier: Modifier = Modifier) {
    val user = users.find { it.id == userId }
    if (user != null) {
        val isStudent: (user: User) -> String = {
            when (user.isStudent) {
                true -> "Student"
                false -> "Not a student"
            }
        }

        Surface {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    Icons.Default.Person, contentDescription = null,
                    modifier = Modifier.size(dimensionResource(id = R.dimen.height_large))
                )
                Text(
                    text = user.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 42.sp
                )
                Text(
                    text = "${user.age} ${stringResource(id = R.string.years_old)}",
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_small))
                )
                Text(
                    text = isStudent(user)
                )
            }
        }
    }
}