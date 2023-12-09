package jiwondev.data.repository

import jiwondev.data.datasource.CharacterRemoteDataSource
import jiwondev.data.mapper.CharacterMapper
import jiwondev.domain.Result
import jiwondev.domain.model.Character
import jiwondev.domain.repository.CharacterRepository
import jiwondev.domain.setResult
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterRemoteDataSource: CharacterRemoteDataSource
) : CharacterRepository {
    override suspend fun getCharacter(
        nameStartsWith: String
    ): Result<Character> {
        return setResult { CharacterMapper.mapperToCharacter(characterRemoteDataSource.getCharacter(nameStartsWith)) }
    }
}