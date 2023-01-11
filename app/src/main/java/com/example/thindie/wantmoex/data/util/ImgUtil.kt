package com.example.thindie.wantmoex.data.network


import com.example.thindie.wantmoex.data.network.retrofit.RetrofitFactory.BASE_IMAGE_URL
import com.example.thindie.wantmoex.domain.entities.Share

fun getImg(share: Share) : String {
    val name = share.id.lowercase()
    val tail =  String.format("%s.jpg", name)
    val image = BASE_IMAGE_URL.plus(tail)


    return image
}