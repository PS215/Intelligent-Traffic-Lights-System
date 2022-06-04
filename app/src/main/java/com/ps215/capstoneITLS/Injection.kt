package com.ps215.capstoneITLS

import android.content.Context
import com.ps215.capstoneITLS.database.Repository

object Injection {
    fun provideRepository(): Repository {
        return Repository.getInstance()
    }
}