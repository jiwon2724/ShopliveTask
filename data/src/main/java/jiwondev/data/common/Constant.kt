package jiwondev.data.common

import jiwondev.data.BuildConfig
import java.security.MessageDigest
import java.sql.Timestamp

object Constant {
    val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()

    fun getHash(): String {
        val hash = "$timeStamp${BuildConfig.PUBLIC_KEY}${BuildConfig.PRIVATE_KEY}".toByteArray()
        val md = MessageDigest.getInstance("MD5").digest(hash)
        return md.joinToString("") { "%02x".format(it) }
    }
}