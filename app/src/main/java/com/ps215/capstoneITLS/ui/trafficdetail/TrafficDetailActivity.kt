package com.ps215.capstoneITLS.ui.trafficdetail

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.ps215.capstoneITLS.data.database.Traffic
import com.ps215.capstoneITLS.databinding.ActivityTrafficDetailBinding
import com.ps215.capstoneITLS.ui.ViewModelFactory
import java.io.File

class TrafficDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTrafficDetailBinding
    private lateinit var detailViewModel: TrafficDetailViewModel
    private lateinit var currentPhotoPath: String
    private var getFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTrafficDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "${intent.getStringExtra(EXTRA_NAME)}"
        supportActionBar?.subtitle = "${intent.getStringExtra(EXTRA_ADDRESS)}"

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        setupViewModel()


    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[TrafficDetailViewModel::class.java]
        detailViewModel.getDensity("${intent.getStringExtra(EXTRA_ID)}").observe(this){
            binding.densityTv.text = "${it.density.toString()}%"
            binding.progressBar.progress = it.density
        }
        binding.cctvBtn.setOnClickListener {
            startTakePhoto()
        }
        binding.scanBtn.setOnClickListener{
            uploadImage()
        }
    }

    private fun startTakePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(packageManager)

        createTempFile(application).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "com.ps215.capstoneITLS",
                it
            )
            currentPhotoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            launcherIntentCamera.launch(intent)
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val myFile = File(currentPhotoPath)
            getFile = myFile

//            val result = BitmapFactory.decodeFile(getFile?.path)
        }
    }

    private fun uploadImage() {
        showLoading(true)
        if (getFile != null) {
//            val file = reduceFileImage(getFile as File)
            val rand = (30..100).random()
            val id = intent.getStringExtra(EXTRA_ID)
            val traffic = id?.let { it1 -> Traffic(it1, rand) }
            if (traffic != null) {
                detailViewModel.updateDensity(traffic)
            }
            showLoading(false)
            Toast.makeText(this, "Density Updated!", Toast.LENGTH_SHORT).show()
        } else {
            showLoading(false)
            Toast.makeText(this, "Image must be inputted", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.loadBar.visibility = View.VISIBLE
        }else{
            binding.loadBar.visibility = View.INVISIBLE
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Permission not granted",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_ADDRESS = "extra_address"
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}