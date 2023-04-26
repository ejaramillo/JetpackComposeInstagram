package cl.scotiabank.libraries.jetpackcomposeinstagram

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.scotiabank.libraries.jetpackcomposeinstagram.model.Superhero

@Composable
fun SimpleRecyclerView() {
    val myList = listOf("Lalin", "Cristóbal", "Bárbara", "Jacinta")
    LazyColumn {
        item { Text(text = "Header") }
        items(myList) {
            Text(text = "hola me llamo $it")
        }
        item { Text(text = "Footer") }
    }
}

@Composable
fun SuperHeroGridView() {
    val context = LocalContext.current
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(getSuperheroes()) { superhero ->
            ItemHero(superhero = superhero) {
                Toast.makeText(context, it.realName, Toast.LENGTH_SHORT).show()
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperHeroStickyView() {
    val context = LocalContext.current
    val superhero: Map<String, List<Superhero>> = getSuperheroes().groupBy { it.publisher }
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {

        superhero.forEach { (publisher, mySuperHero) ->
            stickyHeader {
                Text(
                    text = publisher,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Green),
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
            items(mySuperHero) { superhero ->
                ItemStickyHero(superhero = superhero) {
                    Toast.makeText(context, it.realName, Toast.LENGTH_SHORT).show()
                }
            }
        }


    }
}

@Composable
fun SuperHeroView() {
    val context = LocalContext.current
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(getSuperheroes()) { superhero ->
            ItemHero(superhero = superhero) {
                Toast.makeText(context, it.realName, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun ItemStickyHero(superhero: Superhero, onItemSelected: (Superhero) -> Unit) {
    Card(
        border = BorderStroke(2.dp, Color.Red),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemSelected(superhero) }
    ) {
        Column {
            Image(
                painter = painterResource(id = superhero.photo),
                contentDescription = "SuperHero Avatar",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = superhero.superheroName,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = superhero.realName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )
            Text(
                text = superhero.publisher,
                fontSize = 10.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun ItemHero(superhero: Superhero, onItemSelected: (Superhero) -> Unit) {
    Card(
        border = BorderStroke(2.dp, Color.Red),
        modifier = Modifier
            .width(200.dp)
            .clickable { onItemSelected(superhero) }
    ) {
        Column {
            Image(
                painter = painterResource(id = superhero.photo),
                contentDescription = "SuperHero Avatar",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = superhero.superheroName,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = superhero.realName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )
            Text(
                text = superhero.publisher,
                fontSize = 10.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp)
            )
        }
    }
}

fun getSuperheroes(): List<Superhero> {
    return listOf(
        Superhero(
            superheroName = "Spiderman",
            realName = "real-name Petter Parker",
            publisher = "Marvel",
            R.drawable.spiderman
        ),
        Superhero(
            superheroName = "Wolverine",
            realName = "real-name Wolverine",
            publisher = "James Howlett",
            R.drawable.logan
        ),
        Superhero(
            superheroName = "Batman",
            realName = "real-name Breal-name atman",
            publisher = "DC",
            R.drawable.batman
        ),
        Superhero(
            superheroName = "Thor",
            realName = "real-name Thor",
            publisher = "Marvel",
            R.drawable.thor
        ),
        Superhero(
            superheroName = "Flash",
            realName = "real-name Flash",
            publisher = "DC",
            R.drawable.flash
        ),
        Superhero(
            superheroName = "Green Lantern",
            realName = "real-name Green Lantern",
            publisher = "DC",
            R.drawable.green_lantern
        ),
        Superhero(
            superheroName = "Wonder woman",
            realName = "real-name Wonder woman",
            publisher = "DC",
            R.drawable.wonder_woman
        )
    )
}