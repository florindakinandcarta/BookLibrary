package com.example.booklibrary.data

object SampleData {
    val genres = listOf("Fantasy", "Classics", "Fiction", "Non-fiction", "Romance", "Horror")
    val authors =  listOf("Filani", "Fisteki", "Hasani", "Florinda")
    val languages = listOf("English", "Albanian", "Macedonian")
    val numberOfLikes = listOf(23,24,3,45,21,10,9,18,12)
    val returnStatus = listOf("Return", "Report Damage")
    val countries = listOf("Pristina", "Skopje", "Sofia")

    val books = (0..25).map {
        Book(
            it.toString(),
            title = "Book $it",
            genre = genres.random(),
            author = authors.random(),
            language = languages.random(),
            numberOfLikes = numberOfLikes.random()
        )
    }
}