package com.example.trendyol_internship.ui.listing.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trendyol_internship.data.listing.model.Game
import com.example.trendyol_internship.data.listing.service.ListingAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListingViewModel: ViewModel() {

    private val listingAPIService = ListingAPIService()
    private val disposable = CompositeDisposable() // fragment öldügünde memory'i bosaltıyor, her call memory'de yer tutuyor, fragment temizlendiğinde disposable içindeki objeler temizleniyor

    // mutable = değiştirilebilir data
    val gameList = MutableLiveData<List<Game>>()
    val gameListError = MutableLiveData<Boolean>()
    val gameListLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        getDataFromAPI()
    }

    private fun getDataFromAPI(){
        gameListLoading.value = true

        disposable.add(
            listingAPIService.getList()
                .subscribeOn(Schedulers.io()) // veri cekme isi arkaplanda, asenkron bir sekilde
                .observeOn(AndroidSchedulers.mainThread()) // gösterme isi main thread'de
                .subscribeWith(object : DisposableSingleObserver<List<Game>>(){
                    override fun onSuccess(t: List<Game>) {
                        gameList.value = t
                        gameListError.value = false
                        gameListLoading.value = false
                    }
                    override fun onError(e: Throwable) {
                        gameListError.value = true
                        gameListLoading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }
}