package com.bbj.testpizza.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bbj.testpizza.R
import com.bbj.testpizza.domain.models.ProductType
import com.bbj.testpizza.view.adapters.BannersListAdapter
import com.bbj.testpizza.view.adapters.ProductListAdapter
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_home

    private val homeViewModel by viewModel<HomeViewModel>()

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

        val pizzaChip = view.findViewById<Chip>(R.id.home_chip_pizza)
        pizzaChip.setOnClickListener {
            scrollToProductType(ProductType.PIZZA)
        }

        val comboChip = view.findViewById<Chip>(R.id.home_chip_combo)
        comboChip.setOnClickListener {
            scrollToProductType(ProductType.COMBO)
        }

        val dessertChip = view.findViewById<Chip>(R.id.home_chip_dessert)
        dessertChip.setOnClickListener {
            scrollToProductType(ProductType.DESSERT)
        }

        val drinkChip = view.findViewById<Chip>(R.id.home_chip_drink)
        drinkChip.setOnClickListener {
            scrollToProductType(ProductType.DRINK)
        }

        val productsAdapter = ProductListAdapter(requireContext(), onProductClick)
        productList?.adapter = productsAdapter

        homeViewModel.productList.observe(viewLifecycleOwner) { state ->
            when (state) {
                is StateModel.Success -> {
                    val products = state.data
                    pizzaStartIndex = homeViewModel.productsStartIndices[ProductType.PIZZA] ?: -1
                    comboStartIndex = homeViewModel.productsStartIndices[ProductType.COMBO] ?: -1
                    dessertStartIndex = homeViewModel.productsStartIndices[ProductType.DESSERT] ?: -1
                    drinkStartIndex = homeViewModel.productsStartIndices[ProductType.DRINK] ?: -1

                    pizzaChip.isEnabled = true
                    comboChip.isEnabled = true
                    dessertChip.isEnabled = true
                    drinkChip.isEnabled = true

                    productsAdapter.setList(products)
                }
                is StateModel.Loading -> {
                    //Handle loading state
                }
                is StateModel.Error -> {
                    pizzaChip.isEnabled = false
                    comboChip.isEnabled = false
                    dessertChip.isEnabled = false
                    drinkChip.isEnabled = false
                }
            }
        }

        homeViewModel.requestBannersList()
        homeViewModel.requestProductList()
    }

    private var pizzaStartIndex = -1
    private var comboStartIndex = -1
    private var dessertStartIndex = -1
    private var drinkStartIndex = -1


    private fun scrollToProductType(productType: ProductType) {
        when (productType) {
            ProductType.COMBO -> {
                if (pizzaStartIndex != -1)
                    productList?.smoothScrollToPosition(comboStartIndex)
            }
            ProductType.PIZZA -> {
                if (pizzaStartIndex != -1)
                    productList?.smoothScrollToPosition(pizzaStartIndex)
            }
            ProductType.DESSERT -> {
                if (dessertStartIndex != -1)
                    productList?.smoothScrollToPosition(dessertStartIndex)
            }
            ProductType.DRINK -> {
                if (drinkStartIndex != -1)
                    productList?.smoothScrollToPosition(drinkStartIndex)
            }
        }
    }

}