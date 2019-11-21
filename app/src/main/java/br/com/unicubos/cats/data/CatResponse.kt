package br.com.unicubos.cats.data

data class CatResponse (
    val breed_ids: Any,
    val breeds: List<Any>,
    val categories: List<Category>,
    val created_at: String,
    val height: Int,
    val id: String,
    val original_filename: String,
    val sub_id: String,
    val url: String,
    val width: Int
)