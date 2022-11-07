package io.github.geysersurvival.plugin.utils;

import org.bukkit.Material;

public enum ToStickItems {

    BAMBOO(2, 1, Material.BAMBOO),
    //2 planks = 4 sticks, so 1 plank should give 2 sticks
    PLANKS(1, 2,
            Material.OAK_PLANKS,
            Material.SPRUCE_PLANKS,
            Material.BIRCH_PLANKS,
            Material.JUNGLE_PLANKS,
            Material.ACACIA_PLANKS,
            Material.DARK_OAK_PLANKS,
            Material.MANGROVE_PLANKS,
            Material.CRIMSON_PLANKS,
            Material.WARPED_PLANKS);

    final Material[] materials;
    final int input;
    final int output;

    /**
     * @param input The number of input blocks to make a stick
     * @param output The number of sticks given as a result
     * @param materials List of materials that fit the criteria.
     */
    ToStickItems(int input, int output, Material... materials) {
        this.materials = materials;
        this.input = input;
        this.output = output;
    }

    public Material[] getMaterials() {
        return materials;
    }

    public int getInput() {
        return input;
    }

    public int getOutput() {
        return output;
    }
}
