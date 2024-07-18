package com.example.shoppinglistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em



data class ShoppingItem(val id:Int,
                        var name: String,
                        var quantity: Int,
                        var isEditing: Boolean)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingList(){

    var sItems by remember { mutableStateOf(listOf<ShoppingItem>() ) }
    var showDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                style = TextStyle(
                    fontSize = 6.em,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold),
                text = "Shopping List",
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )

            //Contains the List Item
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ){
                items(sItems){}
            }
        }

        //add new item button
        FloatingActionButton(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .size(width = 80.dp, height = 80.dp),
            onClick = {
                showDialog = true
            }) {
            Icon(
                modifier = Modifier.size(width = 48.dp, height = 48.dp),
                imageVector = Icons.Default.Add,
                contentDescription = "Add new item")
        }


        //adding the list item window
        if (showDialog){
            AlertDialog(onDismissRequest = { showDialog = false }) {
                Card (
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(text = "This Works")
                }
            }
        }

    }
}





@Preview(showBackground = true)
@Composable
fun ShoppingListPreview(){
    ShoppingList()
}