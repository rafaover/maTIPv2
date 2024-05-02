In this case, you can use nested navigation. The `EventsScreen` will have its own `NavHost` which will handle navigation between the list of events and the list of tips inside an event.

Here's how you can implement this:

First, update your `Screen` sealed class to include a nested route for the list of tips inside an event:

```kotlin
sealed class Screen(val route: String) {
    object TipCalculator : Screen("tipCalculator")
    sealed class Events(val route: String) : Screen(route) {
        object EventList : Events("events")
        class EventTips(val eventId: String) : Events("events/$eventId")
    }
}
```

Then, update your `EventsScreen` to include a `NavHost`:

```kotlin
@Composable
fun EventsScreen(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Events.EventList.route) {
        composable(Screen.Events.EventList.route) {
            EventListScreen(
                onEventClick = { eventId ->
                    navController.navigate(Screen.Events.EventTips(eventId).route)
                }
            )
        }
        composable("events/{eventId}") { backStackEntry ->
            val eventId = backStackEntry.arguments?.getString("eventId")
            if (eventId != null) {
                EventTipsScreen(eventId)
            }
        }
    }
}
```

In this code, when an event is clicked in the `EventListScreen`, it calls the `navigate()` function of the `NavController` with the route of the `EventTipsScreen` for the clicked event. The `NavController` then navigates to the `EventTipsScreen` in the `NavHost`.

Finally, update your `NavigationGraph` to pass the `NavController` to the `EventsScreen`:

```kotlin
@Composable
fun NavigationGraph(
    viewModel: MainScreenViewModel,
    navController: NavHostController,
    uiState: MainScreenState
) {
    NavHost(
        navController = navController,
        startDestination = Screen.TipCalculator.route
    ) {
        composable(Screen.TipCalculator.route) {
            TipCalculatorScreen(
                viewModel = viewModel,
                uiState = uiState
            )
        }
        composable(Screen.Events.EventList.route) {
            EventsScreen(navController)
        }
    }
}
```

This way, the `EventsScreen` handles its own navigation, and the `MainScreen` doesn't need to know about the details of how navigation is handled inside the `EventsScreen`.