package com.maestros.bottlehoz.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.maestros.bottlehoz.databinding.ActivitySignUpBinding
import com.maestros.bottlehoz.retrofit.BaseUrl
import com.maestros.bottlehoz.retrofit.BaseUrl.GENERATE_OTP
import com.maestros.bottlehoz.utils.AppConstats
import com.maestros.bottlehoz.utils.Connectivity
import com.maestros.bottlehoz.utils.SharedHelper
import com.maestros.bottlehoz.utils.SharedPreferencesClass
import com.maestros.bottlehoz.utils.SharedPreferencesClass.NAME
import org.json.JSONObject

class SignUpActivity : AppCompatActivity() {

    private lateinit var struserpassword: String
    private lateinit var strusermobile: String
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var context:Context
    lateinit var strEmail:String
    lateinit var strPwd:String
    lateinit var strMobile:String
    lateinit var strAge:String
    lateinit var strUserType:String
    lateinit var struserId:String
    lateinit var struserName:String
    lateinit var struserEmail:String
    lateinit var jsonObject: JSONObject

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
        binding.btnSignUp.setOnClickListener {
            if (connectivity.isOnline()) {
                strEmail=binding.etEmail.text.toString()
                strPwd=binding.etPwd.text.toString()
                strMobile=binding.etMobile.text.toString()
                if (binding.cbAge.isChecked){
                    strAge="yes"
                }else{
                    strAge="no"
                }

                if(!strEmail.isValidEmail()){
                    binding.etEmail.setError("Please enter valid email")
                    binding.etEmail.requestFocus()
                }else
                    if(TextUtils.isEmpty(strEmail)){
                        binding.etEmail.setError("Please enter email")
                        binding.etEmail.requestFocus()
                    }else if (TextUtils.isEmpty(strMobile)){
                        binding.etMobile.setError("Please enter mobile")
                        binding.etMobile.requestFocus()
                    }else if (TextUtils.isEmpty(strPwd)){
                        binding.etPwd.setError("Please enter password")
                        binding.etPwd.requestFocus()
                    }else {

                        if (strUserType.equals("1")) {
                            if (Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.getText().toString()).matches()) {
                                sendData()
                            }else{
                                Toast.makeText(
                                    this,
                                    "Invalid Email-Id, Enter Valid Email Address",
                                    Toast.LENGTH_LONG
                                ).show()
                            }


                        }
                    }
            }else{
                Toast.makeText(applicationContext,"Please check internet connection",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendData() {

        AndroidNetworking.post(BaseUrl.BASEURL)
            .addBodyParameter("control",BaseUrl.SIGNUP)
            .addBodyParameter("email",strEmail)
            .addBodyParameter("password",strPwd)
            .addBodyParameter("mobile",strMobile)
            .addBodyParameter("type",strUserType)
            .addBodyParameter("age_validation",strAge)
            .setTag("SIGNUP")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object :JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {

                    Log.e("response>>signup",response.toString())
                    if (response != null) {
                        if (response.getBoolean("result")){

                            jsonObject=response.getJSONObject("data")
                            struserId=jsonObject.getString("userID")
                            struserName=jsonObject.getString("name")
                            struserEmail=jsonObject.getString("email")
                            strusermobile=jsonObject.getString("mobile")
                            struserpassword=jsonObject.getString("password")

                            SharedHelper.putKey(context,AppConstats.NAME,struserName)
                            SharedHelper.putKey(context,AppConstats.USER_ID,struserId)
                            SharedHelper.putKey(context,AppConstats.EMAIL,struserEmail)
                            SharedHelper.putKey(context,AppConstats.MOBILE,strusermobile)
                            SharedHelper.putKey(context,AppConstats.PASSWORD,struserpassword)

                            genrateotp()

                        }

                        Toast.makeText(applicationContext,""+response.getString("message"),Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(anError: ANError?) {
                    TODO("Not yet implemented")
                }

            })
    }

    private fun genrateotp() {
        AndroidNetworking.post(BaseUrl.BASEURL)
            .addBodyParameter("control",GENERATE_OTP )
            .addBodyParameter("email", strEmail)
            .addBodyParameter("mobile", strMobile)
            .setTag("GENERATE_OTP")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object :JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {

                    Log.e("response>>otp",response.toString())
                    if (response != null) {
                        if (response.getBoolean("result")){

                            startActivity(Intent(applicationContext, VerifyActivity::class.java)
                                .putExtra("userType","1")
                                .putExtra("otp",response.getString("otp"))
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        }
                    }
                }

                override fun onError(anError: ANError?) {
                    TODO("Not yet implemented")
                }

            })
    }

    fun navigateToLogin(view: View) {
        startActivity(Intent(applicationContext, LoginActivity::class.java))
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

    }

    fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

}