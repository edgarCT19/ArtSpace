package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.artspace.ui.theme.ArtSpaceTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val gallery = listOf(
        ArtWork(
            title = "La noche estrellada",
            description = """A menudo pienso que la noche es más viva y rica en colores que el día""",
            author = "Vicent van Gogh",
            year = "1889",
            imageId = R.drawable.arte2
        ),
        ArtWork(
            title = "Campo de trigos con cipreses",
            description = """Los cipreses me siguen preocupando. Me gustaría hacer algo con ellos, como los cuadros de los girasoles...""",
            author = "Vicent van Gogh",
            year = "1889",
            imageId = R.drawable.arte4
        ),
        ArtWork(
            title = "La Gioconda",
            description = "Representa a una mujer joven con una enigmática sonrisa.",
            author = "Leonardo da Vinci",
            year = "1503/1506",
            imageId = R.drawable.arte3
        )
    )

    // Define un estado para controlar la visibilidad de la ventana modal
    val showDialog = remember { mutableStateOf(false) }

    // Muestra la ventana modal si showDialog es verdadero
    if (showDialog.value) {
        // La ventana modal se muestra con un degradado de fondo y sombras
        Dialog(onDismissRequest = { showDialog.value = false }) {
            Box(
                modifier = with(Modifier) {
                    padding(16.dp)
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0xFFB2EBF2), // Color inicial
                                            Color(0xFF4DD0E1)  // Color final
                                        )
                                    ),
                                    shape = RoundedCornerShape(16.dp),
                                )
                                .fillMaxWidth()
                                .height(200.dp)
                }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "¡Bienvenido a ArtSpace!",
                        color = Color.White,
                        style = TextStyle(fontSize = 24.sp),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "¡Disfruta de nuestra galería de arte y vuela con tu imaginación al pasado!",
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }
        }
    }

    val interactionSource = remember { MutableInteractionSource() }
    val currentIndex = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Muro con la obra de arte
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .padding(20.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFF5F5F5), // Color inicial (light)
                            Color(0xFFE3E3E3) // Color final
                        )
                    ),
                    shape = RoundedCornerShape(16.dp) // Borde redondeado con radio de 16.dp
                )
                .border(
                    border = BorderStroke(width = 4.dp, color = Color.Transparent), // Borde transparente
                    shape = RoundedCornerShape(16.dp) // Borde redondeado con radio de 16.dp
                )
                .padding(4.dp)
        )
 {
            Image(
                painter = painterResource(id = gallery[currentIndex.value].imageId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
            )
        }

        // Descriptor de la obra
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFADD8E6), // Color inicial
                            Color(0xFFE0FFFF)  // Color final
                        )
                    ),
                    shape = RoundedCornerShape(16.dp) // Borde redondeado con radio de 16.dp
                )
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = gallery[currentIndex.value].title,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = gallery[currentIndex.value].description,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = "Autor: ${gallery[currentIndex.value].author}, Año: ${gallery[currentIndex.value].year}",
                    color = Color.Black,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFF44336), // Color inicial
                            Color(0xF8F5C2BE)  // Color final
                        )
                    ),
                    shape = RoundedCornerShape(16.dp) // Borde redondeado con radio de 16.dp
                )
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Edgar A Cach Tamay / 66230 / 09-04-2024",
                    color = Color.Black,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }

        // Controlador de pantalla
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    currentIndex.value = (currentIndex.value - 1 + gallery.size) % gallery.size
                },
                interactionSource = interactionSource
            ) {
                Text(text = "Atrás")
            }
            // Botón "Centro"
            Button(
                onClick = {
                    // Cambia el estado para mostrar la ventana modal
                    showDialog.value = true
                },
                interactionSource = interactionSource
            ) {
                Text(text = "Bienvenido")
            }
            Button(
                onClick = {
                    currentIndex.value = (currentIndex.value + 1) % gallery.size
                },
                interactionSource = interactionSource
            ) {
                Text(text = "Siguiente")
            }
        }
    }
}

data class ArtWork(
    val title: String,
    val description: String,
    val author: String,
    val year: String,
    val imageId: Int
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        Greeting("Android")
    }
}