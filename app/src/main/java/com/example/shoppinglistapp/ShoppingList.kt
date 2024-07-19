package com.example.shoppinglistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import java.util.Locale


data class ShoppingItem(val id:Int,
                        var name: String,
                        var quantity: Int,
                        var isEditing: Boolean = false)


@Composable
fun ShoppingList(){

    var sItems by remember { mutableStateOf(listOf<ShoppingItem>() ) }
    var showDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember{ mutableStateOf("") }

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
                items(sItems){
                    ShoppingListItem(it,{},{})
                }
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
            AlertDialog(
                onDismissRequest = { showDialog = false}, 
                confirmButton = {
                                Row(modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween) {
                                    //add button
                                    Button(
                                        onClick = {
                                                  if (itemName.isNotBlank() && itemQuantity.isNotBlank()){
                                                      val newItem = ShoppingItem(
                                                          id = sItems.size+1,
                                                          name = itemName,
                                                          quantity = itemQuantity.toInt()
                                                      )
                                                      sItems = sItems + newItem
                                                      showDialog = false
                                                      itemName = ""
                                                      itemQuantity = ""
                                                  }
                                        },
                                        modifier = Modifier.size(width = 96.dp, height = 48.dp)) {
                                        Text(text = "Add")
                                    }
                                    //cancel button
                                    Button(
                                        onClick = { showDialog = false},
                                        modifier = Modifier.size(width = 96.dp, height = 48.dp)) {
                                        Text(text = "Cancel")
                                    }
                                }
                },
                tonalElevation = 16.dp,
                title = { Text(text = "Add Shopping item")},
                text = {
                    Column {
                        //items name
                        OutlinedTextField(
                            shape = RoundedCornerShape(8.dp),
                            value = itemName,
                            onValueChange = {itemName= it},
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {Text(text = "Enter Item Name")}
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        //item quantity
                        OutlinedTextField(
                            shape = RoundedCornerShape(8.dp),
                            value = itemQuantity,
                            onValueChange = {itemQuantity= it},
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {Text(text = "Enter Item Quantity")}
                        )
                    }
                })
        }

    }
}


@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){
        
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = item.name.capitalize(Locale.ROOT), modifier = Modifier.padding(8.dp))
                Text(text = "QTY :", modifier = Modifier.padding(8.dp))
                Text(text = item.quantity.toString(), modifier = Modifier.padding(8.dp))

                Spacer(modifier = Modifier.width(24.dp))

                Button(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.padding(8.dp).fillMaxHeight(),
                    onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                }

                Button(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.padding(8.dp).fillMaxHeight(),
                    onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
            }
    }
}


@Preview(showBackground = true)
@Composable
fun ShoppingListPreview(){
    ShoppingList()
}