package com.khrayel.main_app.profile;

import com.khrayel.main_app.net.NetCommands;
import java.util.Calendar;

public class ProfileModel implements NetCommands
    {
        public ProfileModel()
            {
                name = "Player";
            }

        private String name;

        public String getName()
            {
                return name;
            }

        public void setName(String name)
            {
                this.name = name;
            }


        private long slots_timestamp = Calendar.getInstance().getTimeInMillis();

        public long getSlots_timestamp()
            {
                return slots_timestamp;
            }

        public void setSlots_timestamp(long slots_timestamp)
            {
                this.slots_timestamp = slots_timestamp;
            }
    }
