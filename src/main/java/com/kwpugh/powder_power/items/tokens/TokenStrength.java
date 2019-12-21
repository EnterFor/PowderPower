package com.kwpugh.powder_power.items.tokens;

import java.util.List;

import com.kwpugh.powder_power.util.EnableUtil;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class TokenStrength extends Item
{

	public TokenStrength(Properties properties)
	{
		super(properties);
	}

	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{		
		if(entity instanceof PlayerEntity && !world.isRemote && EnableUtil.isEnabled(stack))
		{
			PlayerEntity player = (PlayerEntity)entity;
	
			player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 260,2, false, false));
		}
	}	

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		
		if(!world.isRemote && player.isCrouching())
		{
			EnableUtil.changeEnabled(player, hand);
			player.sendMessage(new StringTextComponent("Strength ability active: " + EnableUtil.isEnabled(stack)));
			return new ActionResult<ItemStack>(ActionResultType.SUCCESS, player.getHeldItem(hand));
		}
		return super.onItemRightClick(world, player, hand);
	}

  @Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag)
	{
		super.addInformation(stack, world, list, flag);				
		list.add(new StringTextComponent(TextFormatting.BLUE + "Provides player with extra strength"));
		list.add(new StringTextComponent(TextFormatting.RED + "Strength ability active: " + EnableUtil.isEnabled(stack)));
		list.add(new StringTextComponent(TextFormatting.GREEN + "Sneak right-click to toggle on/off"));
	} 
}
