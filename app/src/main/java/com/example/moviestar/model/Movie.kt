package com.example.moviestar.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val title: String = "",
    val releaseYear: Int = 0,
    val voteAverage: Double = 0.0,
    val genres: List<String> = listOf(""),
    val tagline: String = "",
    val overview: String = ""): Parcelable

fun getWorldMovies(): List<Movie> {
    return listOf(
        Movie("Fight Club", 1999, 8.4, listOf("Drama") , "Mischief. Mayhem. Soap.", "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \\\"fight clubs\\\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion."),
        Movie("The Poseidon Adventure", 1972, 7.2, listOf("Action", "Adventure", "Drama", "Thriller"), "Hell, Upside Down", "When their ocean liner capsizes, a group of passengers struggle to survive and escape."),
        Movie("Bread and Tulips", 2000, 7.3, listOf("Drama", "Comedy", "Romance"), "Imagine your life. Now go live it.", "An endearing light comedy about a woman who spontaneously becomes a resident of Venice after her family left her begin. While enjoying the wonderful people she meets she achieves a new life and the first time independent of her family."),
        Movie("Dogville", 2003, 7.8, listOf("Crime", "Drama", "Thriller"), "A quiet little town not far from here.", "A barren soundstage is stylishly utilized to create a minimalist small-town setting in which a mysterious woman named Grace hides from the criminals who pursue her. The town is two-faced and offers to harbor Grace as long as she can make it worth their effort, so Grace works hard under the employ of various townspeople to win their favor. Tensions flare, however, and Grace's status as a helpless outsider provokes vicious contempt and abuse from the citizens of Dogville."),
        Movie("Absolut", 2005, 7.9, listOf("Thriller"), "", "Two guys against globalization want to plant a virus in the network of a finance corporation. On the day of the attack Alex has an accident and cannot remember anything.")
    )
}

fun getRussianMovies(): List<Movie> {
    return listOf(
        Movie("The Cuckoo", 2002, 7.2, listOf("Drama", "History", "Romance") , "She's Making Peace One Man at a Time.", "September of 1944, a few days before Finland went out of the Second World War. A chained to a rock Finnish sniper-kamikadze Veikko managed to set himself free. Ivan, a captain of the Soviet Army, arrested by the Front Secret Police 'Smersh', has a narrow escape. They are soldiers of the two enemy armies. A Lapp woman Anni gives a shelter to both of them at her farm. For Anni they are not enemies, but just men."),
        Movie("Arrhythmia", 2017, 7.3, listOf("Drama"), "", "Oleg is a young gifted paramedic. His wife Katya works as a nurse at the hospital emergency department. She loves Oleg, but is fed up with him caring more about patients than her. She tells him she wants a divorce. The new head of Oleg’s EMA department is a cold-hearted manager who’s got new strict rules to implement. Oleg couldn’t care less about the rules – he’s got lives to save; his attitude gets him in trouble with the new boss. The crisis at work coincides with the personal life crisis. Caught up between their patients, alcohol-fueled off-shifts, and an evolving health care system, Oleg and Katya have to find the binding force that will keep them together."),
        Movie("Father and Son", 2003, 6.4, listOf("Drama"), "", "A small family \"a father and a son\" lives on the top floor of an old house. The father retired from the military, when he was a student in flight school, he experienced the first and the only love of his life. This girl became his wife and she gave birth to his son. Both of them were twenty years old then. The wife died when she was young. This love remained his secret unique happiness.  The son grew up, and he will probably be a military man like his father. The son's features constantly remind the father of his wife. He doesn't separate his son from his still persisting love: this is his unity with his beloved woman. The father cannot imagine his life without his son. The son loves his father devotedly and deeply, a filial feeling intensified by an instinctive moral responsibility that is being tested by life. Their love is almost of mythological virtue and scale. It cannot happen in real life. This is a fairy–tale collision."),
        Movie("100 Minutes", 2021, 8.0, listOf("Drama"), "", "100 Minutes is the tale of thousands of Soviet soldiers who fought the Nazis and whose only ‘crime’ was to get caught. Stalin’s justice meted out on the prisoners who returned home was swift: ten years of forced labour in the Siberian camps. Why then would prisoners like Ivan Denisovich fight to stay alive to face another day of hell?"),
        Movie("A Man From Podolsk", 2020, 7.3, listOf("Drama", "Comedy"),"", "A simple guy from Podolsk ends up in the Moscow Police Department and morally prepares for all sorts of humiliations. But the representatives of the law suddenly turn out to be exacting intellectuals…")
    )
}