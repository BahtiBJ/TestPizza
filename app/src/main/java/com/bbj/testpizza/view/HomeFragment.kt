package com.bbj.testpizza.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bbj.testpizza.R
import com.bbj.testpizza.domain.models.ProductType
import com.bbj.testpizza.util.isOnline
import com.bbj.testpizza.view.adapters.BannersListAdapter
import com.bbj.testpizza.view.adapters.ProductListAdapter
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val NOT_INIT = -1

    override val layoutId: Int = R.layout.fragment_home

    private val homeViewModel by viewModel<HomeViewModel>()

    private var loadingIndicator: LoadingIndicator? = null

    private val productList by lazy { view?.findViewById<RecyclerView>(R.id.home_products_list) }
    private val onProductClick = object : ProductListAdapter.OnProductClick {
        override fun clickOnProduct(position: Int) {
            //Handle click on product
        }
    }

    private val onBannerClick = object : BannersListAdapter.OnBannerClick {
        override fun clickBanner(position: Int) {
            //Handle click on banner
        }
    }

    private val pizzaChip by lazy { view?.findViewById<Chip>(R.id.home_chip_pizza) }
    private val comboChip by lazy { view?.findViewById<Chip>(R.id.home_chip_combo) }
    private val dessertChip by lazy { view?.findViewById<Chip>(R.id.home_chip_dessert) }
    private val drinkChip by lazy { view?.findViewById<Chip>(R.id.home_chip_drink) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loadingIndicator = context as LoadingIndicator
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bannersList = view.findViewById<RecyclerView>(R.id.home_banner_list)
        val bannersAdapter = BannersListAdapter(requireContext(), onBannerClick)
        bannersList.adapter = bannersAdapter

        homeViewModel.bannersList.observe(viewLifecycleOwner) { state ->
            when (state) {
                is StateModel.Success -> {
                    bannersAdapter.setList(state.data)
                }
                is StateModel.Loading -> {
                    //Handle loading state
                }
                is StateModel.Error -> {
                    //Handle error state
                }
            }
        }

        val productsAdapter = ProductListAdapter(requireContext(), onProductClick)
        productList?.adapter = productsAdapter

        homeViewModel.productList.observe(viewLifecycleOwner) { state ->
            when (state) {
                is StateModel.Success -> {
                    val products = state.data
                    pizzaStartIndex = homeViewModel.productsStartIndices[ProductType.PIZZA] ?: NOT_INIT
                    comboStartIndex = homeViewModel.productsStartIndices[ProductType.COMBO] ?: NOT_INIT
                    dessertStartIndex =
                        homeViewModel.productsStartIndices[ProductType.DESSERT] ?: NOT_INIT
                    drinkStartIndex = homeViewModel.productsStartIndices[ProductType.DRINK] ?: NOT_INIT

                    pizzaChip?.isEnabled = true
                    comboChip?.isEnabled = true
                    dessertChip?.isEnabled = true
                    drinkChip?.isEnabled = true

                    productsAdapter.setList(products)
                    productList?.setHasFixedSize(true)

                }
                is StateModel.Loading -> {
                    //Handle loading state
                }
                is StateModel.Error -> {
                    pizzaChip?.isEnabled = false
                    comboChip?.isEnabled = false
                    dessertChip?.isEnabled = false
                    drinkChip?.isEnabled = false
                }
            }
        }




    }

    override fun onStart() {
        super.onStart()
        requestData()
    }

    override fun onResume() {
        super.onResume()
        val motionRoot = view?.findViewById<MotionLayout>(R.id.home_root)

        pizzaChip?.setOnClickListener {
            if (motionRoot?.currentState == R.id.start)
                motionRoot.transitionToEnd()
            scrollToProductType(ProductType.PIZZA)
        }

        comboChip?.setOnClickListener {
            if (motionRoot?.currentState == R.id.start)
                motionRoot.transitionToEnd()
            scrollToProductType(ProductType.COMBO)
        }

        dessertChip?.setOnClickListener {
            if (motionRoot?.currentState == R.id.start)
                motionRoot.transitionToEnd()
            scrollToProductType(ProductType.DESSERT)
        }

        drinkChip?.setOnClickListener {
            if (motionRoot?.currentState == R.id.start)
                motionRoot.transitionToEnd()
            scrollToProductType(ProductType.DRINK)
        }

    }

    private fun requestData() {
        val isOnline = requireContext().isOnline()
        if (!isOnline)
            loadingIndicator?.showErrorNotification()
        homeViewModel.requestBannersList(isOnline)
        homeViewModel.requestProductList(isOnline)
    }

    private var pizzaStartIndex = NOT_INIT
    private var comboStartIndex = NOT_INIT
    private var dessertStartIndex = NOT_INIT
    private var drinkStartIndex = NOT_INIT


    private fun scrollToProductType(productType: ProductType) {
        when (productType) {
            ProductType.COMBO -> {
                if (comboStartIndex != NOT_INIT) {
                    (productList?.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                        comboStartIndex,
                        0
                    )
                }
            }
            ProductType.PIZZA -> {
                if (pizzaStartIndex != NOT_INIT) {
                    (productList?.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                        pizzaStartIndex,
                        0
                    )
                }
            }
            ProductType.DESSERT -> {
                if (dessertStartIndex != NOT_INIT) {
                    (productList?.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                        dessertStartIndex,
                        0
                    )
                }

            }
            ProductType.DRINK -> {
                if (drinkStartIndex != NOT_INIT) {
                    (productList?.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                        drinkStartIndex,
                        0
                    )
                }
            }
        }
    }

    override fun onDetach() {
        loadingIndicator = null
        super.onDetach()
    }

}