package com.example.tutorial5

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tutorial5.models.FormData
import com.example.tutorial5.models.validations.ValidationResult
import java.time.Year

class MainActivity : AppCompatActivity() {

    //Global variables for the views
    lateinit var editStudentId:EditText
    lateinit var spnYear: Spinner
    lateinit var spnSemester:Spinner
    lateinit var cbAgree:CheckBox


    private var count =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //initialization of the views
        editStudentId = findViewById(R.id.editStudentId)
        spnYear = findViewById(R.id.spnYear)
        spnSemester = findViewById(R.id.spnSemester)
        cbAgree = findViewById(R.id.cbAgree)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    fun displayAlert(title:String,message:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ok"){dialog, which->

        }

        val dialog = builder.create()
        dialog.show()
    }

    fun submit(v: View){
        val myForm = FormData(
            editStudentId.text.toString(),
            spnYear.selectedItem.toString(),
            spnSemester.selectedItem.toString(),
            cbAgree.isChecked
        )
        val studentIdValidation = myForm.validateStudentId()
        val spnYearValidation = myForm.validateYear()
        val spnSemesterValidation = myForm.validateSemester()
        val cbAgreeValidation = myForm.validateAgreement()

        when(studentIdValidation){
            is ValidationResult.Valid -> {
                count++
            }
            is ValidationResult.Invalid -> {
                editStudentId.error = studentIdValidation.errorMessage
            }
            is ValidationResult.Empty -> {
                editStudentId.error = studentIdValidation.errorMessage
            }
        }

        when(spnYearValidation){
            is ValidationResult.Valid -> {
                count++
            }
            is ValidationResult.Invalid -> {

            }
            is ValidationResult.Empty -> {
                val tv:TextView = spnYear.selectedView  as TextView
                tv.error = ""
                tv.text = spnYearValidation.errorMessage
            }
        }

        when(spnSemesterValidation){
            is ValidationResult.Valid -> {
                count++
            }
            is ValidationResult.Invalid -> {

            }
            is ValidationResult.Empty -> {
                val tv:TextView = spnSemester.selectedView  as TextView
                tv.error = ""
                tv.text = spnSemesterValidation.errorMessage
            }
        }

        when(cbAgreeValidation){
            is ValidationResult.Empty -> {

            }
            is ValidationResult.Invalid -> {
                displayAlert("Error",cbAgreeValidation.errorMessage)
            }
            ValidationResult.Valid -> {
                count++
            }
        }

       if (count==4){
           displayAlert("Success","You have registered successfully")
       }else{
           count=0
       }
    }
}