package com.example.space

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.space.viewmodel.MainActivityViewModel
import com.squareup.picasso.Picasso


class SecondFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()
    // TODO: Rename and change types of parameters
    val args:SecondFragmentArgs by navArgs()
   // private var  mShareActionProvider : ShareActionProvider?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        val id =args.Id
        initViewModel(view, id)

        return  view
    }
//    val urlIntent = Intent("android.intent.action.VIEW", Uri.parse(WEBSITE_URL))
//
//    startActivity(urlIntent)

    fun  initViewModel(view: View, id: String){

            val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
            viewModel.makeApiCallRocket(id)
            viewModel.rocketLiveData.observe(viewLifecycleOwner, Observer {
                view.findViewById<TextView>(R.id.rocketName).setText(it.name)
                view.findViewById<TextView>(R.id.rocketDetails).setText(it.description)
                view.findViewById<TextView>(R.id.rocketDate).setText(it.first_flight)

                val image = view.findViewById<ImageView>(R.id.rocket_image)
                val url = it.flickr_images[0]
                Picasso.get().load(url).into(image)

                val readMore = view.findViewById<Button>(R.id.readMore)
                val wikipedia = it.wikipedia
                readMore.setOnClickListener {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(wikipedia))
                    startActivity(browserIntent)
                }

                view.findViewById<Button>(R.id.share).setOnClickListener {
                    val shareIntent = Intent()
                    shareIntent.action = Intent.ACTION_SEND
                    shareIntent.type="text/plain"
                    shareIntent.putExtra(Intent.EXTRA_TEXT,wikipedia )
                    startActivity(Intent.createChooser(shareIntent,"Share via"))
                }


            })
    }


}