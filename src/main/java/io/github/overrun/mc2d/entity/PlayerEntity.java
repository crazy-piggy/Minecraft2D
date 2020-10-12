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

package io.github.overrun.mc2d.entity;

import io.github.overrun.mc2d.item.ItemStack;
import io.github.overrun.mc2d.util.IntUtil;

import java.util.HashMap;

/**
 * @author squid233
 * @since 2020/10/01
 */
public class PlayerEntity {
    private static final transient HashMap<String, PlayerEntity> CACHE = new HashMap<>();
    private final String name;
    private final ItemStack[] hotbarItems = new ItemStack[9];
    private ItemStack handItem;

    private PlayerEntity(String name) {
        this.name = name;
    }

    public static PlayerEntity getByName(String name) {
        if (!CACHE.containsKey(name)) {
            CACHE.put(name, new PlayerEntity(name));
        }
        return CACHE.get(name);
    }

    public String getName() {
        return name;
    }

    public PlayerEntity setHandItem(ItemStack handItem) {
        this.handItem = handItem;
        return this;
    }

    public PlayerEntity setHotbarItem(int index, ItemStack hotbarItem) {
        // Don't ArrayIndexOutOfBoundsException
        hotbarItems[IntUtil.clamp(index, 0, 8)] = hotbarItem;
        return this;
    }

    public ItemStack[] getHotbarItems() {
        return hotbarItems;
    }

    public ItemStack getHandItem() {
        return handItem;
    }
}
