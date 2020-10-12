/*
 * MIT License
 *
 * Copyright (c) 2020 Over-Run
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.overrun.mc2d.text;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author squid233
 * @since 2020/09/18
 */
public class LiteralText implements IText {
    private static final HashMap<String, LiteralText> INSTANCES = new HashMap<>();
    private final String text;
    private final Object[] args;
    public static final Color DEFAULT_COLOR = new Color(63, 63, 63);

    /**
     * Construct a text.<br>
     * Use {@link LiteralText#of(String, Object...)}.
     *
     * @param text The text.
     * @param args Args for format.
     */
    private LiteralText(String text, Object... args) {
        this.text = text;
        this.args = Objects.requireNonNullElseGet(args, () -> new Object[0]);
    }

    public static LiteralText of(String text, Object... args) {
        String s = text + Arrays.deepToString(args);
        if (!INSTANCES.containsKey(s)) {
            INSTANCES.put(s, new LiteralText(text, args));
        }
        return INSTANCES.get(s);
    }

    @Override
    public String asString() {
        return text;
    }

    @Override
    public String asFormattedString() {
        return String.format(text, args);
    }

    /**
     * <b>NOTE:</b> This method is linked to {@link io.github.overrun.mc2d.screen.Screen#drawText(Graphics, int, int, IText) Screen#drawText}.
     *
     * @return The <b>formatted</b> text display length.
     */
    public int getDisplayLength() {
        int l = 0;
        for (int i = 0, len = asFormattedString().length(); i < len; i++) {
            l += 6;
        }
        return l;
    }
}
