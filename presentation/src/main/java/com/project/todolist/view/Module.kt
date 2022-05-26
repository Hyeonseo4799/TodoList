package com.project.todolist.view

import com.project.data.datasource.TodoDataSource
import com.project.data.datasource.TodoDataSourceImpl
import com.project.data.repository.TodoRepositoryImpl
import com.project.domain.repository.TodoRepository
import com.project.domain.usecase.*
import com.project.todolist.viewmodel.TodoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named

import org.koin.dsl.module

val viewModelModule = module {
    viewModel { TodoViewModel(get(), get(), get(), get(), get()) }
}

val deleteModule = module {
    single { DeleteUseCase(get()) }
}

val listModule = module {
    single { ListUseCase(get()) }
}

val insertModule = module {
    single { InsertUseCase(get()) }
}

val getTodoModule = module {
    single { GetTodoUseCase(get()) }
}

val updateModule = module {
    single { UpdateUseCase(get()) }
}

val todoRepositoryModule: Module = module {
    single<TodoRepository> { TodoRepositoryImpl(get()) }
}

val todoDataSourceModule: Module = module {
    single<TodoDataSource> { TodoDataSourceImpl(androidContext()) }
}