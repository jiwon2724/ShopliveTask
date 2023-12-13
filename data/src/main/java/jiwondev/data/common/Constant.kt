package jiwondev.data.common

import jiwondev.data.BuildConfig
import java.security.MessageDigest
import java.sql.Timestamp

object Constant {
    // api
    val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()

    fun getHash(): String {
        val hash = "$timeStamp${BuildConfig.PRIVATE_KEY}${BuildConfig.PUBLIC_KEY}".toByteArray()
        val md = MessageDigest.getInstance("MD5").digest(hash)
        return md.joinToString("") { "%02x".format(it) }
    }

    // preference
    const val CHARACTER = "character"
}