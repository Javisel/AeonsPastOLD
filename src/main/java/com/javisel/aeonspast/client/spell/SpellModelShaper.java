package com.javisel.aeonspast.client.spell;

import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.common.spell.SpellState;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class SpellModelShaper {
    public final Int2ObjectMap<ModelResourceLocation> shapes = new Int2ObjectOpenHashMap<>(256);
    private final Int2ObjectMap<BakedModel> shapesCache = new Int2ObjectOpenHashMap<>(256);
    private final ModelManager modelManager;

    public SpellModelShaper(ModelManager p_109392_) {
        this.modelManager = p_109392_;
    }

    public BakedModel getSpellModel(SpellStack spellStack) {
        BakedModel bakedmodel = this.getSpellModel(spellStack.getSpell());
        // FORGE: Make sure to call the Spell overrides
        return bakedmodel == null ? this.modelManager.getMissingModel() : bakedmodel;
    }

    @Nullable
    public BakedModel getSpellModel(Spell spell) {
        return this.shapesCache.get(getIndex(spell));
    }

    private static int getIndex(Spell spell) {
        return Spell.getId(spell);
    }

    public void register(Spell p_109397_, ModelResourceLocation p_109398_) {
        this.shapes.put(getIndex(p_109397_), p_109398_);
    }

    public ModelManager getModelManager() {
        return this.modelManager;
    }

    public void rebuildCache() {
        this.shapesCache.clear();

        for(Map.Entry<Integer, ModelResourceLocation> entry : this.shapes.entrySet()) {
            this.shapesCache.put(entry.getKey(), this.modelManager.getModel(entry.getValue()));
        }

    }
}
