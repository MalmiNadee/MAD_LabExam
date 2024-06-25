package com.example.tutorial5.models

import com.example.tutorial5.models.validations.ValidationResult

class FormData(
    private var studentID:String,
    private var year:String,
    private var semester:String,
    private var agree:Boolean,

) {

    fun validateStudentId():ValidationResult{
        return if (studentID.isEmpty()){
            ValidationResult.Empty("Student ID is empty ")
        }else if(!studentID.startsWith("IT")){
            ValidationResult.Invalid("Student ID must start with IT ")
        }else if(studentID.length != 10){
            ValidationResult.Invalid("Student ID must conatin 10 characters ")
        }else{
            ValidationResult.Valid
        }
    }

    fun validateYear():ValidationResult{
        return if(year.isEmpty()){
            ValidationResult.Empty("Select the Year")
        }else{
            ValidationResult.Valid
        }
    }

    fun validateSemester():ValidationResult{
        return if(semester.isEmpty()){
            ValidationResult.Empty("Select the Semester")
        }else{
            ValidationResult.Valid
        }
    }

    fun validateAgreement():ValidationResult{
        return if(!agree){
            ValidationResult.Invalid("You have to agree terms and conditions")
        }else{
            ValidationResult.Valid
        }
    }

}