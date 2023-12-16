module zoo.staff {
//    requires zoo.animal.feeding;//因為zoo.animal.care中requires transitive zoo.animal.feeding，所以不用再引入
//    requires zoo.animal.care;//因為zoo.animal.shows中requires transitive zoo.animal.care，所以不用再引入
    requires zoo.animal.shows;
}