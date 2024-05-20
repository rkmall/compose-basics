package com.rm.compose_fundamentals.topics.t4_managing_states

import android.content.res.Resources
import android.graphics.BitmapShader
import android.graphics.Shader
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rm.compose_fundamentals.ui.theme.ComposefundamentalsTheme
import kotlinx.parcelize.Parcelize

@Preview(showBackground = true)
@Composable
fun ManagingStatesPreview() {
    ComposefundamentalsTheme {


        //HelloContentStateful()

        //CreateRemember()
    }
}

/**
 * Ways to create Compose MutableState using remember
 */
@Composable
private fun CreateRemember() {
    // Created type: MutableState<Boolean>
    val state1: MutableState<Boolean> = remember { mutableStateOf(false) }
    val value1: Boolean = state1.value // unwrap the value

    // Created type: Boolean, no need to unwrap the value
    val state2: Boolean by remember { mutableStateOf(false) }

    // Created type: Boolean, no need to unwrap the value
    val (state3, setValue) = remember { mutableStateOf(false) }
}

// Stateful composable
@Composable
private fun HelloContentStateful() {
    Column(modifier = Modifier.padding(16.dp)) {
        val name = remember { "OK" } // internal composable state

        if (name.isNotEmpty()) {
            Text(
                text = "Hello $name",
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(text = "Name")}
        )
    }
}

/**
 * - State goes down from HelloScreen to HelloContentStateless
 * - Events go up from HelloContentStateless to HelloScreen
 */
@Composable
fun HelloScreen() {
    var name by rememberSaveable { mutableStateOf("") }
    HelloContentStateless(name = name) { name = it }
}

// Stateless composable
@Composable
private fun HelloContentStateless(
    name: String,                   // caller passes the state
    onNameChange: (String) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        if (name.isNotEmpty()) {
            Text(
                text = "Hello $name",
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text(text = "Name")}
        )
    }
}

/**
 * Ways to store state
 */
@Composable
fun CityScreen() {
    var selectedCity by rememberSaveable {
        mutableStateOf(City("London", "UK"))
    }
}

// Using Parcelize
@Parcelize
data class City(
    val name: String,
    val country: String
) : Parcelable

// Using MapSaver
data class CityMapped(
    val name: String,
    val country: String
)

val CityMappedSaver = run {
    val nameKey = "Name"
    val countryKey = "Country"
    mapSaver(
        save = { mapOf(nameKey to it.name, countryKey to it.country) },
        restore = { CityMapped(it[nameKey] as String, it[countryKey] as String)}
    )
}

// Using ListSaver
data class CityListed(
    val name: String,
    val country: String
)

val CityListedSaver = listSaver<CityListed, Any>(
    save = { listOf(it.name, it.country) },
    restore = { CityListed(it[0] as String, it[1] as String) }
)

/**
 * Apart from caching state, remember can be used to store any object or
 * result of an operation in the Composition that is expensive to calculate
 * that we don't want to repeat in every recomposition.
 */
@Composable
fun BackgroundBanner(
    res: Resources,
    @DrawableRes imageRes: Int
) {
    val brush = remember {
        ShaderBrush(
            BitmapShader(
                ImageBitmap.imageResource(res, imageRes).asAndroidBitmap(),
                Shader.TileMode.REPEAT,
                Shader.TileMode.REPEAT
            )
        )
    }
}

/**
 * Keys can be used to invalidate object cached by remember.
 */
@Composable
fun BackgroundBanner1(
    res: Resources = LocalContext.current.resources,
    @DrawableRes imageRes: Int,
    modifier: Modifier,
) {
    val brush = remember(key1 = imageRes) { // when key1 changes the cached object is invalidated
        ShaderBrush(                        // and calculation lambda executes again during recomposition
            BitmapShader(
                ImageBitmap.imageResource(res, imageRes).asAndroidBitmap(),
                Shader.TileMode.REPEAT,
                Shader.TileMode.REPEAT
            )
        )
    }
}

@Composable
private fun rememberMyAppState(
    imageClass: ImageClass
): MyAppState {
    return remember(imageClass) { // when key imageClass changes the cached object is invalidated
        MyAppState(imageClass)    // and calculation lambda executes again during recomposition
    }
}

// State holder class
@Stable
class MyAppState(
    private val imageClass: ImageClass
)

class ImageClass(
    @DrawableRes val resId: Int,
    val resource: Resources
) {}

/**
 * rememberSaveable is used to store state that can survive:
 *  - configuration change
 *  - activity recreation
 *  - system-initiated death process
 */
@Composable
private fun RememberMySaveAble(typedQuery: String) {
    var userTypedQuery by rememberSaveable(typedQuery,  stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue(
                text = typedQuery,
                selection = TextRange(typedQuery.length)
            )
        )
    }
}
