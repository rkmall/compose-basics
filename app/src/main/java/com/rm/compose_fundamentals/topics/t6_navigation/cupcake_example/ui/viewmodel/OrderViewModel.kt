package com.rm.compose_fundamentals.topics.t6_navigation.cupcake_example.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.rm.compose_fundamentals.topics.t6_navigation.cupcake_example.data.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * [OrderViewModel] holds information about cupcake order in terms of quantity, flavor
 * and pickup date. It also calculates the total price based on order details.
 */
class OrderViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OrderUiState(pickUpOptions = pickupOptions()))
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    fun setFlavour(flavor: String) {
        _uiState.update { currentState ->
            currentState.copy(flavor = flavor)
        }
    }

    fun setQuantity(numberOfCupcakes: Int) {
        _uiState.update {  currentState ->
            currentState.copy(
                quantity = numberOfCupcakes,
                price = calculatePrice(quantity = numberOfCupcakes)
            )
        }
    }

    fun setPickUpDate(pickUpDate: String) {
        _uiState.update { currentState ->
            currentState.copy(
                date = pickUpDate,
                price = calculatePrice(pickupDate = pickUpDate)
            )
        }
    }

    fun resetOrder() {
        _uiState.value = OrderUiState(pickUpOptions = pickupOptions())
    }

    private fun calculatePrice(
        quantity: Int = _uiState.value.quantity,
        pickupDate: String = _uiState.value.date
    ): String {
        var calculatedPrice = quantity * PRICE_PER_CUPCAKE

        // If the user selects the first day option (same day) for pickup, add surcharge
        if (pickupOptions()[0] == pickupDate) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }

        val formattedPrice = NumberFormat.getCurrencyInstance().format(calculatedPrice)
        return formattedPrice
    }

    private fun pickupOptions(): List<String> {
        val dateOptions = mutableListOf<String>()
        val formatter = DateTimeFormatter.ofPattern("E MM dd", Locale.ENGLISH)
        var date = LocalDate.now()

        repeat(4) {
            dateOptions.add(formatter.format(date))
            date = date.plusDays(1)
        }
        return dateOptions
    }
}

private const val PRICE_PER_CUPCAKE = 2.00
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00