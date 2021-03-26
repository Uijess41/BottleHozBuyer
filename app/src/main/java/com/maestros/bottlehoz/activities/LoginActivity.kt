package com.maestros.bottlehoz.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.maestros.bottlehoz.databinding.ActivityLoginBinding
import com.maestros.bottlehoz.retrofit.BaseUrl
import com.maestros.bottlehoz.utils.AppConstats
import com.maestros.bottlehoz.utils.Connectivity
import com.maestros.bottlehoz.utils.SharedHelper
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var context:Context
    lateinit var strEmail:String
    lateinit var strPwd:String
    lateinit var strRemember:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        context=this
        val connectivity = Connectivity(context)
        binding.btnLogin.setOnClickListener {
            strEmail=binding.etEmail.text.toString()
            strPwd=binding.etPwd.text.toString()
            if (binding.cbRemember.isChecked){
                strRemember="yes"
            }else{
                strRemember="strRemember"
            }

            if (TextUtils.isEmpty(strEmail)){
                binding.etEmail.setError("Please enter email !")
                binding.etEmail.requestFocus()
            }else
            if (TextUtils.isEmpty(strPwd)){
                binding.etEmail.setError("Please enter password !")
                binding.etEmail.requestFocus()
            }else {
                if (connectivity.isOnline()) {
                    sendData()
                } else{
                        Toast.makeText(applicationContext,"Please check internet connection",Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    fun navigateToForgetPassword(view: View) {
            startActivity(
                Intent(applicationContext, AccountHelpActivity::class.java).putExtra(
                    "come_from",
                    "0"
                )
            )
    }

    fun navigateToSignUp(view: View) {
        startActivity(Intent(applicationContext, SignUpActivity::class.java))
        intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
    }

    private fun sendData() {
        AndroidNetworking.post(BaseUrl.BASEURL)
            .addBodyParameter("control", BaseUrl.LOGIN)
            .addBodyParameter("email", strEmail)
            .addBodyParameter("password", strPwd)
            .setTag("LOGIN")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("response", response.toString() + "")
                    try {
                        /* SignupModel model = gson.fromJson(response.getJSONObject("data").toString(), SignupModel.class);
                           if (gson.fromJson(response.getJSONObject("result").toString().equals(""),))
                            Log.e("model>>",model.getData().getEmail());*/
                        if (response.getBoolean("result") == true) {

                            val json = JSONObject(response.getString("data"))

                            SharedHelper.putKey(context, AppConstats.USER_ID, json.getString("userID"))
                            SharedHelper.putKey(context, AppConstats.USER_NAME, json.getString("name"))
                            SharedHelper.putKey(context, AppConstats.USER_EMAIL, json.getString("email"))
                            SharedHelper.putKey(context, AppConstats.USER_PASSWORD, json.getString("password"))
                            SharedHelper.putKey(context, AppConstats.USER_MOBILE, json.getString("mobile"))
                            SharedHelper.putKey(context, AppConstats.USER_TYPE, json.getString("type"))
                            SharedHelper.putKey(context, AppConstats.USER_AGE, json.getString("age_validation"))

                            startActivity(Intent(applicationContext, BottomNavActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))

                        } else {
                        }
                        Toast.makeText(
                            context, "" + response.getString("message"),
                            Toast.LENGTH_SHORT
                        ).show()
                    } catch (e: JSONException) {
                        Log.e("jksjkcj", "onResponse: ",e )
                    }
                }

                override fun onError(error: ANError) {
                 error("err")
                    Log.e("jksjkcj", "onResponse: ",error )
                }
            })
    }
}