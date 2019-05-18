package com.movtrack.List;

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
