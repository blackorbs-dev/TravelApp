package blackorbs.dev.travelapp.ui.details

import androidx.lifecycle.ViewModel
import blackorbs.dev.travelapp.repository.BaseTripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: BaseTripRepository
): ViewModel() {

    private val _id = MutableStateFlow(0)

    @OptIn(ExperimentalCoroutinesApi::class)
    val trip = _id.flatMapLatest {
        repository.getTrip(it)
    }

    fun getTrip(id: Int){
        _id.value = id
    }
}