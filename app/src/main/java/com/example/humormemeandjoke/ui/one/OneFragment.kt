package com.example.humormemeandjoke.ui.one

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.humormemeandjoke.R
import com.example.humormemeandjoke.RequestMethodNew
import com.example.humormemeandjoke.network.RetrofitClient
import kotlinx.coroutines.*
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.Request

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [OneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OneFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var request: Request? = null
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
            uiScope.launch(Dispatchers.IO){
                //asyncOperation
                withContext(Dispatchers.Main){
                    //ui operation
                    RequestMethodNew.request(request!!) {
                        activity?.runOnUiThread {
                            println(it)

                            val textView = view.findViewById<TextView>(R.id.text_view_fragment_one)
                            textView.text = it
                        }
                    }
                }

            }
        }

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
        fun newInstance(param1: String, param2: Request) =
            OneFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
                request = param2
            }
    }
}