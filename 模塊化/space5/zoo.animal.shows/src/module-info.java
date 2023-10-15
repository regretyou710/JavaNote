module zoo.animal.shows {
    exports zoo.animal.shows.content;
    exports zoo.animal.shows.media;
    exports zoo.animal.shows.schedule;
    requires zoo.animal.feeding;
    requires zoo.animal.care;
}