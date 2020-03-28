package com.kwpugh.powder_power.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class PlayerSpecialAbilities
{	
	//Set player health to max on tick update
	public static void giveHealthEffect(World world, PlayerEntity player, ItemStack itemstack)
	{
    	player.setHealth(20);
		return;
	}
	
	//Increases the player's food level to max on tick update, based on inputs
	public static void giveRegenffect(World world, PlayerEntity player, ItemStack itemstack, int newfoodlevel, float newsatlevel)
	{
		player.getFoodStats().addStats(newfoodlevel, newsatlevel);
    	return;
	}
		
	//Set player saturation level to max on tick update
	public static void giveSaturationEffect(World world, PlayerEntity player, ItemStack itemstack)
	{
		player.getFoodStats().setFoodSaturationLevel(7.0F);
    	return;
	}
		
	//Slower increase of yellow hearts on tick update
	public static void giveExtraHearts(World world, LivingEntity player, ItemStack itemstack)
	{
		float current = player.getAbsorptionAmount();
		
		if(player.getHealth() < 20 || current >= 10)
		{
			return;
		}
		
		if(current >= 9)
		{
			if (player.ticksExisted % 180 == 0)
			{
				player.setAbsorptionAmount(10);
			} 
			return;
		}
		if(current < 9)
		{
			if (player.ticksExisted % 180 == 0)
			{
				player.setAbsorptionAmount(current + 0.33F);
			} 
			return;
		}
		
    	return;
	}

	//Faster increase of yellow hearts on tick update
	public static void giveGreaterExtraHearts(World world, LivingEntity player, ItemStack itemstack)
	{
		float current = player.getAbsorptionAmount();
		
		if(player.getHealth() < 20 || current >= 20)
		{
			return;
		}
		
		if(current >= 19)
		{
			if (player.ticksExisted % 180 == 0)
			{
				player.setAbsorptionAmount(20);
			} 
			return;
		}
		if(current < 19)
		{
			if (player.ticksExisted % 180 == 0)
			{
				player.setAbsorptionAmount(current + 1.0F);
			} 
			return;
		}
		
    	return;
	}
	
	//Set player yellow hearts to none on tick update
	public static void giveNoExtraHearts(World world, PlayerEntity player, ItemStack itemstack)
	{
		player.setAbsorptionAmount(0);
    	return;
	}
	
	//	//Set player oxygen to max on tick update
	public static void giveBreathing(World world, PlayerEntity player, ItemStack itemstack)
	{
		player.setAir(300);
    	return;
	}

	//Gives player Conduit Effect
	public static void giveDolphinEffect(World world, PlayerEntity player, ItemStack itemstack)
	{
		if (player.ticksExisted % 180 == 0 && player.isInWater())
		{
			player.addPotionEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 260, 0, false, false));
		} 
	}
	
	//Gives player Conduit Effect
	public static void giveConduitEffect(World world, PlayerEntity player, ItemStack itemstack)
	{
		if (player.ticksExisted % 180 == 0 && player.isInWater())
		{
			player.addPotionEffect(new EffectInstance(Effects.CONDUIT_POWER, 260, 2, false, false));
		} 
	}
}
