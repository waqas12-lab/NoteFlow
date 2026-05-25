package com.example.noteflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.noteflow.ui.navigation.NoteFlowNavGraph
import com.example.noteflow.ui.theme.NoteFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteFlowTheme {
                NoteFlowNavGraph()
            }
        }
    }
}
