package com.perpetua.event.listener

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import java.io.InputStream
import java.io.OutputStream

class ClusterUpdatedEventHandler: RequestStreamHandler {

    override fun handleRequest(p0: InputStream?, p1: OutputStream?, p2: Context?) {
        TODO("Not yet implemented")
    }


}