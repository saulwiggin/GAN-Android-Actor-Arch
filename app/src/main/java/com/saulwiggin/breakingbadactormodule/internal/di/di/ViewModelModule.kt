package com.saulwiggin.breakingbadactormodule.internal.di.di

import com.saulwiggin.breakingbadactormodule.ui.actorlist.ActorListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel { ActorListViewModel(get()) }
}