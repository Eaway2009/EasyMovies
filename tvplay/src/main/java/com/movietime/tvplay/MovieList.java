package com.movietime.tvplay;

import java.util.ArrayList;
import java.util.List;

public final class MovieList {
    public static final String MOVIE_CATEGORY[] = {
            "潮剧",
            "电影",
    };

    static String[] title = {
            "视频A",
            "视频B",
            "视频1",
            "视频2",
            "视频3",
    };

    static String description = "Fusce id nisi turpis. Praesent viverra bibendum semper. "
            + "Donec tristique, orci sed semper lacinia, quam erat rhoncus massa, non congue tellus est "
            + "quis tellus. Sed mollis orci venenatis quam scelerisque accumsan. Curabitur a massa sit "
            + "amet mi accumsan mollis sed et magna. Vivamus sed aliquam risus. Nulla eget dolor in elit "
            + "facilisis mattis. Ut aliquet luctus lacus. Phasellus nec commodo erat. Praesent tempus id "
            + "lectus ac scelerisque. Maecenas pretium cursus lectus id volutpat.";

    static String[] videoUrl = {
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search.mp4",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue.mp4",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole.mp4",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose.mp4"
    };
    static String[] bgImageUrl = {
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review/bg.jpg",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search/bg.jpg",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue/bg.jpg",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole/bg.jpg",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose/bg.jpg",
    };
    static String[] cardImageUrl = {
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review/card.jpg",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search/card.jpg",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue/card.jpg",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole/card.jpg",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose/card.jpg"
    };
    public static List<Movie> list1;
    public static List<Movie> list2;

    public static void initMovies() {
        list1 = new ArrayList<Movie>();
        list2 = new ArrayList<Movie>();

        list1.add(buildMovieInfo("category", title[0],
                description, "Studio Zero", videoUrl[0], cardImageUrl[0], bgImageUrl[0]));
        list1.add(buildMovieInfo("category", title[1],
                description, "Studio One", videoUrl[1], cardImageUrl[1], bgImageUrl[1]));
        list2.add(buildMovieInfo("category", title[2],
                description, "Studio Two", videoUrl[2], cardImageUrl[2], bgImageUrl[2]));
        list2.add(buildMovieInfo("category", title[3],
                description, "Studio Three", videoUrl[3], cardImageUrl[3], bgImageUrl[3]));
        list2.add(buildMovieInfo("category", title[4],
                description, "Studio Four", videoUrl[4], cardImageUrl[4], bgImageUrl[4]));
    }

    public static List<Movie>[] getMovies() {
        return new List[]{list1, list2};
    }

    private static Movie buildMovieInfo(String category, String title,
                                        String description, String studio, String videoUrl, String cardImageUrl,
                                        String bgImageUrl) {
        Movie movie = new Movie();
        movie.setId(Movie.getCount());
        Movie.incCount();
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setStudio(studio);
        movie.setCategory(category);
        movie.setCardImageUrl(cardImageUrl);
        movie.setBackgroundImageUrl(bgImageUrl);
        movie.setVideoUrl(videoUrl);
        return movie;
    }
}
