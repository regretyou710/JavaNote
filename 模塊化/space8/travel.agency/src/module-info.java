module travel.agency {
    requires travel.api;
    provides travel.api.Tour with travel.agency.TourImpl;
}