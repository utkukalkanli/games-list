package com.example.trendyol_internship.ui.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trendyol_internship.data.detail.model.GameDetail
import com.example.trendyol_internship.data.detail.service.DetailAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
//class DetailViewModel @Inject constructor(private val service: DetailAPIService) : ViewModel(){
class DetailViewModel : ViewModel(){

    private val detailAPIService = DetailAPIService()
    private val disposable = CompositeDisposable() // fragment öldügünde memory'i bosaltıyor, her call memory'de yer tutuyor, fragment temizlendiğinde disposable içindeki objeler temizleniyor

    // mutable = değiştirilebilir data
    val gameDetail = MutableLiveData<GameDetail>()
    val gameDetailError = MutableLiveData<Boolean>()
    val gameDetailLoading = MutableLiveData<Boolean>()

    fun getGameDetail(gameID: Int){
        getDataFromAPI(gameID)
    }

    private fun getDataFromAPI(gameID: Int){
        gameDetailLoading.value = true

        disposable.add(
            detailAPIService.getData(gameID)
                .subscribeOn(Schedulers.newThread()) // veri cekme isi arkaplanda, asenkron bir sekilde
                .observeOn(AndroidSchedulers.mainThread()) // gösterme isi main thread'de
                .subscribeWith(object : DisposableSingleObserver<GameDetail>(){
                    override fun onSuccess(t: GameDetail) {
                        gameDetail.value = t
                        gameDetailError.value = false
                        gameDetailLoading.value = false
                    }
                    override fun onError(e: Throwable) {
                        gameDetailError.value = true
                        gameDetailLoading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }
}