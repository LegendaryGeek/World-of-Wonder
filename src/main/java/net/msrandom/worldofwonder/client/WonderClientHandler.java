package net.msrandom.worldofwonder.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.thread.EffectiveSide;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.msrandom.worldofwonder.WorldOfWonder;
import net.msrandom.worldofwonder.block.WonderBlocks;
import net.msrandom.worldofwonder.client.gui.screen.EditStemSignScreen;
import net.msrandom.worldofwonder.client.renderer.entity.DandeLionRenderer;
import net.msrandom.worldofwonder.client.renderer.entity.DandeLionSeedRenderer;
import net.msrandom.worldofwonder.client.renderer.entity.StemBoatRenderer;
import net.msrandom.worldofwonder.client.renderer.tileentity.StemChestTileEntityRenderer;
import net.msrandom.worldofwonder.client.renderer.tileentity.StemSignTileEntityRenderer;
import net.msrandom.worldofwonder.compat.WonderQuarkCompat;
import net.msrandom.worldofwonder.entity.WonderEntities;
import net.msrandom.worldofwonder.tileentity.StemSignTileEntity;
import net.msrandom.worldofwonder.tileentity.WonderTileEntities;

import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Supplier;

public class WonderClientHandler {
    public static void init(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(WonderEntities.STEM_BOAT.get(), StemBoatRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(WonderEntities.DANDE_LION.get(), DandeLionRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(WonderEntities.DANDE_LION_SEED.get(), DandeLionSeedRenderer::new);
        ClientRegistry.bindTileEntityRenderer(WonderTileEntities.STEM_SIGN.get(), StemSignTileEntityRenderer::new);

        RenderTypeLookup.setRenderLayer(WonderBlocks.STEM_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(WonderBlocks.STEM_TRAPDOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(WonderBlocks.DANDE_LION_SPROUT.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(WonderBlocks.POTTED_DANDE_LION_SPROUT.get(), RenderType.cutout());

        if (WorldOfWonder.quarkLoaded) {
            Function<TileEntityRendererDispatcher, StemChestTileEntityRenderer> chestRenderer = StemChestTileEntityRenderer::new;
            ClientRegistry.bindTileEntityRenderer(WonderQuarkCompat.STEM_CHEST_ENTITY.get(), chestRenderer);
            ClientRegistry.bindTileEntityRenderer(WonderQuarkCompat.STEM_TRAPPED_CHEST_ENTITY.get(), chestRenderer);
            RenderTypeLookup.setRenderLayer(WonderQuarkCompat.STEM_LADDER.get(), RenderType.cutout());
        }
    }

    public static Item.Properties getWithRenderer(Item.Properties properties, Supplier<Callable<Object>> ister) {
        return EffectiveSide.get().isClient() ? properties.setISTER(cast(ister)) : properties;
    }

    @SuppressWarnings("unchecked")
    private static <F, T> T cast(F toCast) {
        return (T) toCast;
    }

    @OnlyIn(Dist.CLIENT)
    public static void openSignEditScreen(PlayerEntity player, BlockPos pos) {
        Minecraft.getInstance().setScreen(new EditStemSignScreen((StemSignTileEntity) player.level.getBlockEntity(pos)));
    }
}
