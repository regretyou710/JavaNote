package travel.agency;

import travel.api.Gift;
import travel.api.Tour;

public class TourImpl implements Tour {
    @Override
    public String name() {
        return "One week tour for Taipei";
    }

    @Override
    public int price() {
        return 7000;
    }

    @Override
    public Gift getGift() {
        Gift gift = new Gift();
        gift.setDescription("T-Shirt");
        return gift;
    }
}
