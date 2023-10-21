package travel.mix;

import travel.api.Tour;

import java.util.OptionalInt;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;

public class TourPriceCheck {
    public static void main(String[] args) {
        /**
         * 使用另一個方式找出travel.api.Tour的實作類
         */
        OptionalInt max = ServiceLoader.load(Tour.class).stream().map(Provider::get)
                .mapToInt(Tour::price).max();
        max.ifPresent(System.out::println);

        OptionalInt min = ServiceLoader.load(Tour.class).stream().map(Provider::get)
                .mapToInt(Tour::price).min();
        min.ifPresent(System.out::println);
    }
}
