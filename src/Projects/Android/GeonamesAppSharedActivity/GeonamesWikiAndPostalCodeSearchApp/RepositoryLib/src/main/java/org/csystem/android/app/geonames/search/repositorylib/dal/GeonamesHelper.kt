package org.csystem.android.app.geonames.search.repositorylib.dal

import org.csystem.android.app.geonames.search.postalcode.api.entity.PostalCode
import org.csystem.android.app.geonames.search.repositorylib.database.dao.IPostalCodeSearchDao
import org.csystem.android.app.geonames.search.repositorylib.database.dao.IQuerySearchDao
import org.csystem.android.app.geonames.search.repositorylib.database.dao.IWikiInfoSearchDao
import org.csystem.android.app.geonames.search.repositorylib.database.entity.QuerySearch
import org.csystem.android.app.geonames.search.repositorylib.dto.PostalCodeDTO
import org.csystem.android.app.geonames.search.repositorylib.dto.PostalCodeListDBDTO
import org.csystem.android.app.geonames.search.repositorylib.dto.QuerySearchDTO
import org.csystem.android.app.geonames.search.repositorylib.dto.WikiInfoDTO
import org.csystem.android.app.geonames.search.repositorylib.dto.WikiSearchDBDTO
import org.csystem.android.app.geonames.search.repositorylib.mapper.di.module.postalcode.IPostalCodeMapper
import org.csystem.android.app.geonames.search.repositorylib.mapper.di.module.querysearch.IQuerySearchMapper
import org.csystem.android.app.geonames.search.repositorylib.mapper.di.module.wikiInfo.IWikiInfoMapper
import org.csystem.android.app.geonames.search.wikisearch.api.entity.WikiInfo
import javax.inject.Inject

class GeonamesHelper @Inject constructor(
    wikiSearchDao: IWikiInfoSearchDao,
    postalCodeSearchDao: IPostalCodeSearchDao,
    querySearchDao: IQuerySearchDao,
    wikiInfoMapper: IWikiInfoMapper,
    postalCodeMapper: IPostalCodeMapper,
    querySearchMapper: IQuerySearchMapper)
{
    private val mWikiSearchDao = wikiSearchDao
    private val mPostalCodeSearchDao = postalCodeSearchDao
    private val mQuerySearchDao = querySearchDao
    private val mWikiInfoMapper = wikiInfoMapper
    private val mPostalCodeMapper = postalCodeMapper
    private val mQuerySearchMapper = querySearchMapper

    fun saveWikiInfoList(wikiSearchDBDTO: WikiSearchDBDTO, wikiInfoList:List<WikiInfo>)
    {
        try
        {
            mWikiSearchDao.saveWikiSearch(mWikiInfoMapper.toWikiSearch(wikiSearchDBDTO))

            wikiInfoList.forEach {
                mWikiSearchDao.save(mWikiInfoMapper.toWikiInfoSaveDTO(it, wikiSearchDBDTO.q))
            }

        } catch (ex: Throwable) {

        }
    }

    fun existsWikiInfoByWikiSearchDTO(wikiSearchDBDTO: WikiSearchDBDTO):Boolean
    {
        var result: Boolean
        try {
            result = mWikiSearchDao.existsWikiInfoByQAndRowCount(wikiSearchDBDTO.q, wikiSearchDBDTO.rowCount)
        }catch (ex:Throwable){
            result = false
        }
        return result
    }

    fun findWikiInfoByWikiSearchDTO(wikiSearchDBDTO: WikiSearchDBDTO):List<WikiInfo>
    {
        return mWikiSearchDao.findWikiInfoByWikiSearch(wikiSearchDBDTO.q, wikiSearchDBDTO.rowCount).map {
            mWikiInfoMapper.toWikiInfo(it)
        }
    }

    fun toWikiInfoDTO(wikiInfo: WikiInfo):WikiInfoDTO
    {
        return mWikiInfoMapper.toWikiInfoDTO(wikiInfo)
    }

    fun savePostalCodeList(postalCodeSearchDBDTO: PostalCodeListDBDTO, postalCodeList: List<PostalCode>)
    {
        try {
            mPostalCodeSearchDao.savePostalCodeList(mPostalCodeMapper.toPostalCodeSearchSaveDTO(postalCodeSearchDBDTO))

            postalCodeList.forEach {
                mPostalCodeSearchDao.save(mPostalCodeMapper.toPostalCodeDBDTO(it))
            }
        } catch (ex: Throwable) {

        }
    }

    fun existPostalCodeByPostalCodeListDTO(postalCodeListDBDTO: PostalCodeListDBDTO):Boolean
    {
        return mPostalCodeSearchDao.existsPostalCodeByPostalCodeNumberAndRowCount(postalCodeListDBDTO.postalCode, postalCodeListDBDTO.rowCount)
    }

    fun findPostalCodeByPostalCodeListDTO(postalCodeListDBDTO: PostalCodeListDBDTO):List<PostalCode>
    {
        return mPostalCodeSearchDao.findPostalCodeByPostalCodeNumber(postalCodeListDBDTO.postalCode).map {
            mPostalCodeMapper.toPostalCode(it)
        }
    }

    fun toPostalCodeDTO(postalCode: PostalCode):PostalCodeDTO
    {
        return mPostalCodeMapper.toPostalCodeDTO(postalCode)
    }


    fun saveQuerySearch(querySearchDTO: QuerySearchDTO){
        try {
            mQuerySearchDao.save(mQuerySearchMapper.toQuerySearch(querySearchDTO))
        }catch (ex:Throwable){

        }
    }

    fun getQuerySearchList(queryType:String):List<QuerySearchDTO>
    {
        return mQuerySearchDao.getAllQuerySearch(queryType).map {
            mQuerySearchMapper.toQuerySearchDTO(it)
        }
    }

}