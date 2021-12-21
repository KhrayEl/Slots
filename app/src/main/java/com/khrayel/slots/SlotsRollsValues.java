package com.khrayel.slots;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum SlotsRollsValues
    {
        GRINNING_FACE_WITH_SMILING_EYES(1, new String(Character.toChars(0x1F601)),R.drawable._1f601),
        FACE_WITH_TEARS_OF_JOY(2, new String(Character.toChars(0x1F602)),R.drawable._1f602),
        SMILING_FACE_WITH_OPEN_MOUTH(3, new String(Character.toChars(0x1F603)),R.drawable._1f603),
        SMILING_FACE_WITH_OPEN_MOUTH_AND_SMILING_EYES(4, new String(Character.toChars(0x1F604)),R.drawable._1f604),
        SMILING_FACE_WITH_OPEN_MOUTH_AND_COLD_SWEAT(5, new String(Character.toChars(0x1F605)),R.drawable._1f605),
        SMILING_FACE_WITH_OPEN_MOUTH_AND_TIGHTLY_CLOSED_EYES(6, new String(Character.toChars(0x1F606)),R.drawable._1f606),
        WINKING_FACE(7, new String(Character.toChars(0x1F609)),R.drawable._1f609),
        SMILING_FACE_WITH_SMILING_EYES(8, new String(Character.toChars(0x1F60A)),R.drawable._1f60a),
        FACE_SAVOURING_DELICIOUS_FOOD(9, new String(Character.toChars(0x1F60B)),R.drawable._1f60b),
        RELIEVED_FACE(10, new String(Character.toChars(0x1F60C)),R.drawable._1f60c),
        SMILING_FACE_WITH_HEART_SHAPED_EYES(11, new String(Character.toChars(0x1F60D)),R.drawable._1f60d),
        SMIRKING_FACE(12, new String(Character.toChars(0x1F60F)),R.drawable._1f60f),
        UNAMUSED_FACE(13, new String(Character.toChars(0x1F612)),R.drawable._1f612),
        FACE_WITH_COLD_SWEAT(14, new String(Character.toChars(0x1F613)),R.drawable._1f613),
        PENSIVE_FACE(15, new String(Character.toChars(0x1F614)),R.drawable._1f614),
        CONFOUNDED_FACE(16, new String(Character.toChars(0x1F616)),R.drawable._1f616),
        FACE_THROWING_A_KISS(17, new String(Character.toChars(0x1F618)),R.drawable._1f618),
        KISSING_FACE_WITH_CLOSED_EYES(18, new String(Character.toChars(0x1F61A)),R.drawable._1f61a),
        FACE_WITH_STUCK_OUT_TONGUE_AND_WINKING_EYE(19, new String(Character.toChars(0x1F61C)),R.drawable._1f61c),
        FACE_WITH_STUCK_OUT_TONGUE_AND_TIGHTLY_CLOSED_EYES(20, new String(Character.toChars(0x1F61D)),R.drawable._1f61d),
        DISAPPOINTED_FACE(21, new String(Character.toChars(0x1F61E)),R.drawable._1f61e),
        ANGRY_FACE(22, new String(Character.toChars(0x1F620)),R.drawable._1f620),
        POUTING_FACE(23, new String(Character.toChars(0x1F621)),R.drawable._1f621),
        CRYING_FACE(24, new String(Character.toChars(0x1F622)),R.drawable._1f622),
        PERSEVERING_FACE(25, new String(Character.toChars(0x1F623)),R.drawable._1f623),
        FACE_WITH_LOOK_OF_TRIUMPH(26, new String(Character.toChars(0x1F624)),R.drawable._1f624),
        DISAPPOINTED_BUT_RELIEVED_FACE(27, new String(Character.toChars(0x1F625)),R.drawable._1f625),
        FEARFUL_FACE(28, new String(Character.toChars(0x1F628)),R.drawable._1f628),
        WEARY_FACE(29, new String(Character.toChars(0x1F629)),R.drawable._1f629),
        SLEEPY_FACE(30, new String(Character.toChars(0x1F62A)),R.drawable._1f62a),
        TIRED_FACE(31, new String(Character.toChars(0x1F62B)),R.drawable._1f62b),
        LOUDLY_CRYING_FACE(32, new String(Character.toChars(0x1F62D)),R.drawable._1f62d),
        FACE_WITH_OPEN_MOUTH_AND_COLD_SWEAT(33, new String(Character.toChars(0x1F630)),R.drawable._1f630),
        FACE_SCREAMING_IN_FEAR(34, new String(Character.toChars(0x1F631)),R.drawable._1f631),
        ASTONISHED_FACE(35, new String(Character.toChars(0x1F632)),R.drawable._1f632),
        FLUSHED_FACE(36, new String(Character.toChars(0x1F633)),R.drawable._1f633),
        DIZZY_FACE(37, new String(Character.toChars(0x1F635)),R.drawable._1f635),
        FACE_WITH_MEDICAL_MASK(38, new String(Character.toChars(0x1F637)),R.drawable._1f637),
        GRINNING_CAT_FACE_WITH_SMILING_EYES(39, new String(Character.toChars(0x1F638)),R.drawable._1f638),
        CAT_FACE_WITH_TEARS_OF_JOY(40, new String(Character.toChars(0x1F639)),R.drawable._1f639),
        SMILING_CAT_FACE_WITH_OPEN_MOUTH(41, new String(Character.toChars(0x1F63A)),R.drawable._1f63a),
        SMILING_CAT_FACE_WITH_HEART_SHAPED_EYES(42, new String(Character.toChars(0x1F63B)),R.drawable._1f63b),
        CAT_FACE_WITH_WRY_SMILE(43, new String(Character.toChars(0x1F63C)),R.drawable._1f63c),
        KISSING_CAT_FACE_WITH_CLOSED_EYES(44, new String(Character.toChars(0x1F63D)),R.drawable._1f63d),
        POUTING_CAT_FACE(45, new String(Character.toChars(0x1F63E)),R.drawable._1f63e),
        CRYING_CAT_FACE(46, new String(Character.toChars(0x1F63F)),R.drawable._1f63f),
        WEARY_CAT_FACE(47, new String(Character.toChars(0x1F640)),R.drawable._1f640),
        FACE_WITH_NO_GOOD_GESTURE(48, new String(Character.toChars(0x1F645)),R.drawable._1f645),
        FACE_WITH_OK_GESTURE(49, new String(Character.toChars(0x1F646)),R.drawable._1f646),
        PERSON_BOWING_DEEPLY(50, new String(Character.toChars(0x1F647)),R.drawable._1f647),
        SEE_NO_EVIL_MONKEY(51, new String(Character.toChars(0x1F648)),R.drawable._1f648),
        HEAR_NO_EVIL_MONKEY(52, new String(Character.toChars(0x1F649)),R.drawable._1f649),
        SPEAK_NO_EVIL_MONKEY(53, new String(Character.toChars(0x1F64A)),R.drawable._1f64a),
        HAPPY_PERSON_RAISING_ONE_HAND(54, new String(Character.toChars(0x1F64B)),R.drawable._1f64b),
        PERSON_RAISING_BOTH_HANDS_IN_CELEBRATION(55, new String(Character.toChars(0x1F64C)),R.drawable._1f64c),
        PERSON_FROWNING(56, new String(Character.toChars(0x1F64D)),R.drawable._1f64d),
        PERSON_WITH_POUTING_FACE(57, new String(Character.toChars(0x1F64E)),R.drawable._1f64e),
        PERSON_WITH_FOLDED_HANDS(58, new String(Character.toChars(0x1F64F)),R.drawable._1f64f);

        public final int numeric;
        public final String string_as_html_entity;// as Hex
        public final int drawable_id;

        SlotsRollsValues (int numeric, String string_as_html_entity, int drawable_id)
            {
                this.numeric = numeric;
                this.string_as_html_entity = string_as_html_entity;
                this.drawable_id=drawable_id;
            }


        private static final List<SlotsRollsValues> VALUES =
                Collections.unmodifiableList(Arrays.asList(values()));

        private static final int SIZE = VALUES.size();

        private static final Random RANDOM = new Random();


        public static SlotsRollsValues getRandomRoll ()
            {
                return VALUES.get(RANDOM.nextInt(SIZE));
            }
        public static SlotsRollsValues getRandomRoll (int upto)
            {
                return VALUES.get(RANDOM.nextInt(upto));
            }

        public static SlotsRollsValues getDefaultRoll ()
            {
                return VALUES.get(0); // TODO make a default roll to get
            }
    }
