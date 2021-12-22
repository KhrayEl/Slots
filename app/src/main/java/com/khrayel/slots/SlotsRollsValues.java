package com.khrayel.slots;

import android.graphics.drawable.Drawable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum SlotsRollsValues
    {

        GRINNING_FACE_WITH_SMILING_EYES(0, new String(Character.toChars(0x1F601)), R.drawable._1f601, R.drawable.gem_1),
        FACE_WITH_TEARS_OF_JOY(1, new String(Character.toChars(0x1F602)), R.drawable._1f602, R.drawable.gem_2),
        SMILING_FACE_WITH_OPEN_MOUTH(2, new String(Character.toChars(0x1F603)), R.drawable._1f603, R.drawable.gem_3),
        SMILING_FACE_WITH_OPEN_MOUTH_AND_SMILING_EYES(3, new String(Character.toChars(0x1F604)), R.drawable._1f604, R.drawable.gem_4),
        SMILING_FACE_WITH_OPEN_MOUTH_AND_COLD_SWEAT(1, new String(Character.toChars(0x1F605)), R.drawable._1f605, R.drawable.gem_5),
        SMILING_FACE_WITH_OPEN_MOUTH_AND_TIGHTLY_CLOSED_EYES(2, new String(Character.toChars(0x1F606)), R.drawable._1f606, R.drawable.gem_6),
        WINKING_FACE(6, new String(Character.toChars(0x1F609)), R.drawable._1f609, R.drawable.gem_7),
        SMILING_FACE_WITH_SMILING_EYES(7, new String(Character.toChars(0x1F60A)), R.drawable._1f60a, R.drawable.gem_8),
        FACE_SAVOURING_DELICIOUS_FOOD(8, new String(Character.toChars(0x1F60B)), R.drawable._1f60b, R.drawable.gem_9),
        RELIEVED_FACE(9, new String(Character.toChars(0x1F60C)), R.drawable._1f60c, R.drawable.gem_10),
        SMILING_FACE_WITH_HEART_SHAPED_EYES(10, new String(Character.toChars(0x1F60D)), R.drawable._1f60d, R.drawable.gem_11),
        SMIRKING_FACE(11, new String(Character.toChars(0x1F60F)), R.drawable._1f60f, R.drawable.gem_12),
        UNAMUSED_FACE(12, new String(Character.toChars(0x1F612)), R.drawable._1f612, R.drawable.gem_13),
        FACE_WITH_COLD_SWEAT(13, new String(Character.toChars(0x1F613)), R.drawable._1f613, R.drawable.gem_14),
        PENSIVE_FACE(14, new String(Character.toChars(0x1F614)), R.drawable._1f614, R.drawable.gem_15),
        CONFOUNDED_FACE(15, new String(Character.toChars(0x1F616)), R.drawable._1f616, R.drawable.gem_16),
        FACE_THROWING_A_KISS(16, new String(Character.toChars(0x1F618)), R.drawable._1f618, R.drawable.gem_17),
        KISSING_FACE_WITH_CLOSED_EYES(17, new String(Character.toChars(0x1F61A)), R.drawable._1f61a, R.drawable.gem_18),
        FACE_WITH_STUCK_OUT_TONGUE_AND_WINKING_EYE(18, new String(Character.toChars(0x1F61C)), R.drawable._1f61c, R.drawable.gem_19),
        FACE_WITH_STUCK_OUT_TONGUE_AND_TIGHTLY_CLOSED_EYES(19, new String(Character.toChars(0x1F61D)), R.drawable._1f61d, R.drawable.gem_20),
        DISAPPOINTED_FACE(20, new String(Character.toChars(0x1F61E)), R.drawable._1f61e, R.drawable.gem_21),
        ANGRY_FACE(21, new String(Character.toChars(0x1F620)), R.drawable._1f620, R.drawable.gem_22),
        POUTING_FACE(22, new String(Character.toChars(0x1F621)), R.drawable._1f621, R.drawable.gem_23),
        CRYING_FACE(23, new String(Character.toChars(0x1F622)), R.drawable._1f622, R.drawable.gem_24),
        PERSEVERING_FACE(24, new String(Character.toChars(0x1F623)), R.drawable._1f623, R.drawable.gem_25),
        FACE_WITH_LOOK_OF_TRIUMPH(25, new String(Character.toChars(0x1F624)), R.drawable._1f624, R.drawable.gem_26),
        DISAPPOINTED_BUT_RELIEVED_FACE(26, new String(Character.toChars(0x1F625)), R.drawable._1f625, R.drawable.gem_27),
        FEARFUL_FACE(27, new String(Character.toChars(0x1F628)), R.drawable._1f628, R.drawable.gem_28),
        WEARY_FACE(28, new String(Character.toChars(0x1F629)), R.drawable._1f629, R.drawable.gem_29),
        SLEEPY_FACE(29, new String(Character.toChars(0x1F62A)), R.drawable._1f62a, R.drawable.gem_30),
        TIRED_FACE(30, new String(Character.toChars(0x1F62B)), R.drawable._1f62b, R.drawable.gem_31),
        LOUDLY_CRYING_FACE(31, new String(Character.toChars(0x1F62D)), R.drawable._1f62d, R.drawable.gem_32),
        FACE_WITH_OPEN_MOUTH_AND_COLD_SWEAT(32, new String(Character.toChars(0x1F630)), R.drawable._1f630, R.drawable.gem_33),
        FACE_SCREAMING_IN_FEAR(33, new String(Character.toChars(0x1F631)), R.drawable._1f631, R.drawable.gem_34),
        ASTONISHED_FACE(34, new String(Character.toChars(0x1F632)), R.drawable._1f632, R.drawable.gem_35),
        FLUSHED_FACE(35, new String(Character.toChars(0x1F633)), R.drawable._1f633, R.drawable.gem_36),
        DIZZY_FACE(36, new String(Character.toChars(0x1F635)), R.drawable._1f635, R.drawable.gem_37),
        FACE_WITH_MEDICAL_MASK(37, new String(Character.toChars(0x1F637)), R.drawable._1f637, R.drawable.gem_38),
        GRINNING_CAT_FACE_WITH_SMILING_EYES(38, new String(Character.toChars(0x1F638)), R.drawable._1f638, R.drawable.gem_39),
        CAT_FACE_WITH_TEARS_OF_JOY(39, new String(Character.toChars(0x1F639)), R.drawable._1f639, R.drawable.gem_40),
        SMILING_CAT_FACE_WITH_OPEN_MOUTH(40, new String(Character.toChars(0x1F63A)), R.drawable._1f63a, R.drawable.gem_41),
        SMILING_CAT_FACE_WITH_HEART_SHAPED_EYES(41, new String(Character.toChars(0x1F63B)), R.drawable._1f63b, R.drawable.gem_42),
//        CAT_FACE_WITH_WRY_SMILE(43, new String(Character.toChars(0x1F63C)),R.drawable._1f63c),
//        KISSING_CAT_FACE_WITH_CLOSED_EYES(44, new String(Character.toChars(0x1F63D)),R.drawable._1f63d),
//        POUTING_CAT_FACE(45, new String(Character.toChars(0x1F63E)),R.drawable._1f63e),
//        CRYING_CAT_FACE(46, new String(Character.toChars(0x1F63F)),R.drawable._1f63f),
//        WEARY_CAT_FACE(47, new String(Character.toChars(0x1F640)),R.drawable._1f640),
//        FACE_WITH_NO_GOOD_GESTURE(48, new String(Character.toChars(0x1F645)),R.drawable._1f645),
//        FACE_WITH_OK_GESTURE(49, new String(Character.toChars(0x1F646)),R.drawable._1f646),
//        PERSON_BOWING_DEEPLY(50, new String(Character.toChars(0x1F647)),R.drawable._1f647),
//        SEE_NO_EVIL_MONKEY(51, new String(Character.toChars(0x1F648)),R.drawable._1f648),
//        HEAR_NO_EVIL_MONKEY(52, new String(Character.toChars(0x1F649)),R.drawable._1f649),
//        SPEAK_NO_EVIL_MONKEY(53, new String(Character.toChars(0x1F64A)),R.drawable._1f64a),
//        HAPPY_PERSON_RAISING_ONE_HAND(54, new String(Character.toChars(0x1F64B)),R.drawable._1f64b),
//        PERSON_RAISING_BOTH_HANDS_IN_CELEBRATION(55, new String(Character.toChars(0x1F64C)),R.drawable._1f64c),
//        PERSON_FROWNING(56, new String(Character.toChars(0x1F64D)),R.drawable._1f64d),
//        PERSON_WITH_POUTING_FACE(57, new String(Character.toChars(0x1F64E)),R.drawable._1f64e),
//        PERSON_WITH_FOLDED_HANDS(58, new String(Character.toChars(0x1F64F)),R.drawable._1f64f)
        ;

        public enum DrawableType
            {
                DRAWABLE_TYPE_EMOJI,
                DRAWABLE_TYPE_GEM
            }

        ;
        public final int integer;
        public final String string_as_html_entity;// as Hex
        public final int drawable_emoji;
        public final int drawable_gem;

        SlotsRollsValues (int integer, String string_as_html_entity, int drawable_emoji, int drawable_gem)
            {
                this.integer = integer;
                this.string_as_html_entity = string_as_html_entity;
                this.drawable_emoji = drawable_emoji;
                this.drawable_gem = drawable_gem;
            }


        private static final List<SlotsRollsValues> VALUES =
                Collections.unmodifiableList(Arrays.asList(values()));

        private static final int SIZE = VALUES.size();

        private static final Random RANDOM = new Random();


        public static int getRandomRollDrawable (DrawableType drawableType)
            {
                switch (drawableType)
                    {
                        case DRAWABLE_TYPE_EMOJI:

                            return VALUES.get(RANDOM.nextInt(SIZE)).drawable_emoji;
                        case DRAWABLE_TYPE_GEM:
                            return VALUES.get(RANDOM.nextInt(SIZE)).drawable_gem;
                    }
                return 0;
            }

        public static int getDrawable(int rolled_value, DrawableType drawableType)
            {
                switch (drawableType){
                    case DRAWABLE_TYPE_GEM:{return VALUES.get(rolled_value).drawable_gem;}
                    case DRAWABLE_TYPE_EMOJI:{return VALUES.get(rolled_value).drawable_emoji;}
                }
                return 0;
            }



        public static SlotsRollsValues getRandomRoll (int upto)
            {
                return VALUES.get(RANDOM.nextInt(upto));
            }
        public static SlotsRollsValues getRandomRoll ()
            {
                return VALUES.get(RANDOM.nextInt(VALUES.size()));
            }

        public static SlotsRollsValues getDefaultRoll ()
            {
                return VALUES.get(0); // TODO make a default roll to get
            }
    }
