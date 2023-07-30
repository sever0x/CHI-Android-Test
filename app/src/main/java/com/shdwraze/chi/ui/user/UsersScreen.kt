package com.shdwraze.chi.ui.user

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shdwraze.chi.R
import com.shdwraze.chi.data.model.User
import com.shdwraze.chi.ui.theme.CHIAndroidTestTheme

enum class UsersScreen {
    List,
    Detail
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen(
    userViewModel: UserViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold { innerPadding ->
        val userUiState by userViewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = UsersScreen.List.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = UsersScreen.List.name) {
                UserList(
                    userUiState = userUiState,
                    userViewModel = userViewModel,
                    navController = navController
                )
            }
            composable(
                route = "${UsersScreen.Detail.name}/{userId}",
                arguments = listOf(navArgument("userId") {
                    type = NavType.IntType
                })
            ) { navBackStackEntry ->
                val userId = navBackStackEntry.arguments?.getInt("userId")
                userId?.let { UserDetail(users = userUiState.users, userId = it) }
            }
        }
    }
}

@Composable
fun UserList(
    userUiState: UserUiState,
    userViewModel: UserViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Column {
        LazyColumn(modifier) {
            items(userUiState.users) { user ->
                UserCard(
                    user = user,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)),
                    onCheckedChange = {
                        userViewModel.updateUserState(user.id - 1, !user.isStudent)
                    },
                    checked = user.isStudent,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun UserCard(
    modifier: Modifier = Modifier,
    user: User,
    onCheckedChange: () -> Unit,
    checked: Boolean,
    navController: NavHostController
) {
    Card(
        modifier = modifier
            .height(dimensionResource(id = R.dimen.height_large))
            .clickable {
                navController.navigate("${UsersScreen.Detail.name}/${user.id}")
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.height_large))
                    .padding(dimensionResource(id = R.dimen.padding_small))
            )
            UserInfo(user = user)
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = checked, onCheckedChange = { onCheckedChange() },
                modifier = Modifier.padding(end = dimensionResource(id = R.dimen.padding_medium))
            )
        }

    }
}

@Composable
fun UserInfo(user: User, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = user.name,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 8.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${user.age} ${stringResource(id = R.string.years_old)}",
            style = MaterialTheme.typography.labelSmall,
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserCardPreview() {
    CHIAndroidTestTheme {
        UsersScreen()
    }
}