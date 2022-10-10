package com.jocosero.odd_water_mobs.event;


import com.jocosero.odd_water_mobs.OddWaterMobs;
import com.jocosero.odd_water_mobs.block.ModBlocks;
import com.jocosero.odd_water_mobs.entity.ModEntityTypes;
import com.jocosero.odd_water_mobs.entity.client.AnglerfishRenderer;
import com.jocosero.odd_water_mobs.entity.client.CoelacanthRenderer;
import com.jocosero.odd_water_mobs.entity.client.HorseshoeCrabRenderer;
import com.jocosero.odd_water_mobs.entity.client.IsopodRenderer;
import com.jocosero.odd_water_mobs.particle.ModParticles;
import com.jocosero.odd_water_mobs.particle.custom.HydrothermalSmoke;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = OddWaterMobs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventClientBusEvents {

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BIOLUMINESCENT_SCALES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GIANT_TUBE_WORM.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GLOWING_ANEMONE.get(), RenderType.cutout());

        EntityRenderers.register(ModEntityTypes.ANGLERFISH.get(), AnglerfishRenderer::new);
        EntityRenderers.register(ModEntityTypes.ISOPOD.get(), IsopodRenderer::new);
        EntityRenderers.register(ModEntityTypes.HORSESHOE_CRAB.get(), HorseshoeCrabRenderer::new);
        EntityRenderers.register(ModEntityTypes.COELACANTH.get(), CoelacanthRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticleFactories(final ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.HYDROTHERMAL_SMOKE.get(),
                HydrothermalSmoke.Provider::new);
    }
}