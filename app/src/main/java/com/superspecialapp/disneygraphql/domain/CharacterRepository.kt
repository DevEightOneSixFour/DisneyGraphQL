package com.superspecialapp.disneygraphql.domain

import com.apollographql.apollo.coroutines.await
import com.superspecialapp.disneygraphql.CharacterByNameQuery
import com.superspecialapp.disneygraphql.CharactersQuery
import com.superspecialapp.disneygraphql.data.api.DisneyApi
import com.superspecialapp.disneygraphql.data.model.toDisneyItem
import com.superspecialapp.disneygraphql.data.model.toDisneyResponse
import com.superspecialapp.disneygraphql.ui.UIState
import com.superspecialapp.disneygraphql.utils.CustomException
import com.superspecialapp.disneygraphql.utils.EmptyResponseException
import com.superspecialapp.disneygraphql.utils.FailedResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface CharacterRepository {
    suspend fun queryCharacters(page: Int): Flow<UIState>//Response<CharactersQuery.Data>
    suspend fun queryByName(name: String): Flow<UIState>//Response<CharacterByNameQuery.Data>
}

class CharacterRepositoryImpl @Inject constructor(
    private val api: DisneyApi
) : CharacterRepository {

    override suspend fun queryCharacters(page: Int) = flow {
        emit(UIState.Loading)
        try {
            val response = api.getApolloClient().query(CharactersQuery(page)).await()
            if(!response.hasErrors()) {
                response.apply {
                    emit(UIState.Success(this.data?.characters?.toDisneyResponse()))
                }
            } else throw FailedResponseException()

        } catch (e: CustomException) {
            emit(UIState.Error(e))
        }
    }

    override suspend fun queryByName(name: String) = flow {
        emit(UIState.Loading)
        try {
            val response = api.getApolloClient().query(CharacterByNameQuery(name)).await()
            if (!response.hasErrors()) {
                response.apply {
                    emit(
                        UIState.Success(
                            this.data?.characterByName?.fragments?.characterFragment?.toDisneyItem()
                        )
                    )
                }
            } else throw FailedResponseException()

        } catch (e: CustomException) {
            emit(UIState.Error(e))
        }
    }
}