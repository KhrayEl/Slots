package com.khrayel.slots;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum SlotsRollsValues
    {
        GRINNING_FACE_WITH_SMILING_EYES(1, new String(Character.toChars(0x1F601))),
        FACE_WITH_TEARS_OF_JOY(2, new String(Character.toChars(0x1F602))),
        SMILING_FACE_WITH_OPEN_MOUTH(3, new String(Character.toChars(0x1F603))),
        SMILING_FACE_WITH_OPEN_MOUTH_AND_SMILING_EYES(4, new String(Character.toChars(0x1F604))),
        SMILING_FACE_WITH_OPEN_MOUTH_AND_COLD_SWEAT(5, new String(Character.toChars(0x1F605))),
        SMILING_FACE_WITH_OPEN_MOUTH_AND_TIGHTLY_CLOSED_EYES(6, new String(Character.toChars(0x1F606))),
        WINKING_FACE(7, new String(Character.toChars(0x1F609))),
        SMILING_FACE_WITH_SMILING_EYES(8, new String(Character.toChars(0x1F60A))),
        FACE_SAVOURING_DELICIOUS_FOOD(9, new String(Character.toChars(0x1F60B))),
        RELIEVED_FACE(10, new String(Character.toChars(0x1F60C))),
        SMILING_FACE_WITH_HEART_SHAPED_EYES(11, new String(Character.toChars(0x1F60D))),
        SMIRKING_FACE(12, new String(Character.toChars(0x1F60F))),
        UNAMUSED_FACE(13, new String(Character.toChars(0x1F612))),
        FACE_WITH_COLD_SWEAT(14, new String(Character.toChars(0x1F613))),
        PENSIVE_FACE(15, new String(Character.toChars(0x1F614))),
        CONFOUNDED_FACE(16, new String(Character.toChars(0x1F616))),
        FACE_THROWING_A_KISS(17, new String(Character.toChars(0x1F618))),
        KISSING_FACE_WITH_CLOSED_EYES(18, new String(Character.toChars(0x1F61A))),
        FACE_WITH_STUCK_OUT_TONGUE_AND_WINKING_EYE(19, new String(Character.toChars(0x1F61C))),
        FACE_WITH_STUCK_OUT_TONGUE_AND_TIGHTLY_CLOSED_EYES(20, new String(Character.toChars(0x1F61D))),
        DISAPPOINTED_FACE(21, new String(Character.toChars(0x1F61E))),
        ANGRY_FACE(22, new String(Character.toChars(0x1F620))),
        POUTING_FACE(23, new String(Character.toChars(0x1F621))),
        CRYING_FACE(24, new String(Character.toChars(0x1F622))),
        PERSEVERING_FACE(25, new String(Character.toChars(0x1F623))),
        FACE_WITH_LOOK_OF_TRIUMPH(26, new String(Character.toChars(0x1F624))),
        DISAPPOINTED_BUT_RELIEVED_FACE(27, new String(Character.toChars(0x1F625))),
        FEARFUL_FACE(28, new String(Character.toChars(0x1F628))),
        WEARY_FACE(29, new String(Character.toChars(0x1F629))),
        SLEEPY_FACE(30, new String(Character.toChars(0x1F62A))),
        TIRED_FACE(31, new String(Character.toChars(0x1F62B))),
        LOUDLY_CRYING_FACE(32, new String(Character.toChars(0x1F62D))),
        FACE_WITH_OPEN_MOUTH_AND_COLD_SWEAT(33, new String(Character.toChars(0x1F630))),
        FACE_SCREAMING_IN_FEAR(34, new String(Character.toChars(0x1F631))),
        ASTONISHED_FACE(35, new String(Character.toChars(0x1F632))),
        FLUSHED_FACE(36, new String(Character.toChars(0x1F633))),
        DIZZY_FACE(37, new String(Character.toChars(0x1F635))),
        FACE_WITH_MEDICAL_MASK(38, new String(Character.toChars(0x1F637))),
        GRINNING_CAT_FACE_WITH_SMILING_EYES(39, new String(Character.toChars(0x1F638))),
        CAT_FACE_WITH_TEARS_OF_JOY(40, new String(Character.toChars(0x1F639))),
        SMILING_CAT_FACE_WITH_OPEN_MOUTH(41, new String(Character.toChars(0x1F63A))),
        SMILING_CAT_FACE_WITH_HEART_SHAPED_EYES(42, new String(Character.toChars(0x1F63B))),
        CAT_FACE_WITH_WRY_SMILE(43, new String(Character.toChars(0x1F63C))),
        KISSING_CAT_FACE_WITH_CLOSED_EYES(44, new String(Character.toChars(0x1F63D))),
        POUTING_CAT_FACE(45, new String(Character.toChars(0x1F63E))),
        CRYING_CAT_FACE(46, new String(Character.toChars(0x1F63F))),
        WEARY_CAT_FACE(47, new String(Character.toChars(0x1F640))),
        FACE_WITH_NO_GOOD_GESTURE(48, new String(Character.toChars(0x1F645))),
        FACE_WITH_OK_GESTURE(49, new String(Character.toChars(0x1F646))),
        PERSON_BOWING_DEEPLY(50, new String(Character.toChars(0x1F647))),
        SEE_NO_EVIL_MONKEY(51, new String(Character.toChars(0x1F648))),
        HEAR_NO_EVIL_MONKEY(52, new String(Character.toChars(0x1F649))),
        SPEAK_NO_EVIL_MONKEY(53, new String(Character.toChars(0x1F64A))),
        HAPPY_PERSON_RAISING_ONE_HAND(54, new String(Character.toChars(0x1F64B))),
        PERSON_RAISING_BOTH_HANDS_IN_CELEBRATION(55, new String(Character.toChars(0x1F64C))),
        PERSON_FROWNING(56, new String(Character.toChars(0x1F64D))),
        PERSON_WITH_POUTING_FACE(57, new String(Character.toChars(0x1F64E))),
        PERSON_WITH_FOLDED_HANDS(58, new String(Character.toChars(0x1F64F)));


        public final int numeric;
        public final String string_as_html_entity;// as Hex

        SlotsRollsValues (int numeric, String string_as_html_entity)
            {
                this.numeric = numeric;
                this.string_as_html_entity = string_as_html_entity;
            }


        private static final List<SlotsRollsValues> VALUES =
                Collections.unmodifiableList(Arrays.asList(values()));

        private static final int SIZE = VALUES.size();

        private static final Random RANDOM = new Random();


        public static SlotsRollsValues getRandomRoll ()
            {
                return VALUES.get(RANDOM.nextInt(SIZE));
            }
        public static SlotsRollsValues getDefaultRoll ()
            {
                return VALUES.get(0); // TODO make a default roll to get
            }
    }
