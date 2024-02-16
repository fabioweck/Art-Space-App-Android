package com.example.assignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment2.data.DataSource
import com.example.assignment2.model.Art
import com.example.assignment2.ui.theme.Assignment2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Layout()
                }
            }
        }
    }
}

@Composable
fun Layout()
{
    val listOfArt: List<Art> = DataSource().loadArts()
    var artIndex by remember { mutableStateOf(0) }

    if(artIndex < 0) artIndex = listOfArt.count() - 1
    else if(artIndex > listOfArt.count() - 1) artIndex = 0

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(16.dp)
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1F)
        ){
            Card(listOfArt[artIndex].imageResId)
        }
        DescriptionBlock(
            listOfArt[artIndex].stringAuthorId,
            listOfArt[artIndex].stringArtId,
            listOfArt[artIndex].yearId)
        NavigationButtons({ artIndex -= 1}, { artIndex += 1})
    }
}

@Composable
fun Card(imageId: Int, modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .size(400.dp, 450.dp)
            .shadow(4.dp)
    ){
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(280.dp, 360.dp)
        )
    }
}

@Composable
fun DescriptionBlock(author: Int, description: Int, year: Int, modifier: Modifier = Modifier)
{
    val backgroundColor = Color(236,235,244)

    Column (
        modifier = Modifier
            .padding(32.dp)
            .background(color = backgroundColor)
    ){
        Text(
            text = stringResource(id = description),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Light,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 12.dp, top = 12.dp, end = 12.dp)
            )
        Row{
            Text(
                text = stringResource(id = author),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 12.dp, bottom = 12.dp, end = 4.dp)
            )
            Text(
                text = stringResource(id = year),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(end = 12.dp))
        }
    }
}

@Composable
fun NavigationButtons(backward: ()-> Unit, forward: ()-> Unit, modifier: Modifier = Modifier)
{
    val previousTxt = R.string.btn_previous
    val nextTxt = R.string.btn_next

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ){
        CustomButton(previousTxt ,backward)
        CustomButton(nextTxt, forward)
    }
}

@Composable
fun CustomButton(textId: Int, navigate: ()-> Unit, modifier: Modifier = Modifier)
{
    val buttonColor = Color(73,93,146)

    Button(
        onClick = navigate,
        colors = ButtonDefaults.buttonColors(buttonColor),
        modifier = Modifier
            .width(150.dp))
    {
        Text(stringResource(id = textId))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assignment2Theme {
        Layout()
    }
}