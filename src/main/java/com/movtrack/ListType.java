package com.movtrack;

public enum ListType {
    Watched{
        @Override
        public String toString() {
            return "Watched";
        }
    },

    WatchList{
        @Override
        public String toString() {
            return "WatchList";
        }
    }
}
