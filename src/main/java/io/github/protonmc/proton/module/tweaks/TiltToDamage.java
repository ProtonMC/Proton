package io.github.protonmc.proton.module.tweaks;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.module.ProtonModule;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class TiltToDamage extends ProtonModule {
    public static final Identifier TILT_TO_DAMAGE_PACKET_ID = Proton.identifier("tilt_to_damage_packet");

    public TiltToDamage() {
        super(Proton.identifier("tilt_to_damage"));
    }

    public static void takeKnockback(PlayerEntity player, float f, double d, double e) {
        if (!player.world.isClient) {
            PacketByteBuf data = PacketByteBufs.create();
            data.writeFloat(player.knockbackVelocity);

            ServerPlayNetworking.send((ServerPlayerEntity) player, TILT_TO_DAMAGE_PACKET_ID, data);
        }
    }

    @Override
    public void clientInit() {
        ClientPlayNetworking.registerGlobalReceiver(TILT_TO_DAMAGE_PACKET_ID, TiltToDamage::handlePacket);
    }

    private static void handlePacket(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf data, PacketSender sender) {
        float newKnockbackVelocity = data.readFloat();
        client.execute(() -> {
            client.player.knockbackVelocity = newKnockbackVelocity;
        });
    }
}
