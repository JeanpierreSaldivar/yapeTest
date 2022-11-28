package com.saldivar.detail.map

import android.content.Context
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.saldivar.detail.DetailRecipeNavigator
import com.saldivar.detail.R
import com.saldivar.detail.databinding.FragmentMapLocationDishBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MapLocationDishFragment : Fragment(), OnMapReadyCallback{

    private val args: MapLocationDishFragmentArgs by navArgs()

    private lateinit var mMap: GoogleMap

    @Inject
    lateinit var navigator: DetailRecipeNavigator

    private lateinit var binding: FragmentMapLocationDishBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val contextThemeWrapper: Context =
            ContextThemeWrapper(
                requireContext(), com.saldivar.core.R.style.DetailTheme
            )
        val localInflater: LayoutInflater = inflater.cloneInContext(contextThemeWrapper)
        binding = FragmentMapLocationDishBinding.inflate(localInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    private fun initViews() {
        setupAddButton()
        mapFragment()
    }

    private fun setupAddButton() = with(binding){
        backArrow.setOnClickListener{
            navigator.onBack()
        }
    }

    private fun mapFragment() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        val countryLocation =  LatLng(args.latitude.toDouble(),args.longitude.toDouble())
        mMap.addMarker(MarkerOptions().position(countryLocation).title(args.country))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(countryLocation))
    }

}