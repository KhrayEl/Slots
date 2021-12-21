package com.khrayel.slots.model;

 enum SlotsDefaultValues
    {

        DEFAULT_ROLL_MAX( 25), // how many roll values to choose from
        ROLL_INCREMENT(1), // how many roll values are added per score/divisor
        SCORE_DIVISOR_FOR_ROLL_INCREMENT(10), // divisor to divide score, used to get number of increments
        default_score(100),
        default_record (0),
        default_bet ( 10),
        min_bet (1),
        default_change_in_score(0);

        private final int value;

        SlotsDefaultValues (int value)
            {
                this.value = value                ;
            }

        public int getValue ()
            {
                return value;
            }
    }
