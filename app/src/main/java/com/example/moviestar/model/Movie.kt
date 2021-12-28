package com.example.moviestar.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int = 0,
    val title: String = "",
    val releaseYear: String = "",
    val voteAverage: Double = 0.0,
    val tagline: String = "",
    val overview: String = ""): Parcelable

fun getWorldMovies(): List<Movie> {
    return listOf(
        Movie(550, "Fight Club", "1999", 8.4, "Mischief. Mayhem. Soap.", "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \\\"fight clubs\\\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion."),
        Movie(551, "The Poseidon Adventure", "1972", 7.2, "Hell, Upside Down", "When their ocean liner capsizes, a group of passengers struggle to survive and escape."),
        Movie(552, "Bread and Tulips", "2000", 7.3, "Imagine your life. Now go live it.", "An endearing light comedy about a woman who spontaneously becomes a resident of Venice after her family left her begin. While enjoying the wonderful people she meets she achieves a new life and the first time independent of her family."),
        Movie(553, "Dogville", "2003", 7.8, "A quiet little town not far from here.", "A barren soundstage is stylishly utilized to create a minimalist small-town setting in which a mysterious woman named Grace hides from the criminals who pursue her. The town is two-faced and offers to harbor Grace as long as she can make it worth their effort, so Grace works hard under the employ of various townspeople to win their favor. Tensions flare, however, and Grace's status as a helpless outsider provokes vicious contempt and abuse from the citizens of Dogville."),
        Movie(555, "Absolut", "2005", 7.9, "", "Two guys against globalization want to plant a virus in the network of a finance corporation. On the day of the attack Alex has an accident and cannot remember anything.")
    )
}

fun getRussianMovies(): List<Movie> {
    return listOf(
        Movie(554, "The Cuckoo", "2002", 7.2, "She's Making Peace One Man at a Time.", "September of 1944, a few days before Finland went out of the Second World War. A chained to a rock Finnish sniper-kamikadze Veikko managed to set himself free. Ivan, a captain of the Soviet Army, arrested by the Front Secret Police 'Smersh', has a narrow escape. They are soldiers of the two enemy armies. A Lapp woman Anni gives a shelter to both of them at her farm. For Anni they are not enemies, but just men."),
        Movie(457842,"Arrhythmia", "2017", 7.3, "", "Oleg is a young gifted paramedic. His wife Katya works as a nurse at the hospital emergency department. She loves Oleg, but is fed up with him caring more about patients than her. She tells him she wants a divorce. The new head of Oleg’s EMA department is a cold-hearted manager who’s got new strict rules to implement. Oleg couldn’t care less about the rules – he’s got lives to save; his attitude gets him in trouble with the new boss. The crisis at work coincides with the personal life crisis. Caught up between their patients, alcohol-fueled off-shifts, and an evolving health care system, Oleg and Katya have to find the binding force that will keep them together."),
        Movie(577040, "100 Minutes", "2021", 8.0, "", "100 Minutes is the tale of thousands of Soviet soldiers who fought the Nazis and whose only ‘crime’ was to get caught. Stalin’s justice meted out on the prisoners who returned home was swift: ten years of forced labour in the Siberian camps. Why then would prisoners like Ivan Denisovich fight to stay alive to face another day of hell?"),
        Movie(633882, "A Man From Podolsk", "2020", 7.3, "", "A simple guy from Podolsk ends up in the Moscow Police Department and morally prepares for all sorts of humiliations. But the representatives of the law suddenly turn out to be exacting intellectuals…")
    )
}