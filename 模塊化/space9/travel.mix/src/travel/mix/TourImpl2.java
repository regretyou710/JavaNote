package travel.mix;

import travel.api.Gift;
import travel.api.Tour;

public class TourImpl2 implements Tour {
    @Override
    public String name() {
        return "3 days tour for Taipei";
    }

    @Override
    public int price() {
        return 3000;
    }

    @Override
    public Gift getGift() {
        Gift gift = new Gift();
        gift.setDescription("breakfirst");
        return gift;
    }
}
