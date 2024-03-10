package com.neptuneclient.voidui.testmod.mixins;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "run", at = @At("HEAD"))
    public void mixin$run(CallbackInfo ci) {
        System.out.println("Hello from Fabric (mx)!");
    }
}
