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

package io.github.overrun.mc2d.block;

import io.github.overrun.mc2d.client.Mc2dClient;
import io.github.overrun.mc2d.image.Images;
import io.github.overrun.mc2d.item.Item;
import io.github.overrun.mc2d.screen.Screens;
import io.github.overrun.mc2d.util.Highlight;
import io.github.overrun.mc2d.util.Identifier;
import io.github.overrun.mc2d.util.ResourceLocation;

import java.awt.Graphics;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * @author squid233
 * @since 2020/09/14
 */
public class Block extends AbstractBlock {
    private static final long serialVersionUID = 6678903782835480065L;
    private Identifier regName;
    private final Properties model = new Properties();
    private boolean loadedModel;
    private Item cache;

    public Block(Settings settings) { }

    @Override
    public void draw(Graphics g, int x) {
        if (
                getPreviewX() > -8 && getPreviewX() < Mc2dClient.getInstance().getWidth()
                && getPreviewY() > 14 && getPreviewY() < Mc2dClient.getInstance().getHeight()
        ) {
            g.drawImage(Images.getBlockTexture(this), getPreviewX(x), getPreviewY(), 16, 16, null);
            if (!Screens.isOpeningAnyScreen) {
                Highlight.block(g, getPreviewX(x), getPreviewY());
            }
        }
    }

    @Override
    public Block setRegistryName(Identifier registryName) {
        if (regName != null) {
            throw new IllegalStateException("Registry name is already exist. Old: " + regName + ", New: " + registryName);
        }
        regName = registryName;
        return this;
    }

    @Override
    public Identifier getRegistryName() {
        try {
            return regName;
        } catch (Exception e) {
            return new Identifier("air");
        }
    }

    @Override
    public void draw(Graphics g) {
        draw(g, x);
    }

    @Override
    public AbstractBlock setPos(String pos) {
        BlockPos p = BlockPos.of(pos);
        this.x = p.getX();
        this.y = p.getY();
        return this;
    }

    @Override
    public Properties getModel() {
        if (!loadedModel) {
            try (FileReader fr =
                         new FileReader(new ResourceLocation(getRegistryName().getNamespace(), "models/block/" + getRegistryName().getPath() + ".mc2dm").toString())) {
                model.load(fr);
            } catch (IOException e) {
                e.printStackTrace();
            }
            loadedModel = true;
        }
        return model;
    }

    @Override
    public Item asItem() {
        if (cache == null) {
            cache = Item.getItemByBlock(this);
        }
        return cache;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Block)) {
            return false;
        }
        return Objects.equals(regName, ((Block) o).regName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regName);
    }
}
