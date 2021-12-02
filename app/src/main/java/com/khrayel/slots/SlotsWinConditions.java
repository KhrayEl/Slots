package com.khrayel.slots;

public interface SlotsWinConditions
    {
        enum Multiplier
            {
                NO_MATCH(0),
                TWO_MATCH(10),
                THREE_MATCH(100);

                public final int multiplier_value;

                Multiplier (int multiplier_value)
                    {
                        this.multiplier_value = multiplier_value;
                    }
            }


        default int GetResultMultiplier (int roll1, int roll2, int roll3)
            {



                if (
                        (roll1 == roll2 && roll2 != roll3 && roll1 != roll3) ||
                        (roll1 != roll2 && roll2 == roll3 && roll1 != roll3) ||
                        (roll1 != roll2 && roll2 != roll3 && roll1 == roll3)

                )
                    {
                        return Multiplier.TWO_MATCH.multiplier_value;
                    }
                else if (roll1==roll2&&roll2==roll3)
                    {return Multiplier.THREE_MATCH.multiplier_value;}

                return Multiplier.NO_MATCH.multiplier_value;
            }

    }
