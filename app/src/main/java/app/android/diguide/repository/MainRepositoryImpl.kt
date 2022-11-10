package app.android.diguide.repository

class MainRepositoryImpl(
    private val apiInterface: ApiInterface
) : MainRepository {

    override suspend fun getData() = apiInterface.getData()

}