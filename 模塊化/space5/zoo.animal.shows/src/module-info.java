module zoo.animal.shows {
    exports zoo.animal.shows.content;
    exports zoo.animal.shows.media;
    exports zoo.animal.shows.schedule;
//    requires zoo.animal.feeding;//因為zoo.animal.care中requires transitive zoo.animal.feeding，所以不用再引入
//    requires zoo.animal.care;
    requires transitive zoo.animal.care;
}