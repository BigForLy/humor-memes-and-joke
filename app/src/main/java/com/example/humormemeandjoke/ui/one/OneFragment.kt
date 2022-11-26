package com.example.humormemeandjoke.ui.one

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.example.humormemeandjoke.R
import com.example.humormemeandjoke.network.RequestMethod
import com.example.humormemeandjoke.network.RetrofitClient
import kotlinx.coroutines.*
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.Request

private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [OneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OneFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var url: String = ""
    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    @ExperimentalSerializationApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_one, container, false)

        val button = view.findViewById<Button>(R.id.button_joke)
        button.setOnClickListener{
            val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
            progressBar.visibility = VISIBLE

            uiScope.launch(Dispatchers.IO){
                try {
                    //asyncOperation
                    withContext(Dispatchers.Main) {
                        val request = Request.Builder()
                            .url(RetrofitClient.url_mapping + "stories")
                            .get()
                            .build()
                        //ui operation
                        RequestMethod.request(request) {
                            activity?.runOnUiThread {
                                progressBar.visibility = INVISIBLE

                                val textView = view.findViewById<TextView>(R.id.text_view_fragment_one)
                                textView.text = it
                            }
                        }
                    }
                } catch (e: Exception) {
                    progressBar.visibility = INVISIBLE

                    withContext(Dispatchers.Main) {
                        val textView = view.findViewById<TextView>(R.id.text_view_fragment_one)
                        textView.text = "Что-то пошло не так! Попробуйте снова!"
                    }
                }
            }
        }
        button.callOnClick()

        return view
    }

    override fun onDestroy(){
        job.cancel()
        super.onDestroy()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment OneFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OneFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
                url = param2
            }
    }
}