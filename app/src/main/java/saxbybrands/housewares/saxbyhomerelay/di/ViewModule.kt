package saxbybrands.housewares.saxbyhomerelay.di

import saxbybrands.housewares.saxbyhomerelay.ui.viewmodel.AppViewModel
import saxbybrands.housewares.saxbyhomerelay.ui.viewmodel.CartViewModel
import saxbybrands.housewares.saxbyhomerelay.ui.viewmodel.CheckoutViewModel
import saxbybrands.housewares.saxbyhomerelay.ui.viewmodel.RQDMVOnboardingVM
import saxbybrands.housewares.saxbyhomerelay.ui.viewmodel.OrderViewModel
import saxbybrands.housewares.saxbyhomerelay.ui.viewmodel.ProductDetailsViewModel
import saxbybrands.housewares.saxbyhomerelay.ui.viewmodel.ProductViewModel
import saxbybrands.housewares.saxbyhomerelay.ui.viewmodel.RQDMVSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        RQDMVSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        RQDMVOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        ProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderRepository = get(),
        )
    }
}