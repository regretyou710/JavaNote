module travel.mix {
    requires travel.api;
    uses travel.api.Tour;//因為在travel.mix包中的TourPirceCheck類main方法裡被ServiceLoader引用
    provides travel.api.Tour with travel.mix.TourImpl2;
}