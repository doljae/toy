package com.example.inaction


fun <T> T?.nullSafeToString()=this?.toString()?:"NULL"