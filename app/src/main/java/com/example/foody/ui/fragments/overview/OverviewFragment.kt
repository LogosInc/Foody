package com.example.foody.ui.fragments.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import coil.load
import com.example.foody.R
import com.example.foody.databinding.FragmentOverviewBinding
import com.example.foody.models.Result
import com.example.foody.util.Constants.Companion.RECIPE_RESULT_KEY
import org.jsoup.Jsoup

class OverviewFragment : Fragment() {

    private var _bingding: FragmentOverviewBinding? = null
    private val bingding get() = _bingding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _bingding = FragmentOverviewBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable(RECIPE_RESULT_KEY)

        bingding.mainImageView.load(myBundle?.image)
        bingding.titlTextView.text = myBundle?.title
        bingding.likesTextView.text = myBundle?.aggregateLikes.toString()
        bingding.timeTextView.text = myBundle?.readyInMinutes.toString()
        myBundle?.summary.let {
            val summary = Jsoup.parse(it).text()
            bingding.summaryTextView.text = summary
        }

        if (myBundle?.vegetarian == true){
            bingding.vegetarianImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            bingding.vegetarianTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }

        if (myBundle?.vegan == true){
            bingding.veganImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            bingding.veganTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }

        if (myBundle?.glutenFree == true){
            bingding.glutenFreeImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            bingding.glutenFreeTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }

        if (myBundle?. dairyFree== true){
            bingding.dairyFreeImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            bingding.dairyFreeTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }

        if (myBundle?.veryHealthy == true){
            bingding.healthyImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            bingding.healthyTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }

        if (myBundle?.cheap == true){
            bingding.cheapImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            bingding.cheapTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }

        return bingding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bingding = null
    }
}