    package com.example.perforamancetest.overdraw_custom_view;

    public class CardModel {

        private String name;


        private int color;


        private int imageId;


        public CardModel(String name, int color) {
            this.name = name;
            this.color = color;
        }

        public CardModel(String name, int color, int imageId) {
            this.name = name;
            this.color = color;
            this.imageId = imageId;
        }

        public String getName() {return name;}
        public int getColor() {return color;}
        public int getAvatarId() {return imageId;}
    }