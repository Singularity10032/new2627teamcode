/*********************** SINGULARITY FRC 10032 ***********************/
/* Copyright (c) 2026 Singularity (FRC 10032). All rights reserved. */
/* Use of this source code is governed by an MIT-style license      */
/* that can be found in the repository LICENSE file.                */
/********************************************************************/
package com.singularity.lib.streams.booleans.filters;

import com.singularity.lib.streams.numbers.filters.HighPassFilter;

/**
 * A simple boolean filter that returns true when a boolean stream changes depending on the type.
 * This type of button filter will hold true for a certain decay length before returning to false.
 *
 * <p>1. Both - will return true if the BStream changes at all
 *
 * <p>2. Pressed - will return true if the BStream changes from false to true
 *
 * <p>3. Released - will return true if the BStream changes from true to false
 *
 * @author Sam (sam.belliveau@gmail.com)
 */
public interface BButtonRC extends BFilter {

    public static final double kTrue = 1.0;
    public static final double kFalse = 0.0;

    public static final double kPressedLimit = 1.0 / Math.E;
    public static final double kReleasedLimit = -kPressedLimit;

    public static class Both implements BButtonRC {
        private HighPassFilter mFilter;

        /**
         * @param decay time in seconds to remain true after the BStream is pressed or released
         */
        public Both(Number decay) {
            mFilter = new HighPassFilter(decay);
        }

        public boolean get(boolean next) {
            double value = mFilter.get(next ? kTrue : kFalse);
            return value > kPressedLimit || value < kReleasedLimit;
        }
    }

    public static class Pressed implements BButtonRC {
        private HighPassFilter mFilter;

        /**
         * @param decay time in seconds to remain true after the BStream has been pressed
         */
        public Pressed(Number decay) {
            mFilter = new HighPassFilter(decay);
        }

        public boolean get(boolean next) {
            return mFilter.get(next ? kTrue : kFalse) > kPressedLimit;
        }
    }

    public static class Released implements BButtonRC {
        private HighPassFilter mFilter;

        /**
         * @param decay time in seconds to remain true after the BStream has been released
         */
        public Released(Number decay) {
            mFilter = new HighPassFilter(decay);
        }

        public boolean get(boolean next) {
            return mFilter.get(next ? kTrue : kFalse) < kReleasedLimit;
        }
    }
}
