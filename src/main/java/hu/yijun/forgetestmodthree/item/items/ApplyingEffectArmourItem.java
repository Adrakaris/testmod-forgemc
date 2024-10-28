package hu.yijun.forgetestmodthree.item.items;

import com.google.common.collect.ImmutableMap;
import hu.yijun.forgetestmodthree.item.ModArmourMaterials;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Map;

/**
 * For applying effects with a full armour set. This method will check for a full set, hence only one item
 * in an armour set needs to be this type of item.
 */
public class ApplyingEffectArmourItem extends ArmorItem {

    private static final Map<ArmorMaterial, MobEffectInstance> FULL_ARMOUR_EFFECTS = new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>()
            .put(ModArmourMaterials.SAPPHIRE, new MobEffectInstance(MobEffects.NIGHT_VISION, 200, 1, false, false))
            .build();

    public ApplyingEffectArmourItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        // onArmorTick is deprecated and slated for removal
        // onInventoryTick is called once per inventory slot per tick, except this should only be called when
        // relevant armour items are in the inventory
        if (level.isClientSide()) {
            return;
        }

        if (hasArmourInAllSlots(player)) {
            calculateArmourEffects(player);
        }
    }

    private boolean hasArmourInAllSlots(Player player) {
        ItemStack helmet = player.getInventory().getArmor(3);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack boots = player.getInventory().getArmor(0);

        return !helmet.isEmpty() && !chestplate.isEmpty() && !leggings.isEmpty() && !boots.isEmpty();
    }

    private void calculateArmourEffects(Player player) {
        FULL_ARMOUR_EFFECTS.forEach((armourMaterial, materialMobEffect) -> {
            if (hasCorrectArmourMaterial(armourMaterial, player)) {
                applyStatusEffectForMaterial(player, materialMobEffect);
            }
        });
    }

    private boolean hasCorrectArmourMaterial(ArmorMaterial armourMaterial, Player player) {
        for (ItemStack itemStack : player.getInventory().armor) {
            if (!(itemStack.getItem() instanceof ArmorItem)) {  // e.g. elytra is not an armour item
                return false;
            }
            if (((ArmorItem) itemStack.getItem()).getMaterial() != armourMaterial) {
                return false;
            }
        }

        return true;
    }

    private void applyStatusEffectForMaterial(Player player, MobEffectInstance mobEffect) {
        //noinspection DataFlowIssue
        if (!player.hasEffect(mobEffect.getEffect())
                || player.getEffect(mobEffect.getEffect()).endsWithin(60)) {
            player.addEffect(new MobEffectInstance(mobEffect));  // must be new (since map is immutable)
        }
    }
}
