package io.github.protonmc.proton.module.tweaks;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.module.ProtonModule;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.PacketContext;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class TiltToDamage extends ProtonModule {
    public static final Identifier TILT_TO_DAMAGE_PACKET_ID = Proton.identifier("tilt_to_damage_packet");

    public TiltToDamage() {
        super(Proton.identifier("tilt_to_damage"));
    }

    @Override
    public void clientInit() {
        ClientSidePacketRegistry.INSTANCE.register(TILT_TO_DAMAGE_PACKET_ID, TiltToDamage::handlePacket);
    }

    public static void handlePacket(PacketContext context, PacketByteBuf buf) {
        context.getTaskQueue().execute(() -> {
            context.getPlayer().knockbackVelocity = buf.readFloat();
        });
    }

    public static void takeKnockback(PlayerEntity playerEntity, float f, double d, double e) {
        if (!playerEntity.world.isClient) {
            PacketByteBuf packetByteBuf = new PacketByteBuf(Unpooled.buffer());
            packetByteBuf.writeFloat(playerEntity.knockbackVelocity);

            ServerSidePacketRegistry.INSTANCE.sendToPlayer(playerEntity, TILT_TO_DAMAGE_PACKET_ID, packetByteBuf);
        }
    }
}
