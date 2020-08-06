package com.quilmes.wrapper.model

enum class NexoStoreCoolerStatus {
    NOT_FOUND, REQUIRES_COMMISSIONING, PENDING, SYNCED
}

class NexoStoreCooler(val name: String,
                      val status: NexoStoreCoolerStatus,
                      val progress: Int = 0)