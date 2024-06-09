package ch.skyfy.customnetherratio.mixin;

import ch.skyfy.customnetherratio.CustomNetherRatioMod;
import ch.skyfy.customnetherratio.config.Configs;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DimensionType.class)
public class DimensionTypeMixin {


//    @ModifyVariable(method = "<init>", at = @At("TAIL"), argsOnly = true)
//    private DimensionType init(DimensionType value, OptionalLong fixedTime, boolean hasSkylight, boolean bl, boolean ultrawarm, boolean bl2, double coordinateScale, boolean bl3, boolean piglinSafe, int i, int j, int k, TagKey<Block> tagKey, Identifier identifier, float f, DimensionType.MonsterSettings monsterSettings) {
//        System.out.println("effects.toString() " + identifier.toString());
//        var instance = (DimensionType) (Object) this;
//
//        if (identifier.toString().equalsIgnoreCase("minecraft:the_nether")) {
//            System.out.println("MODIFIED TO 32");
//            return new DimensionType(
//                    instance.fixedTime(),
//                    instance.hasSkyLight(),
//                    instance.hasCeiling(),
//                    instance.ultrawarm(),
//                    instance.natural(),
//                    32.0,
//                    instance.bedWorks(),
//                    instance.respawnAnchorWorks(),
//                    instance.minY(),
//                    instance.height(),
//                    instance.logicalHeight(),
//                    instance.infiniburn(),
//                    instance.effects(),
//                    instance.ambientLight(),
//                    instance.monsterSettings()
//            );
//        }
//
////        return 32;
//
//        return instance;
//    }

//     Mixin class DimensionType.class
//    @ModifyVariable(method = "<init>", at = @At("HEAD"), argsOnly = true, print = true, index = 6)
//    private static double init2(double value, @Local(argsOnly = true, print = true) OptionalLong fixedTime, @Local(argsOnly = true) double  arg6 ) {
//        System.out.println("fixedTime " + fixedTime);
//        // Only change travel scale factor if it's the_nether, don't change for overworld or the_end, or custom_dim...
//        // How to access parameter "identifier" ?
////        if (identifier.toString().equalsIgnoreCase("minecraft:the_nether")) {
////            value = 16;
////        }
//
//        return value;
//    }


    @Inject(method = "coordinateScale", at = @At("RETURN"), cancellable = true)
    private void coordinateScale(CallbackInfoReturnable<Double> cir){
        var instance = (DimensionType) (Object) this;
        if(instance.effects().toString().equalsIgnoreCase("minecraft:the_nether"))
            cir.setReturnValue(Configs.MOD_CONFIG.data.netherRatio);
    }


    @Inject(method = "getCoordinateScaleFactor", at = @At("TAIL"))
    private static void getCoordinateScaleFactor(DimensionType fromDimension, DimensionType toDimension, CallbackInfoReturnable<Double> cir) {

        System.out.println("\n\n");

        CustomNetherRatioMod.Companion.getLOGGER().info(fromDimension.toString());
        CustomNetherRatioMod.Companion.getLOGGER().info(fromDimension.coordinateScale());
//
        System.out.println("----");
//
//
        CustomNetherRatioMod.Companion.getLOGGER().info(toDimension.toString());
        CustomNetherRatioMod.Companion.getLOGGER().info(toDimension.coordinateScale());
//
//        System.out.println(fromDimension.effects().toTranslationKey());
//        System.out.println(fromDimension.effects().getNamespace());
//        System.out.println(fromDimension.effects().getPath());
//        System.out.println(fromDimension.effects().toShortTranslationKey());
//        System.out.println(fromDimension.effects().toString());
//
//        System.out.println("----");
//
//        System.out.println(toDimension.effects().toTranslationKey());
//        System.out.println(toDimension.effects().getNamespace());
//        System.out.println(toDimension.effects().getPath());
//        System.out.println(toDimension.effects().toShortTranslationKey());
//        System.out.println(toDimension.effects().toString());

        if (fromDimension.effects().toTranslationKey().equalsIgnoreCase(DimensionTypes.OVERWORLD.getValue().toTranslationKey())) {

        }
    }

}
