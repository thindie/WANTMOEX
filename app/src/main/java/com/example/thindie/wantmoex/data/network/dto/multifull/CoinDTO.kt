package com.example.thindie.wantmoex.data.network.dto.multifull


import com.google.gson.annotations.SerializedName

data class CoinDTO(
    @SerializedName("CHANGE24HOUR")
    val cHANGE24HOUR: String,
    @SerializedName("CHANGEDAY")
    val cHANGEDAY: String,
    @SerializedName("CHANGEHOUR")
    val cHANGEHOUR: String,
    @SerializedName("CHANGEPCT24HOUR")
    val cHANGEPCT24HOUR: String,
    @SerializedName("CHANGEPCTDAY")
    val cHANGEPCTDAY: String,
    @SerializedName("CHANGEPCTHOUR")
    val cHANGEPCTHOUR: String,
    @SerializedName("CIRCULATINGSUPPLY")
    val cIRCULATINGSUPPLY: String,
    @SerializedName("CIRCULATINGSUPPLYMKTCAP")
    val cIRCULATINGSUPPLYMKTCAP: String,
    @SerializedName("CONVERSIONSYMBOL")
    val cONVERSIONSYMBOL: String,
    @SerializedName("CONVERSIONTYPE")
    val cONVERSIONTYPE: String,
    @SerializedName("FROMSYMBOL")
    val fROMSYMBOL: String,
    @SerializedName("HIGH24HOUR")
    val hIGH24HOUR: String,
    @SerializedName("HIGHDAY")
    val hIGHDAY: String,
    @SerializedName("HIGHHOUR")
    val hIGHHOUR: String,
    @SerializedName("IMAGEURL")
    val iMAGEURL: String,
    @SerializedName("LASTMARKET")
    val lASTMARKET: String,
    @SerializedName("LASTTRADEID")
    val lASTTRADEID: String,
    @SerializedName("LASTUPDATE")
    val lASTUPDATE: String,
    @SerializedName("LASTVOLUME")
    val lASTVOLUME: String,
    @SerializedName("LASTVOLUMETO")
    val lASTVOLUMETO: String,
    @SerializedName("LOW24HOUR")
    val lOW24HOUR: String,
    @SerializedName("LOWDAY")
    val lOWDAY: String,
    @SerializedName("LOWHOUR")
    val lOWHOUR: String,
    @SerializedName("MARKET")
    val mARKET: String,
    @SerializedName("MKTCAP")
    val mKTCAP: String,
    @SerializedName("MKTCAPPENALTY")
    val mKTCAPPENALTY: String,
    @SerializedName("OPEN24HOUR")
    val oPEN24HOUR: String,
    @SerializedName("OPENDAY")
    val oPENDAY: String,
    @SerializedName("OPENHOUR")
    val oPENHOUR: String,
    @SerializedName("PRICE")
    val pRICE: String,
    @SerializedName("SUPPLY")
    val sUPPLY: String,
    @SerializedName("TOPTIERVOLUME24HOUR")
    val tOPTIERVOLUME24HOUR: String,
    @SerializedName("TOPTIERVOLUME24HOURTO")
    val tOPTIERVOLUME24HOURTO: String,
    @SerializedName("TOSYMBOL")
    val tOSYMBOL: String,
    @SerializedName("TOTALTOPTIERVOLUME24H")
    val tOTALTOPTIERVOLUME24H: String,
    @SerializedName("TOTALTOPTIERVOLUME24HTO")
    val tOTALTOPTIERVOLUME24HTO: String,
    @SerializedName("TOTALVOLUME24H")
    val tOTALVOLUME24H: String,
    @SerializedName("TOTALVOLUME24HTO")
    val tOTALVOLUME24HTO: String,
    @SerializedName("VOLUME24HOUR")
    val vOLUME24HOUR: String,
    @SerializedName("VOLUME24HOURTO")
    val vOLUME24HOURTO: String,
    @SerializedName("VOLUMEDAY")
    val vOLUMEDAY: String,
    @SerializedName("VOLUMEDAYTO")
    val vOLUMEDAYTO: String,
    @SerializedName("VOLUMEHOUR")
    val vOLUMEHOUR: String,
    @SerializedName("VOLUMEHOURTO")
    val vOLUMEHOURTO: String
)