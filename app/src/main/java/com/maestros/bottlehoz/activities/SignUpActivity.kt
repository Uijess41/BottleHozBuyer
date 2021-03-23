package com.maestros.bottlehoz.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.maestros.bottlehoz.databinding.ActivitySignUpBinding
import com.maestros.bottlehoz.utils.Connectivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var context:Context
    lateinit var strEmail:String
    lateinit var strPwd:String
    lateinit var strMobile:String
     var strAge:String=""
    lateinit var strUserType:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        context=this

        if( intent != null)
        {
            strUserType=intent.getStringExtra("userType").toString()
        }
        strUserType="3"
        val connectivity = Connectivity(context)

     binding.cbAge.setOnCheckedChangeListener { buttonView, isChecked ->
            if (binding.cbAge.isChecked){
                strAge="1"
            }else{

            }


        }


        binding.btnSignUp.setOnClickListener {
            if (connectivity.isOnline()) {
                strEmail=binding.etEmail.text.toString()
                strPwd=binding.etPwd.text.toString()
                strMobile=binding.etMobile.text.toString()




                if(TextUtils.isEmpty(strEmail)){
                    binding.etEmail.setError("Please enter email")
                    binding.etEmail.requestFocus()
                }else if (TextUtils.isEmpty(strMobile)){
                    binding.etMobile.setError("Please enter mobile")
                    binding.etMobile.requestFocus()

                }
                else if (strMobile.length < 10) {
                    binding.etMobile.setError("Please enter atleast 10 digit mobile number")
                }
                else if (TextUtils.isEmpty(strPwd)){
                    binding.etPwd.setError("Please enter password")
                    binding.etPwd.requestFocus()
                }else {
                    if (strAge.equals("")){
                        Toast.makeText(
                            this,
                            "Firstly Please check in checkbox",
                            Toast.LENGTH_LONG
                        ).show()
                    }else{

                        if (strUserType.equals("3")) {
                            startActivity(
                                Intent(applicationContext, VerifyActivity::class.java)
                                    .putExtra("email", strEmail)
                                    .putExtra("pwd", strPwd)
                                    .putExtra("mobile", strMobile)
                                    .putExtra("userType", "3")
                                    .putExtra("age", strAge).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            )
                        }

                    }


                }
            }else{
                Toast.makeText(applicationContext,"Please check internet connection",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun navigateToLogin(view: View) {
        startActivity(Intent(applicationContext, LoginActivity::class.java))
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP

    }

}