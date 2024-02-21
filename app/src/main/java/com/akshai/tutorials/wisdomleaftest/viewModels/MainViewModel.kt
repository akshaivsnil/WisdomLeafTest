package com.akshai.tutorials.wisdomleaftest.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akshai.tutorials.wisdomleaftest.domain.ListDomainModel
import com.akshai.tutorials.wisdomleaftest.entity.ListApiResponse
import com.akshai.tutorials.wisdomleaftest.mapper.ListModelMapper
import com.akshai.tutorials.wisdomleaftest.repository.DataHandler
import com.akshai.tutorials.wisdomleaftest.repository.MainRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


/**
 * Created by ATM on 20/October/2022
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainRepo: MainRepo,
) : ViewModel() {

    private val _liveData =
        MutableLiveData<DataHandler<List<ListDomainModel>>>()
    val responseLiveData: LiveData<DataHandler<List<ListDomainModel>>> = _liveData

    var response: List<ListDomainModel>? = null

    init {
        getListFromRepo()
    }


    fun getListFromRepo() = viewModelScope.launch {
//        _liveData.postValue(DataHandler.LOADING())
        val response = async {
            mainRepo.getListFromApiHelper()
        }
        _liveData.postValue(handleResponse(response.await()))
    }


    /**
     * Response handler ,
     * It will map the Entity to the Domain model
     */
    private fun handleResponse(response: Response<List<ListApiResponse>>): DataHandler<List<ListDomainModel>> {
        if (response.isSuccessful) {
            response.body()?.let {
                val listData: List<ListApiResponse> = it
                val mapper = ListModelMapper()
                return DataHandler.SUCCESS(
                    mapper.fromEntityList(
                        listData
                    )
                )
            }
        }
        return DataHandler.ERROR(message = "Data not Found")
    }

}