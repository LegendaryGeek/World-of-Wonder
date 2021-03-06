package net.msrandom.worldofwonder.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Direction;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.msrandom.worldofwonder.WorldOfWonder;
import net.msrandom.worldofwonder.item.StemSignItem;
import net.msrandom.worldofwonder.item.WonderItems;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class WonderBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, WorldOfWonder.MOD_ID);
    public static final RegistryObject<Block> DANDELION_FLUFF = add("dandelion_fluff", () -> new DandelionFluffBlock(), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Block> STEM_PLANKS = add("stem_planks", () -> new WonderFlammableBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD)), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Block> STEM_LOG = add("stem_log", () -> new WonderWoodBlock(AbstractBlock.Properties.of(Material.WOOD, (state) -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.WOOD : MaterialColor.COLOR_GREEN).strength(2.0F).sound(SoundType.WOOD)), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Block> STRIPPED_STEM_LOG = add("stripped_stem_log", () -> new WonderWoodBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.0F).sound(SoundType.WOOD)), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Block> STEM_WOOD = add("stem_wood", () -> new WonderWoodBlock(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F).sound(SoundType.WOOD)), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Block> STRIPPED_STEM_WOOD = add("stripped_stem_wood", () -> new WonderWoodBlock(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F).sound(SoundType.WOOD)), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Block> DANDELION_PETALS = add("dandelion_petals", () -> new DandelionBlock(Block.Properties.of(Material.LEAVES).strength(0.2F).sound(SoundType.GRASS)), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Block> STEM_STAIRS = add("stem_stairs", () -> new FlammableStairsBlock(() -> STEM_PLANKS.get().defaultBlockState(), Block.Properties.copy(STEM_PLANKS.get())), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Block> STEM_SIGN = add("stem_sign", StemStandingSignBlock::new, new Item.Properties().stacksTo(16).tab(ItemGroup.TAB_DECORATIONS), StemSignItem::new);
    public static final RegistryObject<Block> STEM_DOOR = add("stem_door", () -> new DoorBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_GREEN).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), new Item.Properties().tab(ItemGroup.TAB_REDSTONE));
    public static final RegistryObject<Block> STEM_WALL_SIGN = add("stem_wall_sign", StemWallSignBlock::new);
    public static final RegistryObject<Block> STEM_PRESSURE_PLATE = add("stem_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.of(Material.WOOD, MaterialColor.COLOR_GREEN).noCollission().strength(0.5F).sound(SoundType.WOOD)), new Item.Properties().tab(ItemGroup.TAB_REDSTONE));
    public static final RegistryObject<Block> STEM_FENCE = add("stem_fence", () -> new FlammableFenceBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD)), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Block> STEM_TRAPDOOR = add("stem_trapdoor", () -> new TrapDoorBlock(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.0F).noOcclusion().sound(SoundType.WOOD).randomTicks()), new Item.Properties().tab(ItemGroup.TAB_REDSTONE));
    public static final RegistryObject<Block> STEM_FENCE_GATE = add("stem_fence_gate", () -> new FlammableFenceGateBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD)), new Item.Properties().tab(ItemGroup.TAB_REDSTONE));
    public static final RegistryObject<Block> STEM_BUTTON = add("stem_button", () -> new WoodButtonBlock(Block.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), new Item.Properties().tab(ItemGroup.TAB_REDSTONE));
    public static final RegistryObject<Block> STEM_SLAB = add("stem_slab", () -> new FlammableSlabBlock(Block.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS));
    public static final RegistryObject<Block> DANDE_LION_SPROUT = add("dande_lion_sprout", DandeLionSproutBlock::new, new Item.Properties().tab(ItemGroup.TAB_DECORATIONS));
    public static final RegistryObject<Block> POTTED_DANDE_LION_SPROUT = add("potted_dande_lion_sprout", PottedSproutBlock::new);

    //Register a block without an item, add("name", new Block(...))
    public static <T extends Block> RegistryObject<T> add(String name, Supplier<T> block) {
        return add(name, block, null);
    }

    //Register a block with an item, add("name", new Block(...), new Item.Properties())
    public static <T extends Block> RegistryObject<T> add(String name, Supplier<T> block, Item.Properties properties) {
        return add(name, block, properties, BlockItem::new);
    }

    //Register a block with a custom item, add("name", new Block(...), new Item.Properties(), Item::new)
    public static <T extends Block> RegistryObject<T> add(String name, Supplier<T> block, Item.Properties properties, BiFunction<Block, Item.Properties, BlockItem> itemConstructor) {
        final RegistryObject<T> registryObject = REGISTRY.register(name, block);
        if (itemConstructor != null && properties != null) WonderItems.REGISTRY.register(name, () -> itemConstructor.apply(registryObject.get(), properties));
        return registryObject;
    }
}

