package kz.artyom.bmi_witch_progressbar

import android.annotation.SuppressLint
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.pow


class MainActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

            fun after() {

                val weight: Double =
                    this.findViewById<EditText>(R.id.EditTextWeight).text.toString().toDouble()

                val height: Double =
                    this.findViewById<EditText>(R.id.EditTextHeight).text.toString().toDouble()

                val recalut: Double = ((weight / (height).pow(2.0)) * 10000)

                val df = DecimalFormat("#.#")
                df.roundingMode = RoundingMode.DOWN

                val roundoff = df.format(recalut)

                val progoress = findViewById<ProgressBar>(R.id.progressBar)

                val progressRes = recalut.toInt()

                val edittext = findViewById<TextView>(R.id.textView3)

                if (recalut <= 18.5) {
                    progoress.progressDrawable.colorFilter =
                        BlendModeColorFilter(Color.BLUE, BlendMode.SRC_IN)
                    progoress.progress = progressRes
                    edittext.text = "Недостаток массы тела, ИМТ=" + roundoff

                } else if (recalut > 18.5 && recalut <= 25) {
                    progoress.progressDrawable.colorFilter =
                        BlendModeColorFilter(Color.GREEN, BlendMode.SRC_IN)
                    edittext.text = "Норма, ИМТ=" + roundoff
                    progoress.progress = progressRes

                } else if (recalut > 25 && recalut <= 30) {
                    progoress.progressDrawable.colorFilter =
                        BlendModeColorFilter(Color.YELLOW, BlendMode.SRC_IN)
                    edittext.text = "Избыточная масса тела, ИМТ=" + roundoff
                    progoress.progress = progressRes

                } else if (recalut > 30) {
                    progoress.progressDrawable.colorFilter =
                        BlendModeColorFilter(Color.RED, BlendMode.SRC_IN)
                    edittext.text = "Переожирение, ИМТ=" + roundoff
                    progoress.progress = progressRes

                } else {
                    edittext.text = "Ошибка"
                }
            }

        val heightS = findViewById<EditText>(R.id.EditTextHeight)

        val weightS = findViewById<EditText>(R.id.EditTextWeight)


            heightS.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    val heightS: String =
                        findViewById<EditText>(R.id.EditTextHeight).text.toString()
                    val weightS: String =
                        findViewById<EditText>(R.id.EditTextWeight).text.toString()
                    if (weightS != "" && heightS != "") {
                        after()
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            })

            weightS.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    val heightS: String =
                        findViewById<EditText>(R.id.EditTextHeight).text.toString()
                    val weightS: String =
                        findViewById<EditText>(R.id.EditTextWeight).text.toString()
                    if (weightS != "" && heightS != "") {
                        after()
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            })
        }
}
