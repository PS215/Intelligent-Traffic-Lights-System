package com.ps215.capstoneITLS

import com.ps215.capstoneITLS.data.Repository

object Injection {
    fun provideRepository(): Repository {
        return Repository.getInstance()
    }
}