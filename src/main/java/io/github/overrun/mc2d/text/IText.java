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

import io.github.overrun.mc2d.util.StringHelper;

import java.awt.Graphics;

/**
 * @author squid233
 * @since 2020/09/18
 */
public interface IText {
    /**
     * as un-formatted string
     *
     * @return string
     */
    String asString();

    /**
     * as formatted string
     *
     * @return string
     */
    String asFormattedString();


    /**
     * <b>NOTE:</b> This method is linked to {@link io.github.overrun.mc2d.screen.Screen#drawText(Graphics, int, int, IText) Screen#drawText}.
     *
     * @return The <b>formatted</b> text display length.
     */
    default int getDisplayLength() {
        char[] c = asFormattedString().toCharArray();
        int len = 0;
        for (char value : c) {
            if (StringHelper.isLatin1(String.valueOf(value))) {
                len += 9;
            } else {
                len += 16;
            }
        }
        return len;
    }
}