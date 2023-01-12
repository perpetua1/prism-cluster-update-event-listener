package com.perpetua.event.listener

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.regions.Regions
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.AmazonSNSClientBuilder
import com.amazonaws.services.sns.model.PublishRequest
import java.io.InputStream
import java.io.OutputStream

class ClusterUpdatedEventHandler : RequestStreamHandler {

    companion object {
        val CLUSTER_UPDATED_TOPIC_ARN: String = System.getenv("CLUSTER_UPDATED_TOPIC")
    }

    override fun handleRequest(inputStream: InputStream, outputStream: OutputStream, context: Context) {
        val messageAsString = inputStream.bufferedReader().use { it.readText() }
        getSnsClient().publish(getPublishRequest(messageAsString))
    }

    private fun getPublishRequest(message: String): PublishRequest {
        val publishRequest = PublishRequest()
        publishRequest.message = message
        publishRequest.topicArn = CLUSTER_UPDATED_TOPIC_ARN
        return publishRequest
    }

    private fun getSnsClient(): AmazonSNS = AmazonSNSClientBuilder.standard().withRegion(Regions.EU_WEST_1)
        .withCredentials(DefaultAWSCredentialsProviderChain()).build()


}