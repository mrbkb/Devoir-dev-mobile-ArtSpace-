package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    // Liste des œuvres d'art = artworks (Images, titles, and artists)
    val artworks = listOf(
        Artwork(R.drawable.art1, "Happy Labor Day", "Mr BKB "),
        Artwork(R.drawable.art2, "Flutter Crash Course", "Flutter Addict"),
        Artwork(R.drawable.art3, "Amour Du Code", "Codeur")
    )

    // suivre l'index de l'œuvre en cours
    var currentIndex by remember { mutableStateOf(0) }

    // afficher l'œuvre suivante
    fun nextArtwork() {
        currentIndex = (currentIndex + 1) % artworks.size
    }

    // afficher l'œuvre précédente
    fun previousArtwork() {
        currentIndex = if (currentIndex == 0) artworks.size - 1 else currentIndex - 1
    }

    // Récupérer l'œuvre actuelle à afficher
    val currentArtwork = artworks[currentIndex]

    // UI Layout
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = currentArtwork.imageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = currentArtwork.title)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = currentArtwork.artist)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Button(onClick = { previousArtwork() }) {
                Text("Previous")
            }
            Button(onClick = { nextArtwork() }) {
                Text("Next")
            }
        }
    }
}

// classe ArtWork pour stocker les détails d'une œuvre d'art
data class Artwork(val imageResId: Int, val title: String, val artist: String)

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}
