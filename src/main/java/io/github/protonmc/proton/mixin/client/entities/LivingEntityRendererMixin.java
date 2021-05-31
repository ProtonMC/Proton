package io.github.protonmc.proton.mixin.client.entities;

import io.github.protonmc.proton.base.annotation.FromModule;
import io.github.protonmc.proton.base.config.ProtonConfig;
import io.github.protonmc.proton.module.client.AngryCreepersModule;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

/**
 * Mixin essential for the VariantAnimalTexturesModule.
 *
 * @author joaoh1
 */
@Environment(EnvType.CLIENT)
@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> implements
                                                                                                                            FeatureRendererContext<T, M> {
    @Unique
    @FromModule(AngryCreepersModule.class)
    private float creeperColor = 1.0F;

    public LivingEntityRendererMixin(EntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    /**
     * Makes creepers go red when explode pt. 1
     *
     * @see LivingEntityRenderer#render(LivingEntity, float, float, MatrixStack, VertexConsumerProvider, int)
     */
    @Inject(method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At("HEAD"))
    @FromModule(AngryCreepersModule.class)
    public void getCreeperColor(
            T livingEntity,
            float f,
            float g,
            MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider,
            int i,
            CallbackInfo ci
                               ) {
        if (ProtonConfig.Client.angryCreepers) {
            if (livingEntity instanceof CreeperEntity) {
                creeperColor = 1.0F - (((CreeperEntity) livingEntity).getClientFuseTime(g) / 1.0714285F);
            } else {
                creeperColor = 1.0F;
            }
        } else {
            creeperColor = 1.0F;
        }
    }

    /**
     * Makes creeper go red when explode pt. 2
     *
     * @see LivingEntityRenderer#render(LivingEntity, float, float, MatrixStack, VertexConsumerProvider, int)
     */
    @FromModule(AngryCreepersModule.class)
    @ModifyArgs(method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
                at = @At(value = "INVOKE",
                         target = "Lnet/minecraft/client/render/entity/model/EntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;IIFFFF)V"))
    private void modifyCreeperColor(Args args) {
        args.set(5, creeperColor);
        args.set(6, creeperColor);
    }
}
