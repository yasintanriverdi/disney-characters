package com.yasintanriverdi.disneycharacters.data.repository

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yasintanriverdi.disneycharacters.common.Resource
import com.yasintanriverdi.disneycharacters.data.remote.CharactersApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
class CharacterRepositoryImplTest {

    lateinit var repository: CharacterRepositoryImpl
    lateinit var charactersApi: CharactersApi
    lateinit var mockServer: MockWebServer

    val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        configureMockServer()
        charactersApi = provideTestApi()
        repository = CharacterRepositoryImpl(charactersApi, dispatcher)
    }

    @After
    fun tearDown() {
        stopMockServer()
    }

    @Test
    fun `fetch characters successfully by given mock data`() = runTest {
        mockHttpResponse("api_response/mock_characters.json", HttpURLConnection.HTTP_OK)

        val result = repository.getCharacters()
        assertThat(result).isInstanceOf(Resource.Success::class.java)
        assertThat(result.data).isNotNull()
    }

    @Test
    fun `fetch characters with exception`() = runTest {
        mockHttpResponse("api_response/mock_characters.json", HttpURLConnection.HTTP_FORBIDDEN)

        val result = repository.getCharacters()
        assertThat(result).isInstanceOf(Resource.Error::class.java)
        assertThat(result.data).isNull()
    }

    @Test
    fun `fetch character successfully by given mock data`() = runTest {
        val characterId = "58"
        mockHttpResponse("api_response/mock_character.json", HttpURLConnection.HTTP_OK)

        val result = repository.getCharacter(characterId)
        assertThat(result).isInstanceOf(Resource.Success::class.java)
        assertThat(result.data).isNotNull()
    }

    @Test
    fun `fetch character with exception`() = runTest {
        val characterId = "58"
        mockHttpResponse("api_response/mock_character.json", HttpURLConnection.HTTP_FORBIDDEN)

        val result = repository.getCharacter(characterId)
        assertThat(result).isInstanceOf(Resource.Error::class.java)
        assertThat(result.data).isNull()
    }

    fun configureMockServer() {
        mockServer = MockWebServer()
        mockServer.start()
    }

    fun stopMockServer() {
        mockServer.shutdown()
    }

    fun mockHttpResponse(fileName: String, responseCode: Int) =
        mockServer.enqueue(
            MockResponse()
                .setResponseCode(responseCode)
                .setBody(getJson(fileName))
        )

    fun getJson(path: String): String {
        val uri = this.javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }

    fun provideTestApi(): CharactersApi {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        val okHttpClient = OkHttpClient.Builder().build()

        return Retrofit.Builder()
            .baseUrl(mockServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create(CharactersApi::class.java)
    }

}