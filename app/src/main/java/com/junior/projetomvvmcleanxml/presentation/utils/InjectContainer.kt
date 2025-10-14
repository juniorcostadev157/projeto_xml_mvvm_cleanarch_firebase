package com.junior.projetomvvmcleanxml.presentation.utils


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.junior.projetomvvmcleanxml.data.datasource.FirebaseAuthDataSource
import com.junior.projetomvvmcleanxml.data.datasource.FirebaseItemDataSource
import com.junior.projetomvvmcleanxml.data.datasource.FirebaseUserDataSource
import com.junior.projetomvvmcleanxml.data.repository.ItemRepositoryImpl
import com.junior.projetomvvmcleanxml.data.repository.UserRepositoryImpl
import com.junior.projetomvvmcleanxml.data.repository.authrepository.AuthRepositoryImpl
import com.junior.projetomvvmcleanxml.domain.usecase.authenticationusecase.CreateRegisterValidationUseCase
import com.junior.projetomvvmcleanxml.domain.usecase.authenticationusecase.LoginValidationUseCase
import com.junior.projetomvvmcleanxml.domain.usecase.item.CreateItemUseCase
import com.junior.projetomvvmcleanxml.domain.usecase.item.ListItemUseCase
import com.junior.projetomvvmcleanxml.domain.usecase.users.CreateUsersUseCase
import com.junior.projetomvvmcleanxml.presentation.cadastro.CadastroViewModel
import com.junior.projetomvvmcleanxml.presentation.login.LoginViewModel
import com.junior.projetomvvmcleanxml.presentation.principal.createitem.CreateItemViewModel
import com.junior.projetomvvmcleanxml.presentation.principal.list_item.ListItemViewModel

object InjectContainer {

    //login
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val authDataSource by lazy { FirebaseAuthDataSource(firebaseAuth) }
    private val authRepository by lazy { AuthRepositoryImpl(authDataSource) }
    private val loginValidationUseCase by lazy { LoginValidationUseCase(authRepository) }

    val loginFactory: GenericViewModelFactory<LoginViewModel> by lazy {
        GenericViewModelFactory { LoginViewModel(loginValidationUseCase) }
    }

    //cadastro
    private val firestore by lazy { FirebaseFirestore.getInstance() }
    private val userDataSource by lazy { FirebaseUserDataSource(firestore) }
    private val userRepository by lazy { UserRepositoryImpl(userDataSource) }
    private val createUsersUseCase by lazy { CreateUsersUseCase(userRepository) }
    private val createRegisterValidationUseCase by lazy { CreateRegisterValidationUseCase(authRepository) }

    val cadastroFactory: GenericViewModelFactory<CadastroViewModel> by lazy {
        GenericViewModelFactory{
            CadastroViewModel(
                createUsers = createUsersUseCase,
                createRegister = createRegisterValidationUseCase
            )
        }
    }

    //cadastroItem
    private val itemDataSource by lazy { FirebaseItemDataSource(firestore) }
    private val itemRepository by lazy { ItemRepositoryImpl(itemDataSource) }
    private val createItemUseCase by lazy { CreateItemUseCase(itemRepository) }

    val createItemFactory: GenericViewModelFactory<CreateItemViewModel> by lazy {
        GenericViewModelFactory{
            CreateItemViewModel(createItemUseCase)
        }
    }

    //listItem
    private val listItemUseCase by lazy { ListItemUseCase(itemRepository) }
    val listItemFactory: GenericViewModelFactory<ListItemViewModel> by lazy {
        GenericViewModelFactory {
            ListItemViewModel(listItemUseCase)

        }


    }

}

