package com.example.booklibrary.data

object SampleData {
    val genres = listOf("Fantasy", "Classics", "Fiction", "Non-fiction", "Romance", "Horror")
    val authors = listOf("Filani", "Fisteki", "Hasani", "Florinda")
    val languages = listOf("English", "Albanian", "Macedonian")
    val numberOfLikes = listOf(23, 24, 3, 45, 21, 10, 9, 18, 12)
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

    val users = listOf(
        User(name = "John", email = "john.doe@example.com", role = "Admin"),
        User(name = "Jane", email = "jane.smith@example.com", role = "User"),
        User(name = "Alice", email = "alice.johnson@example.com", role = "User"),
        User(name = "Bob", email = "bob.brown@example.com", role = "Admin"),
        User(name = "Charlie", email = "charlie.davis@example.com", role = "User"),
        User(name = "David", email = "david.wilson@example.com", role = "User"),
        User(name = "Eve", email = "eve.miller@example.com", role = "Admin"),
        User(name = "Frank", email = "frank.taylor@example.com", role = "User"),
        User(name = "Grace", email = "grace.anderson@example.com", role = "User"),
        User(name = "Hank", email = "hank.thomas@example.com", role = "Admin")
    )
}