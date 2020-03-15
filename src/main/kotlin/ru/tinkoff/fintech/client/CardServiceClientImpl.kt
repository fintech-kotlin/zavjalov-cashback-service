package ru.tinkoff.fintech.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import ru.tinkoff.fintech.model.Card

@Service
class CardServiceClientImpl(
    private val restTemplate: RestTemplate,
    @Value("\${paimentprocessing.uri.card-info}")
    private val uri: String
) : CardServiceClient {

    override fun getCard(id: String): Card {
        val res = restTemplate.getForEntity("$uri/$id", Card::class.java)
        if (!res.statusCode.is2xxSuccessful) {
            throw RestClientException("Incorrect status: ${res.statusCodeValue}")
        }
        return res.body
    }
}