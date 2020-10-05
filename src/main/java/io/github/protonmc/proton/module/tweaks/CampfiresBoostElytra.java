package io.github.protonmc.proton.module.tweaks;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.module.ProtonModule;
import io.github.protonmc.tiny_config.Configurable;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class CampfiresBoostElytra extends ProtonModule {
	@Configurable
	public static double boostStrength = 0.5;
	@Configurable
	public static double maxSpeed = 1;

	public CampfiresBoostElytra() {
		super(Proton.identifier("campfires_boost_elytra"));
	}

	@Override
	public void clientInit() {
		ClientTickEvents.END_WORLD_TICK.register((t) -> {
			if (!enabled) return;
			for (AbstractClientPlayerEntity ply : t.getPlayers()) {
				if (ply.isFallFlying()) {
					Vec3d vel = ply.getVelocity();

					if (vel.y < maxSpeed) {
						BlockPos pos = ply.getBlockPos();
						BlockState state = null;
						int distance = -1;
						while (++distance < 20) {
							state = t.getBlockState(pos);
							if (!state.isAir() || pos.getY() <= 0) break;
							pos = pos.down();
						}

						if (state != null &&
								state.getBlock() == Blocks.CAMPFIRE &&
								state.get(CampfireBlock.LIT) &&
								state.get(CampfireBlock.SIGNAL_FIRE)) {
							double force = boostStrength;
							if (distance > 16)
								force -= force * (1.0 - ((distance - 16.0) / 4.0));
							ply.addVelocity(0, force, 0);
						}
					}
				}
			}
		});
	}
}
