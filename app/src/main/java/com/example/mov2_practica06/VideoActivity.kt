package com.example.mov2_practica06

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView

class VideoActivity : AppCompatActivity() {

    private val pickVideo = 101
    private var videoUri: Uri? = null

    private lateinit var btnVideo: Button
    private lateinit var videoView: VideoView
    private lateinit var mediaController: MediaController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        setSupportActionBar(findViewById(R.id.barraVideo))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnVideo = findViewById(R.id.btnVideo)
        videoView = findViewById(R.id.selectedVideoView)

        // Configurar el MediaController
        mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        btnVideo.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "video/*"
            startActivityForResult(intent, pickVideo)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pickVideo && resultCode == RESULT_OK) {
            videoUri = data?.data
            videoView.setVideoURI(videoUri)
            videoView.start()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
