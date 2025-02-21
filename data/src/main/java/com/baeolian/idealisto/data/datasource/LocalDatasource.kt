package com.baeolian.idealisto.data.datasource

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Date
import javax.inject.Inject

class LocalDatasource @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) {
    companion object {
        private const val FAVORITES = "favorites"
    }

    private val _favorites = MutableStateFlow<Map<String, Date>>(emptyMap())
    val favorites = _favorites.asStateFlow()

    private fun getFavoritesFromStoredJSON(): MutableMap<String, Date> {
        val jsonString = sharedPreferences.getString(FAVORITES, null) ?: return mutableMapOf()
        val type = object : TypeToken<MutableMap<String, Long>>() {}.type
        val storedMap: MutableMap<String, Long> = gson.fromJson(jsonString, type) ?: mutableMapOf()
        return storedMap.mapValues { Date(it.value) }.toMutableMap()
    }

    private fun saveMapToStoredJSON(map: Map<String, Date>) {
        val timestampMap = map.mapValues { it.value.time }
        val jsonString = gson.toJson(timestampMap)
        sharedPreferences.edit().putString(FAVORITES, jsonString).apply()
        _favorites.update {
            timestampMap.mapValues { Date(it.value) }
        }
    }

    fun fetchFavorites() {
        _favorites.update {
            getFavoritesFromStoredJSON()
        }
    }

    fun addFavorite(mapKey: String, mapValue: Date) {
        val map = getFavoritesFromStoredJSON()
        map[mapKey] = mapValue
        saveMapToStoredJSON(map)
    }

    fun deleteFavorite(mapKey: String) {
        val map = getFavoritesFromStoredJSON()
        if (map.remove(mapKey) != null) {
            saveMapToStoredJSON(map)
        }
    }
}
