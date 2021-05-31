package io.github.protonmc.proton.mixin.client.entities;

import io.github.protonmc.proton.base.annotation.FromModule;
import io.github.protonmc.proton.base.config.ProtonConfig;
import io.github.protonmc.proton.module.client.VariantAnimalTexturesModule;
import net.minecraft.client.render.entity.RabbitEntityRenderer;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin essential for the VariantAnimalTexturesModule.
 *
 * @author hYdos
 * @author kara-b
 */
@Mixin(RabbitEntityRenderer.class)
public class RabbitRendererMixin {
    @Shadow
    @Final
    private static Identifier TOAST_TEXTURE;

    @Shadow
    @Final
    private static Identifier BROWN_TEXTURE;

    @Shadow
    @Final
    private static Identifier WHITE_TEXTURE;

    @Shadow
    @Final
    private static Identifier BLACK_TEXTURE;

    @Shadow
    @Final
    private static Identifier WHITE_SPOTTED_TEXTURE;

    @Shadow
    @Final
    private static Identifier GOLD_TEXTURE;

    @Shadow
    @Final
    private static Identifier SALT_TEXTURE;

    @Shadow
    @Final
    private static Identifier CAERBANNOG_TEXTURE;

    /**
     * Variated rabbit texturess.
     *
     * @see RabbitEntityRenderer#getTexture(RabbitEntity)
     */
    @Inject(method = "getTexture(Lnet/minecraft/entity/passive/RabbitEntity;)Lnet/minecraft/util/Identifier;", at = @At("HEAD"), cancellable = true)
    @FromModule(VariantAnimalTexturesModule.class)
    public void getTypeTexture(RabbitEntity rabbitEntity, CallbackInfoReturnable<Identifier> cir) {
        if (ProtonConfig.Client.VariantAnimalTextures.enableShinyRabbit) {
            cir.setReturnValue(VariantAnimalTexturesModule.getTextureOrShiny(rabbitEntity,
                                                                             VariantAnimalTexturesModule.VariantTextureType.RABBIT,
                                                                             () -> getOldTexture(rabbitEntity)
                                                                            ));
        }
    }

    /**
     * Gets the original texture of a RabbitEntity.
     *
     * @param rabbitEntity The rabbit to get the texture from.
     *
     * @return Original texture in Identifier form.
     */
    @FromModule(VariantAnimalTexturesModule.class)
    @Unique
    public Identifier getOldTexture(RabbitEntity rabbitEntity) {
        String string = Formatting.strip(rabbitEntity.getName().getString());
        if (string != null && "Toast".equals(string)) {
            return TOAST_TEXTURE;
        } else {
            switch (rabbitEntity.getRabbitType()) {
                case 0:
                default:
                    return BROWN_TEXTURE;
                case 1:
                    return WHITE_TEXTURE;
                case 2:
                    return BLACK_TEXTURE;
                case 3:
                    return WHITE_SPOTTED_TEXTURE;
                case 4:
                    return GOLD_TEXTURE;
                case 5:
                    return SALT_TEXTURE;
                case 99:
                    return CAERBANNOG_TEXTURE;
            }
        }
    }
}
