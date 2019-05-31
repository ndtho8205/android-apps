package edu.ndtho8205.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import edu.ndtho8205.aboutme.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val mMyName = MyName("Black Rabbit")
    private lateinit var mContent: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.myName = mMyName

        mContent = readPoemFile()
        mBinding.tvContent.text = mContent
    }


    fun setNickname(view: View) {
        mBinding.apply {
            myName?.nickname = et_nickname.text.toString()

            // why we need this?
            invalidateAll()

            tvNickname.visibility = View.VISIBLE
            etNickname.visibility = View.GONE
            view.visibility = View.GONE
        }

        // hide the keyboard
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun readPoemFile(): String {
        val inputStreamReader = InputStreamReader(this.resources.openRawResource(R.raw.poem))
        val content = inputStreamReader.readText()
        inputStreamReader.close()

        return content
    }
}
