package com.khrayel.slots;

public interface SlotsWinConditions
    {
        enum Multiplier
            {
                NO_MATCH(0),
                TWO_MATCH(10);

                public final int multiplier_value;

                Multiplier (int multiplier_value)
                    {
                        this.multiplier_value = multiplier_value;
                    }
            }



        default int GetResultMultiplier (int roll1, int roll2, int roll3)
            {

                int multiplier_win = Multiplier.TWO_MATCH.multiplier_value;
                int multiplier_loss = 0;


                if (roll1 == roll2 || roll2 == roll3 || roll1 == roll3)
                    {
                        return multiplier_win;
                    }
                return Multiplier.NO_MATCH.multiplier_value;
            }

    }
