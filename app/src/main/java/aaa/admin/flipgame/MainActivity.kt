package aaa.admin.flipgame

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import coil.load

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgBack = findViewById<ImageView>(R.id.img_back)

        if (Fligamu.getFligamnet(this)) {
            imgBack.load(Fligamu.getFligamimg(this))
        } else {
            Fligamu.getFligamdlg(this)
        }

    }

}